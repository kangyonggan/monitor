<div id="navbar" class="navbar navbar-default">
    <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="${ctx}/" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                <@s.message "app.name"/>
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
        <@shiro.user>
            <#if user??>
                <ul class="nav ace-nav">
                    <li class="dark">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <img class="nav-user-photo" id="userAvatar" src="${ctx}/${(user.smallAvatar!='')?string('${user.smallAvatar}', 'static/app/images/default.jpg')}"
                                 alt="${(user.realname!='')?string('${user.realname}', '${user.username}')}">
                            <span class="user-info">
									<small>欢迎,</small>
                            <span id="navFullname">${(user.realname!='')?string('${user.realname}', '${user.username}')}</span>
								</span>

                            <i class="ace-icon fa fa-caret-down"></i>
                        </a>

                        <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li>
                                <a href="${ctx}/#logout">
                                    <i class="ace-icon fa fa-power-off"></i>
                                    安全退出
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            <#else>
                <ul class="nav navbar-nav hidden-xs">
                    <li>
                        <a href="${ctx}/dashboard">工作台</a>
                    </li>
                </ul>
            </#if>
        </@shiro.user>

        <@shiro.guest>
            <ul class="nav navbar-nav hidden-xs">
                <li>
                    <a href="#index">登录</a>
                </li>
            </ul>
        </@shiro.guest>
        </div>
    </div>
</div>