let chart = Highcharts.chart('container', {

    chart: {
        type: 'column'
    },
    title: {
        text: '各等级会员人数'
    },
    subtitle: {
        text: ''
    },
    xAxis: {
        categories: vm.categories
    },
    yAxis: {
        title: {
            text: '数量'
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                // 开启数据标签
                enabled: true
            },
            // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            enableMouseTracking: true
        }
    },

    series:[{
        name: '人数',
        data: [1,1,5,0,0,0,0,0]
    }],

});

console.log("charts"+vm.dataInY);
console.log(vm.statisticsInformation);

chart.series[0].update({
    data:[1,0,1,0,0,0,0,0]
});