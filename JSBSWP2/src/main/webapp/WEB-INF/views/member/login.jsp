<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<table id="loginTable">
	<form action="member.login" method="post" name="loginForm" onsubmit="return loginCheck();">
			<tr>
				<td align="center">
					<input name="jm_id"  placeholder="ID" maxlength="10" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="jm_pw" placeholder="PW" maxlength="10" autocomplete="off" type="password">
				</td>
			</tr>
			<tr>
				<td align="center">
					<button>로그인</button>&nbsp;
	</form>
					<button onclick="goJoin();">회원가입</button>
				</td>
			</tr>
		</table>
</body>
</html>



