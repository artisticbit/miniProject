<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�Ǹ� �����ȸ</title>

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
			+"<option value='1'>�����</option>"
			+"<option value='2'>��ۿϷ�</option>"
			+"<option value='3'>�ŷ��Ϸ�</option>"
		+"</select>";	
		}
		else{
			searchSpan.innerHTML="<input type='text' name='searchKeyword'  class='ct_input_g' style='width:200px; height:19px'"
				+"value='${!empty search.searchKeyword ? search.searchKeyword : ''}'/>";
		}
	}
</script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	

	function fncGetPurchaseList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	
		 $(function() {
			 
			 $( "td.ct_btn01:contains('�˻�')" ).on("click" , function() {
		
			 	$("#currentPage").val("1");
				 $("form").attr("method" , "POST").attr("action" , "/purchase/listSale").submit();
			});
			
			 ///////��ũ�����//////////
			 $(".hidden_link").css("display","none");
			// $(".hidden_link").css("visibility","hidden");
		
			/////�ѹ� �������� ��ȸ �̺�Ʈ////////////
			$( ".ct_list_pop td:nth-child(1)" ).on("click" , function() {

				var url=$('span',$(this)).text();
				if(url!=''){
					self.location = url;					
				}
			});

				/////��ǰ  ���� ��ȸ �̺�Ʈ////////////
			$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {

				var url=$('span',$(this)).text();
				if(url!=''){
					self.location = url;					
				}
			});
			/////����  ���� ��ȸ �̺�Ʈ////////////
			$( ".ct_list_pop td:nth-child(5)" ).on("click" , function() {

				var url=$('span',$(this)).text();
				if(url!=''){
					self.location = url;					
				}
			});
			
			/////////��� �̺�Ʈ//////////////////////
			$( ".ct_list_pop td:nth-child(11)" ).on("click" , function() {	
				
				var url=$('span',$(this)).text();
				
				if(url!=''){
				
					self.location = url;					
				}

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
		 
	</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">


<form name="detailForm" action="/purchase/listSale" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">�Ǹ� �����ȸ</td>
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
				<option value="0" ${!empty search && search.searchCondition=='0'? "selected" : ""}>ȸ����</option>
				<option value="1" ${!empty search && search.searchCondition=='1'? "selected" : ""}>��ǰ��</option>
				<option value="2" ${!empty search && search.searchCondition=='2'? "selected" : ""}>��ۻ���</option>
			</select>
			<span id="searchSpan">
				<c:if test="${empty search.searchCondition || search.searchCondition!='2' }">
				<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px"
						value="${!empty search.searchKeyword ? search.searchKeyword : ""}"/>
				</c:if>
				
				<c:if test="${search.searchCondition=='2'}">
					<select name="searchKeyword"class="ct_input_g" style="width:80px">
						<option value="1">�����</option>
						<option value="2">��ۿϷ�</option>
						<option value="3">�ŷ��Ϸ�</option>
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
					<!-- 
						<a href="javascript:fncGetSaleList('1');">�˻�</a>
					 -->
					 �˻�
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
		<td colspan="11">��ü ${resultPage.totalCount}�Ǽ�, ���� ${resultPage.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
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
			<!-- 
			<a href="/purchase/getPurchase?tranNo=${purchase.tranNo}">${ no }</a>
			 -->
			 <span class="hidden_link" >/purchase/getPurchase?tranNo=${purchase.tranNo}</span>
			 ${ no }
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
		<td align="left">
		<!-- 
		<a href="/user/getUser?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
		 -->
		   <span class="hidden_link" >/user/getUser?userId=${purchase.buyer.userId}</span>
		   ${purchase.buyer.userId}
		</td>
		<td></td>
		<td align="left">${ purchase.receiverPhone }</td>
		<td></td>
		<td align="left">

		<c:if test="${purchase.tranCode=='1'}">
			���� ��� �� �Դϴ�.
		</c:if>
		<c:if test="${purchase.tranCode=='2'}">
			���� ����� ���� �Դϴ�.
		</c:if>
		<c:if test="${purchase.tranCode=='3'}">
			���� �ǸſϷ� ���� �Դϴ�.
		</c:if>

		</td>
		<td></td>
		<td align="left">
	
			<c:if test="${purchase.tranCode=='1'}">
				<!-- 
				 <a href="/purchase/updateTranCodeByProd?prodNo=${ purchase.purchaseProd.prodNo }&tranCode=2">���</a>
				 -->
			  <span class="hidden_link" >/purchase/updateTranCodeByProd?prodNo=${ purchase.purchaseProd.prodNo }&tranCode=2</span>
			   ���
			</c:if>
			<c:if test="${purchase.tranCode=='2'}">
			�����
			</c:if>
		    <c:if test="${purchase.tranCode=='3'}">
			�ǸſϷ�
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
		
			<c:set var="script" value="fncGetSaleList" scope="request"/>
		
			<jsp:include page="../common/pageNavigator.jsp"/>	
			
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>