Vue.http.options.emulateJSON = true;

let vm = new Vue({

    el:"#app",
    data: {
        statisticsInformation:{},
        activeIndex:"2"
    },

    methods: {

        getStatisticsInformation(){
            this.$http.post('/merchant/getMerchantStatistics', {
                account: sessionStorage.getItem("account"),
            }).then(result => {
                console.log(result);
                this.statisticsInformation = result.body;
                let chartData  =[{"name":"订单","y":this.statisticsInformation.receivedOrdersNum},{"name":"废弃的订单","y":this.statisticsInformation.abolishedOrdersNum}];
                chart1.series[0].setData(chartData);

            });
        },


    },

    created() {
        this.getStatisticsInformation();
    },

});



