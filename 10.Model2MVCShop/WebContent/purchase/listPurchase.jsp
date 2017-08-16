<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	

	function fncGetPurchaseList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	
		 $(function() {
			 
			 $( "td.ct_btn01:contains('검색')" ).on("click" , function() {
		
			 	$("#currentPage").val("1");
				 $("form").attr("method" , "POST").attr("action" , "/purchase/listPurchase").submit();
			});
			
			 ///////링크숨기기//////////
			 $(".hidden_link").css("display","none");
			// $(".hidden_link").css("visibility","hidden");
		
			/////넘버 구입정보 조회 이벤트////////////
			$( ".ct_list_pop td:nth-child(1)" ).on("click" , function() {

				var url=$('span',$(this)).text();
				if(url!=''){
					self.location = url;					
				}
			});

				/////상품  정보 조회 이벤트////////////
			$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {

				var url=$('span',$(this)).text();
				if(url!=''){
					self.location = url;					
				}
			});
			
			/////////배송 이벤트//////////////////////
			$( ".ct_list_pop td:nth-child(11)" ).on("click" , function() {	
				
				var url=$('span',$(this)).text();
				self.location = url;

			});

			
			//$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
			
			var arrayProdName=$( ".ct_list_pop td:nth-child(3)" );
			arrayProdName.each(function(index,elem){
				var text=$('span' ,$(elem)).text();
				if(text!=''){
					$(this).css("color" , "red");
				}
			});
			
			var arrayProdName=$( ".ct_list_pop td:nth-child(11)" );
			arrayProdName.each(function(index,elem){
				var text=$('span' ,$(elem)).text();
				if(text!=''){
					$(this).css("color" , "blue");
				}
			});

		 
						
			$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
			
		
		});	
		//onload end// 
		 
		
		/*
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
		
		};
		*/
	</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">


<form name="detailForm" action="/purchase/listPurchase" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
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
		<td class="ct_list_b" width="150">회원명</td>
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
		<!-- 
			<a href="/purchase/getPurchase?tranNo=${purchase.tranNo}">${ no }</a>
		 -->
		 <span class="hidden_link" >/purchase/getPurchase?tranNo=${purchase.tranNo}</span>
		 ${no}
		</td>
		<td></td>
		<td align="left">
		<!-- 
			<a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
		 -->
		 <!-- 
			<a href="/product/getProduct?prodNo=${purchase.purchaseProd.prodNo}">${purchase.purchaseProd.prodName}</a> 
		  -->
		   <span class="hidden_link" >/product/getProduct?prodNo=${purchase.purchaseProd.prodNo}</span>
		   ${purchase.purchaseProd.prodName}
		</td>
		<td></td>
		<td align="left">${purchase.buyer.userName}</td>
		<td></td>
		<td align="left">${ purchase.receiverPhone }</td>
		<td></td>
		<td align="left">

		<c:if test="${purchase.tranCode=='1'}">
			현재 발송 전 상태 입니다.
		</c:if>
		<c:if test="${purchase.tranCode=='2'}">
			현재 배송중 상태 입니다.
		</c:if>
		<c:if test="${purchase.tranCode=='3'}">
			현재 구매완료 상태 입니다.
		</c:if>

		</td>
		<td></td>
		<td align="left">
	
			<c:if test="${purchase.tranCode=='1' ||purchase.tranCode=='2' }">
			<!-- 
			<a href="/purchase/updateTranCode?tranNo=${ purchase.tranNo }&tranCode=3">물건도착</a>
			 -->
			<span class="hidden_link" >/purchase/updateTranCode?tranNo=${ purchase.tranNo }&tranCode=3</span>
			물건도착
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
		
			<c:set var="script" value="fncGetPurchaseList" scope="request"/>
			<jsp:include page="../common/pageNavigator.jsp"/>	
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>