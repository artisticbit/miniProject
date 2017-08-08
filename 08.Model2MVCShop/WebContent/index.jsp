<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>Model2 MVC Shop</title>
</head>
<script type="text/javascript">

</script>

<frameset rows="80,*" cols="*" frameborder="NO" border="0" framespacing="0">
  
  <frame src="/layout/top.jsp" name="topFrame" scrolling="NO" noresize >
 				<!--  cols="160,*"                 frameborder="NO" border="0"   -->							
  <frameset rows="*" cols="250,*" framespacing="0" frameborder="NO" border="0">
    
    <!-- 추가줄 --><frameset rows="500,*" cols="*" framespacing="0" frameborder="NO" border="0">
   	 				<frame src="/layout/left.jsp" name="leftFrame" scrolling="NO" noresize>
   					<frame src="/websocket/websocket.jsp" name= leftTest scrolling="YES">
   <!-- 추가줄 --></frameset>
    
    <frame src="" name="rightFrame"  scrolling="auto">
  </frameset>

</frameset>

<noframes>
<body>
</body>
</noframes>

</html>