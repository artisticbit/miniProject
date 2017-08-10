<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:if test="${ param.menu=='search' }">
<title>상품 목록조회</title>
</c:if>
<c:if test="${ param.menu=='manage' }">
<title>상품 관리</title>
</c:if>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

function fncGetProductList(currentPage){
	document.getElementById("currentPage").value = currentPage;
	document.detailForm.submit();
}
function fncSerchPrice(searchCondition){
	var value=searchCondition.value;
	//alert(value);
	if(value=='2'){
	var con2=document.getElementById("con2");
	con2.innerHTML="~ <input type='text' name='searchKeyword2' size='3' class='ct_input_g' style='width:200px; height:19px' value='${!empty search.searchKeyword2 ? search.searchKeyword2: ''}'/> "
	}
	else{
		var con2=document.getElementById("con2");
		con2.innerHTML="";
	}
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct?" method="post">
<input name="menu" value="${ param.menu }" type="hidden"/>
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						<c:if test="${ param.menu=='search' }">
						상품 목록조회
						</c:if>
						<c:if test="${ param.menu=='manage' }">
						상품 관리
						</c:if>
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px"
			onchange="fncSerchPrice(this)">
				<option value="0" ${!empty search && search.searchCondition=='0'? "selected" : ""}>상품번호</option>
				<option value="1" ${!empty search && search.searchCondition=='1'? "selected" : ""}>상품명</option>
				<option value="2" ${!empty search && search.searchCondition=='2'? "selected" : ""}>상품가격</option>
			</select>
			<input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px"
					value="${!empty search.searchKeyword ? search.searchKeyword: ""}"/>
			<span id="con2">
			<c:if test="${search.searchCondition=='2' }">
			<input type='text' name='searchKeyword2' size='3' class='ct_input_g' style='width:200px; height:19px' 
			value='${!empty search.searchKeyword2 ? search.searchKeyword2: ''}'/>
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
						<a href="javascript:fncGetProductList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 ${ resultPage.totalCount } 건수, 현재  ${ resultPage.currentPage }페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">
		<c:if test="${param.menu=='manage'}">
		등록일
		</c:if>
		<c:if test="${param.menu=='search'}">
		제조일자
		</c:if>
		</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<!--  상품리스트	시작 -->		
	
	
		<c:set var="no" value="0"/>
		 <c:forEach var="prod" items="${list}">
			<c:set var="no" value="${ no+1 }"/>
	<tr class="ct_list_pop">
		<td align="center">${ no }</td>
		<td></td>
				<!-- =================상품명 ====================-->
				<c:if test="${empty user|| user.role == 'user' }">
					<c:choose>
						<c:when test="${empty prod.proTranCode }">					
				<td align="left"><a href="/product/getProduct?prodNo=${ prod.prodNo }&menu=${param.menu}">${ prod.prodName }</a></td>
						</c:when>
						<c:otherwise>
				<td align="left">${ prod.prodName }</td>
						</c:otherwise>
					</c:choose>
				</c:if> 
				
				<c:if test="${ user.role=='admin' }">				
				<td align="left"><a href="/product/getProduct?prodNo=${ prod.prodNo }&menu=${param.menu}">${ prod.prodName }</a></td>	
				</c:if>
				<!-- =================상품명 ====================-->
		<td></td>
		<td align="left">${ prod.price }</td>
		<td></td>
		<td align="left">${ prod.regDate }</td>
		<td></td>
		<td align="left">
			<c:if test="${ param.menu=='search' }">
				<c:if test="${empty user || user.role=='user'}">
					<c:choose>
					<c:when test="${empty prod.proTranCode }">
						판매중
					</c:when>
					<c:otherwise>
						재고 없음
					</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${ user.role=='admin' }">
						<c:if test="${empty prod.proTranCode }">
						판매중
						</c:if>
						<c:if test="${ prod.proTranCode=='1' }">
						배송중 
						</c:if>
						<c:if test="${ prod.proTranCode=='2' }">
						배송완료
						</c:if>
						<c:if test="${ prod.proTranCode=='3' }">
						거래완료
						</c:if>
				</c:if>
			</c:if>
			<c:if test="${ param.menu=='manage' }">
				<c:if test="${empty prod.proTranCode }">
				판매중
				</c:if>
				<c:if test="${ prod.proTranCode=='1' }">
				배송중 
				<a href="/purchase/updateTranCodeByProd?prodNo=${ prod.prodNo }&tranCode=2">배송</a>
				</c:if>
				<c:if test="${ prod.proTranCode=='2' }">
				배송완료
				</c:if>
				<c:if test="${ prod.proTranCode=='3' }">
				거래완료
				</c:if>
			</c:if>	
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	</c:forEach>
	<!-- 상품리스트 끝  -->
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		  <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<%-- 
				<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
					◀ 이전
				</c:if>
				<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
					<a href="javascript:fncGetProductList('${ resultPage.currentPage-1}')">◀ 이전</a>
				</c:if>
	
				<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
					<a href="javascript:fncGetProductList('${ i }');">${ i }</a>
				</c:forEach>
	
				<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
				이후 ▶
				</c:if>
				<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
					<a href="javascript:fncGetProductList('${resultPage.endUnitPage+1}')">이후 ▶</a>
				</c:if>
		--%>
			<c:set var="script" value="fncGetProductList" scope="request"/>
			<jsp:include page="../common/pageNavigator.jsp"/>	
    	</td>
	</tr>

</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
