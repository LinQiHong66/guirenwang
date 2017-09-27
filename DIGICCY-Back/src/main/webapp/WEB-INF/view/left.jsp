<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/app/js/ajax/menu.js"></script>
<!DOCTYPE html>
    <!-- BEGIN SIDEBAR -->
    <div id="sidebar" class="nav-collapse collapse">

        <div class="sidebar-toggler hidden-phone"></div>

        <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
        <div class="navbar-inverse">
            <form class="navbar-search visible-phone">
                <input type="text" class="search-query" placeholder="Search" />
            </form>
        </div>
        <!-- END RESPONSIVE QUICK SEARCH FORM -->
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="sidebar-menu" id="menu">
            <%--<li class="has-sub">--%>
                <%--<a href="javascript:;" class="">--%>
                    <%--<span class="icon-box"> <i class="icon-dashboard"></i></span> Dashboard--%>
                    <%--<span class="arrow"></span>--%>
                <%--</a>--%>
                <%--<ul class="sub">--%>
                    <%--<li><a class="" href="index.jsp">Dashboard 1</a></li>--%>
                    <%--<li><a class="" href="index.jsp">Dashboard 2</a></li>--%>

                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="has-sub">--%>
                <%--<a href="javascript:;" class="">--%>
                    <%--<span class="icon-box"> <i class="icon-book"></i></span> UI Elements--%>
                    <%--<span class="arrow"></span>--%>
                <%--</a>--%>
                <%--<ul class="sub">--%>
                    <%--<li><a class="" href="view/ui_elements_general.jsp">General</a></li>--%>
                    <%--<li><a class="" href="view/ui_elements_buttons.jsp">Buttons</a></li>--%>

                    <%--<li><a class="" href="view/ui_elements_tabs_accordions.jsp">Tabs & Accordions</a></li>--%>
                    <%--<li><a class="" href="view/ui_elements_typography.jsp">Typography</a></li>--%>
                    <%--<li><a class="" href="view/tree_view.jsp">Tree View</a></li>--%>
                    <%--<li><a class="" href="view/nestable.jsp">Nestable List</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="has-sub">--%>
                <%--<a href="javascript:;" class="">--%>
                    <%--<span class="icon-box"><i class="icon-cogs"></i></span> Components--%>
                    <%--<span class="arrow"></span>--%>
                <%--</a>--%>
                <%--<ul class="sub">--%>
                    <%--<li><a class="" href="view/calendar.jsp">Calendar</a></li>--%>
                    <%--<li><a class="" href="view/data_table.jsp">Data Table</a></li>--%>
                    <%--<li><a class="" href="view/grids.jsp">Grids</a></li>--%>
                    <%--<li><a class="" href="view/charts.jsp">Visual Charts</a></li>--%>
                    <%--<li><a class="" href="view/messengers.jsp">Conversations</a></li>--%>
                    <%--<li><a class="" href="view/gallery.jsp"> Gallery</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="has-sub">--%>
                <%--<a href="javascript:;" class="">--%>
                    <%--<span class="icon-box"><i class="icon-tasks"></i></span> Form Stuff--%>
                    <%--<span class="arrow"></span>--%>
                <%--</a>--%>
                <%--<ul class="sub">--%>
                    <%--<li><a class="" href="view/form_layout.jsp">Form Layouts</a></li>--%>
                    <%--<li><a class="" href="view/form_component.jsp">Form Components</a></li>--%>
                    <%--<li><a class="" href="view/form_wizard.jsp">Form Wizard</a></li>--%>
                    <%--<li><a class="" href="view/form_validation.jsp">Form Validation</a></li>--%>
                    <%--<li><a class="" href="view/dropzone.jsp">Dropzone File Upload </a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="has-sub">--%>
                <%--<a href="javascript:;" class="">--%>
                    <%--<span class="icon-box"><i class="icon-fire"></i></span> Icons--%>
                    <%--<span class="arrow"></span>--%>
                <%--</a>--%>
                <%--<ul class="sub">--%>
                    <%--<li><a class="" href="view/font_awesome.jsp">Font Awesome</a></li>--%>
                    <%--<li><a class="" href="view/glyphicons.jsp">Glyphicons</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="has-sub">--%>
                <%--<a href="javascript:;" class="">--%>
                    <%--<span class="icon-box"><i class="icon-map-marker"></i></span> Maps--%>
                    <%--<span class="arrow"></span>--%>
                <%--</a>--%>
                <%--<ul class="sub">--%>
                    <%--<li><a class="" href="view/maps_google.jsp"> Google Maps</a></li>--%>
                    <%--<li><a class="" href="view/maps_vector.jsp"> Vector Maps</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="has-sub">--%>
                <%--<a href="javascript:;" class="">--%>
                    <%--<span class="icon-box"><i class="icon-file-alt"></i></span> Sample Pages--%>
                    <%--<span class="arrow"></span>--%>
                <%--</a>--%>
                <%--<ul class="sub">--%>
                    <%--<li class="active"><a class="" href="view/blank.jsp">Blank Page</a></li>--%>
                    <%--<li><a class="" href="view/sidebar_closed.jsp">Sidebar Closed Page</a></li>--%>
                    <%--<li><a class="" href="view/coming_soon.jsp">Coming Soon</a></li>--%>
                    <%--<li><a class="" href="view/blog.jsp">Blog</a></li>--%>
                    <%--<li><a class="" href="view/about_us.jsp">About Us</a></li>--%>
                    <%--<li><a class="" href="view/contact_us.jsp">Contact Us</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="has-sub">--%>
                <%--<a href="javascript:;" class="">--%>
                    <%--<span class="icon-box"><i class="icon-glass"></i></span> Extra--%>
                    <%--<span class="arrow"></span>--%>
                <%--</a>--%>
                <%--<ul class="sub">--%>
                    <%--<li><a class="" href="view/lock.jsp">Lock Screen</a></li>--%>
                    <%--<li><a class="" href="view/profile.jsp">Profile</a></li>--%>
                    <%--<li><a class="" href="view/invoice.jsp">Invoice</a></li>--%>
                    <%--<li><a class="" href="view/pricing_tables.jsp">Pricing Tables</a></li>--%>
                    <%--<li><a class="" href="view/faq.jsp">FAQ</a></li>--%>
                    <%--<li><a class="" href="view/404.jsp">404 Error</a></li>--%>
                    <%--<li><a class="" href="view/500.jsp">500 Error</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li><a class="" href="login.html"><span class="icon-box"><i class="icon-user"></i></span> Login Page</a></li>--%>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
<!-- END BODY -->
</html>
