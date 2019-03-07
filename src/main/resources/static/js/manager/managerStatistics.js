Vue.http.options.emulateJSON = true;

let vm = new Vue({

    el:"#app",
    data: {

            statisticsInformation:{},
            dataInY: [0,0,0,0,0,0,0,0],
            categories:['level 1','level 2','level 3','level 4','level 5','level 6','level 7','level 8'],
            activeIndex:"2"
    },


    methods: {

        getStatisticsInformation(){
            this.$http.post('/manager/getStatistics', {
                account: sessionStorage.getItem("account"),
            }).then(result => {
                console.log(result);
                this.statisticsInformation = result.body;
                this.dataInY = result.body.eachLevelMemberNum;
                chart.series[0].update({
                    data:this.dataInY
                });

            });
        },


    },


    created:function (ev) {
        let _this = this;
        this.$nextTick(function () {
            _this.getStatisticsInformation();
            _this.yData = _this.statisticsInformation.eachLevelMemberNum;
            console.log("created"+_this.dataInY);
        })
    },

});



