<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/article/report" method="post">
	     
	     投诉的内容标题:${acticle.title}
	     <input type="hidden"  name="article_id"  value="${acticle.id}" >
	     <input type="hidden"  name="user_id"  value="${acticle.user.id}" >
		<table>
			<tr>
				<td>
				    <input type="checkbox"  name="complaintype"    value="1">涉及黄色
				     <input type="checkbox"  name="complaintype"   value="2">涉及暴力
				     <input type="checkbox"  name="complaintype"   value="3">涉及国家安全
				     <input type="checkbox"  name="complaintype"   value="4">涉及违背宗教
				     <input type="checkbox"  name="complaintype"   value="5">抄袭
				     <input type="checkbox"  name="complaintype"   value="6">其它
				
				</td>
				

			</tr>
			
			<tr>
			 <td>
			         证据url:<input type="text"  name="urlip">
			 </td>
			</tr>

            
		</table>


  <button>提交</button>

	</form>



</body>
</html>