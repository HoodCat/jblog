<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
			<c:import url="/WEB-INF/views/includes/admin-navigator.jsp"/>
				<!-- <ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="">카테고리</a></li>
					<li><a href="">글작성</a></li>
				</ul> -->
				<form action="${pageContext.servletContext.contextPath}/blog/${ownerID}/admin/modify" method="post"  enctype="multipart/form-data">
					<table class="admin-config">
						<tr>
							<td class="t">블로그 제목</td>
							<td><input type="text" size="40" name="title" required></td>
						</tr>
						<tr>
							<td class="t">로고이미지</td>
							<%-- <td><img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg"></td> --%>
							<c:choose>
								<c:when test="${empty blogVo.logo}">
									<td><img src="${pageContext.servletContext.contextPath}/assets/images/spring-logo.jpg"></td>
								</c:when>
								<c:otherwise>
									<td><img src="${pageContext.request.contextPath}${blogVo.logo}"></td>
								</c:otherwise>
							</c:choose>

						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td><input type="file" name="logo-file"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td class="s"><input type="submit" value="기본설정 변경"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>