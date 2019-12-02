<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="snsIndex" action="sns.search" onsubmit="return snsIndexCheck();" method="post">
	<table id="snsIndex">
		<tr>
			<td><input name="search" type="text"></td>
			<td><button>검색</button></td>
		</tr>
	</table>
	</form>
	<c:if test="${curPage != 1 }">
		<a id="snsL" href="sns.page.change?p=${curPage - 1 }">&lt;</a>
	</c:if>
	<c:if test="${curPage != pageCount }">
		<a id="snsR" href="sns.page.change?p=${curPage + 1 }">&gt;</a>
	</c:if>
	<c:forEach var="sm" items="${msgs }">
		<table class="aSNSMsg">
			<tr>
				<td class="asmImgTd" align="center" valign="top" rowspan="4">
					<img src="resources/img/${sm.jm_photo }">
				</td>
				<td class="asmOwner">${sm.js_owner }</td>
			</tr>
			<tr>
				<td align="right">
					<fmt:formatDate value="${sm.js_when }" type="both" dateStyle="long" timeStyle="short"/>
				</td>
			</tr>
			<tr>
				<td class="asmTxt">${sm.js_txt }</td>
			</tr>
			
			<tr>
				<td id="SNSComment">
				<c:forEach var="sr" items="${sm.js_replys }">
					<span class="SNSComment_id">${sr.jsr_owner }</span>
					<span class="SNSComment_com">${sr.jsr_txt }</span>
					<span class="SNSComment_date"><fmt:formatDate value="${sr.jsr_when }" type="both" dateStyle="short" timeStyle="short"/></span>
					<c:if test="${sr.jsr_owner == sessionScope.loginMember.jm_id }">
					<button class="SNSComment_btn" onclick="deleteSNSComment(${sr.jsr_no}, ${curPage });">삭제</button>
					</c:if><br>
				</c:forEach>

					<c:if test="${sessionScope.loginMember != null }">
					<form action="sns.reply.write" onsubmit="return snsCommentWriteCheck(this);">
						<span class="SNSComment_id">${sessionScope.loginMember.jm_id }</span>
						<input id="SNSComment_me" type="hidden" name="token" value="${token }">
						<input type="hidden" name="jsr_js_no" value="${sm.js_no }">
						<input type="hidden" name="p" value="${curPage }">
						<input class="SNSComment_txt" name="jsr_txt" maxlength="800">
						<button class="SNSComment_btn">쓰기</button>
					</form>
					</c:if>
				</td>
		</tr>
			
			<c:if test="${sm.js_owner == sessionScope.loginMember.jm_id }">
			<tr id="InsDel">
				<td colspan="2" align="right">
					<button onclick="updateSNS(${sm.js_no}, '${sm.js_txt}', ${curPage });">수정</button>
					<button onclick="deleteSNS(${sm.js_no });">삭제</button>
				</td>
			</tr>
			</c:if>
		</table>
	</c:forEach>
	<!-- ----------------------------------------- -->
	<c:if test="${sessionScope.loginMember != null }">
		<table id="snsWriteArea" style="bottom:-130px;">
			<tr>
				<td align="center">
					<span id="snsWriteHideBtn">▲</span>
				</td>
			</tr>
			<tr>
				<td align="center">
					<form action="sns.write" method="post" 
						name="snsWriteForm" onsubmit="return snsWriteCheck();">
						<input type="hidden" name="token" value="${token }">
						<table id="snsWriteTable">
							<tr>
								<td id="swtTd1"><textarea name="js_txt" placeholder="말" maxlength="250"></textarea></td>
								<td id="swtTd2"><button>쓰기</button></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>