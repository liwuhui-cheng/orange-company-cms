<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- <link rel="stylesheet"  href="/resource/css/style.css"> -->

 <select id="status">
    <option value="-1" ${status=='-1'?'selected':''}>全部</option>
	<option value="0" ${status==0?'selected':'' }>待审核</option>
	<option value="1" ${status==1?'selected':'' }>审核通过</option>
	<option value="2" ${status==2?'selected':'' }>审核被拒</option>
	
 </select>
 <input type="button" value="查询" onclick="getstatus()">
     <table  class="table" style="width:1300px;margin-top: 30px;">
         <thead>
            <tr>
                <th>ID</th>
                <th>标题题目</th>
                <th width="100px">精彩栏目</th>
                <th width="100px">多彩分类</th>
                <th>作者</th>
                <th>发布时间</th>
                <th>状态</th>
                <th>是否热门</th>
                <th>操作</th>
            </tr>
         </thead>
         <tbody>
           <c:forEach  items="${articlePage.list}" var="p">
                 <tr>
                    <td>${p.id }</td>
                    <td>${p.title}</td>
                    <td>${p.channel.name}</td>
                    <td>${p.category.name}</td>
                    <td>${p.user.username}</td>
                    <td>
                    <fmt:formatDate value="${p.created}"  pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <c:choose>
                        <c:when test="${p.status==0}">待审核</c:when>
                        <c:when test="${p.status==1}">审核通过</c:when>
                        <c:when test="${p.status==2}">审核被拒</c:when>
                         <c:otherwise>                   
                                                                          未知
                         </c:otherwise>                         
                          </c:choose>
                     </td>
                     <td>${p.hot==1?"热门":"非热门"}</td>
                     <td width="200px">
                         <input type="button"  value="刪除"  onclick="del(${p.id})">
                         <input type="button"  value="审核"  onclick="check(${p.id})">
                        <%--  <input type="button" value="管理"  class="btn btn-warning" onclick="complainList(${p.id})" > --%>
                         
                     </td>
                 
                 </tr>
            </c:forEach>
         </tbody>
     </table>
     
     
     
      <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <li class="page-item">
		      <a class="page-link" onclick="gopage(${articlePage.prePage})">Previous</a>
		    </li>
		   	<c:forEach begin="1" end="${articlePage.pages}" varStatus="i">
		   		<li class="page-item"><a class="page-link" href="javascript:void()" onclick="gopage(${i.index})">${i.index}</a></li>
		   	</c:forEach>
		    
		   
		    <li class="page-item">
		      <a class="page-link" onclick="gopage(${articlePage.nextPage})">Next</a>
		    </li>
		  </ul>
		</nav>
		
		
		 <!-- 审核文章 -->
<div class="modal fade"   id="articleContent" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-left:100px;">
    <div class="modal-content" style="width:1200px;" >
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">文章审核</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body ">
         	<div class="row" id="divTitle"></div>
         	<div class="row" id="divOptions" ></div>
         	<div class="row" id="divContent"></div>		
      </div>
      
        
      
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="setStatus(1)">审核通过</button>
        <button type="button" class="btn btn-primary" onclick="setStatus(2)">审核拒绝</button>
        <button type="button" class="btn btn-primary" onclick="setHot(1)">设置热门</button>
        <button type="button" class="btn btn-primary" onclick="setHot(0)">取消热门</button>
      </div>
    </div>
  </div>
</div>
     
     
<!-- 查看投书 -->
<div class="modal fade"   id="complainModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-left:100px;">
    <div class="modal-content" style="width:1200px;" >
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">文章审核</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="complainListDiv">
         
         		
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="setStatus(1)">审核通过</button>
        <button type="button" class="btn btn-primary" onclick="setStatus(2)">审核拒绝</button>
      </div>
    </div>
  </div>
</div>
     
<script>
   var global_article_id;

   //消失的时候刷新当前的列表
   $('#articleContent').on('hidden.bs.modal',function(e){
	   refreshPage();
   })
   
   
   /**
	* 查看文章的1
	*/
	function complainList(id){
		global_article_id=id;
		$("#complainModal").modal('show')
		$("#complainListDiv").load("/article/complains?articleId="+id);
		
	}
 
   
   
   function del(id) {
	   alert(id);
	   if(!confirm("您确定删除吗???"))  
		   return;
		   $.post("/user/del",{id:id},function(re){
			   
			   if(re){
				   alert("删除成功！");
				   $("#workcontent").load("/user/articles");
			   }else{
				   alert("删除失败！");
			   }  
		   },"json");  
	    
} 
      /**
      * 分页函数
      **/
    function gopage(pageNum) {
    	  
		$("#workcontent").load("/admin/article?pageNum="+pageNum + "&status="+'${status}');
	}
      
    function getstatus(){
		$("#workcontent").load("/admin/article?status="+$("#status").val());
	}
      /**
      * 审核状态
      *
      */
      
      function setStatus(status) {
		
    	  var id=global_article_id;
    	  
    	  $.post("/admin/setArticeStatus",{id:id,status:status},function(msg){
    		  
    		  if(msg.code==1){
    			  
    			  alert("操作成功!");
    			  //隐藏当前的模态框
    			  $('#articleContent').modal('hide')
    			  $('#complainModal').modal('hide')
    			  //刷新当前页面
    			  //refreshPage();
    			  return;	
    		  }
    		  alert(msg.error);
    	  },"json");
    	  
	}
      
      function refreshPage() {
  		$("#workcontent").load("/admin/article?pageNum=" + '${articlePage.pageNum}' + "&status="+'${status}');
	}
      
      
      /**
 	 0 非热门
 	 1 热门
 	*/
 	function setHot(status){
 		
 		var id = global_article_id;// 文章id
 		$.post("/admin/setArticeHot",{id:id,status:status},function(msg){
 			if(msg.code==1){
 				alert('操作成功')
 				//隐藏当前的模态框
 				$('#articleContent').modal('hide')
 				//刷新当前的页面
 				//refreshPage();
 				return;
 			}
 			alert(msg.error);
 		},
 		"json")
 	}
      
      
	function check(id){
		
     	$.post("/article/getDetail",{id:id},function(msg){
     		if(msg.code==1){
     			//
     			$("#divTitle").html(msg.data.title);
     			//
     			$("#divOptions").html("栏目：" +msg.data.channel.name + 
     					" 分类："+msg.data.category.name + " 作者：" + msg.data.user.username );
     			//
     			$("#divContent").html(msg.data.content);
     			$('#articleContent').modal('show')
     			//文章id保存到全局变量当中
     			global_article_id=msg.data.id;
     			return;
     		}
     		alert(msg.error);  		
     	},"json");
		
   	  $('#articleContent').modal('toggle');
		//$("#workcontent").load("/user/updateArticle?id="+id);
	
	}
      

</script>


 