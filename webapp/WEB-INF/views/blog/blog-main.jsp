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
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">				 
		            <h4>${viewPost.title}</h4>
		            <p>${viewPost.content}</p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList}" var="post">
						<li><a href="/jblog/blog/${ownerID}?categoryNo=${param.categoryNo}&postNo=${post.no}">${post.title}</a> <span>${post.regDate}</span>
						</li>				
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<c:choose>
					<c:when test="${empty blogVo.logo}">
						<td><img
							src="${pageContext.servletContext.contextPath}/assets/images/spring-logo.jpg"></td>
					</c:when>
					<c:otherwise>
						<td><img
							src="${pageContext.servletContext.contextPath}${blogVo.logo}"></td>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
		      <li><a href="/jblog/blog/${ownerID}?categoryNo=-1">미분류</a></li>     
			<c:forEach items="${categoryList}" var="category">
				<li><a href="/jblog/blog/${ownerID}?categoryNo=${category.no}">${category.name}</a></li>			
			</c:forEach>
			</ul>
		</div>

		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>