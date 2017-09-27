<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../taglib.jsp"%>
<!DOCTYPE html>
<!--
Template Name: Admin Lab Dashboard build with Bootstrap v2.3.1
Template Version: 1.2
Author: Mosaddek Hossain
Website: http://thevectorlab.net/
-->
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>主页</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<script src="/app/js/header.js"></script>
<link href="/app/assets/bootstrap-table/bootstrap-table.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="/app/assets/wangEditor/dist/css/wangEditor.min.css" />


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
	<%@include file="../header.jsp"%>
	<!-- BEGIN CONTAINER -->
	<div id="container" class="row-fluid">
		<!-- BEGIN SIDEBAR -->
		<%@include file="../left.jsp"%>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div id="main-content">
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							公告管理
							<%--<small>simple form layouts</small>--%>
						</h3>
						<ul class="breadcrumb">
							<li><a href="#"><i class="icon-home"></i></a><span
								class="divider">&nbsp;</span></li>
							<li><a href="#">公告管理</a> <span class="divider">&nbsp;</span>
							</li>
							<li><a href="#">新增</a><span class="divider-last">&nbsp;</span></li>
						</ul>
					</div>
				</div>
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN  widget-->
						<div class="widget">
							<div class="widget-title">
								<h4>
									<i class="icon-reorder"></i>新增公告
								</h4>
								<span class="tools"> <a href="javascript:;"
									class="icon-chevron-down"></a> <a href="javascript:;"
									class="icon-remove"></a>
								</span>
							</div>
							<div class="widget-body form">
								<!-- BEGIN FORM-->
								<form action="#" class="form-horizontal">
									<div class="control-group">
										<label class="control-label">标题</label>
										<div class="controls">
											<input type="text" name="role.description" id="title"
												class="form-control">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">类型</label>
										<div class="controls">
											<select class="input-large m-wrap" tabindex="1" id="type">
												<option value="1" selected>公告</option>
												<option value="2">新闻</option>
												<option value="3">常见问题</option>
												<option value="4">其他</option>
											</select> <select class='input-large m-wrap' tabindex="1"
												id="nametype" name="noticeNameType" disabled="disabled">
												<!-- <option value="0" selected>请选择</option> -->
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">内容</label>
										<div class="controls">
											<textarea id="editor" placeholder="这里输入内容"
												style="height: 200px"></textarea>
										</div>
									</div>
									<div class="control-group">
										<button type="button" class="btn blue" id="add_Role"
											onclick="addNotice()">
											<i class="icon-ok"></i>保存
										</button>
									</div>
								</form>
								<!-- END FORM-->
							</div>
						</div>
						<!-- END EXTRAS widget-->
					</div>
				</div>
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<%@include file="../footer.jsp"%>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS -->
	<!-- Load javascripts at bottom, this will reduce page load time -->
	<script src="/app/js/footer.js"></script>
	<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
	<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript"
		src="/app/assets/wangEditor/dist/js/wangEditor.js"></script>
	<script>
    var editor = new wangEditor('editor');
    editor.create(); 

    $(function(){
    	$('#type').bind('change',function(a,b){
    		var type = $('#type').val();
    		var nametype = $('#nametype');
    		if(type != 4){
    			nametype.attr('disabled', 'disabled');
    			$('#nametype option[value=0]').attr('selected','selected');
    		}else{
    			nametype.removeAttr('disabled');
    		}
    	});
    	$
		.ajax({
			url : '/notice/getNameType.do',
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.code == 100) {
					var result = data.data;
					if (result != 'none') {
						var nameType = $('#nametype');
						var s = '<option value="0" selected>请选择</option>';
						$
								.each(
										result,
										function(a, b) {
											s+='<option value="'+b.id+'">'+b.name+'</option>';
										});
						nameType.html(s);
					}
				}
			}
		});
    });
    
    function addNotice() {
        var text = editor.$txt.html();
        var title = $("#title").val();
        var type = $("#type").val();
        var nameType = $('#nametype').val();
        $.ajax({
            url:"/notice/addNotice.do",
            type: "post",
            dataType: "json",
            data:{
                title:title,
                type:type,
                context:text,
                noticeNameType:nameType
            },
            success: function (msg) {
                if(msg.code == "100"){
                	alert("增加成功");
                	window.location.href = "/notice/gotoNotice.do";
				} else {
					alert('添加失败');
					return false;
				}
            }
        });
    }

</script>

	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
