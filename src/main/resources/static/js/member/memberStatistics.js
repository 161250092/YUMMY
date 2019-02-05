
var vm = new Vue({

    el:"#app",
    data() {
        return {
            series:[{
                name: '东京',
                data: [10, 10, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }]
        }

    },


    methods: {


    },

    created() {

    }


})




let chart = Highcharts.chart('container', {
    chart: {
        type: 'line'
    },
    title: {
        text: '月平均气温'
    },
    subtitle: {
        text: '数据来源: WorldClimate.com'
    },
    xAxis: {
        categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
    },
    yAxis: {
        title: {
            text: '气温 (°C)'
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                // 开启数据标签
                enabled: true
            },
            // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            enableMouseTracking: false
        }
    },
    series: vm.series

});

