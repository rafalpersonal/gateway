<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@include file="/commons/jquery.jsp" %>
    <%@include file="/commons/bootstrap.jsp" %>
</head>
<body>
	<jsp:include page="/module/navbar/navbar_b.html?styleType=${skins.navbarSkin.styleType }" />
	
	<div class="container">
		<div style="text-align:center; font-weight:bold; font-size:24px; padding-top:40px;">
			${message }
		</div>
		<div style="text-align:center; font-weight:normal; font-size:18px; padding-top:20px;">
			${description }
		</div>
	</div>
	
	<jsp:include page="/module/footer/footer_b.html?styleType=${skins.footerSkin.styleType }" />
</body>
</html>