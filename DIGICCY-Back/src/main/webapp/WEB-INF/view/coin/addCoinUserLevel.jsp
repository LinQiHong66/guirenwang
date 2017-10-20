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
    <script src="/app/js/header.js"></script>
    <script src="/app/assets/laydate/laydate.js"></script>
    <link href="/app/assets/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <link href="/app/css/tab.css" rel="stylesheet" />
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
                       虚拟货币分润信息
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">虚拟货币分润信息</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">新增货币分润信息</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>新增货币分润信息</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <%--<button class="btn btn-inverse" id="btn_update"><i class="icon-pencil icon-white"></i>&nbsp;修改</button>--%>
                            <%--<button class="btn btn-primary" id="btn_delete"><i class="icon-remove icon-white"></i>&nbsp;删除</button>--%>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">货币类型:</label>&nbsp;&nbsp;
                                	<select  class="input-large m-wrap" tabindex="1" id="coin_no">
										<c:forEach items="${level_coin.coinList}" var="level">
											<option value="${level.coin_no}">${level.coin_name}</option>
										</c:forEach>
									</select>
                               	</div>
           						<div class="form-group" style="margin-top: 5px">
                					<label class="form-label">经纪人1比例:</label>&nbsp;&nbsp;
                					<input type="text"  id="level_one"  onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
                					<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
            					</div>
            					<div class="form-group" style="margin-top: 5px">
                					<label class="form-label">经纪人2比例:</label>&nbsp;&nbsp;
                					<input type="text"  id="level_two"  onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
                					<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
            					</div>
            					<div class="form-group" style="margin-top: 5px">
                					<label class="form-label">子机构比例:</label>&nbsp;&nbsp;
                					<input type="text"  id="level_three"  onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
                					<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
            					</div>
           					 	<div class="form-group" style="margin-top: 5px">
            					    <label class="form-label">机构比例:</label>&nbsp;&nbsp;
           					     	<input type="text"  id="level_four"  onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
           					     	<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
       					    	 </div>
        					    <!-- <div class="form-group" style="margin-top: 5px">
          					      	<label class="form-label">推荐人四代比例:</label>&nbsp;&nbsp;
           					     	<input type="text"  id="level_five"  onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
           					     	<span style="color:red">注意：请填小数并且五代比例相加不能大于1</span>
          					 	</div> -->
          					  	<div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">分润货币类型:</label>&nbsp;&nbsp;
                                	<select  class="input-large m-wrap" tabindex="1" id="level_type">
               					        <option value="0" selected="selected">买：交易数量*手续费*分润比例；卖：交易数量*兑换货币金额*手续费*分润比例</option>
             					   </select>
                              	</div>
                              	<%-- <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">分润货币类型:</label>&nbsp;&nbsp;
                                	<select  class="input-large m-wrap" tabindex="1" id="level_type">
										<c:forEach items="${coin.data}" var="coin">
											<option value="${coin.coin_no}">${coin.coin_name}</option>
										</c:forEach>
									</select>
                              	</div> --%>
          					  	<div class="form-group" style="margin-top: 5px">
             					   <label class="form-label">分润状态:</label>&nbsp;&nbsp;
             					   <select  class="input-large m-wrap" tabindex="1" id="state">
               					        <option value="0" selected="selected">关闭</option>
               					        <option value="1">启用</option>
             					   </select>
           					 	</div>
                            	<div class="form-group" style="margin-top: 5px">
                                	<button id="addLevel"  class="btn">提交</button>
                                	<button type="reset" class="btn"><i class=" icon-remove"></i>重置</button>
                            	</div>
                        	</div>
                    	</div>
                    <!-- END SAMPLE FORM PORTLET-->
                </div>
            </div>
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<!-- Load javascripts at bottom, this will reduce page load time -->
<script src="/app/js/footer.js"></script>
<script src="/app/js/echarts.min.js"></script>
<script src="/app/js/ajax/userCoinLevel.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
//判断是否数字
function isNumber(v) {
	var r = /^[0-9]+.?[0-9]*$/;
	if (!r.test(v.value)) { //isNaN也行的,正则可以随意扩展
		alert('只能输入数字');
		v.value = "0";
	}
}
</script>
</body>
</html>
