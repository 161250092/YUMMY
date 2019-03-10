
Vue.http.options.emulateJSON = true;

// Vue实例
let vm = new Vue({
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
            selectOrder:{},

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

            showSearchDialog:false,

            deliveryRightNow:true,


            form:{
                account:'',
                password:'',
                idCode:'',
                orderId:0,
                deliveryRightNow:false,
                dateTime:''
            }

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
            this.showSearchDialog = false;
            this.$http.post('/member/searchMemberOrders',JSON.stringify(this.search)).then(result => {
                console.log(result);
                this.orders = result.body;
            });
        },


        openPayForm(order){
            this.totalPriceInBill = order.totalPrice;
            this.showPayForm = true;

            this.form.idCode = order.idCode;
            this.form.orderId = order.orderId;

        },

        pay(){

            console.log(this.form);
            this.$http.post('/bankAccount/payOut',JSON.stringify(this.form)
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
            this.selectOrder = order.dishes;
            console.log(order);
            this.showOrderDetails = true;

            this.orderDetails +="总价: "+order.totalPrice+" 元";

            if(order.orderState.payed)
                this.orderDetails +=" 预计送达时间:"+order.expectedArriveTime.split("T")[0]+" "+order.expectedArriveTime.split("T")[1];
            if(order.orderState.received)
                this.orderDetails +="   送达时间:"+order.orderAcceptedTime.split("T")[0]+" "+order.orderAcceptedTime.split("T")[1];
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
