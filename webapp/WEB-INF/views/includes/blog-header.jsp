<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="header">
	<a href="/jblog/blog/${ownerID}">
		<h1>${blogVo.title }</h1>
	</a>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="/jblog/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/jblog/user/logout">로그아웃</a></li>
				<c:if test="${ownerID == authUser.id }">
					<li><a href="/jblog/blog/${ownerID}/admin/basic">블로그 관리</a></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
</div>