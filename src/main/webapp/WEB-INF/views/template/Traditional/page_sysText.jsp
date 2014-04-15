<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <title>SiFu Inc</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/commons/jquery.jsp" %>
    <%@include file="/commons/bootstrap.jsp" %>
    <link rel="stylesheet" href="${ctx }/static/skins/Traditional/css/style.css">
  </head>
  <body>
 
    <jsp:include page="/module/navbar/navbar_b.html?styleType=${skins.navbarSkin.styleType }" />

    <div class="container" style="min-height:400px; padding-top:50px;">
      ${pageData.content }
    </div>
    
    <jsp:include page="/module/footer/footer_b.html?styleType=${skins.footerSkin.styleType }" />

  </body>
</html>

		