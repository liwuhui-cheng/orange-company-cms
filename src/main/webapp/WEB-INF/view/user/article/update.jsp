<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/localization/messages_zh.js"></script>

<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>






<h2 style="color:red;">修改文章</h2>

<form  name="articleform" id="articleform">
    
    <input  type="hidden"     name="id"  value="${article.id}">

	<div class="form-group">
		<label for="exampleFormControlInput1">标题</label> <input type="text"
			class="form-control" id="title"  name="title"   value="${article.title}" placeholder="请输入文章标题">
	</div>

	<div class="form-group">
		<label for="channel"> 栏目</label>
		 <select class="form-control"
			id="channel" name="channelId" >
			<option value="0">请选择</option>
            <c:forEach items="${channels}" var="cat">
            
              
              	<!-- 下拉框的回显 -->	
      		   <option value="${cat.id}" ${article.channelId==cat.id?"selected":""}>${cat.name}</option>	
           
            </c:forEach>
		</select>
	</div>

	<div class="form-group">
		<label for="category">分类</label> <select class="form-control"
			id="category" name="categoryId">
			<option value="0">请选择</option>
			
		</select>
	</div>


	<div class="form-group">
		<label for="exampleFormControlFile1">文章图片</label> <img alt=""
			src="/pic/${article.picture}"> <input type="file"
			class="form-control-file" id="file" name="file">
	</div>

	<div class="form-group">
		<label for="exampleFormControlTextarea1">文章内容</label>
		<textarea class="form-control"  id="contenId" name="content1" id="content1" rows="20"
			cols="200">${content1}</textarea>
	</div>

	<div  class="form-group" >
		<input type="button" class="btn btn-outline-primary"  value="提交"  onclick="commitArticle()">
	
	</div>
</form>

<script>
      
    function channelChange() {
		
    	
    	console.log("选中的数据："+$("#channel").val())
    	$.post("/user/getCategoris",{cid:$("#channel").val()},function(data){
    		
    		$("#category").empty();
    		for(var i  in  data){
    			
    			if(data[i].id=='${article.categoryId}'){
        		$("#category").append("<option  selected  value='"+data[i].id+"'>"+data[i].name+"</option>")

    			}
    			$("#category").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")

    			
    		}
    	})
    	
	}

    $("#channel").change(function(){
    
    	channelChange();
    }) 
    
     
    
     $(document).ready(function(){
    	// 执行 栏目下拉框的改变事件
    	 channelChange();
   
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				//uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				uploadJson:'/file/upload.do',
				
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
     })

        //文章修改
		function commitArticle(){
		alert(editor1.html())
		
	    var formData=	new  FormData($("#articleform")[0])
		formData.set("content",editor1.html())
		
		$.ajax({
			
			url:"updateArticle",
			dataType:"json",
			data:formData,
			//让jquery 不要在提交数据之前进行处理
			processData:false,
			//提交的数据不能叫消息头
			contentType:false,
			//提交方式
			type:"post",
			//成功后的回调函数
			success:function(res){
				 showWork($("#postLink"),"/user/articles")
			}
			
		},"json");
		
		
			//alert( $("[name='content1']").attr("src"))
		} 

</script>

