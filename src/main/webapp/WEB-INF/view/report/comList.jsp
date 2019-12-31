<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>标题</td>
			<td>时间</td>
			<td>类型</td>
			<td>证据</td>
		</tr>
		<c:forEach items="${list}"  var="p">
			<tr>
                 <td>${p.title}</td>
                 <td>${p.article_id }</td>
                 <td>
                 ${p.complaintype }
                 </td>

			</tr>

		</c:forEach>


	</table>
</body>
</html>