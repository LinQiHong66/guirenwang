<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp" %>
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
    <script src="/app/assets/echarts/echarts.min.js"></script>
    <link href="/app/assets/layui/css/layui.css" rel="stylesheet" />
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
<%@include file="header.jsp" %>
<!-- BEGIN CONTAINER -->
<div id="container" class="row-fluid">
    <!-- BEGIN SIDEBAR -->
    <%@include file="left.jsp" %>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div id="main-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">

        </div>
        <fieldset class="layui-elem-field site-demo-button" style="margin-top: 30px;">
  		<legend>贵人交易平台首页</legend>
  		<div>
    		<button class="layui-btn">当前总用户量：${sumUser}</button>
    		<button class="layui-btn layui-btn-normal">平台累计充值人民币总额：${sumRecharge}</button>
   			<button class="layui-btn layui-btn-warm">平台累计提现人民币总额：${sumWithdraw}</button>
   			<button class="layui-btn layui-btn-danger">平台累计交易总额：${sumTrade}</button>
  		</div>
		</fieldset>
		
		<div>
          	<span id="userPicture" style="width: 600px;height:300px; float:left"></span>
          
          	<span id="tradePicture" style="width: 600px;height:300px;float:left"></span>
        </div>
        
        <div>
          	<span id="rmbRechargePicture" style="width: 600px;height:300px; float:left"></span>
          
          	<span id="rmbWithdrawPicture" style="width: 600px;height:300px;float:left"></span>
        </div>
        
        
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<!-- Load javascripts at bottom, this will reduce page load time -->
<script src="/app/js/footer.js"></script>
<%--<script src="js/jquery-1.8.3.min.js"></script>--%>
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
<script type="text/javascript">
	$.ajax({
		url:'/auth/getUserPicture.do',
		type : 'POST',
		dateType : 'JSON',
		date : {
		},
		success : function (data){
			var arr_date = new Array();
			var arr_num = new Array();
			$.each(data.data,function(a,b){
				arr_date.push(b.dates);
				arr_num.push(b.id);
			});
			var myChart = echarts.init(document.getElementById('userPicture'));
			var option = {
		            title: {
		                text: '用户30天注册报表'
		            },
		            tooltip: {},
		            legend: {
		                data:['用户量']
		            },
		            xAxis: {
		                data: arr_date
		            },
		            yAxis: {},
		            series: [{
		                name: '用户量',
		                type: 'bar',
		                data: arr_num
		            }]
		        };
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
		},
		error :function (data){
			
		}
	});
</script>
<script type="text/javascript">
	$.ajax({
		url:'/auth/getTradePicture.do',
		type : 'POST',
		dateType : 'JSON',
		date : {
		},
		success : function (data){
			
			var arr_date = new Array();
			var arr_num = new Array();
			$.each(data.data,function(a,b){
				arr_date.push(b.dates);
				arr_num.push(b.sum_price);
			});
			var myChart = echarts.init(document.getElementById('tradePicture'));
			var option = {
					title: {
		                text: '30天交易详情'
		            },
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'cross',
				            crossStyle: {
				                color: '#999'
				            }
				        }
				    },
				    toolbox: {
				        feature: {
				            dataView: {show: true, readOnly: false},
				            magicType: {show: true, type: ['line', 'bar']},
				            restore: {show: true},
				            saveAsImage: {show: true}
				        }
				    },
				    legend: {
				        data:['蒸发量']
				    },
				    xAxis: [
				        {
				            type: 'category',
				            data: arr_date,
				            axisPointer: {
				                type: 'shadow'
				            }
				        }
				    ],
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        {
				            name:'交易金额',
				            type:'bar',
				            data:arr_num
				        }
				    ]
				};
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
		},
		error :function (data){
			
		}
	});
</script>
<script type="text/javascript">
	$.ajax({
		url:'/auth/getRmbRechargePicture.do',
		type : 'POST',
		dateType : 'JSON',
		date : {
		},
		success : function (data){
			var arr_date = new Array();
			var arr_num = new Array();
			$.each(data.data,function(a,b){
				arr_date.push(b.attr3);
				arr_num.push(b.recharge_price);
			});
			var myChart = echarts.init(document.getElementById('rmbRechargePicture'));
			var option =  {
				    title: {
				        text: 'RMB充值'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    toolbox: {
				        feature: {
				            saveAsImage: {}
				        }
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: arr_date
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        {
				            name:'充值',
				            type:'line',
				            stack: '金额',
				            data:arr_num
				        }
				    ]
				};
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
		},
		error :function (data){
			
		}
	});
</script>
<script type="text/javascript">
	$.ajax({
		url:'/auth/getRmbWithdrawPicture.do',
		type : 'POST',
		dateType : 'JSON',
		date : {
		},
		success : function (data){
			var arr_date = new Array();
			var arr_num = new Array();
			$.each(data.data,function(a,b){
				arr_date.push(b.attr3);
				arr_num.push(b.price);
			});
			var myChart = echarts.init(document.getElementById('rmbWithdrawPicture'));
			var option =  {
				    title: {
				        text: 'RMB提现'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    toolbox: {
				        feature: {
				            saveAsImage: {}
				        }
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: arr_date
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        {
				            name:'充值',
				            type:'line',
				            stack: '金额',
				            data:arr_num
				        }
				    ]
				};
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
		},
		error :function (data){
			
		}
	});
</script>
</body>
<!-- END BODY -->
</html>
