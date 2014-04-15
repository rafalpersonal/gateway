<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="manager.name" /> v0.1</title>

<link href="${ctx }/static/dwz1.4.6/themes/azure/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx }/static/dwz1.4.6/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx }/static/dwz1.4.6/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${ctx }/static/dwz1.4.6/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="${ctx }/static/dwz1.4.6/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${ctx }/static/dwz1.4.6/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="${ctx }/static/dwz1.4.6/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/jquery.bgiframe.js" type="text/javascript"></script>

<script src="${ctx }/static/dwz1.4.6/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<!-- 
<script src="${ctx }/static/dwz1.4.6/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
 -->
 <script src="${ctx }/static/dwz1.4.6/xheditor/xheditor_lang/en.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="${ctx }/static/dwz1.4.6/chart/raphael.js"></script>
<script type="text/javascript" src="${ctx }/static/dwz1.4.6/chart/g.raphael.js"></script>
<script type="text/javascript" src="${ctx }/static/dwz1.4.6/chart/g.bar.js"></script>
<script type="text/javascript" src="${ctx }/static/dwz1.4.6/chart/g.line.js"></script>
<script type="text/javascript" src="${ctx }/static/dwz1.4.6/chart/g.pie.js"></script>
<script type="text/javascript" src="${ctx }/static/dwz1.4.6/chart/g.dot.js"></script>

<script src="${ctx }/static/dwz1.4.6/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx }/static/dwz1.4.6/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->

<script src="${ctx }/static/dwz1.4.6/js/dwz.regional.en.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${ctx }/static/dwz1.4.6/dwz.frag.${language}.xml", {
		loginUrl:"login_dialog.r", loginTitle:"Login",	// 弹出登录对话框
//		loginUrl:"login.shtml",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{
			pageNum:"pageNo", 
			numPerPage:"pageSize", 
			orderField:"orderField", 
			orderDirection:"orderDirection"
		}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${ctx }/static/dwz1.4.6/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<%--
				<a class="logo" href="http://j-ui.com">标志</a> --%>
				<ul class="nav">
					<li><a href="###"><spring:message code="manager.index.welcome" />&nbsp;<sec:authentication property="principal.userName"/></a></li>
					<li><a href="${ctx }/manager/logout.r"><spring:message code="manager.index.logout" /></a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="azure"><div class="selected">Azure</div></li>
					<li theme="default"><div>Blue</div></li>
					<li theme="purple"><div>Purple</div></li>
					<li theme="green"><div>Green</div></li>
					<li theme="silver"><div>Silver</div></li>
					
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>Main</h2><div>Collapsible</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span><sec:authentication property="principal.userName"/></h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						
							<!-- Business Menu -->
							<li>
								<a><spring:message code="manager.index.businessmenu" /></a>
								<ul>
									<c:forEach var="a" items="${alist }" varStatus="i">
										<c:if test="${fn:startsWith(resmap[a].resourceName, 'manager.index.businessmenu')}">
											<li><a href="${ctx }${resmap[a].resourceString}" target="navTab" rel="${resmap[a].resourceId}"><spring:message code="${resmap[a].resourceName }" /></a></li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
							
							<!-- Function Menu -->
							<li>
								<a><spring:message code="manager.index.functionmenu" /></a>
								<ul>
									<c:forEach var="a" items="${alist }" varStatus="i">
										<c:if test="${fn:startsWith(resmap[a].resourceName, 'manager.index.functionmenu')}">
											<li><a href="${ctx }${resmap[a].resourceString}" target="navTab" rel="${resmap[a].resourceId}"><spring:message code="${resmap[a].resourceName }" /></a></li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
							<!-- Payment Menu -->
							<li>
								<a>Modules</a>
								<ul> 
									<li><a href="#">ZipZap</a></li>
									<li><a href="#">Subledger</a></li>
								</ul>
							</li>
							<li>
								<a>API</a>						
							</li>
							<!-- System Menu -->
							<li>
								<a><spring:message code="manager.index.systemmenu" /></a>
								<ul>
									<%--  
									<li><a><spring:message code="manager.index.systemmenu.changepassword" /></a></li>
									--%>
									<li><a href="${ctx }/manager/logout.r" target="_self"><spring:message code="manager.index.systemmenu.exit" /></a></li>
								</ul>
							</li>

 						</ul>
					</div>
					
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon"><spring:message code="manager.index.homepage" /></span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;"><spring:message code="manager.index.homepage" /></a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo"></div>
						<div class="pageFormContent" layoutH="80">
							<spring:message code="manager.name" /> v0.1<br/><br/>

						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2014 <a href="" target="dialog">HXTM.ltd</a> Tel：00000000</div>
</body>
</html>


