
Vue.http.options.emulateJSON = true;
let vm = new Vue({
    el:"#app",
    data: {
            startTime:'2019-02-15',
            endTime:'2019-04-12',
            type:"mock",
            activeIndex:'2'
    },

    methods: {

        getOrderCharacteristics(){
            console.log(this.startTime);
            this.$http.post('/member/getOrderCharacteristics', {
                startTime:this.startTime,
                endTime:this.endTime,
                type:this.type
            }).then(result => {
                console.log(result.body);

                let x = [];
                let y =[];
                let map = result.body.ordersCount;
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        x.push(item);
                        y.push(map[item]);
                    }
                }
                let a = [];
                let b = [];
                for(let i=0;i<x.length;i++){
                    a[x.length-1-i] = x[i];
                    b[x.length-1-i] = y[i];
                }


                this.lineCharts(a,b,"ordersCount");


                x = [];
                y =[];
                map = result.body.consumptionCount;
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        x.push(item);
                        y.push(map[item]);
                    }
                }
                a = [];
                b = [];
                for(let i=0;i<x.length;i++){
                    a[x.length-1-i] = x[i];
                    b[x.length-1-i] = y[i];
                }


                this.lineCharts(a,b,"consumptionCount");



            });
        },



        lineCharts(x,y,container){
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
                type:this.type
            }).then(result => {
                console.log(result.body);
            });
        },


        updateInfo(){
            this.type = "realData";
            this.getOrderCharacteristics();
        }

    },

    created() {
            this.getOrderCharacteristics();
    },

});



