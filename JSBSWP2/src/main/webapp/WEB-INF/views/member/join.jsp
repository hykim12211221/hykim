<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<form action="member.join"
		method="post" enctype="multipart/form-data"
		name="joinForm" onsubmit="return joinCheck();">
		<table id="joinTable">
			<tr>
				<td id="joinTableTitle" colspan="2" align="center">
					회원가입
				</td>
			</tr>
			<tr>
				<td class="td1">ID</td>
				<td class="td2"><input name="jm_id" placeholder="필수, 영어/숫자만" maxlength="10" autocomplete="off" autofocus="autofocus"></td>
			</tr>
			<tr>
				<td class="td1">PW</td>
				<td class="td2"><input name="jm_pw" placeholder="필수, 숫자 하나이상 반드시" type="password" maxlength="10" autocomplete="off"></td>
			</tr>
			<tr>
				<td class="td1">PW확인</td>
				<td class="td2"><input name="jm_pwChk" placeholder="필수, 숫자 하나이상 반드시" type="password" maxlength="10" autocomplete="off"></td>
			</tr>
			<tr>
				<td class="td1">이름</td>
				<td class="td2"><input name="jm_name" placeholder="필수" maxlength="10" autocomplete="off"></td>
			</tr>
			<tr>
				<td class="td1">주소</td>
				<td class="td2">
					<input name="jm_addr1" maxlength="30" autocomplete="off" placeholder="주소"><br>
					<input name="jm_addr2" maxlength="30" autocomplete="off" placeholder="상세주소">
					<input name="jm_addr3" maxlength="5" autocomplete="off" placeholder="우편번호"><br>
				</td>
			</tr>
			<tr>
				<td class="td1">사진</td>
				<td class="td2"><input name="jm_photo" type="file"></td>
			</tr>
			<tr>
				<td colspan="2" align="center" style="background-color: #FFFFFF;">
					<button>가입</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>