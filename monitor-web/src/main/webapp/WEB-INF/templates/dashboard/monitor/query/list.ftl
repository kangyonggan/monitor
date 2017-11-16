<#assign ctx="${(rca.contextPath)!''}">
<#assign app = RequestParameters.app!'' />
<#assign type = RequestParameters.type!'' />
<#assign packageName = RequestParameters.packageName!'' />
<#assign className = RequestParameters.className!'' />
<#assign methodName = RequestParameters.methodName!'' />
<#assign minUsedTime = RequestParameters.minUsedTime!'' />
<#assign maxUsedTime = RequestParameters.maxUsedTime!'' />

<div class="page-header">
    <h1>
        监控列表
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
            <input type="text" class="form-control" name="minUsedTime" value="${minUsedTime}" placeholder="最小耗时(ms)"
                   autocomplete="off"/>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="maxUsedTime" value="${maxUsedTime}" placeholder="最大耗时(ms)"
                   autocomplete="off"/>
        </div>

        <button class="btn btn-sm btn-inverse" data-toggle="search-submit">
            搜索
            <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
        </button>
    </div>

</form>

<div class="space-10"></div>

<table id="user-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>系统名称</th>
        <th>类型</th>
        <th>类名</th>
        <th>方法名</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>耗时</th>
        <th>描述</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#if page.list?? && page.list?size gt 0>
        <#list page.list as monitor>
            <#include "table-tr.ftl"/>
        </#list>
    <#else>
    <tr>
        <td colspan="20">
            <div class="empty">暂无查询记录</div>
        </td>
    </tr>
    </#if>
    </tbody>
</table>
<@c.pagination url="#monitor/query" param="app=${app}&type=${type}&packageName=${packageName}&className=${className}&methodName=${methodName}&minUsedTime=${minUsedTime}&maxUsedTime=${maxUsedTime}"/>

<script src="${ctx}/static/app/js/dashboard/monitor/query/list.js"></script>
