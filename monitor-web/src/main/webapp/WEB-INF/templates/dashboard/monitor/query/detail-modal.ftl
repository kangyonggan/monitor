<#assign modal_title="监控详情" />

<@override name="modal-body">
<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
    <tbody>
    <tr>
        <th width="25%">ID</th>
        <td width="75%">${monitor.id}</td>
    </tr>
    <tr>
        <th>系统名称</th>
        <td>${monitor.app}</td>
    </tr>
    <tr>
        <th>类型</th>
        <td>${monitor.type}</td>
    </tr>
    <tr>
        <th>包名</th>
        <td>${monitor.packageName}</td>
    </tr>
    <tr>
        <th>类名</th>
        <td>${monitor.className}</td>
    </tr>
    <tr>
        <th>方法名</th>
        <td>${monitor.methodName}</td>
    </tr>
    <tr>
        <th>描述</th>
        <td>${monitor.description}</td>
    </tr>
    <tr>
        <th>开始时间</th>
        <td>${monitor.beginTime?number_to_date?string('yyyy-MM-dd HH:mm:ss.SSS')}</td>
    </tr>
    <tr>
        <th>结束时间</th>
        <td>${monitor.endTime?number_to_date?string('yyyy-MM-dd HH:mm:ss.SSS')}</td>
    </tr>
    <tr>
        <th>耗时</th>
        <td>${monitor.endTime - monitor.beginTime}ms</td>
    </tr>
    <tr>
        <th>入参类型</th>
        <td>${monitor.argTypes}</td>
    </tr>
    <tr>
        <th>返回值类型</th>
        <td>${monitor.returnType}</td>
    </tr>
    <tr>
        <th>逻辑删除</th>
        <td>${(monitor.isDeleted==1)?string('是', '否')}</td>
    </tr>
    <tr>
        <th>创建时间</th>
        <td><#if monitor.createdTime??>${monitor.createdTime?datetime}</#if></td>
    </tr>
    <tr>
        <th>更新时间</th>
        <td><#if monitor.updatedTime??>${monitor.updatedTime?datetime}</#if></td>
    </tr>
    </tbody>
</table>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    <@s.message "app.button.close"/>
</button>
</@override>

<@extends name="../../../modal-layout.ftl"/>