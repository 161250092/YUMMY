//设置全局表单提交格式
Vue.http.options.emulateJSON = true;



var vm = new Vue({
    el:"#app",
    data() {
        return {
            activeIndex: '3', //默认激活

            search:{
                startTime:'',
                endTime:'',
                lowPrice:0.0,
                highPrice:1000000,
                lowLevel:0,
                highLevel:8
            },

            orders:[{
                orderId:'',
                account:'',
                idCode:'',
                userLocation:{
                    lat:0.0,
                    lng:0.0,
                    address:'address'
                },

                orderAcceptedTime:'',
                expectedArriveTime:'',

                dishes:[{
                    dishId:'',
                    type:'',
                    name:'',
                    price:'',
                    quantity:'',
                    description:''
                }],

                totalPrice:0,
                orderState:{
                    isPayed:false,
                    isReceived:false,
                    isAbolished:false
                }
            }],

            showOrderDetails:false,

            showSearchDialog:false,

            orderDetails:''


        }

    },


    methods: {

        getMerchantOrders(){
            this.$http.post('/merchantOrders/getOrders',{
                idCode:sessionStorage.getItem("account"),
            }).then(result => {
                console.log(result);
                this.orders = result.body;
            });
        },

        openSearchDialog(){
          this.showSearchDialog = true;
        },


        check(){
            this.showSearchDialog = false;
            this.$http.post('/merchantOrders/checkMerchantOrders',JSON.stringify(this.search)).then(result => {
                console.log(result);
                this.orders = result.body;
            });
        },

        checkOrderDetails(order){
            this.orderDetails = '';
            console.log(order);
            this.showOrderDetails = true;
            this.orderDetails += "菜品:\n";
            for(var i=0;i<order.dishes.length;i++){
                this.orderDetails += order.dishes[i].name+" "+order.dishes[i].quantity+" "+order.dishes[i].price+"\n";
            }

            if(order.orderState.received)
            this.orderDetails +="送达时间:"+order.orderAcceptedTime;

        }


    },

    created(){
            this.getMerchantOrders();
    }

});