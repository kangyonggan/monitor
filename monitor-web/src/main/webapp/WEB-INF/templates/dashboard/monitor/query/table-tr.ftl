<#assign ctx="${(rca.contextPath)!''}">

<tr id="monitor-${monitor.id}">
    <td>${monitor.app}</td>
    <td>${monitor.type}</td>
    <td>${monitor.packageName}</td>
    <td>${monitor.className}</td>
    <td>${monitor.methodName}</td>
    <td>${monitor.beginTime?number_to_date?string('yyyy-MM-dd HH:mm:ss.SSS')}</td>
    <td>${monitor.endTime?number_to_date?string('yyyy-MM-dd HH:mm:ss.SSS')}</td>
    <td>${monitor.endTime - monitor.beginTime}ms</td>
    <td><@c.substring monitor.description 20/></td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs btn-inverse" href="${ctx}/dashboard/monitor/query/${monitor.id}"
               data-target="#myModal">详情</a>
        </div>
    </td>
</tr>