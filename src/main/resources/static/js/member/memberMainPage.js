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

            card:[],
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
                    account:sessionStorage.getItem("account")
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
        this.card = [
            {url:"http://thyrsi.com/t6/677/1551960876x2890202977.jpg",id:1},
            {url:"http://thyrsi.com/t6/675/1551424860x2890202402.png",id:2},
            {url:"http://thyrsi.com/t6/676/1551625184x2728309394.jpg",id:3}];

    }


});