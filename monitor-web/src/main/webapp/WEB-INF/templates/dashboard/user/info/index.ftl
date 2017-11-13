<#assign ctx="${(rca.contextPath)!''}">

<div class="space-10"></div>

<form id="form" method="post" action="${ctx}/dashboard/user/info" class="form-horizontal"
      enctype="multipart/form-data">
    <div class="tabbable">
        <ul class="nav nav-tabs padding-16">
            <li class="active">
                <a data-toggle="tab" href="#edit-basic" aria-expanded="true">
                    <i class="green ace-icon fa fa-pencil-square-o bigger-125"></i>
                    基础信息
                </a>
            </li>

            <li class="">
                <a data-toggle="tab" href="#edit-password" aria-expanded="false">
                    <i class="blue ace-icon fa fa-key bigger-125"></i>
                    修改密码
                </a>
            </li>
        </ul>

        <div class="tab-content profile-edit-tab-content">
            <div id="edit-basic" class="tab-pane active">
                <h4 class="header blue bolder smaller">基础信息</h4>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right">手机号</label>

                    <div class="col-xs-12 col-sm-5">
                        <input class="form-control readonly" readonly type="text" value="${user.username}">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="merchNm">真实姓名<span class="red">*</span></label>

                    <div class="col-xs-12 col-sm-5">
                    <@s.formInput "user.realname" 'class="form-control" placeholder="请输入您的真实姓名"'/>
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="publicKey">上传头像</label>

                    <div class="col-xs-12 col-sm-5">
                        <input type="file" id="avatar" name="avatar" class="ace ace-file-input"/>
                        <div>请上传 png、gif、jpg 格式的图片文件，文件大小不能超过10MB。建议上传一张 240*240 像素或等比例的图片。</div>
                    </div>
                </div>
            </div>

            <div id="edit-password" class="tab-pane">
                <div class="space-10"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right">新密码<span class="red">*</span></label>

                    <div class="col-xs-12 col-sm-5">
                        <input type="password" id="password" name="password" class="form-control"
                               placeholder="密码:6至20位的字母数字组合" autocomplete="off"/>
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right">确认密码<span class="red">*</span></label>

                    <div class="col-xs-12 col-sm-5">
                        <input type="password" name="rePassword" class="form-control" placeholder="密码:6至20位的字母数字组合"
                               autocomplete="off"/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="clearfix form-actions">
        <div class="col-xs-offset-3">
            <button id="submit" class="btn btn-inverse" data-loading-text="正在提交...">
                <i class="ace-icon fa fa-check"></i>
            <@s.message "app.button.save"/>
            </button>
        </div>
    </div>
</form>

<script src="${ctx}/static/app/js/dashboard/user/info/index.js"></script>
