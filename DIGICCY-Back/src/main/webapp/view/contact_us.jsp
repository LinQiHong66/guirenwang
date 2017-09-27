<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 11:18
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
    <title>Contact Us</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <link href="css/style_responsive.css" rel="stylesheet" />
    <link href="css/style_default.css" rel="stylesheet" id="style_color" />

    <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
    <link href="assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
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
                        Contact Us
                        <small>contact us sample page</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">Sample Pages</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">Contact Us</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div id="page">
                <div class="row-fluid">
                    <div class="span12">
                        <!-- BEGIN BASIC PORTLET-->
                        <div class="widget">
                            <div class="widget-title">
                                <h4><i class="icon-reorder"></i>Contact Us</h4>
                                <span class="tools">
									<a href="javascript:;" class="icon-chevron-down"></a>
									<a href="javascript:;" class="icon-remove"></a>
									</span>
                            </div>
                            <div class="widget-body">
                                <div id="gmap_basic" class="gmaps"></div>
                                <div class="space20"></div>
                                <!--BEGIN CONTACT US-->
                                <div class="contact-us">
                                    <h3>Our Contacts</h3>
                                    <div class="row-fluid">
                                        <div class="span4">
                                            <h4>Location</h4>
                                            <p>Jonathon Smith <br>
                                                House 16, Road 12, Sector 4<br>
                                                Uttara Model Town, Dhaka 1230<br>
                                                Phone: +966 1 000000<br>
                                                Fax : 1234 5678 909</p>
                                        </div>
                                        <div class="span4">
                                            <h4>Online</h4>
                                            <p> <strong>Email :</strong> info@vectorlab.com <br>
                                                <strong>Support :</strong> support@vectorlab.com<br>
                                                <strong>Live Chat :</strong> live@vectorlab.com<br>
                                                <strong>Skype :</strong> skype@vectorlab.com<br>
                                                <strong>Fax :</strong> 1234 5678 909</p>
                                        </div>
                                        <div class="span4">
                                            <h4>Social Network</h4>
                                            <p>
                                                <strong>Facebook :</strong> www.facebook.com/vectorlab1<br>
                                                <strong>Twitter :</strong> www.twitter.com/vectorlab1<br>
                                                <strong>Google + :</strong> www.googleplux.com/vectorlab1<br>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="space20"></div>
                                    <div class="row-fluid">
                                        <div class="feedback">
                                            <h3>Feedback</h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore</p>
                                            <div class="space20"></div>

                                            <form class="form-inline ">
                                                <div class="control-group">
                                                    <input type="text" class="span12" placeholder="Name">
                                                </div>
                                                <div class="control-group ">
                                                    <input type="text" class="span6 one-half" placeholder="Email">
                                                    <input type="text" class="span6" placeholder="Phone">
                                                </div>
                                                <div class="control-group">
                                                    <textarea rows="5" class="span12" placeholder="Message"></textarea>
                                                </div>
                                                <div class="text-center">
                                                    <button type="submit" class="btn btn-info ">Submit</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <!--END CONTACT US-->
                            </div>
                        </div>
                        <!-- END BASIC PORTLET-->
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
<script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>
<script src="assets/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="assets/fancybox/source/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script src="js/scripts.js"></script>
<script src="js/gmaps.js"></script>
<script src="js/demo.gmaps.js"></script>
<script>
    jQuery(document).ready(function() {
        // initiate layout and plugins
        App.init();
        DemoGMaps.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>