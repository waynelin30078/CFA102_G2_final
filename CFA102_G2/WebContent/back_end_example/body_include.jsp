<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
        <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand  最左上角的圖片-->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">
                    <h3>後台管理</h3>
                </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">


            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading 說明 不可選取-->


            <!-- Nav Item - Pages Collapse Menu 第一個主選單-->
 <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span style=font-size:19px>管理員管理</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">管理員系統管理</h6>
                        <a class="collapse-item" href="<%= request.getContextPath() %>/back_end/admin/blank_select_page.jsp">管理員帳號管理</a>
                    
                    </div>
                </div>
            </li>
            <hr class="sidebar-divider">
            <!-- Nav Item - Utilities Collapse Menu 第二個-->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span style=font-size:19px>前台管理</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">前台系統管理</h6>
                        <a class="collapse-item" href="back_end/news/select_news_page.jsp">最新訊息管理</a>
                        <a class="collapse-item" href="<%=request.getContextPath()%>/artice/select_artice_page">健康專欄管理</a>

                    </div>
                </div>
            </li>

            <!-- Divider 分隔-->
            <hr class="sidebar-divider">



            <!-- Nav Item - Pages Collapse Menu 第三個主選單-->
            <li class="nav-item ">
                <a class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true"
                    aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span style=font-size:18px font-weight=normal>後台管理</span>
                </a>
                <div id="collapsePages" class="collapse show" aria-labelledby="headingPages"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">後台系統管理</h6>
                             <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/product/select_page.jsp">商品管理</a>
                         <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/p_order/select_page.jsp">訂單管理</a>
                          <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/p_order_detail/select_page.jsp">訂單明細管理</a>
                        <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/promotion/select_page.jsp">優惠活動管理</a>
                        <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/promotion_detail/select_page.jsp">優惠活動明細管理</a>
                        <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/course/select_page.jsp">課程管理</a>
           
                    </div>
                </div>
            </li>
            <hr class="sidebar-divider">

            <!-- Heading 說明文字-->
            <div class="sidebar-heading">
                <h5>會員管理</h5>
            </div>
            <!-- Nav Item - Charts選單 -->

            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/back_end/member/allMemberList.jsp">
                    <i class="fas fa-fw fa-table"></i>
                    <span style=font-size:19px>一般會員管理</span></a>
            </li>

            <!-- Nav Item - Tables選單 -->
            
           <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/back_end/dietician/blank_select_pageDietician.jsp">
                    <i class="fas fa-fw fa-table"></i>
                    <span style=font-size:19px>營養師會員管理</span></a>
            </li>

            <hr class="sidebar-divider">

            <li class="nav-item dropdown">
                <a style=font-size:19px class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    檢舉管理
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">課程檢舉審核</a>
                    <a class="dropdown-item" href="#">營養師檢舉審核</a>
                </div>
            </li>

            <!-- Divider 分隔線-->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) 往內縮-->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar 最上面的搜尋部分-->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Topbar Navbar 右邊上面三個-->
                    <ul class="navbar-nav ml-auto">






                        <!-- 右上的直線 -->
                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">登出/登入</span>
                                <img class="img-profile rounded-circle" src="<%= request.getContextPath() %>/back_end_example/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">

                                <a class="dropdown-item" href="<%= request.getContextPath() %>/back_end_home/index.jsp">
                                    <i class="fas fa-sign-in-alt fa-sm fa-fw mr-2 text-gray-400"></i>

                                    登入
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/back_end_home/index.jsp" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    登出
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->