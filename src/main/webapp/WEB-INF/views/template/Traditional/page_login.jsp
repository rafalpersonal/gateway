<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<!DOCTYPE html> 
<html>
<head>
	<title>${pageData.vo.name }</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@include file="/commons/jquery.jsp" %>
    <%@include file="/commons/bootstrap.jsp" %>
    <link rel="stylesheet" href="${ctx }/static/skins/Traditional/css/style.css">
	<script type="text/javascript">
	</script>
</head>
<body>	
	<jsp:include page="/module/navbar/navbar_b.html?styleType=${skins.navbarSkin.styleType }" />

	<div class="container" style="padding-top:30px;">
		<div class="row">
			<div class="col-sm-3">
				<jsp:include page="/module/sidebar/login_b.html" />
			</div>
			<div class="col-sm-8 col-sm-offset-1">
				<section class="intro">
					<h1>${pageData.vo.title }</h1>
					<p>	${pageData.vo.content }</p>
				</section>
			</div>
		</div>
	</div>
	
	<jsp:include page="/module/footer/footer_b.html?styleType=${skins.footerSkin.styleType }" />
</body>
</html>