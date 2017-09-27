<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 11:03
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
    <title>About Us Page</title>
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
                        About Us
                        <small>about us page sample</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">Sample Pages</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">About Us</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12">
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-user"></i>About Us</h4>
                            <span class="tools">
                           <a href="javascript:;" class="icon-chevron-down"></a>
                           <a href="javascript:;" class="icon-remove"></a>
                           </span>
                        </div>
                        <div class="widget-body">
                            <!--BEGIN ABOUT US-->
                            <div class="about-us">
                                <h3>Here is an example of how you can decorate your about us page. <br> Here you will able to present your team list and their skills.</h3>
                                <div class="row-fluid">
                                    <div class="span6">
                                        <h4>Our Philosophy</h4>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Donec ut volutpat metus. Aliquam tortor lorem, fringilla tempor dignissim at, pretium et arcu. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis.</p>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Donec ut volutpat metus. Aliquam tortor lorem, fringilla tempor dignissim at, pretium et arcu. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis.</p>
                                    </div>
                                    <div class="span6">
                                        <h4>Mission & Vision</h4>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Donec ut volutpat metus. Aliquam tortor lorem, fringilla tempor dignissim at, pretium et arcu. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis.</p>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Donec ut volutpat metus. Aliquam tortor lorem, fringilla tempor dignissim at, pretium et arcu. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis.</p>
                                    </div>
                                </div>
                                <div class="space20"></div>
                                <div class="row-fluid">
                                    <div class="span6">
                                        <h4>Our Skills</h4>
                                        <h5><strong>Web Design</strong></h5>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                        <h5><strong>Wordpress</strong></h5>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                        <h5><strong>Jquery</strong></h5>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                        <h5><strong>HTML</strong></h5>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                        <h5><strong>CSS</strong></h5>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                        <h5><strong>PHP</strong></h5>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                        <h5><strong>Content Management</strong></h5>
                                        <p>Etiam eget mi enim, non ultricies nisi voluptatem, illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo nemo enim ipsam voluptatem.</p>
                                    </div>
                                    <div class="span6">
                                        <div class="space20"></div>
                                        <div class="space20"></div>
                                        <div class="space5"></div>
                                        <h5>Web Design <span class="pull-right">35%</span></h5>
                                        <div class="progress progress-striped progress-danger">
                                            <div class="bar" style="width: 35%;"></div>
                                        </div>
                                        <h5>Wordpress <span class="pull-right">40%</span></h5>
                                        <div class="progress progress-striped">
                                            <div class="bar" style="width: 40%;"></div>
                                        </div>
                                        <h5>Web Design <span class="pull-right">35%</span></h5>
                                        <div class="progress progress-striped">
                                            <div class="bar" style="width: 35%;"></div>
                                        </div>
                                        <h5>Jquery<span class="pull-right">55%</span></h5>
                                        <div class="progress progress-striped progress-success">
                                            <div class="bar" style="width: 55%;"></div>
                                        </div>
                                        <h5>HTML<span class="pull-right">85%</span></h5>
                                        <div class="progress progress-striped progress-warning">
                                            <div class="bar" style="width: 85%;"></div>
                                        </div>
                                        <h5>CSS<span class="pull-right">67%</span></h5>
                                        <div class="progress progress-striped progress-danger">
                                            <div class="bar" style="width: 67%;"></div>
                                        </div>
                                        <h5>PHP <span class="pull-right">45%</span></h5>
                                        <div class="progress progress-striped">
                                            <div class="bar" style="width: 45%;"></div>
                                        </div>
                                        <h5>Content Management<span class="pull-right">50%</span></h5>
                                        <div class="progress progress-striped progress-success">
                                            <div class="bar" style="width: 50%;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--END ABOUT US-->
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-group"></i>Our Team</h4>
                            <span class="tools">
                           <a href="javascript:;" class="icon-chevron-down"></a>
                           <a href="javascript:;" class="icon-remove"></a>
                           </span>
                        </div>
                        <div class="widget-body">
                            <div class="about-us">
                                <h3>Here is an example of how you can decorate your about us page. <br> Here you will able to present your team list and their skills.</h3>
                                <div class="row-fluid">
                                    <div class="span4">
                                        <div class="team-member">
                                            <img src="img/team/team-1.jpg" alt="">
                                            <h3>Jonathan Smith</h3>
                                            <span>Chief Executive Officer</span>
                                            <ul class="unstyled">
                                                <li><a href="#"><i class=" icon-facebook-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-twitter-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-linkedin-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-google-plus-sign"></i></a></li>
                                            </ul>
                                            <p>
                                                Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Donec ut volutpat metus. Aliquam tortor lorem, fringilla tempor dignissim at, pretium et arcu. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.
                                            </p>
                                            <p>Totam rem aperiam, eaque ipsa quae ab illo inventore veritatis.
                                            </p>
                                        </div>
                                    </div>
                                    <div class="span4">
                                        <div class="team-member">
                                            <img src="img/team/team-2.jpg" alt="">
                                            <h3>Kelly Bizz</h3>
                                            <span>Web Developer</span>
                                            <ul class="unstyled">
                                                <li><a href="#"><i class=" icon-facebook-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-twitter-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-linkedin-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-google-plus-sign"></i></a></li>
                                            </ul>
                                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Donec ut volutpat metus. </p>
                                            <p>Aliquam tortor lorem, fringilla tempor dignissim at, pretium et arcu. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis.
                                            </p>
                                        </div>
                                    </div>
                                    <div class="span4">
                                        <div class="team-member">
                                            <img src="img/team/team-3.jpg" alt="">
                                            <h3>Cinthya Gomez</h3>
                                            <span>graphic & Web designer</span>
                                            <ul class="unstyled">
                                                <li><a href="#"><i class=" icon-facebook-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-twitter-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-linkedin-sign"></i></a></li>
                                                <li><a href="#"><i class=" icon-google-plus-sign"></i></a></li>
                                            </ul>
                                            <p>
                                                Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Donec ut volutpat metus. Aliquam tortor lorem, fringilla tempor dignissim at, pretium et arcu. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.
                                            </p>
                                            <p>Totam rem aperiam, eaque ipsa quae ab illo inventore veritatis.
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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