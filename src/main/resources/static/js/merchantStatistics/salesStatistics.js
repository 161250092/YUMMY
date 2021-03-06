
Vue.http.options.emulateJSON = true;
let vm = new Vue({
    el:"#app",
    data: {
        startTime:'2019-02-12',
        endTime:'2019-04-12',
        type:"mock",
        activeIndex:'2',
        income:0,
        orderNums:0,
        averagePriceOfOrders:0.0
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
            this.type = "realData";
            this.getSalesStatistics();
        },

        getSalesStatistics(){
            this.$http.post('/merchant/getSalesStatistics', {
                startTime:this.startTime,
                endTime:this.endTime,
                type:this.type
            }).then(result => {
                console.log(result);
                this.income=result.body.income;
                this.orderNums = result.body.orderNums;
                this.averagePriceOfOrders = result.body.averagePriceOfOrders;

                //菜品销售数   histogram
                let x = [];
                let y =[];
                let map = result.body.dishesProportion;
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        x.push(item);
                        y.push(map[item]);
                    }
                };
                this.histogram(x,y,"dishesProportion")



                //订单价格分布 histogram
                x = [];
                y =[];
                map = result.body.orderPricesInterval;
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        x.push(item);
                        y.push(map[item]);
                    }
                };
                this.histogram(x,y,"orderPricesInterval")



                //订单时间分布  pie
                map =  result.body.orderTimeInterval;
                let data = [];
                let legend = [];
                for(let item in map){
                    if(map.hasOwnProperty(item)) {
                        let temp = {};
                        temp.value = map[item];
                        temp.name = item;
                        legend.push(item);
                        data.push(temp)
                    }
                }
                this.pieCharts(legend,data,"orderTimeInterval");



                //销售额涨跌   线
                x = [];
                y =[];
                map = result.body.salesAmountCondition;
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
                this.lineCharts(a,b,"salesAmountCondition");


            });
        },

    },

    created() {
        this.getSalesStatistics();
    },

});

