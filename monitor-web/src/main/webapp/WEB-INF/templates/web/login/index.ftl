<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">
<div id="login-box" class="login-box visible widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header grey lighter bigger">
                <i class="ace-icon fa fa-coffee dark"></i>
                登录
            </h4>

            <div class="space-6"></div>

            <form id="login-form" action="${ctx}/login" method="post" novalidate="novalidate">
                <div>
                    <label for="username">用户名<span class="red">*</span></label>
                    <div class="input-icon input-icon-right">
                        <input type="text" name="username" class="form-control"
                               placeholder="请输入5-20位字母和数字的用户名" autocomplete="off"/>
                        <i class="ace-icon fa fa-user"></i>
                    </div>
                </div>

                <div class="space space-8"></div>

                <div>
                    <label for="password">密码<span class="red">*</span></label>
                    <div class="input-icon input-icon-right">
                        <input type="password" name="password" class="form-control"
                               placeholder="请输入8至20位字母和数字的密码" autocomplete="off">
                        <i class="ace-icon fa fa-key"></i>
                    </div>
                </div>

                <div class="space space-8"></div>

                <div>
                    <label for="captcha">验证码<span class="red">*</span></label>
                    <div class="input-icon input-icon-right">
                        <input type="text" id="captcha" name="captcha" class="col-xs-6" placeholder="请输入4位数字的验证码" autocomplete="off">
                        <img id="captchaCode" onclick="this.src='${ctx}/captcha?'+Math.random();" src="${ctx}/captcha">
                    </div>
                </div>

                <div class="space-14"></div>

                <div class="clearfix">
                    <button id="reset" type="reset" class="width-30 pull-left btn btn-sm">
                        <i class="ace-icon fa fa-refresh"></i>
                        重置
                    </button>
                    <button id="submit" class="width-35 pull-right btn btn-sm btn-inverse"
                            data-loading-text="登录中...">
                        <i class="ace-icon fa fa-key"></i>
                        登录
                    </button>
                </div>
            </form>
        </div>

        <div class="toolbar clearfix">
            <div>
                <a href="#forgot" class="forgot-password-link">
                    <i class="ace-icon fa fa-arrow-left"></i>
                    忘记密码？
                </a>
            </div>
            <div>
                <a href="#register" class="user-signup-link">
                    注册
                    <i class="ace-icon fa fa-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
</div>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/web/login/index.js"></script>
</@override>

<@extends name="../auth-layout.ftl"/>