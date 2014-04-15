<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<!DOCTYPE html> 
<html>
<head>
	<title>${pageData.vo.title }</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@include file="/commons/jquery.jsp" %>
	<%@include file="/commons/jquery-mobile.jsp" %>
	<%@include file="/static/skins/css/skins.jsp" %>
	<link rel="stylesheet" href="${ctx }/static/skins/Simplify/css/style.css">
	<link rel="stylesheet" href="${ctx }/static/skins/Simplify/css/admin.css">
	<style type="text/css">
	</style>
	<script type="text/javascript">
		$(document).bind('pageinit', function(event){
		});
		
	</script>
</head>
<body data-role="page" data-theme="f">
 	<jsp:include page="/module/navbar/navbar_b.html?styleType=${skins.navbarSkin.styleType }" />
	
	<div role="main" class="ui-content" data-theme="f" style="margin-left:2%; min-height:400px;">
		<jsp:include page="${pageData.modulePath }/user_banner.r" />
	</div>
	
	<jsp:include page="/module/footer/footer_b.html?styleType=${skins.footerSkin.styleType }" />
	
</body>
</html>


		