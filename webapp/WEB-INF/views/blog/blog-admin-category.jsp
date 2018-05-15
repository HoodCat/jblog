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
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script type="text/javascript">

$(function() {
    $("#add").click(
        function() {
            $.ajax({
                'url' : '${pageContext.servletContext.contextPath}/api/category/add',
                'type' : 'post',
                'data' : {
                    'name' : $('#name').val(),
                    'description' : $('#description').val(),
                    'blogNo' : '${blogVo.no}'
                },
                'dataType' : 'JSON',
                'success' : function(response) {
                    $('.admin-cat *').children('tr:not(:first)').remove();
                    var str = '<tr>';
                    var length = response.length
                    console.log(response);
                    $.each(response, function(i) {
                        str += '<td>'
                                + (length - i)
                                + '</td><td>'
                                + response[i]['name']
                                + '</td><td>'
                                + response[i]['cntPost']
                                + '</td><td>'
                                + response[i]['description']
                                + "</td><td><a href='javascript:removeCategory(" + response[i]['no'] + ")'>"
                                + '<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>';
                        str += '</tr>';
                    });
                    $('.admin-cat').append(str);
                },
                'error' : function(xhr, status, e) {
                    console.error(status + "+" + e);
                }
        });
    });
});
</script>
<script type="text/javascript">
function removeCategory(no) {
    $.ajax({
        'url':'${pageContext.servletContext.contextPath}/api/category/remove',
        'type' : 'post',
        'data' : {'no': no},
        'dataType' : 'JSON',
        'success' : function(response) {
            $('.admin-cat *').children('tr:not(:first)').remove();
            var str = '<tr>';
            var length = response.length
            console.log(response);
            $.each(response, function(i) {
                str += '<td>'
                        + (length - i)
                        + '</td><td>'
                        + response[i]['name']
                        + '</td><td>'
                        + response[i]['cntPost']
                        + '</td><td>'
                        + response[i]['description']
                        + "</td><td><a href='javascript:removeCategory(" + response[i]['no'] + ")'>"
                        + '<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>';
                str += '</tr>';
            });
            $('.admin-cat').append(str);
        },
        'error' : function(xhr, status, e) {
            console.error(status + "+" + e);
        }
    });
};
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-navigator.jsp" />
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:set var="length" value="${categoryList.size()}" />
					<c:forEach items="${categoryList}" var="entry" varStatus="status">
						<tr>
							<td>${length - status.index}</td>
							<td>${entry["name"]}</td>
							<td>${entry["cntPost"]}</td>
							<td>${entry["description"]}</td>
							<td><a href='javascript:removeCategory(${entry["no"]})'>
							<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="name" type="text" name="name"
							required="required"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input id="description" type="text" name="description"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input id="add" type="submit" value="카테고리 추가"></td>
					</tr>
				</table>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>