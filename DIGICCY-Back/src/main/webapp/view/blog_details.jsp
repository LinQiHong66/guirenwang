<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>Blog Details Page</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
    <link href="assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <link href="css/style_responsive.css" rel="stylesheet" />
    <link href="css/style_default.css" rel="stylesheet" id="style_color" />

    <link href="assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
<!-- BEGIN HEADER -->
<%@include file="header.jsp" %>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div id="container" class="row-fluid">
    <!-- BEGIN SIDEBAR -->
    <%@include file="left.jsp" %>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div id="main-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN THEME CUSTOMIZER-->
                    <div id="theme-change" class="hidden-phone">
                        <i class="icon-cogs"></i>
                        <span class="settings">
                            <span class="text">Theme:</span>
                            <span class="colors">
                                <span class="color-default" data-style="default"></span>
                                <span class="color-gray" data-style="gray"></span>
                                <span class="color-purple" data-style="purple"></span>
                                <span class="color-navy-blue" data-style="navy-blue"></span>
                            </span>
                        </span>
                    </div>
                    <!-- END THEME CUSTOMIZER-->
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        Blog Details
                        <small>blog details sample page</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="javascript:;"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="javascript:;">Blog</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="javascript:;">Blog Details</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span9">
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-edit"></i> Blog Details</h4>
                            <span class="tools">
                           <a href="javascript:;" class="icon-chevron-down"></a>
                           <a href="javascript:;" class="icon-remove"></a>
                           </span>
                        </div>
                        <div class="widget-body">
                            <div class="row-fluid blog">

                                <div class="span12">
                                    <h2>
                                        Suspendisse et mauris nisi, in semper justo.
                                    </h2>
                                    <p>
                                        By <a href="javascript:;">Vector Lab</a>
                                    </p>
                                    <img src="img/blog/blog-big-img.jpg" alt="">
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <ul>
                                                <li>22 May 2013 12:30 pm</li>
                                            </ul>

                                        </div>
                                        <div class="span6 ">
                                            <ul class="show-right">
                                                <li><a href="javascript:;"><i class="icon-comments-alt"></i> 55 Comments</a></li>
                                                <li><a href="javascript:;"><i class="icon-heart"></i> 342 Likes</a></li>
                                                <li><a href="javascript:;"><i class="icon-share"></i> 34 Shares</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div>
                                        <p>
                                            Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus.
                                        </p>
                                        <p>
                                            Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum.
                                        </p>
                                        <blockquote>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit posuere erat a ante.</p>
                                            <small>Someone famous <cite title="Source Title">Source Title</cite></small>
                                        </blockquote>
                                        <p>
                                            Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus. Nam sed arcu non tellus fringilla fringilla ut vel ipsum. Aliquam ac magna metus.
                                        </p>
                                    </div>
                                    <div class="media">
                                        <h3>Comments</h3>
                                        <hr>
                                        <a class="pull-left" href="#">
                                            <img class="media-object" src="img/avatar1.jpg" alt="">
                                        </a>
                                        <div class="media-body">
                                            <h4 class="media-heading">Mosaddek Hossain </h4>
                                            <span>2 hours ago | <a href="#">Reply</a></span>
                                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                                            <hr>
                                            <!-- Nested media object -->
                                            <div class="media">
                                                <a class="pull-left" href="#">
                                                    <img class="media-object" src="img/avatar2.jpg" alt="">
                                                </a>
                                                <div class="media-body">
                                                    <h4 class="media-heading">Dulal Khan </h4>
                                                    <span>6 hours ago | <a href="#">Reply</a></span>
                                                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                                                </div>
                                            </div>
                                            <!--end media-->
                                            <hr>
                                            <div class="media">
                                                <a class="pull-left" href="#">
                                                    <img class="media-object" src="img/avatar1.jpg" alt="">
                                                </a>
                                                <div class="media-body">
                                                    <h4 class="media-heading">Mosaddek Hossain </h4>
                                                    <span>9 days ago | <a href="#">Reply</a></span>
                                                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                                                </div>
                                            </div>
                                            <!--end media-->

                                        </div>
                                    </div>
                                    <div class="media">
                                        <a class="pull-left" href="#">
                                            <img class="media-object" src="img/avatar2.jpg" alt="">
                                        </a>
                                        <div class="media-body">
                                            <h4 class="media-heading">Dulal Khan </h4>
                                            <span>July 5,2013 | <a href="#">Reply</a></span>
                                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                                        </div>
                                    </div>
                                    <!--end media-->
                                    <hr>
                                    <!--begin post comments-->
                                    <div class="post-comment">
                                        <h3>Post Comments</h3>
                                        <form>
                                            <label>Name</label>
                                            <input type="text" class="span7">
                                            <label>Email <span class="color-red">*</span></label>
                                            <input type="text" class="span7">
                                            <label>Message</label>
                                            <textarea rows="8" class="span10"></textarea>
                                            <p><button type="submit" class="btn btn-info">Post Comment</button></p>
                                        </form>
                                    </div>
                                    <!--end post comments-->
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="span3">
                    <div class="blog-side-bar">
                        <h2>categories</h2>
                        <ul class="unstyled">
                            <li><a href="#"><i class="icon-chevron-right"></i>  Animals</a></li>
                            <li><a href="#"><i class="icon-chevron-right"></i> Landscape</a></li>
                            <li><a href="#"><i class="icon-chevron-right"></i> Portait</a></li>
                            <li><a href="#"><i class="icon-chevron-right"></i> Wild Life</a></li>
                            <li><a href="#"><i class="icon-chevron-right"></i> Video</a></li>
                        </ul>
                        <hr>
                        <h2>latest blog post</h2>
                        <div class="space15"></div>
                        <div class="row-fluid">
                            <div class="span3">
                                <img src="img/blog/blog-thumb-1.jpg" alt="">
                            </div>
                            <div class="span9">
                                <h5>
                                    <a href="javascript:;">02 MAY 2013</a>
                                </h5>
                                <p>Nam sed arcu non tellus
                                    fringilla fringilla ut vel ipsum.</p>
                            </div>
                        </div>
                        <div class="space10"></div>
                        <div class="row-fluid">
                            <div class="span3">
                                <img src="img/blog/blog-thumb-2.jpg" alt="">
                            </div>
                            <div class="span9">
                                <h5>
                                    <a href="javascript:;">02 MAY 2013</a>
                                </h5>
                                <p>Nam sed arcu non tellus
                                    fringilla fringilla ut vel ipsum.</p>
                            </div>
                        </div>
                        <div class="space10"></div>
                        <div class="row-fluid">
                            <div class="span3">
                                <img src="img/blog/blog-thumb-3.jpg" alt="">
                            </div>
                            <div class="span9">
                                <h5>
                                    <a href="javascript:;">02 MAY 2013</a>
                                </h5>
                                <p>Nam sed arcu non tellus
                                    fringilla fringilla ut vel ipsum.</p>
                            </div>
                        </div>
                        <hr>
                        <h2>popular tags</h2>
                        <ul class="unstyled tag">
                            <li><a href="#">Admin Panel</a></li>
                            <li><a href="#"> Dashboard Theme</a></li>
                            <li><a href="#"> Admin</a></li>
                            <li><a href="#"> Control Panel</a></li>
                            <li><a href="#"> UI</a></li>
                            <li><a href="#"> Web Design</a></li>
                            <li><a href="#"> UIX</a></li>
                            <li><a href="#"> Blog</a></li>
                        </ul>
                        <hr>
                        <h2>ARCHIVES</h2>
                        <ul class="unstyled">
                            <li><a href="#"><i class="icon-chevron-right"></i> January 2013</a></li>
                            <li><a href="#"><i class="icon-chevron-right"></i> February 2013 </a></li>
                            <li><a href="#"><i class="icon-chevron-right"></i> March 2013</a></li>
                            <li><a href="#"><i class="icon-chevron-right"></i> April 2013</a></li>
                            <li><a href="#"><i class="icon-chevron-right"></i> May 2013</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- END PAGE CONTENT-->
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<!-- Load javascripts at bottom, this will reduce page load time -->
<script src="js/jquery-1.8.3.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="js/jquery.blockui.js"></script>
<!-- ie8 fixes -->
<!--[if lt IE 9]>
<script src="js/excanvas.js"></script>
<script src="js/respond.js"></script>
<![endif]-->
<script type="text/javascript" src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>
<script src="js/scripts.js"></script>
<script>
    jQuery(document).ready(function() {
        // initiate layout and plugins
        App.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
