let chart = Highcharts.chart('container', {

    chart: {
        type: 'column'
    },
    title: {
        text: '个商家消费情况'
    },
    subtitle: {
        text: ''
    },
    xAxis: {
        categories: vm.consumedMerchants
    },
    yAxis: {
        title: {
            text: '消费'
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
        name: '消费额',
        data: [1]
    }],

});


let chart1 = Highcharts.chart('container1', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: '订单情况'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                }
            }
        }
    },
    series: [{
        type: 'pie',
        name: '订单占比',
    }]

});





