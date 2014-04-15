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
	<style type="text/css">
		#createWallet, #serviceTerm { color:red; }
	</style>
	<script type="text/javascript">
	</script>
</head>
<body data-role="page" data-theme="f">
	
	<jsp:include page="/module/navbar/navbar_b.html?styleType=${skins.navbarSkin.styleType }" />
	
	<div class="container">		
		<jsp:include page="/module/portlet/password_reset.html" />
	</div>
	
	<jsp:include page="/module/footer/footer_b.html?styleType=${skins.footerSkin.styleType }" />
	
</body>
</html>