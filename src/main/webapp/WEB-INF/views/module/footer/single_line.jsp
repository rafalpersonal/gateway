<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<style type="text/css">
	<c:if test="${not empty skins.footerSkin}">
		#footer .navbar-inverse {
			background: -moz-linear-gradient(top, ${skins.footerSkin.bgColor.startColor } 0%, ${skins.footerSkin.bgColor.endColor } 100%); 
			/* FF3.6+ */
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.footerSkin.bgColor.startColor }), color-stop(100%, ${skins.footerSkin.bgColor.endColor })); /* Chrome,Safari4+ */
			background: -webkit-linear-gradient(top, ${skins.footerSkin.bgColor.startColor } 0%, ${skins.footerSkin.bgColor.endColor } 100%); /* Chrome10+,Safari5.1+ */
			background: -o-linear-gradient(top, ${skins.footerSkin.bgColor.startColor } 0%, ${skins.footerSkin.bgColor.endColor } 100%); 
			/* Opera11.10+ */
			background: -ms-linear-gradient(top, ${skins.footerSkin.bgColor.startColor } 0%, ${skins.footerSkin.bgColor.endColor } 100%); 
			/* IE10+ */
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.footerSkin.bgColor.startColorForIE }', endColorstr='${skins.footerSkin.bgColor.endColorForIE }',GradientType=0 ); /* IE6-9 */
			background: linear-gradient(top, ${skins.footerSkin.bgColor.startColor } 0%, ${skins.footerSkin.bgColor.endColor } 100%); 
			/* W3C */
		}

		#footer .navbar-inverse  a {
			color: ${skins.footerSkin.fontColor };
		}
	</c:if>
</style>
<div id="footer">
	<footer class="navbar navbar-inverse navbar-fixed-bottom" role="navigation">
		<div class="container">
			<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".footer-navbar-collapse">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		    </button>
			<nav class="collapse navbar-collapse footer-navbar-collapse" role="navigation">
		      <ul class="nav navbar-nav">
		      	<c:forEach items="${hongxu_footer.vo.itemList }" var="item" varStatus="i">
		          <li><a href="${ctx }/${item.url }">${item.name }</a></li>
		        </c:forEach>
		        <c:forEach items="${hongxu_footer.vo.otherItems }" var="item" varStatus="i">
		          <li><a href="${ctx }/${item.url }">${item.name }</a></li>
		        </c:forEach>
		      </ul>
			</nav>
		</div>
	</footer>
</div>
