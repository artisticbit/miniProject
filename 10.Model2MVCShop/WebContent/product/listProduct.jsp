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
 	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/css/admin.css" type="text/css">

 	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
 	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
	
		function fncGetProductList(currentPage){
			document.getElementById("currentPage").value = currentPage;
			document.detailForm.submit();
		}
		
	
		 $(function() {
			 $( "td.ct_btn01:contains('검색')" ).on("click" , function() {
		
			 $("#currentPage").val("1");
			 $("form").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
			 
			//	fncGetUserList(1);
			});
			
			 ///////링크숨기기//////////
			 $(".hidden_link").css("display","none");
			// $(".hidden_link").css("visibility","hidden");
		
			/////상품명 이벤트////////////
			$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
					
				//alert($('span',$(this)).text());		
				
				var url=$('span',$(this)).text();
				if(url!=''){
					self.location = url;					
				}
				// console.log('${param.menu}');
			});
			//////////////////////////////
			/////////배송 이벤트//////////////////////
			$( ".ct_list_pop td:nth-child(9)" ).on("click" , function() {
					//self.location ="/user/getUser?userId="+$(this).text().trim();
				alert($('span',$(this)).text());		
				
				var url=$('span',$(this)).text();
				self.location = url;
				// console.log('${param.menu}');
			});
			/////////////////////////////////////////////////////
			
			//$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
			var arrayProdName=$( ".ct_list_pop td:nth-child(3)" );
			arrayProdName.each(function(index,elem){
				//alert($('span' ,$(elem)).text());
				var text=$('span' ,$(elem)).text();
				if(text!=''){
					$(this).css("color" , "red");
				}
				
				
				$(this).attr("title","").tooltip({
					//content:"<img src='/images/uploadFiles/aaa.jpg'/>"
					content:function(){
						
						var fileName="";
						
						$.ajax({
							url:"/product/json/getProduct/10047",
							method:"GET",
							datatype:"json",
							async:false,
							success:function(jsonData,status){
								fileName=jsonData.fileName;
								//alert(JSON.stringify(jsonData));
							//	alert("1:"+fileName);
								
							}
							
						})
					//	alert("2:"+fileName);
						return "<img src='/images/uploadFiles/"+fileName+"' />";
					}
				});
				//alert($(elem).text());
			});
			
			
			var test=$('');
			
			//$("h7").css("color" , "red");
			
						
			$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
			
			
		//	console.log ( $(".ct_list_pop:nth-child(4)" ).html() );
		
		});	
		 

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
		
		$(function(){
			$('#jsonTest').on('click',function(){
				$.ajax({
					url:"/product/json/listProduct",
					method:"POST",
					datatype:"json",
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					data:JSON.stringify({	
						searchKeyword:"가"
					})
					
				})
				
				
			})
			
		});
	
	</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm">
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
					value="${!empty search.searchKeyword ? search.searchKeyword: ""}" />
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
						<!-- 
						<a href="javascript:fncGetProductList('1');">검색</a>
						 -->
						 검색
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
					<td>
						<button id="jsonTest" type="button" >test</button>
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
				<td align="left">
				<!-- 
				<a href="/product/getProduct?prodNo=${ prod.prodNo }&menu=${param.menu}">${ prod.prodName }</a>
				 -->
				 <span class="hidden_link" >/product/getProduct?prodNo=${ prod.prodNo }&menu=${param.menu}</span>			
				 ${ prod.prodName }
				</td>
						</c:when>
						<c:otherwise>
				<td align="left">${ prod.prodName }</td>
						</c:otherwise>
					</c:choose>
				</c:if> 
				
				<c:if test="${ user.role=='admin' }">				
				<td align="left">
				<!-- 
				<a href="/product/getProduct?prodNo=${ prod.prodNo }&menu=${param.menu}">${ prod.prodName }</a>
				 -->
				 <span class="hidden_link" >/product/getProduct?prodNo=${ prod.prodNo }&menu=${param.menu}</span>
				 ${ prod.prodName }
				</td>	
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
				배송전 
				<span class="hidden_link" >/purchase/updateTranCodeByProd?prodNo=${ prod.prodNo }&tranCode=2</span>
				<!-- 
				<a href="/purchase/updateTranCodeByProd?prodNo=${ prod.prodNo }&tranCode=2">배송</a>
				 -->
				 배송
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
