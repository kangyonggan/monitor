<#assign ctx="${(rca.contextPath)!''}">
<#assign app = RequestParameters.app!'' />
<#assign type = RequestParameters.type!'' />
<#assign packageName = RequestParameters.packageName!'' />
<#assign className = RequestParameters.className!'' />
<#assign methodName = RequestParameters.methodName!'' />
<#assign beginTime = RequestParameters.beginTime!'' />
<#assign endTime = RequestParameters.endTime!'' />
<#assign interval = RequestParameters.interval!'' />

<link rel="stylesheet" href="${ctx}/static/ace/dist/css/bootstrap-datetimepicker.min.css" />

<div class="page-header">
    <h1>
        监控统计
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
        <div class="form-group">
            <input type="text" class="form-control" name="className" value="${className}" placeholder="类名"
                   autocomplete="off"/>
        </div>
    </div>

    <div class="space-4"></div>

    <div>
        <div class="form-group">
            <input type="text" class="form-control" name="methodName" value="${methodName}" placeholder="方法名"
                   autocomplete="off"/>
        </div>
        <div class="form-group">
            <div class="input-group">
                <input name="beginTime" value="${beginTime}" type="datetime" class="form-control datetime" placeholder="开始时间"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <input name="endTime" value="${endTime}" type="datetime" class="form-control datetime" placeholder="结束时间"/>
            </div>
        </div>
        <div class="input-group">
            <input type="number" class="form-control" name="interval" value="${interval}" placeholder="取点间隔(分钟)"
                   autocomplete="off"/>
        </div>

        <button class="btn btn-sm btn-inverse" data-toggle="search-submit">
            统计
            <span class="ace-icon fa fa-bar-chart-o icon-on-right bigger-110"></span>
        </button>
    </div>
</form>

<div class="space-20"></div>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="echart" style="width: 1000px;height:700px;"></div>

<script src="${ctx}/static/libs/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/static/libs/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/static/libs/echart/echarts.common.min.js"></script>
<script src="${ctx}/static/app/js/dashboard/monitor/stat/index.js"></script>

