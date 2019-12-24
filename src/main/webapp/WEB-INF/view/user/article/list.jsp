<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet"  href="/resource/css/style.css">
      <form action="">
                 查询审核状态:<select name="status">
             <option value="-1">-请选择-</option>
             <option value="1">审核通过</option>
             <option value="2">审核被拒</option>
              <option value="3">待审核</option>
             </select>
             <input type="submit"  value="查询">
      </form>
     <table  class="table">
         <thead>
            <tr>
                <th>ID</th>
                <th>标题题目</th>
                <th width="100px">精彩栏目</th>
                <th width="100px">多彩分类</th>
                <th>发布时间</th>
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
                     <td width="200px">
                         <input type="button"  value="刪除"  onclick="del(${p.id})">
                         <input type="button"  value="修改"  onclick="upd(${p.id})">
                     
                     </td>
                 
                 </tr>
            </c:forEach>
         </tbody>
     </table>
     
     
     
       <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <li class="page-item disabled">
		      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
		    </li>
		    <c:forEach  begin="1"  end="${articlePage.pages}" varStatus="i">
		       <li class="page-item"><a class="page-link" href="#" onclick="gopage(${i.index})">${i.index}</a></li>
		    </c:forEach>
		    
		    <li class="page-item">
		      <a class="page-link" href="#">Next</a>
		    </li>
		  </ul>
		</nav>
     
     
<script type="text/javascript">
   function del(id) {
	   alert(id);
	   if(confirm("您确定删除吗???")){	   
		   $.post("/user/del",{id:id},function(re){
			   
			   if(re){
				   alert("删除成功！");
				   $("#workcontent").load("/user/articles");
			   }else{
				   alert("删除失败！");
			   }  
		   },"json");  
	   }   
} 
      /**
                     分页函数
      */
    function gopage(pageNum) {
		$("#workcontent").load("/user/articles?pageNum="+pageNum)
	}
      
      function upd(id) {
    	  $("#workcontent").load("/user/updateArticle?id="+id);
    	  
	}
</script>