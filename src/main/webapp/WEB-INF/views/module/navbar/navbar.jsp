<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<style type="text/css">
	#navbar_header .navbar {
	    border-radius: 0px;
	    margin-bottom:0px;
	}

	<c:if test="${not empty skins.navbarSkin.bgColor}">
		/********************
			navbar 的背景色 
		*********************/
		#navbar_header .navbar-inverse { 
			background: -moz-linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); 
			/* FF3.6+ */
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.navbarSkin.bgColor.startColor }), color-stop(100%, ${skins.navbarSkin.bgColor.endColor })); /* Chrome,Safari4+ */
			background: -webkit-linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); /* Chrome10+,Safari5.1+ */
			background: -o-linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); 
			/* Opera11.10+ */
			background: -ms-linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); 
			/* IE10+ */
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.navbarSkin.bgColor.startColorForIE }', endColorstr='${skins.navbarSkin.bgColor.endColorForIE }',GradientType=0 ); /* IE6-9 */
			background: linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); 
			/* W3C */
			border-color: transparent;
			border: 0px;
		}
		
		/****************************
		 		navbar 上的字体色
		*****************************/
		#navbar_header .navbar-inverse .navbar-nav > li > a { 
			color: ${skins.navbarSkin.fontColor }; 
		}
		
		#navbar_header .navbar-inverse .navbar-nav > li > a,
		#navbar_header .navbar-inverse .navbar-nav > li > a:hover { 
			color: ${skins.navbarSkin.fontColor }; 
			font-size:1.1em;
			text-shadow:none;
			font-weight:bold;
		}
		
		/*****************************
			toggle 按钮
		******************************/
		<c:choose>
		<c:when test="${skins.navbarSkin.bgColor.startColorForIE == '#FFFFFF'}">
			#navbar_header .navbar-inverse .navbar-toggle {
			    border-color: transparent;
			    background-color: ${skins.navbarSkin.fontColor};
			}
			
			#navbar_header .navbar-inverse .navbar-toggle:hover,
			#navbar_header .navbar-inverse .navbar-toggle:focus {
			    border-color: ${skins.navbarSkin.fontColor};
			    background-color: ${skins.navbarSkin.fontColor};
			}
		</c:when>
		<c:otherwise>
			#navbar_header .navbar-inverse .navbar-toggle {
			    border-color: transparent;
			    background-color: transparent;
			}
			
			#navbar_header .navbar-inverse .navbar-toggle:hover,
			#navbar_header .navbar-inverse .navbar-toggle:focus {
			    border-color: ${skins.navbarSkin.fontColor};
			    background-color: transparent;
			}
		</c:otherwise>
		</c:choose>
		
		/*********************************
		    	有下拉菜单的按钮打开
		**********************************/
		#navbar_header .navbar-inverse .navbar-nav > .open > a, 
		#navbar_header .navbar-inverse .navbar-nav > .open > a:hover, 
		#navbar_header .navbar-inverse .navbar-nav > .open > a:focus 
		{
			background-color: ${skins.navbarSkin.bgColor.startColorForIE};
			color: ${skins.navbarSkin.fontColor};	
		}
		
		/*********************************
				下拉菜单 
		**********************************/
		#navbar_header .dropdown-menu { 
			background: -moz-linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); 
			/* FF3.6+ */
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.navbarSkin.bgColor.startColor }), color-stop(100%, ${skins.navbarSkin.bgColor.endColor })); /* Chrome,Safari4+ */
			background: -webkit-linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); /* Chrome10+,Safari5.1+ */
			background: -o-linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); 
			/* Opera11.10+ */
			background: -ms-linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); 
			/* IE10+ */
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.navbarSkin.bgColor.startColorForIE }', endColorstr='${skins.navbarSkin.bgColor.endColorForIE }',GradientType=0 ); /* IE6-9 */
			background: linear-gradient(top, ${skins.navbarSkin.bgColor.startColor } 0%, ${skins.navbarSkin.bgColor.endColor } 100%); 
			/* W3C */
			color: ${skins.navbarSkin.fontColor};
		} 
		
		#navbar_header .dropdown-menu > li > a { color: ${skins.navbarSkin.fontColor }; }
		#navbar_header .dropdown-menu > li > a:hover, .dropdown-menu > li > a:focus {
			background: none;
		}    
		
	</c:if>
</style>
<div id="navbar_header">
	<div class="navbar navbar-inverse" role="navigation">
	  <div class="container">
	    <div class="navbar-header">
	      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".header-navbar-collapse">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a href="#" class="navbar-brand">
	      	<img src="${ctx }${hongxu_navbar.vo.logo}" height="30" />
	      </a>
	    </div>
	    <nav class="collapse navbar-collapse header-navbar-collapse" role="navigation">
	      <ul class="nav navbar-nav navbar-right">
	      	<c:forEach items="${hongxu_navbar.vo.itemList }" var="item" varStatus="i">
	        <li <c:if test="${i.index == hongxu_navbar.activeButton }">class="active"</c:if> >
	          <a href="${ctx }/${item.url }">${item.name }</a>
	        </li>
	        </c:forEach>
	        <li class="dropdown">
	        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">User<b class="caret"></b></a>
	        	<ul class="dropdown-menu">
	        		<li><a href="${ctx }/user/register.html">User Register</a></li>
	        		<li><a href="${ctx }/user/login.html">User Login</a></li>
	        		<li><a href="${ctx }/user/logout.html">User Logout</a></li>
	        	</ul>
	        </li>
	      </ul>
	    </nav>
	  </div>
	</div>
</div>
