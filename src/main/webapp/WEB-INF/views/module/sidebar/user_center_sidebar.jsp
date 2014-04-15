<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<style style="text/css">
	<c:if test="${not empty skins.sidebarSkin.bgColor}">
		#user_withdraw_sidebar a.list-group-item.active, 
		#user_withdraw_sidebar a.list-group-item.active:hover, 
		#user_withdraw_sidebar a.list-group-item.active:focus {
		    background: -moz-linear-gradient(top, ${skins.sidebarSkin.bgColor.startColor } 0%, ${skins.sidebarSkin.bgColor.endColor } 100%); 
			/* FF3.6+ */
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.sidebarSkin.bgColor.startColor }), color-stop(100%, ${skins.sidebarSkin.bgColor.endColor })); /* Chrome,Safari4+ */
			background: -webkit-linear-gradient(top, ${skins.sidebarSkin.bgColor.startColor } 0%, ${skins.sidebarSkin.bgColor.endColor } 100%); /* Chrome10+,Safari5.1+ */
			background: -o-linear-gradient(top, ${skins.sidebarSkin.bgColor.startColor } 0%, ${skins.sidebarSkin.bgColor.endColor } 100%); 
			/* Opera11.10+ */
			background: -ms-linear-gradient(top, ${skins.sidebarSkin.bgColor.startColor } 0%, ${skins.sidebarSkin.bgColor.endColor } 100%); 
			/* IE10+ */
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.sidebarSkin.bgColor.startColorForIE }', endColorstr='${skins.sidebarSkin.bgColor.endColorForIE }',GradientType=0 ); /* IE6-9 */
			background: linear-gradient(top, ${skins.sidebarSkin.bgColor.startColor } 0%, ${skins.sidebarSkin.bgColor.endColor } 100%); 
			/* W3C */
			
		    border-radius: 4px;
		    padding: 15px;
		    color: ${skins.sidebarSkin.fontColor };
		}
	</c:if>
</style>
<div id="user_withdraw_sidebar">
<div class="list-group">
  <c:forEach items="${hongxu_user_center_sidebar.vo.itemList }" var="item" varStatus="i">
  	<a href="${ctx }/${item.url }" class="list-group-item <c:if test="${i.index == hongxu_user_center_sidebar.activeItem }">active</c:if>">
  		${item.name }
  	</a>
  </c:forEach>
</div>
</div>
