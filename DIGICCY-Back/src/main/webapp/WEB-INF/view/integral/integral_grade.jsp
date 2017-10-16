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
    <link href="/app/assets/toastr/toastr.css" rel="stylesheet" />
    <script src="/app/assets/toastr/toastr.min.js"></script>
    <link href="/app/assets/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <link href="/app/css/tab.css" rel="stylesheet" />
    
      <style>
    	.pagination {
		  display: inline-block;
		  padding-left: 0;
		  margin: 20px 0;
		  border-radius: 4px;
		}
		.pagination > li {
		  display: inline;
		}
		.pagination > li > a,
		.pagination > li > span {
		  position: relative;
		  float: left;
		  padding: 6px 12px;
		  margin-left: -1px;
		  line-height: 1.42857143;
		  color: #337ab7;
		  text-decoration: none;
		  background-color: #fff;
		  border: 1px solid #ddd;
		}
		.pagination > li:first-child > a,
		.pagination > li:first-child > span {
		  margin-left: 0;
		  border-top-left-radius: 4px;
		  border-bottom-left-radius: 4px;
		}
		.pagination > li:last-child > a,
		.pagination > li:last-child > span {
		  border-top-right-radius: 4px;
		  border-bottom-right-radius: 4px;
		}
		.pagination > li > a:hover,
		.pagination > li > span:hover,
		.pagination > li > a:focus,
		.pagination > li > span:focus {
		  z-index: 2;
		  color: #23527c;
		  background-color: #eee;
		  border-color: #ddd;
		}
		.pagination > .active > a,
		.pagination > .active > span,
		.pagination > .active > a:hover,
		.pagination > .active > span:hover,
		.pagination > .active > a:focus,
		.pagination > .active > span:focus {
		  z-index: 3;
		  color: #fff;
		  cursor: default;
		  background-color: #337ab7;
		  border-color: #337ab7;
		}
		.pagination > .disabled > span,
		.pagination > .disabled > span:hover,
		.pagination > .disabled > span:focus,
		.pagination > .disabled > a,
		.pagination > .disabled > a:hover,
		.pagination > .disabled > a:focus {
		  color: #777;
		  cursor: not-allowed;
		  background-color: #fff;
		  border-color: #ddd;
		}
		.pagination-lg > li > a,
		.pagination-lg > li > span {
		  padding: 10px 16px;
		  font-size: 18px;
		  line-height: 1.3333333;
		}
		.pagination-lg > li:first-child > a,
		.pagination-lg > li:first-child > span {
		  border-top-left-radius: 6px;
		  border-bottom-left-radius: 6px;
		}
		.pagination-lg > li:last-child > a,
		.pagination-lg > li:last-child > span {
		  border-top-right-radius: 6px;
		  border-bottom-right-radius: 6px;
		}
		.pagination-sm > li > a,
		.pagination-sm > li > span {
		  padding: 5px 10px;
		  font-size: 12px;
		  line-height: 1.5;
		}
		.pagination-sm > li:first-child > a,
		.pagination-sm > li:first-child > span {
		  border-top-left-radius: 3px;
		  border-bottom-left-radius: 3px;
		}
		.pagination-sm > li:last-child > a,
		.pagination-sm > li:last-child > span {
		  border-top-right-radius: 3px;
		  border-bottom-right-radius: 3px;
		}
    </style>
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
                        任务列表
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">积分奖励管理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">特权列表</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>特权列表</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <!-- 触发修改的按钮 -->
                        <div style="display: none;"><button class="btn btn-success" id="btn_update" data-toggle="modal" data-target="#myModal"></button></div>
                        <div class="widget-body">
                        
							<label for="firstname" class="col-sm-2 control-label">等级</label>
						    <input type="text" class="form-control" id="firstname" 
							 placeholder="请输入等级"> 
		                    <button type="button" onclick="ready('','');" style="margin-bottom: 8px;" class="btn btn-default">搜索</button>
                           <a onclick="addRead();"><button class="btn btn-success" id="btn_add" data-toggle="modal" data-target="#myModal" style="margin-bottom: 8px;"><i class="icon-plus icon-white"></i>&nbsp;新增</button></a>
                           <div style="height:500px;">
                            <table id="complete_table" class="table table-hover">
									<thead>
										<tr>
											<th style="width:20%">等级</th>
											<th style="width:20%">积分条件</th>
											<th style="width:20%">快速提现率</th>  
											<th style="width:20%">极速提现率</th> 
											<th style="width:20%">操作</th>
										</tr>
								  </thead>
								  <tbody id="complete_tbody">
								  </tbody>
                            </table>
                            </div>
                        </div>
                         <div style="text-align:right">
	                    <span style="margin-right:30px;">当前是：01页</span>
	                    <ul class="pagination pagination-sm" id="paging" style="vertical-align:middle;margin-right: 10px;">
				      </ul>
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

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				添加
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
				</h4>
			</div>
			<div class="modal-body">
			<form action="" id="submitForm">
			    <div class="form-group" align="center">
					
					<div class="col-sm-10">
						<label for="firstname" class="col-sm-2 control-label">等级</label>
						<input type="text" class="form-control" id="gradeValue" 
						   placeholder="请输入等级">
					</div>
				</div>
				<div class="form-group" align="center">
					<label for="lastname" class="col-sm-2 control-label">积分条件</label>
			<div align="center">
			<ul class="nav nav-pills" style="width:215px">
				<li class="dropdown all-camera-dropdown" style="float: none;line-height:0;">
				<a class="dropdown-toggle" id="symbol" data-toggle="dropdown" href="#" style="line-height:1;">
				大于等于
			</a>
			<ul class="dropdown-menu" style="float: none;width:215px;left:0;right:0;margin:auto;">
				<li onclick="getValue('#xy')"  ><a id="xy" data-toggle="tab" href="#">小于</a></li>
				<li onclick="getValue('#dy')"  ><a id="dy" data-toggle="tab" href="#">大于</a></li>
				<li onclick="getValue('#dydy')" ><a id="dydy" data-toggle="tab" href="#">大于等于</a></li>
				<li onclick="getValue('#xydy')"  ><a id="xydy" data-toggle="tab" href="#">小于等于</a></li>
				<li onclick="getValue('#dydydy')"  ><a id="dydydy" data-toggle="tab" href="#">等于</a></li>
			</ul>
			<input type="hidden" id="additional" value="10"/>
			  </li>
   		</ul>
		</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="conditionsValue" 
						   placeholder="请输入积分条件"> 
					</div>
				</div>
				<div class="form-group" align="center">
					<label for="lastname" class="col-sm-2 control-label">快速提现率</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="quicksValue" 
						   placeholder="请输入快速提现率"> 
					</div>
				</div>
				<div class="form-group" align="center">
					<label for="lastname" class="col-sm-2 control-label">快速提现率</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="speedValue" 
						   placeholder="请输入极速提现率"> 
					</div>
				</div>
	 </form>
	 		<div align="center" id="errInput" style="color: red;"></div>
			</div>
			<div class="modal-footer">
				<button id="closeW" type="button" class="btn btn-default" 
						data-dismiss="modal">关闭
				</button>
				<button type="button" id="submit"  onclick="addComplete();" class="btn btn-primary">
					提交
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- 信息删除确认 -->  
<div class="modal fade" id="delcfmModel">  
  <div class="modal-dialog">  
    <div class="modal-content message_align">  
      <div class="modal-header">  
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>  
        <h4 class="modal-title">提示信息</h4>  
      </div>  
      <div class="modal-body">  
        <p>您确认要删除吗？</p>  
      </div>  
      <div class="modal-footer">  
         <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>  
         <a  id="removeA" class="btn btn-success" data-dismiss="modal">确定</a>  
      </div>  
    </div><!-- /.modal-content -->  
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->  

<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<script src="/app/js/footer.js"></script>
<script src="/app/js/echarts.min.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/app/js/bootstrap-table/integral/integral-grade.js"></script>
</body>
<!-- END BODY -->
</html>
