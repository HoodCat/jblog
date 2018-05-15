<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<c:choose>
		<c:when test='${menu == "basic"}'>
			<ul class="admin-menu">
				<li class="selected">기본설정</li>
				<li><a href="/jblog/blog/${ownerID}/admin/category">카테고리</a></li>
				<li><a href="/jblog/blog/${ownerID}/admin/write">글작성</a></li>
			</ul>
		</c:when>
		<c:when test='${menu == "category"}'>
			<ul class="admin-menu">
				<li><a href="/jblog/blog/${ownerID}/admin/basic">기본설정</a></li>
				<li class="selected">카테고리</li>
				<li><a href="/jblog/blog/${ownerID}/admin/write">글작성</a></li>
			</ul>
		</c:when>
		<c:when test='${menu == "write"}'>
			<ul class="admin-menu">
				<li><a href="/jblog/blog/${ownerID}/admin/basic">기본설정</a></li>
				<li><a href="/jblog/blog/${ownerID}/admin/category">카테고리</a></li>
				<li class="selected">글작성</li>
			</ul>
		</c:when>
	</c:choose>
</div>