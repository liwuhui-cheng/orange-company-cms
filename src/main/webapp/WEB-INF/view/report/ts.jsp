<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="/resource/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
   function comList(id) {
	alert(id);
	   location="/article/comList?id="+id;
	   
}
  function tsNum1() {
	location="/article/tsNum1" ;
}

  function tsNum2() {
		location="/article/tsNum2" ;
	}

</script>
<title>Insert title here</title>
</head>
<body>
	<form action="/article/ts" method="post">
		投诉类型:<select name="mh1">
			<option value="-1">-请选择-</option>
			<option value="1">涉黄</option>
			<option value="2">暴力</option>
			<option value="3">抄袭</option>
		</select> 次数大于:<input type="text" name="mh2" value="${con.mh2}">- 次数小于:<input
			type="text" name="mh3" value="${con.mh3}"> <input
			type="submit" value="查询">
		<table>
			<tr>
				<td>ID</td>
				<td>标题</td>

				<td>投诉类型</td>
				<td>投诉次数 <input type="button" onclick="tsNum1()" value="倒序">
					<input type="button" onclick="tsNum2()" value="正序">
				</td>
				<td>投诉时间</td>
				<td>投诉详情</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${com}" var="p" varStatus="i">

				<tr>
					<td>${i.index+1}</td>
					<td>${p.title}</td>

					<td>
				         <c:if test="${p.complaintype==1}">
                                                                           涉黄
                          </c:if>
                           <c:if test="${p.complaintype==2}">
                                                                      涉及暴力                                          
                          </c:if>
                           <c:if test="${p.complaintype==3}">
                                                                     抄袭内容                                    
                          </c:if>
					</td>
					<%-- <td>${p.complainnum}</td>
					<td><fmt:formatDate value="${p.created}" /></td>
					<td><input type="button" onclick="comList(${p.id})" value="详情">
					</td>
					<td><c:if test="${p.complaintype>=50}">
							<input type="button" value="禁止">
						</c:if></td>

				</tr> --%>
			</c:forEach>
			<tr>
				<td rowspan="100">
					<button name="pageNum" value="1">首页</button>
					<button name="pageNum" value="${pp.prePage==0?1:pp.prePage}">上一页</button>
					<button name="pageNum"
						value="${pp.nextPage==0?pp.pages:pp.nextPage}">下一页</button>
					<button name="pageNum" value="${pp.pages}">尾页</button>

				</td>

			</tr>

		</table>
	</form>
</body>
</html>