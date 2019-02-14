//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

var vm = new Vue({

    el:"#app",
    data() {
        return {

            userLocations:[{
                locationId:0,
                lat:0.0,
                lng:0.0,
                address:''
            }],

            //餐品
            dishes: [{
                dishId:'',
                startTime:'',
                endTime:'',
                type:'',
                name:'',
                price:'',
                //库存
                quantity:'',
                description:'',
                //选择的数量
                selectQuantity:1,
                canCancel:false,
                canAdd:false,
            }],

            //购物车
            cart:{
                account:'',
                idCode:'',
                dishes: [],
                userLocation:{
                    locationId:0,
                    lat:0.0,
                    lng:0.0,
                    address:''
                },
            },

            cartDetails:'',

            showOrderForm:false,


            activeIndex: '0', //默认激活






        }

    },


    methods: {

        selectNum(dish){
            if(dish.selectQuantity<=0)
                dish.canAdd = false;

            if(dish.selectQuantity>=1&&dish.canCancel===false)
                dish.canAdd = true;

            if(dish.selectQuantity>dish.quantity) {
                console.log(dish.selectQuantity+" "+dish.quantity)
                dish.canAdd = false;
            }
        },

        addToCart(dish) {
            // alert("添加成功");
            dish.canAdd = false;
            dish.canCancel = true;
            //console.log(dish);
            this.cart.dishes.push(dish);
            console.log(this.cart.dishes);
        },

        removeFromCart(dish){
            // console.log(this.cart.dishes);
            // console.log(this.cart.dishes.length)
            let index = -1;
            for(let i=0;i<this.cart.dishes.length;i++)
                if(this.cart.dishes[i].dishId === dish.dishId) {
                    index = i;
                }
            //console.log(index);
            dish.canAdd = true;
            dish.canCancel = false;
            this.cart.dishes.splice(index,1);
            //console.log(this.cart.dishes);
            // alert("删除成功");
        },


        showOrder(){

            this.cart.account = sessionStorage.getItem("account");
            this.cart.idCode = sessionStorage.getItem("idCode");
            this.showOrderForm = true;

            this.cartDetails = '';
            var total = 0;
            for(var i=0;i<this.cart.dishes.length;i++){
                total += this.cart.dishes[i].selectQuantity*this.cart.dishes[i].price;
                this.cartDetails += this.cart.dishes[i].name+" 数量:"+this.cart.dishes[i].selectQuantity+" 单价"+this.cart.dishes[i].price+"\n";
            }
            this.cartDetails +="\n总价:"+total;
        },

        //菜品信息
        getMerchantAllDishes() {
            this.$http.post('/member/getMerchantAllDishes',{
                idCode:sessionStorage.getItem("idCode"),
            }).then(result => {
                console.log(result);
                this.dishes = result.body;
            });
        },


        getMemberLocations(){
            this.$http.post('/member/getMemberLocations',{
                account:sessionStorage.getItem("account"),
            }).then(result => {
                console.log(result);
                this.userLocations = result.body;
            });
        },

        submitOrder() {
            console.log(this.cart);

            this.$http.post('/member/submitOrder', JSON.stringify(this.cart)
            ).then(result => {
                console.log(result);


                // if (result.body.success) {
                //     //支付成功
                //     this.$message({
                //         type: 'success',
                //         message: result.body.message,
                //         duration: 6000
                //     });
                //     this.showPayForm = false;
                //     this.getMemberOrders();
                // }else {
                //     //失败
                //     this.$emit(
                //         this.$message({
                //             type: 'warning',
                //             message: result.body.message,
                //             duration: 6000
                //         }),
                //
                //     );
                //     this.showPayForm = false;
                //     this.getMemberOrders();
                // }


            });

            this.showOrderForm = false;

        },


        toPay(){

            this.submitOrder();
            this.showOrderForm = false;

            window.location.href = "/memberOrders";
        },




    },

    created(){
        this.getMerchantAllDishes();
        this.getMemberLocations();
    }


});