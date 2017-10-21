<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>
<!DOCTYPE html>
<!--
Template Name: Admin Lab Dashboard build with Bootstrap v2.3.1
Template Version: 1.2
Author: Mosaddek Hossain
Website: http://thevectorlab.net/
-->

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>主页</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <script src="/app/js/jquery-1.8.3.min.js"></script>
    <script src="/app/js/header.js"></script>
    <link href="/app/assets/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <link href="/app/assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
    <link href="/app/css/tab.css" rel="stylesheet" />
    <script src="/app/js/ajax/coin.js"></script>
    <%--<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />--%>
    <%--<link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />--%>
    <%--<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />--%>
    <%--<link href="css/style.css" rel="stylesheet" />--%>
    <%--<link href="css/style_responsive.css" rel="stylesheet" />--%>
    <%--<link href="css/style_default.css" rel="stylesheet" id="style_color" />--%>

    <%--<link href="assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />--%>
    <%--<link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />--%>
    <%--<link href="assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />--%>
    <%--<link href="assets/jqvmap/jqvmap/jqvmap.css" media="screen" rel="stylesheet" type="text/css" />--%>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
<!-- BEGIN HEADER -->
<%@include file="../header.jsp" %>
<!-- BEGIN CONTAINER -->
<div id="container" class="row-fluid">
    <!-- BEGIN SIDEBAR -->
    <%@include file="../left.jsp" %>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div id="main-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        虚拟货币信息
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">虚拟货币管理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">虚拟货币信息</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>虚拟货币信息</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <!-- <button class="btn btn-success" id="btn_add"><i class="icon-plus icon-white"></i>&nbsp;新增</button> -->
                            <a href="/coin/coinCount.do"><button class="btn btn-success"><i class="icon-plus icon-white"></i>&nbsp;货币统计报表</button></a>
                            <%--<button class="btn btn-inverse" id="btn_update"><i class="icon-pencil icon-white"></i>&nbsp;修改</button>--%>
                            <%--<button class="btn btn-primary" id="btn_delete"><i class="icon-remove icon-white"></i>&nbsp;删除</button>--%>
                            <table id="coin_table">

                            </table>
                        </div>
                    </div>
                    <!-- END SAMPLE FORM PORTLET-->
                </div>
            </div>
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
    <!-- 新增角色 -->
    <div class="boxK" id="box_add">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_add"></i></div>
        <h4>新增虚拟货币</h4><br/><br/>
        <form class="form-inline">
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币编号:</label>&nbsp;&nbsp;
                <input type="text"  id="coin_no" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币名称:</label>&nbsp;&nbsp;
                <input type="text"  id="coin_name" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币代码:</label>&nbsp;&nbsp;
                <input type="text"  id="coin_code" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">接口地址:</label>&nbsp;&nbsp;
                <input type="text"  id="coin_address" class="form-control" style="border-radius: 5px">
            </div>
            <!--<div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;端口号:</label>&nbsp;&nbsp;
                <input type="text"  id="coin_port" class="form-control" style="border-radius: 5px">
            </div>-->
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">钱包地址:</label>&nbsp;&nbsp;
                <input type="text"  id="wallet_host" name="wallet_host" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">钱包端口:</label>&nbsp;&nbsp;
                <input type="text"  id="wallet_post" name="wallet_post" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                &nbsp;&nbsp;&nbsp;&nbsp;<label class="form-label">钱包名:</label>&nbsp;&nbsp;
                <input type="text"  id="wallet_username" name="wallet_username" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">钱包密码:</label>&nbsp;&nbsp;
                <input type="text"  id="wallet_password" name="wallet_password" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;钱包锁:</label>&nbsp;&nbsp;
                <input type="text"  id="wallet_lock" name="wallet_lock" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;买入手续费比率:</label>&nbsp;&nbsp;
                <input type="text"  id="buy_poundatge" name="buy_poundatge" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;卖出手续费比率:</label>&nbsp;&nbsp;
                <input type="text"  id="sell_poundatge" name="sell_poundatge" class="form-control" style="border-radius: 5px">
            </div>
            
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;一级提现手续费:</label>&nbsp;&nbsp;
                <input type="text"  id="sell_withdraw_poundatge_one" name="withdraw_poundatge_one" class="form-control" style="border-radius: 5px">
            </div>
            
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;二级提现手续费:</label>&nbsp;&nbsp;
                <input type="text"  id="sell_withdraw_poundatge_twe" name="withdraw_poundatge_twe" class="form-control" style="border-radius: 5px">
            </div>
            
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;三级提现手续费:</label>&nbsp;&nbsp;
                <input type="text"  id="sell_withdraw_poundatge_three" name="withdraw_poundatge_three" class="form-control" style="border-radius: 5px">
            </div>
             <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;币种区块数:</label>&nbsp;&nbsp;
                <input type="text"  id="block" name="block" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <button type="button" class="btn blue" id="add_Role" onclick="addCoin()"><i class="icon-ok"></i> 新增</button>
                <button type="reset" class="btn"><i class=" icon-remove"></i> Cancel</button>
            </div>
        </form>
    </div>
    <!-- 修改角色 -->
    <div class="boxK" id="box_update">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_update"></i></div>
        <h4>修改虚拟货币</h4><br/><br/>
        <form class="form-inline">
            <input type="hidden" id="update_id">
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币编号:</label>&nbsp;&nbsp;
                <input type="text" disabled="true" style="" id="update_no" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币名称:</label>&nbsp;&nbsp;
                <input type="text"  id="update_name" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币代码:</label>&nbsp;&nbsp;
                <input type="text"  id="update_code" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">接口地址:</label>&nbsp;&nbsp;
                <input type="text"  id="update_address" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">钱包地址:</label>&nbsp;&nbsp;
                <input type="text"  id="update_host" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">钱包端口:</label>&nbsp;&nbsp;
                <input type="text"  id="update_post" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                &nbsp;&nbsp;&nbsp;&nbsp;<label class="form-label">钱包名:</label>&nbsp;&nbsp;
                <input type="text"  id="update_wallet_name" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">钱包密码:</label>&nbsp;&nbsp;
                <input type="text"  id="update_wallet_pwd" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                &nbsp;&nbsp;&nbsp;&nbsp;<label class="form-label">钱包锁:</label>&nbsp;&nbsp;
                <input type="text"  id="update_wallet_lockpwd" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px;margin-left: 15px">
                <label class="form-label">买入手续费比率:</label>&nbsp;&nbsp;
                <input type="text"  id="update_buy_poundatge" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">卖出手续费比率:</label>&nbsp;&nbsp;
                <input type="text"  id="update_sell_poundatge" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;一级提现手续费:</label>&nbsp;&nbsp;
                <input type="text"  id="update_sell_withdraw_poundatge_one" name="withdraw_poundatge_one" class="form-control" style="border-radius: 5px">
            </div>
            
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;二级提现手续费:</label>&nbsp;&nbsp;
                <input type="text"  id="update_sell_withdraw_poundatge_twe" name="withdraw_poundatge_twe" class="form-control" style="border-radius: 5px">
            </div>
            
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;三级提现手续费:</label>&nbsp;&nbsp;
                <input type="text"  id="update_sell_withdraw_poundatge_three" name="withdraw_poundatge_three" class="form-control" style="border-radius: 5px">
            </div>
             <div class="form-group" style="margin-top: 5px">
                <label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;币种区块数:</label>&nbsp;&nbsp;
                <input type="text"  id="update_block" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <button type="button" class="btn blue" id="update_Role" onclick="updateCoin()"><i class="icon-ok"></i> Save</button>
                <button type="reset" class="btn"><i class=" icon-remove"></i> Cancel</button>
            </div>
        </form>
    </div>
    <!-- 投票结果 -->
   <!--  <div class="boxK" id="box_vote">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_vote"></i></div>
        <h4>投票结果</h4><br/><br/>
        <div id="count" style="width:80%;margin:0 auto">
            <div class="control-group">
                <label class="control-label">支持:</label>
                <div class="controls">
                    <div class="progress progress-striped progress-success" id="sup">
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">反对:</label>
                <div class="controls">
                    <div class="progress progress-striped progress-danger" id="opp">
                    </div>
                </div>
            </div>
        </div>
    </div> -->
    <div class="boxK" id="box_icon">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_icon"></i></div>
        <h4>货币图标</h4><br/><br/>
        <div class="control-group">
            <div class="controls">
                <div class="fileupload fileupload-new" data-provides="fileupload">
                    <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                    <div>
                        <form action="/coin/uploadIcon.do" method="post" enctype="multipart/form-data">
                            <input type="hidden" class="default" id="id" name="id"/>
                            <span class="btn btn-file"><span class="fileupload-new">选择图片</span>
                              <span class="fileupload-exists">更改</span>
                              <input type="file" class="default" id="icon" name="icon"/>
                            </span>
                            <input type="submit" type="submit" class="btn fileupload-exists" />
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<!-- Load javascripts at bottom, this will reduce page load time -->
<script src="/app/js/footer.js"></script>
<script src="/app/js/echarts.min.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/app/js/bootstrap-table/coin-tab.js"></script>
<script type="text/javascript" src="/app/assets/bootstrap/js/bootstrap-fileupload.js"></script>

