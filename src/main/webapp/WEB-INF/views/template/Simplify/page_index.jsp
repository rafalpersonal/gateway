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
		.intro h1 	{ margin-top:0px; }
		.intro p  	{ font-size:22px; line-height:30px; margin:0px; }
		.news 		{background-color:RGB(232,240,216); padding:10px; margin:0 10px 0 5px;}
		.news h3  	{ margin-top:5px; }
		.news p  	{ text-indent:2em; font-size:16px; }
		.sectionCss 	{float:left; width:31%; min-height:100px; padding:5px; }
		.sectionCss li 	{ font-size:16px; }
	</style>
</head>
<body>
 
	<jsp:include page="/module/navbar/navbar_b.html?styleType=${skins.navbarSkin.styleType }" />

	<div class="container">
		<div class="col-sm-3" style="padding:0px;">
			<jsp:include page="/module/sidebar/login_b.html" />
		</div>
		<div class="col-sm-8 col-sm-offset-1"  style="padding-top:20px;">
			<section class="intro">
				<h1>${pageData.vo.title }</h1>
				<p>${pageData.vo.description }</p>
			</section>
		</div>
	</div>

	<div class="container">
		<div class="jumbotron">
			${pageData.vo.message }
		</div>
	</div>
	
	<div class="container">
		<c:forEach items="${pageData.vo.sectionList }" var="section" varStatus="i">
			<div class="sectionCss">
				<h1>${section.title }</h1>
				<div>${section.content }</div>
			</div>
		</c:forEach>
	</div>
		
	    
	<jsp:include page="/module/footer/footer_b.html?styleType=${skins.footerSkin.styleType }" />
	 
</body>
</html>


		