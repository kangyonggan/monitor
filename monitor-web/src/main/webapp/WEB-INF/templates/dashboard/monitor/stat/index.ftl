<#assign ctx="${(rca.contextPath)!''}">
<#assign app = RequestParameters.app!'' />
<#assign type = RequestParameters.type!'' />
<#assign packageName = RequestParameters.packageName!'' />
<#assign className = RequestParameters.className!'' />
<#assign methodName = RequestParameters.methodName!'' />
<#assign interval = RequestParameters.interval!'2' />
<#assign intervalCount = RequestParameters.intervalCount!'5' />

<div class="page-header">
    <h1>
        实时监控
    </h1>
</div>

<div class="space-10"></div>

<form class="form-inline" method="get">
    <div>
        <div class="form-group">
            <input type="text" class="form-control" name="app" value="${app}" placeholder="系统名称"
                   autocomplete="off"/>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="type" value="${type}" placeholder="类型"
                   autocomplete="off"/>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="packageName" value="${packageName}" placeholder="包名"
                   autocomplete="off"/>
        </div>
    </div>

    <div class="space-4"></div>

    <div>
        <div class="form-group">
            <input type="text" class="form-control" name="className" value="${className}" placeholder="类名"
                   autocomplete="off"/>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="methodName" value="${methodName}" placeholder="方法名"
                   autocomplete="off"/>
        </div>
        <div class="input-group">
            <input type="number" class="form-control" name="interval" value="${interval}" placeholder="取点间隔(秒)"
                   autocomplete="off"/>
        </div>
        <div class="input-group">
            <input type="number" class="form-control" name="intervalCount" value="${intervalCount}" placeholder="取点个数"
                   autocomplete="off"/>
        </div>

        <button class="btn btn-sm btn-inverse" data-toggle="search-submit">
            开始监控
            <span class="ace-icon fa fa-bar-chart-o icon-on-right bigger-110"></span>
        </button>
    </div>
</form>

<div class="space-10"></div>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="echart" style="width: 100%;height:400px;"></div>

<script>
    var app = '${app}';
    var type = '${type}';
    var packageName = '${packageName}';
    var className = '${className}';
    var methodName = '${methodName}';
    var interval = '${interval}';
    var nextTime = ${lastTime};

    var xAxisData = [];
    var seriesData1 = [];
    var seriesData2= [];
    var seriesData3 = [];

    var maxValue = 0;
    <#list monitors as monitor>
    xAxisData.unshift('${monitor.beginTime?number_to_date?string("HH:mm:ss")}' + "~" + '${monitor.endTime?number_to_date?string("HH:mm:ss")}');
    var concurrencyCount = ${monitor.concurrencyCount};
    seriesData1.unshift(concurrencyCount);
    var usedTime = ${monitor.usedTime};
    seriesData2.unshift(usedTime);
    var tps = ${monitor.tps};
    seriesData3.unshift(tps);

    if (concurrencyCount > maxValue) {
        maxValue = Math.ceil(concurrencyCount);
    }
    if (usedTime > maxValue) {
        maxValue = Math.ceil(usedTime);
    }
    if (tps > maxValue) {
        maxValue = Math.ceil(tps);
    }
    </#list>


</script>

<script src="${ctx}/static/libs/echart/echarts.common.min.js"></script>
<script src="${ctx}/static/libs/dateformat.js"></script>
<script src="${ctx}/static/app/js/dashboard/monitor/stat/index.js"></script>