<%--<script type="text/javascript">

    function addCoin() {
        var no = $("#coin_no").val();
        var name = $("#coin_name").val();
        var code = $("#coin_code").val();
        var address = $("#coin_address").val();
        var host = $("#wallet_host").val();
        var post = $("#wallet_post").val();
        var wallet_name = $("#wallet_username").val();
        var wallet_pwd= $("#wallet_password").val();
        var wallet_lockpwd = $("#wallet_lock").val();
        $.ajax({
            url:"/coin/addCoin.do",
            type: "post",
            dataType: "json",
            data:{
                no:no,
                name:name,
                code:code,
                address:address,
                host:host,
                post:post,
                wallet_name:wallet_name,
                wallet_pwd:wallet_pwd,
                wallet_lockpwd:wallet_lockpwd
            },
            success: function (msg) {
                // if(msg.code == "100"){
                $.alertable.alert(msg.desc);
                $("#box_add").css({"display":"none"});
                window.location.reload();
                // }
            }
        });
    }

</script>--%>


<%--
<script type="text/javascript">
    function updateCoin() {
        var no = $("#update_no").val();
        var name = $("#update_name").val();
        var code = $("#update_code").val();
        var address = $("#update_address").val();
        var host = $("#update_host").val();
        var post = $("#update_post").val();
        var wallet_name = $("#update_wallet_name").val();
        var wallet_pwd= $("#update_wallet_pwd").val();
        var wallet_lockpwd = $("#update_wallet_lockpwd").val();
        $.ajax({
            url:"/coin/updateCoin.do",
            type: "post",
            dataType: "json",
            data:{
                no:no,
                name:name,
                code:code,
                address:address,
                host:host,
                post:post,
                wallet_name:wallet_name,
                wallet_pwd:wallet_pwd,
                wallet_lockpwd:wallet_lockpwd

            },
            success: function (msg) {
                // if(msg.code == "100"){
                $.alertable.alert(msg.desc);
                $("#box_update").css({"display":"none"});
                window.location.reload();
                // }
            }
        });
    }
