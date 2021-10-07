<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.c_favorite.model.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.video.model.*"%>
<%
	CourseVO courseVO = (CourseVO) request.getAttribute("courseVO");
	CourseService courseSvc = new CourseService();
	List<CourseVO> list = courseSvc.getAll();//list是給分頁用的\
	pageContext.setAttribute("list", list);
	MemberVO memberVO1 = (MemberVO) session.getAttribute("memberVO1");//取得登陸的會員物件
	C_FavoriteVO c_favoriteVO = null;
	C_FavoriteService courseFavoriteSvc = new C_FavoriteService();
	if (memberVO1!=null){
	c_favoriteVO = courseFavoriteSvc.getOneCourseFavorite(memberVO1.getMno(), courseVO.getCno());
	pageContext.setAttribute("c_favoriteVO", c_favoriteVO);
	}
	Integer cno = courseVO.getCno();
	pageContext.setAttribute("courseVO", courseVO);
	VideoService videoSvc = new VideoService();

	List<VideoVO> videoList = videoSvc.getAll(cno);//課程編號取得影片list
	long videoCount = videoList.stream().count();//取得影片數

	long videoTime =0;//取得總時長
	for(int i = 0 ;i<videoList.size();i++){
		videoTime += videoList.get(i).getVlength().getTime()/1000+28800;
	}
	Timestamp totalTime = new Timestamp(videoTime*1000-28800000);



	pageContext.setAttribute("totalTime", totalTime);	 
	pageContext.setAttribute("videoCount", videoCount);	  
	pageContext.setAttribute("videoList", videoList);
%>

<jsp:useBean id="VideoSvc" scope="page"
	class="com.video.model.VideoService" />
<%
	com.dietician.model.DieticianService dietsvc = new com.dietician.model.DieticianService();
	pageContext.setAttribute("dietsvc", dietsvc);
