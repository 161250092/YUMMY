
Vue.http.options.emulateJSON = true;
let vm = new Vue({
    el:"#app",
    data: {
        startTime:'2019-02-15',
        endTime:'2019-04-12',
        type:"mock",
        activeIndex:'1'
    },

    methods: {

        lineCharts(x,y,container){
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

        pieCharts(legend,data,container){
            let dom = document.getElementById(container);
            let myChart = echarts.init(dom);
            let app = {};
            let option = {
                title : {
                    text: '',
                    subtext: '',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: legend
                },
                series : [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:data,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };


            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        },

        histogram(x,y,container){
            let dom = document.getElementById(container);
            let myChart = echarts.init(dom);
            let app = {};
            app.title = '坐标轴刻度与标签对齐';

            let option = {
                color: ['#3398DB'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : x,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'次数',
                        type:'bar',
                        barWidth: '60%',
                        data:y
                    }
                ]
            };

            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        },

        pointCharts(data,container){
            let dom = document.getElementById(container);
            let myChart = echarts.init(dom);
            let app = {};
            let option = {
                xAxis: {},
                yAxis: {},
                series: [{
                    symbolSize: 20,
                    data: data,
                    type: 'scatter'
                }]
            };
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        },


        updateInfo(){
            this.type="realData";
            this.getConsumptionCharacteristics();
        },

        getConsumptionCharacteristics(){
            this.$http.post('/member/getConsumptionCharacteristics', {
                startTime:this.startTime,
                endTime:this.endTime,
                type:this.type
            }).then(result => {
                console.log(result.body);
                let data = [];
                let legend = [];
                let map = result.body.merchantsFavor;
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        let temp = {};
                        temp.value = map[item];
                        temp.name = item;
                        legend.push(item);
                        data.push(temp)
                    }
                }
                this.pieCharts(legend,data,"merchantsFavor");


                map =  result.body.dishesFavor;
                data = [];
                legend = [];
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        let temp = {};
                        temp.value = map[item];
                        temp.name = item;
                        legend.push(item);
                        data.push(temp)
                    }
                }

                this.pieCharts(legend,data,"dishesFavor");


                map =  result.body.consumptionTimeIntervals;
                data = [];
                legend = [];
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        let temp = {};
                        temp.value = map[item];
                        temp.name = item;
                        legend.push(item);
                        data.push(temp)
                    }
                }
                this.pieCharts(legend,data,"consumptionTimeIntervals");



                map =  result.body.consumptionIntervals;
                let x = [];
                let y =[];
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        x.push(item);
                        y.push(map[item]);
                    }
                }
                this.histogram(x,y,"consumptionIntervals");


                map =  result.body.consumptionDistance;
                data = [];

                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        let temp = [];
                        temp.push(item);
                        temp.push(map[item]);
                        data.push(temp);
                    }
                }
                this.pointCharts(data,"consumptionDistance")

            });
        },

    },

    created() {
        this.getConsumptionCharacteristics();
    },

});



