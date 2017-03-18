<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="StyleSheet" href="../css/dtree.css" type="text/css" charset="gbk">
<script type="text/javascript" src="../js/dtree.js" charset="gbk"></script>

<title>Insert title here</title>
</head>
<body>
dassadas
 <h1>This is surface..</h1>
 <div class="dtree">
   	<SCRIPT LANGUAGE="JavaScript">
		d = new dTree('d');

		// 添加一个根节点:
		d.add('01',-1,'商城管理系统');
		// 添加一个子节点:
		d.add('0101','01','一级分类管理');
		d.add('010101','0101','一级分类管理','data.html','','right');
		d.add('010101','0102','一级分类管理','right.html','','right');
		document.write(d);
	</SCRIPT>
  <s:iterator  var="p" value="orders">
	<s:property value="#p.sku"></s:property>
  </s:iterator>
	
</div>
</body>
</html>