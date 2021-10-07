<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Nutras Fitness & Nutrition Bootstrap 5 Template</title>
    <meta name="robots" content="noindex, follow" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/Nutras-template/assets/images/favicon.ico">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Nutras-template/assets/css/vendor/plugins.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Nutras-template/assets/css/style.min.css">
<style>


</style>

</head>

<body>

    <div class="main-wrapper">

        <!-- Header Section Start -->
        <div class="header section">

            <!-- Header Main Start -->
            <div class="header-main">
                <div class="container">

                    <!-- Header Wrapper Start -->
                    <div class="header-wrapper">

                        <!-- Header Logo Start -->
                        <div class="header-logo">
                            <a href="<%= request.getContextPath() %>/Nutras-template/index.html"><img src="<%= request.getContextPath() %>/Nutras-template/assets/images/logo.png" alt="Logo"></a>
                        </div>
                        <!-- Header Logo End -->

                        <!-- Header Menu Start -->
                        <div class="header-menu d-none d-lg-flex">

                            <ul class="nav-menu">
                                 <li>
                                 <a href="#">資訊</a>
                                   <ul class="sub-menu">
                                        <li><a href="<%= request.getContextPath() %>/Nutras-template/courses.html">關於我們</a></li>
                                        <li><a href="<%= request.getContextPath() %>/Nutras-template/-courses.html">最新消息</a></li>
                                        <li><a href="#">常見問題</a>
										<li><a href="#">健康專欄</a>
										<li><a href="#">聯絡我們</a>
                                    </ul>
                                </li>
                                <li><a href="<%= request.getContextPath() %>/Nutras-template/about.html">專屬營養師</a></li>
                                <li>
                                    <a href="#">營養課程</a>
                                    <ul class="sub-menu">
                                        <li><a href="<%= request.getContextPath() %>/Nutras-template/courses.html">尋找課程</a></li>
                                        <li><a href="<%= request.getContextPath() %>/Nutras-template/my-courses.html">我的課程</a></li>
                                        <li><a href="#">課程頁面</a>
                                            <ul class="sub-menu">
                                                <li><a href="<%= request.getContextPath() %>/Nutras-template/courses-details-left-sidebar.html">課程頁面1</a></li>
                                                <li><a href="<%= request.getContextPath() %>/Nutras-template/courses-details-right-sidebar.html">課程頁面2</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">營養商城 </a>
                                    <ul class="sub-menu">
                                        <li><a href="<%= request.getContextPath() %>/Nutras-template/after-enroll.html">After Enroll</a></li>
                                        <li><a href="<%= request.getContextPath() %>/Nutras-template/courses-admin.html">Instructor Dashboard (Course List)</a></li>

                                    </ul>
                                </li>
                                <li>
                                    <a href="#">一般會員專區</a>
                                    <ul class="sub-menu">
                                        <li>
                                            <a href="#">一般會員</a>
                                            <ul class="sub-menu">
                                                <li><a href="<%= request.getContextPath() %>/Nutras-template/blog-grid.html">Blog Grid</a></li>
                                                <li><a href="<%= request.getContextPath() %>/Nutras-template/blog-left-sidebar.html">Blog Left Sidebar</a></li>
                                                <li><a href="<%= request.getContextPath() %>/Nutras-template/blog-right-sidebar.html">Blog Right Sidebar</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#">營養師會員</a>
                                            <ul class="sub-menu">
                                                <li><a href="<%= request.getContextPath() %>/Nutras-template/blog-details-left-sidebar.html">Blog Details Left Sidebar</a></li>
                                                <li><a href="<%= request.getContextPath() %>/Nutras-template/blog-details-right-sidebar.html">Blog Details Right Sidebar</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li><a href="<%= request.getContextPath() %>/Nutras-template/contact.html">營養師會員專區</a></li>
                            </ul>


                        </div>
                        <!-- Header Menu End -->

                        <!-- Header Toggle Start -->
                        <div class="header-toggle d-lg-none">

                            <a class="btn btn-secondary btn-hover-primary" href="<%= request.getContextPath() %>/Nutras-template/login.html">Sign Up</a>

                            <a href="#" class="menu-toggle">
                                <span></span>
                                <span></span>
                                <span></span>
                            </a>
                        </div>
                        <!-- Header Toggle End -->

                    </div>
                    <!-- Header Wrapper End -->

                </div>
            </div>
            <!-- Header Main End -->

        </div>
        <!-- Header Section End -->


           

         <!-- Footer Start -->
        <div class="section footer-section">

            <!-- Footer Widget Section Start -->
            <div class="footer-widget-section section-padding">

                <img class="shape-01 animation-rotate" src="<%= request.getContextPath() %>/Nutras-template/assets/images/shape/shape-9.png" alt="Shape">

                <div class="container">

                    <!-- Footer Widget Wrapper Start -->
                    <div class="footer-widget-wrapper">
                        <div class="row">
 
                            <div class="col-lg-8">

                                <!-- Footer Widget Start -->
                                <div class="widget-wrapper">

                                    <!-- Footer Widget Start -->
                                    <div class="footer-widget">
                                        <h3 class="footer-widget-title">All Courses</h3>

                                        <ul class="widget-link">
                                            <li><a href="#">Daily Exercise</a></li>
                                            <li><a href="#">Find Your Balance</a></li>
                                            <li><a href="#">Personal Program</a></li>
                                            <li><a href="#">Natural Process</a></li>
                                            <li><a href="#">Immune System</a></li>
                                            <li><a href="#">Gives You Energy</a></li>
                                        </ul>
                                    </div>
                                    <!-- Footer Widget End -->

                                    <!-- Footer Widget Start -->
                                    <div class="footer-widget">
                                        <h3 class="footer-widget-title">Help Links</h3>

                                        <ul class="widget-link">
                                            <li><a href="#">Privacy Policy</a></li>
                                            <li><a href="#">Discussion</a></li>
                                            <li><a href="#">Terms & Conditions</a></li>
                                            <li><a href="#">Customer Support</a></li>
                                            <li><a href="#">Course FAQ’s</a></li>
                                            <li><a href="#">Online Classes</a></li>
                                        </ul>
                                    </div>
                                    <!-- Footer Widget End -->

                                    <!-- Footer Widget Start -->
                                    <div class="footer-widget">
                                        <h3 class="footer-widget-title">Opening Hours</h3>

                                        <ul class="widget-link">
                                            <li><a href="#">Mon-Fri: 9 AM – 6 PM</a></li>
                                            <li><a href="#">Saturday: 9 AM – 4 PM</a></li>
                                            <li><a href="#">Sunday: Closed</a></li>
                                        </ul>

                                        <div class="widget-location">
                                            <h3 class="footer-widget-title">Location</h3>

                                            <p>176 W street name, New <br> York, NY 10014</p>
                                        </div>
                                    </div>
                                    <!-- Footer Widget End -->

                                </div>
                                <!-- Footer Widget End -->

                            </div>
                        </div>
                    </div>
                    <!-- Footer Widget Wrapper End -->

                </div>

                <img class="shape-02 animation-rotate" src="<%= request.getContextPath() %>/Nutras-template/assets/images/shape/shape-9.png" alt="Shape">

            </div>
            <!-- Footer Widget Section End -->

            <!-- Footer Copyright Section Start -->
            <div class="footer-copyright">
                <div class="container">

                    <!-- Copyright Wrapper Start -->
                    <div class="copyright-wrapper">

                        <div class="copyright-text">
                            <p>&copy; 2021 <span> Nutras </span> Made with <i class="icofont-heart-alt"></i> by <a href="" target="_blank">HasThemes</a></p>
                        </div>

                        <div class="copyright-link">
                            <a href="#">Terms of Service</a>
                            <a href="#">Privacy Policy</a>
                            <a href="#">Sitemap</a>
                            <a href="#">Security</a>
                        </div>

                    </div>
                    <!-- Copyright Wrapper End -->

                </div>
            </div>
            <!-- Footer Copyright Section End -->

        </div>
        <!-- Footer End -->

        <!--Back To Start-->
        <a href="#" class="back-to-top">
            <i class="icofont-simple-up"></i>
        </a>
        <!--Back To End-->

    </div>

    <!-- JS
    ============================================ -->

    <!-- Modernizer & jQuery JS -->
    <script src="<%= request.getContextPath() %>/Nutras-template/assets/js/vendor/modernizr-3.11.2.min.js"></script>
    <script src="<%= request.getContextPath() %>/Nutras-template/assets/js/vendor/jquery-3.5.1.min.js"></script>

    <!-- Bootstrap JS -->
    <!-- <script src="assets/js/plugins/popper.min.js"></script>
    <script src="assets/js/plugins/bootstrap.min.js"></script> -->

    <!-- Plugins JS -->
    <!-- <script src="assets/js/plugins/swiper-bundle.min.js"></script>
    <script src="assets/js/plugins/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/plugins/jquery.nice-select.min.js"></script>
    <script src="assets/js/plugins/video-playlist.js"></script>
    <script src="assets/js/plugins/ajax-contact.js"></script> -->

    <!--====== Use the minified version files listed below for better performance and remove the files listed above ======-->
    <script src="<%= request.getContextPath() %>/Nutras-template/assets/js/plugins.min.js"></script>


    <!-- Main JS -->
    <script src="<%= request.getContextPath() %>/Nutras-template/assets/js/main.js"></script>

</body>

</html>