<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  href="/resource/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="/resource/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/localization/messages_zh.min.js"></script>

<title>小小路飞呀!${article.title}</title>
</head>
<body>
     	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> </nav>
	   
    <div  class="container">
          <div  class="row justify-content-center" >
           <h3 style="color:#FF0000;">${acticle.title}</h3>   
          </div>
          <div  class="row  justify-content-center">
          <h5>
                                  作者：       ${acticle.user.username} &nbsp;&nbsp;&nbsp;
                                  栏目：      ${acticle.channel.name}&nbsp;&nbsp;&nbsp;
                                  分类：    ${acticle.category.name}&nbsp;&nbsp;&nbsp;
                                  发表时间：<fmt:formatDate value="${acticle.created}"  pattern="yyyy-MM-dd"/> 
         </h5>
            <%--  <a href="/article/complain?articleId=${acticle.id}">投诉</a> --%>
            
         
          </div>
        
          <div style="margin-top:30px">
            ${acticle.title}
          
          </div>
          
          <div>
          <nav aria-label="...">
					  <ul class="pagination">
					    <li class="page-item ">
					      <a class="page-link" href="#" tabindex="-1" >上一篇</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="#">下一篇</a>
					    </li>
					  </ul>
					</nav>
		</div>
		<div>
			<!-- 发布评论 -->
			<textarea rows="5" cols="500" id="commentText">
			 
			</textarea>
			
			
			<input type="button" class="btn btn-primary" onclick="addComment()" value="发表评论">
	
		</div>
		<div  id="comment">
			  
		</div>
	</div>
	
	<script type="text/javascript">
     
	function gopage(page) {
		
		showComment(page);
		
		
	}
	
	function showComment(page) {
		
		$("#comment").load("/article/comments?id=${acticle.id}&page="+page)
	}
	
	$(document).ready(function(){
		//显示第一页的评论
		showComment(1);
	});
	
	function addComment(){
		alert($("#commentText").val());
		
		 $.post("/article/postcomment",
				{articleId:'${acticle.id}',content:$("#commentText").text()},
			function(msg){
				if(msg.code==1){
					alert('发布成功')
					$("#commentText").val('');
					showComment(1);
				}else{
					alert(msg.error)
				}
				
			},
			"json") 
	} 
	
	
	
	</script>

</body>
</html>