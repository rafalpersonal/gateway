<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<!DOCTYPE html> 
<html>
<head>
	<title>${pageData.vo.name }</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@include file="/commons/jquery.jsp" %>
	<%@include file="/commons/bootstrap.jsp" %>
	<link rel="stylesheet" href="${ctx }/static/skins/Simplify/css/style.css">
	<script type="text/javascript">
	</script>
</head>
<body data-role="page" data-theme="f">

	<jsp:include page="/module/navbar/navbar_b.html?styleType=${skins.navbarSkin.styleType }" />
	
	<div align="center" style="margin-top:20px;"  style="margin-left:2%;">
		<div style="width:500px;">
			<jsp:include page="/module/portlet/user_register.html" />
		</div>
	</div>
	
	<jsp:include page="/module/footer/footer_b.html?styleType=${skins.footerSkin.styleType }" />
	
</body>
</html>