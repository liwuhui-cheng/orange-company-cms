<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.css">
<script type="text/javascript"  src="/resource/js/jquery/jquery.min.js"></script>
<script type="text/javascript"  src="/resource/bootstrap/js/bootstrap.js"></script> 
<script type="text/javascript"  src="/resource/js/jqueryvalidate/jquery.validate.js"></script>
<script type="text/javascript"  src="/resource/js/jqueryvalidate/localization/messages_zh.js"></script>


<title> orange-个人中心</title>
<style type="text/css">
    
    .menuselected li{
        background:#4DFFFF;
    }

    .mymenuselected li:hover {
      
      background:#FFA042;
    }

</style>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light "  style="background:#79FF79"> 

    <div>
         <ul class="nav">
         	<li class="nav-item nav-link"> <img width="50px" height="55px" src="/resource/images/t2.jpg"> </li>
            <li class="nav-item nav-link">山隔壁还是山</li>
            <li class="nav-item nav-link">你成仙</li>
            <li class="nav-item nav-link">我替你留守人间</li>
         
         </ul>
    
    </div>
    
</nav>
    
  
	<div class="container row">
		<div class="col-md-2" style="margin-top:20px ; border-right:solid 2px"> 
			<!-- 左侧的菜单 -->
			<ul class="nav flex-column mymenuselected">
			
			     <li class="nav-item ">
			    <a  class="nav-link active" href="/index">首页</a>
			     </li>
				  <li class="nav-item">
				    <a class="nav-link active" href="#"  onclick="showWork($(this),'/admin/article?status=0&pageNum=1')">文章管理</a>
				  </li>
			
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/commten')"  >评论管理</a>
				  </li>
				  
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/link')"  >友情链接管理</a>
				  </li>
				  
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/user')"  >用户管理</a>
				  </li>
				  
				
				</ul>	
		</div>
		
		<div class="col-md-10" id="workcontent"> 
			
		</div>	
	</div>
  
  


<!-- 尾开始 -->
<nav class="nav fixed-bottom justify-content-center "  style="background:#FF9966" height="50px"> 
	   风华模样你落落大方,坐在桥上听你唱歌
</nav>
<script type="text/javascript">
 
 function showWork(obj,url){
	$(".mymenuselected li").removeClass("menuselected");
	obj.parent().addClass("menuselected")		
	$("#workcontent").load(url);
	
 }
 
</script>


</body>
</html>