</script>
--%>
<%--<script src="assets/jquery-slimscroll/jquery-ui-1.9.2.custom.min.js"></script>--%>
<%--<script src="assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>--%>
<%--<script src="assets/fullcalendar/fullcalendar/fullcalendar.min.js"></script>--%>
<%--<script src="assets/bootstrap/js/bootstrap.min.js"></script>--%>
<%--<script src="js/jquery.blockui.js"></script>--%>
<%--<script src="js/jquery.cookie.js"></script>--%>
<%--<!-- ie8 fixes -->--%>
<%--<!--[if lt IE 9]>--%>
<%--<script src="js/excanvas.js"></script>--%>
<%--<script src="js/respond.js"></script>--%>
<%--<![endif]-->--%>
<%--<script src="assets/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>--%>
<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>--%>
<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>--%>
<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>--%>
<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>--%>
<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>--%>
<%--<script src="assets/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>--%>
<%--<script src="assets/jquery-knob/js/jquery.knob.js"></script>--%>
<%--<script src="assets/flot/jquery.flot.js"></script>--%>
<%--<script src="assets/flot/jquery.flot.resize.js"></script>--%>

<%--<script src="assets/flot/jquery.flot.pie.js"></script>--%>
<%--<script src="assets/flot/jquery.flot.stack.js"></script>--%>
<%--<script src="assets/flot/jquery.flot.crosshair.js"></script>--%>

<%--<script src="js/jquery.peity.min.js"></script>--%>
<%--<script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>--%>
<%--<script src="js/scripts.js"></script>--%>
<%--<script>--%>
    <%--jQuery(document).ready(function() {--%>
        <%--// initiate layout and plugins--%>
        <%--App.setMainPage(true);--%>
        <%--App.init();--%>
    <%--});--%>
<%--</script>--%>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
