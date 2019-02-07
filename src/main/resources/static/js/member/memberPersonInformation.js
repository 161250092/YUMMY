//设置全局表单提交格式
Vue.http.options.emulateJSON = true;



var vm = new Vue({

    el:"#app",
    data() {
        return {
            //用户信息
            details:{
                account:'',
                nickName:'',
                phone:'',
                locations:[{
                    locationId:0,
                    account:'',
                    lat:0.0,
                    lng:0.0,
                    address:'address'
                }],
                mail:'',
                memberLevel:{
                    level:0
                }
            },

            location:{
                account:'',
                lat:0.0,
                lng:0.0,
                address:''
            },

            // activeIndex: '2', //默认激活


            showInformation:false,

            showEditor:false,

            showLocationEditor:false
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
            this.location.lat = position.lat;
            this.location.lng = position.lng;
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
                    _this.location.address = result.detail.address;
                }
            });
            this.qq_position();
            let coord = new qq.maps.LatLng(this.location.lat, this.location.lng);
            geocoder.getAddress(coord);


        },



        getPersonInformation(){
            var _this = this;
            this.details.account = sessionStorage.getItem("account");
            this.$http.post('/member/getMemberInformation',{
                account:this.details.account
            }).then(result => {
                console.log(result);

                _this.details = result.body;
                // _this.details.restaurantName= result.body.restaurantName;
                // _this.details.phone=result.body.phone;
                // _this.details.location=result.body.location;
                // _this.details.restaurantType=result.body.restaurantType;
                // _this.details.minDeliveryCost=result.body.minDeliveryCost;
                // _this.details.deliveryCost = result.body.deliveryCost;
                // _this.discounts = result.body.discounts;
                console.log(this.details);
            });
        },


        updateInformation(){
            this.$http.post('/member/updateMemberInformation', JSON.stringify(this.details)
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

        openLocationEditor(){
            this.showLocationEditor = true;
        },


        //增加新的地址
        addNewLocation(){
            this.showLocationEditor = false;
            this.location.account = sessionStorage.getItem("account");
            console.log(this.discountEditor);
            this.$http.post('/member/addNewLocation',JSON.stringify(this.location)
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


        //删除地址
        deleteLocation(locationId){
            this.$http.post('/member/deleteLocation', {
                locationId:locationId}
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


        deleteAccount(){
            this.$http.post('/member/deleteAccount', {
                account:sessionStorage.getItem("account")}
            ).then(result => {
                if (result.body.success) {
                    //更新成功
                    this.$message({
                        type: 'success',
                        message: result.body.message,
                        duration: 6000
                    });
                    window.location.href = "/member";
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
    }

});