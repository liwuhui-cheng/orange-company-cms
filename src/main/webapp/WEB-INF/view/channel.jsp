<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  href="/resource/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="/resource/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/localization/messages_zh.min.js"></script>
<style type="text/css">
 body {
   font-family: PingFang SC,Hiragino Sans GB,Microsoft YaHei,WenQuanYi Micro Hei,Helvetica Neue,Arial,sans-serif;
  font-size: 17px;
 }
  
  .myactive{
    
    background:#33FF66!important;
    
  }
.menu {
	display: block;
	width: 110px;
	height: 40px;
	line-height: 40px;
	text-align: center;
	color: #444;
	border-radius: 4px;
	margin-bottom: 2px;
	transition-property: color,background-color;
	 font-size: 22px;
}

.menu:hover {
    animation-name: hvr-back-pulse;
    animation-duration: .2s;
    animation-timing-function: linear;
    animation-iteration-count: 1;
    background-color: 
	#ed4040;
	color:
	    #fff;
}

.ex {
		overflow: hidden;
		text-overflow:ellipsis;
		white-space: nowrap;
	}
</style>

<title>橙子头条咨询</title>
</head>
<body>
 <jsp:include page="common/header.jsp"></jsp:include>

<div class="container-fluid"  style="margin-top:26px">
    <div class="row">
        <!--左侧栏目 -->
       <div class="col-md-2">
              <div>万里阳光号</div>
              <div>
                  <ul class="nav flex-column">
                   <c:forEach  items="${channels}" var="channel">
                         <li class="nav-item">
						  <a class="nav-link menu ${channelId==channel.id?'myactive':''}" href="/channel?channelId=${channel.id}"> ${channel.name} </a>
                         </li>
                         
                   </c:forEach>
                  </ul>
                
              </div>
       </div>
          
        <!-- 中间 内容列表-->
       <div class="col-md-7">
          
     <div>
				<ul class="nav nav-pills">
				 	  <li class="nav-item">
						    <a class="nav-link ${catId==0?'active':''}" href="/channel?channelId=${channelId}" >全部</a>
				       </li>
					  <c:forEach items="${catgoris}" var="category" >
						  <li class="nav-item">
						    <a class="nav-link ${catId==category.id?'active':''}"  href="/channel?channelId=${channelId}&catId=${category.id}">${category.name}</a>
						  </li>
					  </c:forEach>
					  
					</ul>
			</div>
          <!-- 文章列表 -->
          <div>
          <div  style="margin-top:25px">
                <c:forEach items="${articlePage.list}" var="article">
                  <div  class="row">
                       <div  class="col-md-3" style="margin-top:15px">
                         <img width="200px" height="150px" src="/pic/${article.picture}"
                         onerror="this.src='/resource/images/1464850154893.jpg'"
                         class="rounded"  style="border-radius:12px!important;"
                         >
                       
                       </div>
                       <div class="col-md-9">
                          <a href="/article/detail?id=${article.id}" target="_blank">${article.title}</a>
                          <br>
                                                                          作者: ${article.user.username}
                          <br>
                                                                          栏目:<a> ${article.channel.name}</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                          分类: <a>${article.category.name}</a>  
                       </div>
                  </div>
                </c:forEach>
          </div>
       </div>
       
      	<!-- 分页开始 -->
			<div class="row justify-content-center" style="margin-top:20px">
				<nav aria-label="Page navigation example" >
					  <ul class="pagination ">
					  
					    <li class="page-item">
					      <a class="page-link" href="/channel?channelId=${channelId}&catId=${catId}&page=${articlePage.pageNum-1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    
					    <c:forEach begin="1" end="${articlePage.pages}" varStatus="index">
					    	<li class="page-item"><a class="page-link" href="/channel?channelId=${channelId}&catId=${catId}&page=${index.index}"> ${index.index}</a></li>
					    </c:forEach>
					    
					    <li class="page-item">
					      <a class="page-link" href="/channel?channelId=${channelId}&catId=${catId}&page=${articlePage.pageNum+1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    
					  </ul>
					</nav>
			</div>
			<!-- 分页结束 -->
       
    </div>
    
    
      <!-- 右侧 -->
       <div class="col-md-3">
       
            <div class="card">
					  <div class="card-header">
					        <h4 style="color:red;">最新文章</h4>
					  </div>
					  <div class="card-body">
					      <ol>
					     	<c:forEach items="${lastArticle}" var="article" varStatus="index">
					     		<li class="ex"> ${index.index+1}. <a href="/article/detail?id=${article.id}" target="_blank" >${article.title}</a></li>
					     	</c:forEach>
					     	
					     </ol>
					  </div>
				</div>	
				
					
			  <div class="card" style="margin-top:50px">
					  <div class="card-header">
					    公告
					  </div>
					  <div class="card-body">
					     <ul>
					     	<li>新时代“一国两制”事业，习近平这样擘画蓝图</li>
					     	<li>中国的一个超大工程，投资2000亿，预计能在下半年彻底完成</li>
					     	<li>中国十大河流，你知道几个，长江是中国第一长、世界第三长大河！</li>
					     </ul>
					  </div>
				</div>			
          
       
       
       </div>
    
</div>
</div>
<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>