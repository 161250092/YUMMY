Vue.http.options.emulateJSON = true;

let vm = new Vue({

    el:"#app",
    data: {

        statisticsInformation:{},

        consumedMerchants:[],

        consumedAmount:[],

        activeIndex:'3'

    },


    methods: {

        getStatisticsInformation(){
            this.$http.post('/member/getMemberStatisticsInformation', {
                account: sessionStorage.getItem("account"),
            }).then(result => {
                console.log(result);
                this.statisticsInformation = result.body;
                let map = result.body.consumptionInformation;
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        this.consumedMerchants.push(item);
                        this.consumedAmount.push(map[item]);
                    }
                }
                chart.series[0].update({
                    data:this.consumedAmount
                });
                chart.xAxis.categories = this.consumedMerchants;


                let chartData  =[{"name":"订单","y":this.statisticsInformation.acceptedOrdersNum},{"name":"废弃的订单","y":this.statisticsInformation.abolishedOrderNum}];
                chart1.series[0].setData(chartData);

            });
        },


    },


    created() {
       this.getStatisticsInformation();
    },

});



