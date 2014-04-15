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

	<div id="banner" align="center">
		<div class="container" >
			<div class="row">
		  		<div class="col-md-8">
					${pageData.vo.message }
				</div>
				<div class="col-md-3 col-md-offset-1">
					<jsp:include page="/module/sidebar/login_b.html" />
				</div>
			</div>
		</div>
	</div>
	
	<div class="container" style="min-height:400px; padding-top:50px;">
      <div class="row">
        <c:forEach items="${pageData.vo.sectionList }" var="section" varStatus="i">
        <div class="col-md-4">
          <h2>${section.title }</h2>
          <p>${section.content }</p>
        </div>
        </c:forEach>
      </div>
    </div>
    
	<jsp:include page="/module/footer/footer_b.html?styleType=${skins.footerSkin.styleType }" />
</body>
</html>