%>
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->



		<div class="main-wrapper">


			<!-- Header Toggle Start -->
			<div class="header-toggle d-lg-none">

				<a class="btn btn-secondary btn-hover-primary" href="login.html">Sign
					Up</a> <a href="#" class="menu-toggle"> <span></span> <span></span>
					<span></span>
				</a>
			</div>
			<!-- Header Toggle End -->


			

			<!-- Overlay Start -->
			<div class="overlay"></div>
			<!-- Overlay End -->

			<!-- Courses Details Start -->
			<div class="section section-padding-02">
				<div class="container">

					<div class="courses-details-wrapper">
						<div class="row gx-xxl-5">
							<div class="col-lg-8">

								<!-- Courses Details Start -->
								<div class="courses-details">

									<div class="courses-details-images">
										<img
											src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"
											width='640px' height='480px' />
									</div>

									<h2 class="title">${courseVO.cname}</h2>

									<div class="courses-details-admin">
										<div class="admin-author">
											<div class="author-thumb">
												<!-- 											營養師照片 -->
												<img src="" alt="Author">
											</div>
											<div class="author-content">
												<p class="name">
													By: <a href="#">${dietsvc.findByPrimaryKey(courseVO.dno).dname}</a>
												</p>
												<span class="enroll"><span>${courseVO.quantity}</span>
													Enrolled Students</span>
											</div>
										</div>
										<div class="admin-rating">
											<span class="rating-count"><fmt:formatNumber
													type="number" maxFractionDigits="1"
													value="${courseVO.ctotalScore/courseVO.ctotalPeople}" /></span> 
												<span
												class="rating-star"> <span class="rating-bar"
												style="width:${courseVO.ctotalScore/courseVO.ctotalPeople*20}%;"></span>
											</span> <span class="rating-text">評價人數:${courseVO.ctotalPeople}</span>
										</div>
									</div>

									<!-- Courses Details Tab Start -->
									<div class="courses-details-tab">

										<!-- Details Tab Menu Start -->
										<div class="details-tab-menu">
											<ul class="nav justify-content-center">
												<li><button class="active" data-bs-toggle="tab"
														data-bs-target="#description">課程介紹</button></li>
												<li><button data-bs-toggle="tab"
														data-bs-target="#instructors">講師</button></li>
												<li><button data-bs-toggle="tab"
														data-bs-target="#reviews">課程評價</button></li>
											</ul>
										</div>
										<!-- Details Tab Menu End -->

										<!-- Details Tab Content Start -->
										<div class="details-tab-content">
											<div class="tab-content">
												<div class="tab-pane fade show active" id="description">

													<!-- Tab Description Start -->
													<div class="tab-description">
														<div class="description-wrapper">
															<h3 class="tab-title">課程介紹:</h3>
															<p>${courseVO.cintroduction}</p>
															<br> <br> <br> <br> <br>

														</div>

													</div>
													<!-- Tab Description End -->

												</div>
												<div class="tab-pane fade" id="instructors">
													<h3>講師姓名:${dietsvc.findByPrimaryKey(courseVO.dno).dname}</h3>
													<br>
													<p>講師經歷:${dietsvc.findByPrimaryKey(courseVO.dno).exp}</p>
													<p>講師專長:${dietsvc.findByPrimaryKey(courseVO.dno).prof}</p>
													<!-- 												講師介紹 -->
													<br>
													<br>

												</div>
												<div class="tab-pane fade" id="reviews">

													<!-- Tab Reviews Start -->
													<div class="tab-reviews">
														<h3 class="tab-title">Student Reviews:</h3>

														<div class="reviews-wrapper reviews-active">
															<div class="swiper-container">
																<div class="swiper-wrapper">

																	<!-- Single Reviews Start 評價-->
																	<div class="single-review swiper-slide">
																		<div class="review-author">
																			<div class="author-thumb">
																				<img src=""
																					alt="Author"> <i class="icofont-quote-left"></i>
																			</div>
																			<div class="author-content">
																				<h4 class="name">Sara Alexander</h4>
																				<span class="designation">Product Designer,
																					USA</span> <span class="rating-star"> <span
																					class="rating-bar" style="width: 80%;"></span>
																				</span>
																			</div>
																		</div>
																		<p>Lorem Ipsum has been the industry's standard
																			dummy text since the 1500 when unknown printer took a
																			galley of type and scrambled to make type specimen
																			book has survived not five centuries but also the
																			leap into electronic type and book.</p>
																	</div>
																	<!-- Single Reviews End -->

																	

																</div>
																<!-- Add Pagination -->
																<div class="swiper-pagination"></div>
															</div>
														</div>

														<div class="reviews-btn">
															<button type="button"
																class="btn btn-dark btn-hover-primary"
																data-bs-toggle="modal" data-bs-target="#reviewsModal">Write
																A Review</button>
														</div>

														<!-- Reviews Form Modal Start -->
														<div class="modal fade" id="reviewsModal">
															<div class="modal-dialog modal-dialog-centered">
																<div class="modal-content">
																	<div class="modal-header">
																		<h5 class="modal-title">Add a Review</h5>
																		<button type="button" class="btn-close"
																			data-bs-dismiss="modal" aria-label="Close"></button>
																	</div>
																	
<!-- 																	評價用 -->
																	<!-- Reviews Form Start -->
																	<div class="modal-body reviews-form">
																		<form action="<%=request.getContextPath()%>/c_order/c_order.do" method = "get">
																			<div class="row">
																				<div class="col-md-6">
																					<!-- Single Form Start -->
																					<div class="single-form">
																					
																				
																						<p>${memberVO1.mname}</p>
																																											</div>
																					<!-- Single Form End -->
																				</div>
																				<div class="col-md-12">
																					<!-- Single Form Start -->
																					<div class="reviews-rating">
																						<label>Rating</label>
																						<ul name="rating" id="rating" class="rating">
																							<li class="star" title='Poor' data-value='1'><i
																								class="icofont-star"></i></li>
																							<li class="star" title='Poor' data-value='2'><i
																								class="icofont-star"></i></li>
																							<li class="star" title='Poor' data-value='3'><i
																								class="icofont-star"></i></li>
																							<li class="star" title='Poor' data-value='4'><i
																								class="icofont-star"></i></li>
																							<li class="star" title='Poor' data-value='5'><i
																								class="icofont-star"></i></li>
																						</ul>
																					</div>
																					<!-- Single Form End -->
																				</div>
																				<div class="col-md-12">
																					<!-- Single Form Start -->
																					<div class="single-form">
																						<textarea placeholder="Write your comments here"></textarea>
																					</div>
																					<!-- Single Form End -->
																				</div>
																				<div class="col-md-12">
																					<!-- Single Form Start -->
																					<div class="single-form">
																						<button class="btn btn-dark btn-hover-primary">Submit
																							Review</button>
																			
																					</div>
																							
																					<!-- Single Form End -->
																				</div>
																				
																			</div>
																		</form>
																	</div>
																
