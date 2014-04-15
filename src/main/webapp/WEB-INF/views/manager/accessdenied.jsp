<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <title>禁止访问！</title>
  </head>
<body>
<div id="content">
<h2>禁止访问！</h2>
<p>
提示信息：<c:if test="${not empty issueErrorInfo}">${issueErrorInfo}</c:if><a href="/logout">返回</a>
</p>
</div>
</body>
</html>
