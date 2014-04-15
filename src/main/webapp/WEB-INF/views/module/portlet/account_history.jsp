<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<style type="text/css">
	.line { 
		line-height:1.6em; font-size:1.2em; 
		border-bottom:1px dotted; 
		padding-top:8px; padding-bottom:3px;
	}
	
</style>
<div id="account_history">
	<div style="font-size:20px;">
		Account History 
		<hr style="height:1px;"/>
	</div>
	<c:choose>
	<c:when test="${not empty hongxu_account_history.message }">
		${hongxu_account_history.message }
	</c:when>
	<c:otherwise>
	    <c:forEach items="${hongxu_account_history.lines.lines }" var="record" varStatus="idx">
	    	<div class="line">
	    		<span style="padding-left:10px;">
	    			<fmt:formatDate value="${record.postedAt}" pattern="yy-MM-dd HH:mm"/>
	    		</span>
	    		<span style="padding-left:30px; display:inline-block; width:200px;"> 
	    			${record.value }
	    		</span>
	    		<span style="padding-left:30px;"> 
	    			 ${record.description } 
	    		 </span>
	    	</div>
	    </c:forEach>
	</c:otherwise>
	</c:choose>
</div>
