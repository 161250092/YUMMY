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

            showLocationEditor:false,

        }

    },


    methods: {
        qq_position() {
            // var geolocation = new qq.maps.Geolocation("OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77", "myapp");

            let geolocation = new qq.maps.Geolocation("RYSBZ-4TDKF-UVYJL-JMNYH-MMMIS-RSB5L", "myapp");
            if (geolocation) {
                let options = {timeout: 4000};
                geolocation.getLocation(this.showPosition, this.showErr, options);
            } else {
                alert("定位尚未加载");}
        },

        showPosition(position) {
            this.location.lat = position.lat;
            this.location.lng = position.lng;
        },

        showErr(err) {
            //所有可能的错误
            console.log(err);
        },


        address() {
            let _this = this;
            let geocoder = new qq.maps.Geocoder({
                complete: function (result) {
                    // alert(result.detail.address)
                    _this.location.address = result.detail.address;
                }
            });
            this.qq_position();
            let coord = new qq.maps.LatLng(this.location.lat, this.location.lng);
            geocoder.getAddress(coord);

        },

        checkAddressInMap(){
            sessionStorage.setItem("address",this.location.address);

            if(this.location.address!=='') {
                // geocoder.getLocation(this.location.address);
                window.open('/mapLocation');
            }
            else
                alert("请输入地址信息");
        },



        getPersonInformation(){
            var _this = this;
            this.details.account = sessionStorage.getItem("account");
            this.$http.post('/member/getMemberInformation',{
                account:this.details.account
            }).then(result => {
                // console.log(result);

                _this.details = result.body;
                // console.log(this.details);
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

                    this.showEditor = false;
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


        getLocation(){
            sessionStorage.setItem("address",this.location.address);
            let _this = this;
            let geocoder = new qq.maps.Geocoder({
                complete: function (result) {
                    _this.location.lat = result.detail.location.lat;
                    _this.location.lng = result.detail.location.lng;
                    console.log(result);
                },

            });
            geocoder.getLocation(this.location.address);
        },


        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 1000);
        },

        //增加新的地址
        addNewLocation(){

            this.loadings();

            let _this = this;
            if(this.location.addres===''){
                alert("请输入地址");
                return;
            }


            this.getLocation();
            this.showLocationEditor = false;



            setTimeout(function () {
                _this.addLocation();
            }, 2*1000);


        },

        addLocation(){


            this.location.account = sessionStorage.getItem("account");

            console.log(this.location);

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


        deleteAccount() {
            this.$confirm('确定撤销', '提示', {
                confirmButtonText: '确定注销账号吗，该操作不可恢复！',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/member/deleteAccount', {
                    account:sessionStorage.getItem("account")}
                ).then(result => {
                    if (result.body.success) {
                        //更新成功
                        this.$message({
                            type: 'success',
                            message: result.body.message,
                            duration: 4000
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
        });

        }

    },

    created(){
        // this.findAll();
        this.getPersonInformation();
    }

});