$(function () {
    updateState("monitor/stat");

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: 'auto'
                }
            }
        },
        legend: {
            data: ['并发量', '平均耗时', 'TPS']
        },
        toolbox: {
            show: true,
            feature: {
                saveAsImage: {show: true}
            }
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function () {
                    var now = new Date();
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.unshift(now.pattern("HH:mm:ss") + "+2");
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '并发量&TPS',
                scale: true,
                max: 30,
                min: 0,
                boundaryGap: [0.2, 0.2]
            },
            {
                type: 'value',
                name: '平均耗时(ms)',
                scale: true,
                max: 2000,
                min: 500,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name: '并发量',
                type: 'line',
                data: (function () {
                    var res = [];
                    var len = 0;
                    while (len < 10) {
                        res.push((Math.random() * 10 + 5).toFixed(1) - 0);
                        len++;
                    }
                    return res;
                })()
            },
            {
                name: '平均耗时',
                type: 'line',
                data: (function () {
                    var res = [];
                    var len = 0;
                    while (len < 10) {
                        res.push((Math.random() * 10 + 5).toFixed(1) - 0);
                        len++;
                    }
                    return res;
                })()
            },
            {
                name: 'TPS',
                type: 'line',
                data: (function () {
                    var res = [];
                    var len = 0;
                    while (len < 10) {
                        res.push((Math.random() * 10 + 5).toFixed(1) - 0);
                        len++;
                    }
                    return res;
                })()
            }
        ]
    };

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart'));
    myChart.setOption(option);

    count = 11;
    setInterval(function () {

        option.series[0].data.shift();
        option.series[0].data.push(Math.round(Math.random() * 20));

        option.series[1].data.shift();
        option.series[1].data.push(Math.round(Math.random() * 20));

        option.series[2].data.shift();
        option.series[2].data.push(Math.round(Math.random() * 20));

        var axisData = new Date().pattern("HH:mm:ss") + "+2";
        option.xAxis[0].data.shift();
        option.xAxis[0].data.push(axisData);

        myChart.setOption(option);
    }, 2000);



});