<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetPurchaseList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">


<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü ${resultPage.totalCount}�Ǽ�, ���� ${resultPage.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

<%-----------------���Ÿ�Ͻ���--------------%>
	<c:set var="no" value="0"/>
	<c:forEach var="purchase" items="${ list }">
	<c:set var="no" value="${ no+1 }"/>
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=${purchase.tranNo}">${ no }</a>
		</td>
		<td></td>
		<td align="left">
		<!-- 
			<a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
		 -->
			<a href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo}">${purchase.purchaseProd.prodName}</a> 
		</td>
		<td></td>
		<td align="left">${purchase.buyer.userName}</td>
		<td></td>
		<td align="left">${ purchase.receiverPhone }</td>
		<td></td>
		<td align="left">

		<c:if test="${purchase.tranCode=='1'}">
			���� �߼� �� ���� �Դϴ�.
		</c:if>
		<c:if test="${purchase.tranCode=='2'}">
			���� ����� ���� �Դϴ�.
		</c:if>
		<c:if test="${purchase.tranCode=='3'}">
			���� ���ſϷ� ���� �Դϴ�.
		</c:if>

		</td>
		<td></td>
		<td align="left">
	
			<c:if test="${purchase.tranCode=='1' ||purchase.tranCode=='2' }">
			<a href="/updateTranCode.do?tranNo=${ purchase.tranNo }&tranCode=3">���ǵ���</a>
			</c:if>
		
			</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
	<%-----------------���Ÿ�ϳ�--------------%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="currentPage" name="currentPage" value=""/>
		
			<c:set var="script" value="fncGetPurchaseList" scope="request"/>
			<jsp:include page="../common/pageNavigator.jsp"/>	
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>