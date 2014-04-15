<style type="text/css">
	.ui-body-f {
	  background-color: #f9f9f9;
	}
	
	a { text-decoration: none; }
	
	/********************
			Nav Bar
	*********************/
	.ui-bar-f {
	  color: #fff;
	  /*border-bottom: 2px solid #000; */
	  background-color: #000;
	  background: -moz-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* FF3.6+ */
	  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.color1 }), color-stop(100%, ${skins.color2 })); /* Chrome,Safari4+ */
	  background: -webkit-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); /* Chrome10+,Safari5.1+ */
	  background: -o-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* Opera11.10+ */
	  background: -ms-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* IE10+ */
	  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.ie_color1 }', endColorstr='${skins.ie_color2 }',GradientType=0 ); /* IE6-9 */
	  background: linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* W3C */
	}
	
	/************************
			Collapsible 
	*************************/
	.ui-body-f .ui-collapsible .ui-collapsible-heading a {
		background-color: #000;
	  background: -moz-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* FF3.6+ */
	  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.color1 }), color-stop(100%, ${skins.color2 })); /* Chrome,Safari4+ */
	  background: -webkit-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); /* Chrome10+,Safari5.1+ */
	  background: -o-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* Opera11.10+ */
	  background: -ms-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* IE10+ */
	  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.ie_color1 }', endColorstr='${skins.ie_color2 }',GradientType=0 ); /* IE6-9 */
	  background: linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* W3C */
	  text-decoration: none;
	}
	
	.ui-collapsible .ui-collapsible-content {
		border-style: none;
		border-width: 0 0 1px;
		background-color: #EEE;
	}
	
	/**********************
	    ListView
	***********************/
	/*
	.ui-listview .ui-li-static, .ui-listview .ui-btn {
		border-style: none;
		border-width: 0px;
		padding: 0.3em 0em 0.2em 1em ;
	}
	*/
	
	.ui-listview .ui-li-static {
	  background-color: #000;
	  background: -moz-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* FF3.6+ */
	  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.color1 }), color-stop(100%, ${skins.color2 })); /* Chrome,Safari4+ */
	  background: -webkit-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); /* Chrome10+,Safari5.1+ */
	  background: -o-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* Opera11.10+ */
	  background: -ms-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* IE10+ */
	  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.ie_color1 }', endColorstr='${skins.ie_color2 }',GradientType=0 ); /* IE6-9 */
	  background: linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* W3C */
	  text-decoration: none;	
	}
	
	.ui-listview .ui-li-static:hover {
	  background: ${skins.color1 };
	  text-decoration: none;
	}
	
	.ui-listview .ui-li-static a {
		color: #FFF;	
	}
	
	.ui-listview .ui-li-static.ui-collapsible {
	    padding: 0;
	}
				
	.ui-listview .ui-li-static.ui-collapsible > .ui-collapsible-heading {
	    margin: 0;
	}
	
	.ui-listview .ui-li-static.ui-collapsible > .ui-collapsible-heading > .ui-btn {
	    border-top-width: 0;
	}
	
	.ui-listview .ui-li-static.ui-collapsible > .ui-collapsible-heading.ui-collapsible-heading-collapsed > .ui-btn,
	.ui-listview .ui-li-static.ui-collapsible > .ui-collapsible-content {
	    border-bottom-width: 0;
	}
	
	.ui-listview .ui-li-static.ui-collapsible > .ui-collapsible-content a {
	    background-color: #DDD;
	    color: #333;
	}
	
	/******************
	    Button
	*******************/
	.ui-btn-f {
	  background-color: #000;
	  background: -moz-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* FF3.6+ */
	  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.color1 }), color-stop(100%, ${skins.color2 })); /* Chrome,Safari4+ */
	  background: -webkit-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); /* Chrome10+,Safari5.1+ */
	  background: -o-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* Opera11.10+ */
	  background: -ms-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* IE10+ */
	  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.ie_color1 }', endColorstr='${skins.ie_color2 }',GradientType=0 ); /* IE6-9 */
	  background: linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* W3C */
	  text-decoration: none;	
	  text-shadow: #225377 0px -1px 1px;
	  color: #fff;
	}
	
	.ui-btn-f:hover {
	  background: ${skins.color1 };
	  color: #FFF;
	  text-decoration: none;
	}
	
	.round-corner {
	    -moz-border-radius: 15px;
	    -webkit-border-radius: 15px;
	    border-radius:15px;
	}


	.ui-footer {
	  background-color: #000;
	  background: -moz-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* FF3.6+ */
	  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, ${skins.color1 }), color-stop(100%, ${skins.color2 })); /* Chrome,Safari4+ */
	  background: -webkit-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); /* Chrome10+,Safari5.1+ */
	  background: -o-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* Opera11.10+ */
	  background: -ms-linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* IE10+ */
	  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='${skins.ie_color1 }', endColorstr='${skins.ie_color2 }',GradientType=0 ); /* IE6-9 */
	  background: linear-gradient(top, ${skins.color1 } 0%, ${skins.color2 } 100%); 
	  /* W3C */
	  text-decoration: none;	
	  text-shadow: #225377 0px -1px 1px;
	  color: #fff;
	}
	
</style>