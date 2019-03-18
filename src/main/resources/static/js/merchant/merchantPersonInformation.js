//设置全局表单提交格式
Vue.http.options.emulateJSON = true;



var vm = new Vue({

    el:"#app",
    data() {
        return {
            //商店信息
            details:{
                idCode:'',
                bankAccount:'0000',
                restaurantName:'',
                phone:'',
                location:{
                    lat:0.0,
                    lng:0.0,
                    address:'address'
                },
                restaurantType:'',
                minDeliveryCost:0,
                deliveryCost:0
            },

            discounts:[{
                discountId:'',
                totalPrice:0.0,
                reducePrice:0.0
            }],


            discountEditor:{
              totalPrice: 0.0,
              reducePrice:0.0,
            },

            loading: {},

            activeIndex: '2', //默认激活


            showInformation:false,

            showEditor:false,

            showDiscountEditor:false
        }

    },


    methods: {
        qq_position() {
             var geolocation = new qq.maps.Geolocation("OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77", "myapp");
            if (geolocation) {
                   var options = {timeout: 8000};
                  geolocation.getLocation(this.showPosition, this.showErr, options);
            } else {
                    alert("定位尚未加载");}
            },

            showPosition(position) {
            this.details.location.lat = position.lat;
            this.details.location.lng = position.lng;
            // console.log(position);
            // console.log(position.lat);
            // console.log(position.lng);
            },

            showErr(err) {
            //所有可能的错误
            console.log(err);
            },


            address() {
            var _this = this;
             let geocoder = new qq.maps.Geocoder({
                  complete: function (result) {
                      alert(result.detail.address)
                      _this.details.location.address = result.detail.address;
                }
            });
             this.qq_position();
             let coord = new qq.maps.LatLng(this.details.location.lat, this.details.location.lng);
             geocoder.getAddress(coord);
        },

        getLocation(){
            sessionStorage.setItem("address",this.details.location.address);
            let _this = this;
            let geocoder = new qq.maps.Geocoder({
                complete: function (result) {
                    _this.details.location.lat = result.detail.location.lat;
                    _this.details.location.lng = result.detail.location.lng;
                    console.log(result);
                },

            });
            geocoder.getLocation(this.details.location.address);
        },

        checkAddressInMap(){
            sessionStorage.setItem("address",this.details.location.address);

            if(this.details.location.address!=='') {
                // geocoder.getLocation(this.location.address);
                window.open('/mapLocation');
            }
            else
                alert("请输入地址信息");
        },

        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);
        },

        //更新
        update(){

            this.loadings();

            let _this = this;
            if(this.details.location.address===''){
                alert("请输入地址");
                return;
            }

            this.getLocation();

            setTimeout(function () {
                _this.updateInformation();
            }, 2*1000);


        },

        getPersonInformation(){
            var _this = this;
            this.details.idCode = sessionStorage.getItem("account");
            this.$http.post('/merchantInformation/getMerchantInformation',{
                idCode:this.details.idCode
            }).then(result => {
                console.log(result);

                _this.details.bankAccount = result.body.bankAccount;
                _this.details.restaurantName= result.body.restaurantName;
                _this.details.phone=result.body.phone;
                _this.details.location=result.body.location;
                _this.details.restaurantType=result.body.restaurantType;
                _this.details.minDeliveryCost=result.body.minDeliveryCost;
                _this.details.deliveryCost = result.body.deliveryCost;
                _this.discounts = result.body.discounts;
                //console.log(this.details);
                this.loading.close(); //数据更新成功就手动关闭动画
            });
        },


        updateInformation(){
            this.$http.post('/merchantInformation/updateMerchantInformation', JSON.stringify(this.details)
            ).then(result => {
                if (result.body.success) {
                    //更新成功
                    this.$message({
                        type: 'success',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新信息
                    this.reloadInformation();
                    this.showEditor = false;
                    // alert("success");
                } else {
                    //更新失败
                    this.$message({
                        type: 'warning',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadInformation();
                }
            })
        },


        reloadInformation() {
           this.getPersonInformation()
        },


        //打开对话框
        Editor(){
            this.showEditor = true;
        },

        Information(){
            this.showInformation = true;
        },

        openDiscountEditor(){
            this.showDiscountEditor = true;
        },


        //增加新的优惠措施
        addNewDiscount(){
            this.showDiscountEditor = false;
            console.log(this.discountEditor);
            this.$http.post('/merchantInformation/addNewDiscount', {
                totalPrice:this.discountEditor.totalPrice,
                reducePrice:this.discountEditor.reducePrice
                }
            ).then(result => {
                if (result.body.success) {
                    //更新成功
                    this.$message({
                        type: 'success',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新信息
                    this.reloadInformation();
                } else {
                    //更新失败
                    this.$message({
                        type: 'warning',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadInformation();
                }
            })
        },


        //删除优惠措施
        deleteDiscount(id){
            this.$http.post('/merchantInformation/deleteDiscount', {
                discountId:id}
            ).then(result => {
                if (result.body.success) {
                    //更新成功
                    this.$message({
                        type: 'success',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新信息
                    this.reloadInformation();
                } else {
                    //更新失败
                    this.$message({
                        type: 'warning',
                        message: result.body.message,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadInformation();
                }
            })
        }

    },

    created(){
        // this.findAll();
        this.getPersonInformation();
        this.loadings(); //加载动画
    }

});