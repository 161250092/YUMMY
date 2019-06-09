
Vue.http.options.emulateJSON = true;
let vm = new Vue({
    el:"#app",
    data: {
            startTime:'2016-06-01',
            endTime:'2016-06-30',
            interval:"",
            dom:null,
            myChart:null,
            app:{}
    },

    methods: {

        getOrderCharacteristics(){
            console.log(this.startTime);
            this.$http.post('/member/getOrderCharacteristics', {
                startTime:this.startTime,
                endTime:this.endTime,
                interval:"day"
            }).then(result => {
                console.log(result.body);

                let x = [];
                let y =[];
                let map = result.body.consumptionCount;
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        x.push(item);
                        y.push(map[item]);
                    }
                }
                this.consumptionCountLineCharts(x,y,"container")
            });
        },

        consumptionCountLineCharts(x,y,container){
            console.log(x);
            console.log(y);
            let dom = document.getElementById(container);
            let myChart = echarts.init(dom);
            let app = {};
            app.title = "线图";
            let option = {
                xAxis: {
                    type: 'category',
                    data: x
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: y,
                    type: 'line',
                    smooth: true
                }]
            };



            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        },

        getConsumptionCharacteristics(){
            this.$http.post('/member/getConsumptionCharacteristics', {
                startTime:this.startTime,
                endTime:this.endTime,
                interval:"day"
            }).then(result => {
                console.log(result.body);
            });
        },

    },

    created() {
            this.getOrderCharacteristics();
    },

});



