<%@page import="com.model2.mvc.service.domain.User"%>
<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
	User vo=(User)session.getAttribute("user");

	String role="";

	if(vo != null) {
		role=vo.getRole();
	}
--%>


<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function history(){
	popWin = window.open("/history.jsp","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}
</script>
</head>

<body background="/images/left/imgLeftBg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >
		<!-- width="159"  -->
<table width="500" border="0" cellspacing="0" cellpadding="0">

<!--menu 01 line-->
<tr>
<td valign="top"> 
	<table  border="0" cellspacing="0" cellpadding="0" width="300" >	
		<tr>
		
		<c:if test="${!empty user.role }">
		<tr>
		<td class="Depth03">
			<a href="/getUser.do?userId=${user.userId}" target="rightFrame">����������ȸ</a>
		</td>
		</tr>
		</c:if>

		<c:if test="${user.role=='admin' }">
		<tr>
		<td class="Depth03" >
			<a href="/listUser.do" target="rightFrame">ȸ��������ȸ</a>
		</td>
		</tr>
		</c:if>
	
		<tr>
			<td class="DepthEnd">&nbsp;</td>
		</tr>
	</table>
</td>
</tr>

	<c:if test="${user.role=='admin' }">
<!--menu 02 line-->
<tr>
<td valign="top"> 
	<table  border="0" cellspacing="0" cellpadding="0" width="159">
		<tr>
			<td class="Depth03">
				<a href="../product/addProductView.jsp;" target="rightFrame">�ǸŻ�ǰ���</a>
			</td>
		</tr>
		<td class="Depth03">
				<a href="/listProduct.do?menu=manage" target="rightFrame">�ǸŻ�ǰ����</a>
			</td>
		</tr>
		<tr>
			<td class="DepthEnd">&nbsp;</td>
		</tr>
	</table>
</td>
</tr>
</c:if>


<!--menu 03 line-->
<tr>
<td valign="top">
	<table  border="0" cellspacing="0" cellpadding="0" width="159">
		<tr>
			<td class="Depth03">
				<a href="/listProduct.do?menu=search" target="rightFrame">�� ǰ �� ��</a>
			</td>
		</tr>

		<c:if test="${!empty user && user.role=='user' }">
		<tr>
			<td class="Depth03">
				<a href="/listPurchase.do" target="rightFrame">�����̷���ȸ</a>
			</td>
		</tr>
		</c:if>
		<c:if test="${!empty user && user.role=='admin' }">
		<tr>
			<td class="Depth03">
				<a href="/listSale.do" target="rightFrame">�Ǹ��̷���ȸ</a>
			</td>
		</tr>
		</c:if>

		<tr>
		<td class="DepthEnd">&nbsp;</td>
		</tr>
		<tr>
			<td class="Depth03">
				<a href="javascript:history()">�ֱ� �� ��ǰ</a>
			</td>
		</tr>
	</table>
</td>
</tr>

</table>
</body>
</html>