Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {

            searchInfo:{
                RestaurantName:'',
                RestaurantType:''
            },
            merchants:[{
                idCode:'',
                restaurantName:'',
                location:{
                    lat:0,
                    lng:0,
                    address:''
                },

                restaurantType:'',
                minDeliveryCost:'',
                deliveryCost:''

            }],

            activeIndex:'0',

        };
    },
    methods: {
            searchMerchants(){
                this.$http.post('/member/searchMerchants',{
                   restaurantName:this.searchInfo.RestaurantName,
                    restaurantType:this.searchInfo.RestaurantType
                }).then(result => {
                    console.log(result);
                    this.merchants = result.body;
                });

            },

            getAllMerchants(){
                this.$http.post('/member/getAllMerchants',{
                    account:sessionStorage.getItem("idCode")
                }).then(result => {
                    console.log(result);
                    this.merchants = result.body;
                });

            },

            enter(merchant){
                sessionStorage.setItem("idCode",merchant.idCode);
                window.location.href = "/enterMerchant";
            }
    },



    created(){
        this.getAllMerchants();
    }


});