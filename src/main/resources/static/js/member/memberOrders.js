
Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            bankAccount:{
                account:'',
                password:'',
            },

            orders:[{
                orderId:0,
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


            showPayForm:false,
            activeIndex: '2',


            showOrderDetails:false,

            orderDetails:'',

            totalPriceInBill:0,
            selectOrderId:'',
            selectMerchant:'',

            search:{
                startTime:'',
                endTime:'',
                lowPrice:0.0,
                highPrice:1000000,
                restaurantName:''
            },

            showSearchDialog:false



        };
    },
    methods: {



        getMemberOrders(){
            this.$http.post('/member/getMemberOrders', {
                    account:sessionStorage.getItem("account")
                }
            ).then(result => {
                console.log(result);
                this.orders = result.body;
                console.log(this.orders);
            });
        },

        openSearchDialog(){
            this.showSearchDialog = true;
        },

        check(){
            this.$http.post('/member/searchMemberOrders',JSON.stringify(this.search)).then(result => {
                console.log(result);
                this.orders = result.body;
            });
        },


        payForm(order){
            this.selectMerchant = order.idCode;
            this.selectOrderId = order.orderId;
            this.totalPriceInBill = order.totalPrice;
            this.showPayForm = true;
        },


        pay(){
            console.log(this.bankAccount);
            this.$http.post('/bankAccount/out',{
                orderId:this.selectOrderId,
                idCode:this.selectMerchant,
                account:this.bankAccount.account,
                password:this.bankAccount.password
            }
            ).then(result => {
                if (result.body.success) {
                    //支付成功
                    this.$message({
                        type: 'success',
                        message: result.body.message,
                        duration: 6000
                    });
                    this.showPayForm = false;
                    this.getMemberOrders();
                }else {
                    //失败
                    this.$emit(
                        this.$message({
                            type: 'warning',
                            message: result.body.message,
                            duration: 6000
                        }),

                    );
                    this.showPayForm = false;
                    this.getMemberOrders();
                }
            });


        },


        checkOrderDetails(order){
            this.orderDetails='';
            console.log(order);
            this.showOrderDetails = true;
            this.orderDetails += "菜品:\n";
            for(var i=0;i<order.dishes.length;i++){
                this.orderDetails += order.dishes[i].name+" 数量:"+order.dishes[i].selectQuantity+" 单价:"+order.dishes[i].price+"\n";
            }
            this.orderDetails +="\n总价: "+order.totalPrice+" 元";

            if(order.orderState.received)
                this.orderDetails +=" \n送达时间:"+order.orderAcceptedTime;

        },

        // 撤销订单
        cancelOrder(orderId){
            this.$confirm('确定撤销', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/member/cancelOrder', {
                        orderId:orderId,
                    }
                ).then(result => {
                    if (result.body.success) {
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });

                        this.showPayForm = false;
                        this.getMemberOrders();

                    }
                    else
                        alert("撤销失败");
                });

            });
        },

        //  确认订单送达
        confirmOrder(orderId){
            this.$confirm('确定接收', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/member/confirmOrder', {
                        orderId:orderId,
                    }
                ).then(result => {
                    if (result.body.success) {
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });
                        this.showPayForm = false;
                        this.getMemberOrders();
                    }
                });

            });

        },

        //废弃该订单
        abolishOrder(orderId){
            this.$confirm('确定废弃', '提示', {
                confirmButtonText: '确定(折损将由您承担)',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/member/abolishOrder', {
                        orderId: orderId,
                    }
                ).then(result => {
                    if (result.body.success) {
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 6000
                        });
                        this.showPayForm = false;
                        this.getMemberOrders();
                    }
                });

            });

        }


    },


    created(){
        this.getMemberOrders();
    }

});