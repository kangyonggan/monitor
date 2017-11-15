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
                data: xAxisData
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '并发量&TPS&平均耗时(s)',
                scale: true,
                max: maxValue + 10,
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name: '并发量',
                type: 'line',
                data: seriesData1
            },
            {
                name: '平均耗时',
                type: 'line',
                data: seriesData2
            },
            {
                name: 'TPS',
                type: 'line',
                data: seriesData3
            }
        ]
    };

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart'));
    myChart.setOption(option);

    setInterval(function () {
        $.get(ctx + "/dashboard/monitor/stat/next", {
            app: app,
            type: type,
            packageName: packageName,
            className: className,
            methodName: methodName,
            interval: interval,
            nextTime: nextTime
        }, function (data) {
            data = eval('(' + data + ')');
            nextTime = data.lastTime;

            option.xAxis[0].data.shift();
            var beginTime = new Date();
            beginTime.setTime(data.nextMonitor.beginTime);

            var endTime = new Date();
            endTime.setTime(data.nextMonitor.endTime);
            option.xAxis[0].data.push(beginTime.pattern("HH:mm:ss") + "~" + endTime.pattern("HH:mm:ss"));

            option.series[0].data.shift();
            option.series[0].data.push(data.nextMonitor.concurrencyCount);

            option.series[1].data.shift();
            option.series[1].data.push(data.nextMonitor.usedTime);

            option.series[2].data.shift();
            option.series[2].data.push(data.nextMonitor.tps);

            myChart.setOption(option);
        });

    }, interval * 1000);

});