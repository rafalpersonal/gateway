<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<%@ include file="/commons/bootstrap.jsp" %>
<body style="padding:20px; text-align:center;">
	<c:forEach items="${messageList }" var="message" varStatus="idx">
		<div>${message }</div>
	</c:forEach>
</body>
</html>