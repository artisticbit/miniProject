<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>판매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetSaleList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	
	function fncChangeSearch(searchCondition){
		
		var value=searchCondition.value;
		var searchSpan=document.getElementById("searchSpan");
		if(value=="2"){
			searchSpan.innerHTML="<select name='searchKeyword' class='ct_input_g' style='width:80px'>"
			+"<option value='1'>배송전</option>"
			+"<option value='2'>배송완료</option>"
			+"<option value='3'>거래완료</option>"
		+"</select>";	
		}
		else{
			searchSpan.innerHTML="<input type='text' name='searchKeyword'  class='ct_input_g' style='width:200px; height:19px'"
				+"value='${!empty search.searchKeyword ? search.searchKeyword : ''}'/>";
		}
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">


<form name="detailForm" action="/listSale.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">판매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		
		<td align="right">
			<select name="searchCondition" onchange="fncChangeSearch(this)" class="ct_input_g" style="width:80px">
				<option value="0" ${!empty search && search.searchCondition=='0'? "selected" : ""}>회원명</option>
				<option value="1" ${!empty search && search.searchCondition=='1'? "selected" : ""}>상품명</option>
				<option value="2" ${!empty search && search.searchCondition=='2'? "selected" : ""}>배송상태</option>
			</select>
			<span id="searchSpan">
				<c:if test="${empty search.searchCondition || search.searchCondition!='2' }">
				<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px"
						value="${!empty search.searchKeyword ? search.searchKeyword : ""}"/>
				</c:if>
				
				<c:if test="${search.searchCondition=='2'}">
					<select name="searchKeyword"class="ct_input_g" style="width:80px">
						<option value="1">배송전</option>
						<option value="2">배송완료</option>
						<option value="3">거래완료</option>
					</select>
				</c:if>
			</span>		
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetSaleList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>



<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount}건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

<%-----------------구매목록시작--------------%>
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
		<td align="left">
		<a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
		</td>
		<td></td>
		<td align="left">${ purchase.receiverPhone }</td>
		<td></td>
		<td align="left">

		<c:if test="${purchase.tranCode=='1'}">
			현재 배송 전 입니다.
		</c:if>
		<c:if test="${purchase.tranCode=='2'}">
			현재 배송중 상태 입니다.
		</c:if>
		<c:if test="${purchase.tranCode=='3'}">
			현재 판매완료 상태 입니다.
		</c:if>

		</td>
		<td></td>
		<td align="left">
	
			<c:if test="${purchase.tranCode=='1'}">
			<a href="/updateTranCodeByProd.do?prodNo=${ purchase.purchaseProd.prodNo }&tranCode=2">배송</a>
			</c:if>
			<c:if test="${purchase.tranCode=='2'}">
			배송중
			</c:if>
		    <c:if test="${purchase.tranCode=='3'}">
			판매완료
			</c:if>
			</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
	<%-----------------구매목록끝--------------%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="currentPage" name="currentPage" value=""/>
		
			<c:set var="script" value="fncGetSaleList" scope="request"/>
		
			<jsp:include page="../common/pageNavigator.jsp"/>	
			
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>