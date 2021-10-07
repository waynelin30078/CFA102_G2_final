
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>





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
                            <a href="<%= request.getContextPath() %>/front_end/free/home.jsp"><img src="<%= request.getContextPath() %>/Nutras-template/assets/images/logo.png" alt="Logo"></a>
                        </div>
                         <div > 
                            <a href="<%= request.getContextPath() %>/front_end/protected/chat/member_chat_box_icon.jsp"><div style="width: 100px; height: 50px; cursor:auto"> </div></a>
					</div>
					<!-- Header Logo End -->

					<!-- Header Menu Start -->
					<div class="header-menu d-none d-lg-flex">

						<ul class="nav-menu">
							<li><a href="#">資訊</a>
								<ul class="sub-menu">
									<li>
                                        <li><a href="<%= request.getContextPath() %>/front_end/free/news/listAllNews.jsp">最新消息</a></li>
									<li>
										<a href="<%=request.getContextPath()%>/Nutras-template/-courses.html">健康專欄</a></li>
								</ul></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/free/dietician/select_dietician01.jsp">專屬營養師</a></li>
							<li><a href="#">營養課程</a>
								<ul class="sub-menu">
									<li><a
										href="<%=request.getContextPath()%>/front_end/free/course/course_front_page.jsp">尋找課程</a></li>
									

								</ul></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/free/product/listAllProduct.jsp">營養商城
							</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/protected/diary/diary_calendar_page.jsp">飲食日記
							</a></li>
							<li><a href="#">一般會員專區</a>
								<ul class="sub-menu">
									<li><a
										href="<%=request.getContextPath()%>/front_end/protected/member/member_info.jsp">管理會員資料</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/front_end/protected/appointment/search_appointment.jsp">查詢諮詢時間</a>
									</li>
									 <li><a href="<%= request.getContextPath() %>/front_end/protected/p_order/listAllP_orderByMno.jsp">我的訂單</a></li>
                                                <li><a href="<%= request.getContextPath() %>/front_end/protected/p_favorite/listAllP_favoriteByMno.jsp">我的商品收藏</a></li>
								<li><a
										href="<%=request.getContextPath()%>/course/course.do?action=mycourse">我的課程</a></li>
									<li><a
										href="<%=request.getContextPath()%>/front_end/protected/course_favorite/mycourse_favorite.jsp">我的課程收藏</a></li>
								
									<li><a
										href="<%=request.getContextPath()%>/front_end/free/member/memberLogin.jsp">登入</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/memberLoginHandler.do?action=signOut">登出</a>
									</li>
								</ul></li>
							<li><a href="#">營養師會員專區</a>
								<ul class="sub-menu">
									<li><a
										href="<%=request.getContextPath()%>/front_end/protected/dietician/one_dietician_page01.jsp">管理營養師會員資料</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/dorder.do?action=show_list_Order">查看訂閱會員</a>
									</li>

									<li><a
										href="<%=request.getContextPath()%>/front_end/protected/chat/dietician_chat_page.jsp">線上諮詢室</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/front_end/protected/course/addCourse.jsp">新增課程</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/front_end/protected/course/listAllCourse.jsp ">管理課程</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/front_end/free/dietician/login_dietician.jsp">登入</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/DieticianLoginHandler.do?action=logout">登出</a>
									</li>
								</ul></li>
						</ul>


					</div>
					<!-- Header Menu End -->

					<!-- Header Toggle Start -->
					<div class="header-toggle d-lg-none">

						<a class="btn btn-secondary btn-hover-primary"
							href="<%=request.getContextPath()%>/Nutras-template/login.html">Sign
							Up</a> <a href="#" class="menu-toggle"> <span></span> <span></span>
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
</div>