<!-- 																	評價結束 -->
																
																</div>
																		
															</div>
														
														</div>
														<!-- Reviews Form Modal End -->
														<br>
														<br>
													</div>
													<!-- Tab Reviews End -->

												</div>
											</div>
										</div>
										<!-- Details Tab Content End -->

									</div>
									<!-- Courses Details Tab End -->

								</div>
								<!-- Courses Details End -->

							</div>
							<div class="col-lg-4">
								<!-- Courses Details Sidebar Start -->
								<div class="sidebar">

									<!-- Sidebar Widget Information Start -->
									<div class="sidebar-widget widget-information">
										<div class="info-price">
											<span class="price">$${courseVO.cprice}</span>
										</div>
										<div class="info-list">
											<ul>
												<li><i class="icofont-man-in-glasses"></i> <strong>講師</strong>
													<span>${dietsvc.findByPrimaryKey(courseVO.dno).dname}</span></li>
												<li><i class="icofont-clock-time"></i> <strong>總時間</strong>
													<span><fmt:formatDate value="${totalTime}"
																		pattern="HH小時mm分鐘ss秒 " /></span></li>
												<li><i class="icofont-ui-video-play"></i> <strong>課程數</strong>
													<span><%=videoCount %></span></li>

											</ul>
										</div>
										<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/c_order/c_order.do">
									
											<input type="hidden" name="cno" value="${courseVO.cno}">
										  	<input type="hidden" name="action" value="ADD">
										  	 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
										  	 	<div class="info-btn">
											 <input type="submit" name="Submit" value="加入購買清單" class="btn btn-secondary btn-hover-primary">
										</div>
										</form>
										<%if(c_favoriteVO==null){%>
										<div class="info-btn">
										<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/c_favorite/c_favorite.do">
										<input type="hidden" name="cno" value="${courseVO.cno}">
										  	<input type="hidden" name="action" value="add" id="addvalue">
										  	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											 <input type="submit" name="Submit"  class="btn btn-secondary btn-hover-primary" value="加入收藏"  id="addbtn">
										</FORM>
										</div>
										<%}else{ %>
										<div class="info-btn">
										<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/c_favorite/c_favorite.do">
										<input type="hidden" name="cno" value="${courseVO.cno}">
										  	<input type="hidden" name="action" value="delete" id="delvalue">
										  	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											 <input type="submit" name="Submit"  class="btn btn-secondary btn-hover-primary" value="取消收藏"  id="delbtn">
										</FORM>
										</div>
										<% }%>
											<div class="info-btn">
										<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/c_order/c_order.do">
										<input type="hidden" name="cno" value="${courseVO.cno}">
										  	<input type="hidden" name="action" value="CART">
											 <input type="submit" name="Submit"  class="btn btn-secondary btn-hover-primary" value="前往結帳">
										</FORM>
									</div>
									<!-- Sidebar Widget Information End -->

								</div>
								<!-- Courses Details Sidebar End -->
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- Courses Details End -->
		<!--Back To Start-->
			<a href="#" class="back-to-top"> <i class="icofont-simple-up"></i>
			</a>
			<!--Back To End-->

		</div>


	</div>
	<!-- JS
    ============================================ -->
<!-- <script> -->
<!-- // $(documnet).ready(function(){ -->
<!-- // 	$("#addbtn").click(function(){ -->
<!-- // 		$("#addbtn").val("取消收藏"); -->
<!-- // 		$("#addbtn").attr("id","delbtn"); -->
<!-- // 		$("#addval").val("delete"); -->
<!-- // 		$("#addval").attr("id","delval"); -->
<!-- // 		alert("成功加入收藏"); -->
<!-- // 	}); -->
<!-- // 	$("#delbtn").click(function(){ -->
<!-- // 		$("#delval").val("加入收藏"); -->
<!-- // 		$("#delval").attr("id","addbtn"); -->
<!-- // 		$("#delval").val("add"); -->
<!-- // 		$("#delval").attr("id","addval"); -->
<!-- // 		alert("已取消收藏"); -->
<!-- // 	}); -->
<!-- // }); -->
<!-- </script> -->
<script>
				 var addbtn= document.getElementById("addbtn")
					addbtn.addEventListener('click',function(){
					alert("成功加入收藏");
			
				});
				var delbtn = document.getElementById("delbtn")
				delbtn.addEventListener('click',function(){
					alert("已移除收藏");
			
				});
	</script>



	<!-- 服務很好先做一個置中的div(結束) -->

	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>