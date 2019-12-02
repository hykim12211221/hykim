<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="loginSuccessTable">
		<tr>
			<td rowspan="2" style="width:70px;">
				<img src="resources/img/${sessionScope.loginMember.jm_photo }">
			</td>
			<td style="height:20px;">${sessionScope.loginMember.jm_id }</td>
		</tr>	
		<tr>
			<td align="right" valign="top">(<span id="loginSuccessName">${sessionScope.loginMember.jm_name }</span>)</td>
		</tr> 
		<tr>
			<td colspan="2" align="center">
				<button onclick="goMemberInfo();">정보확인</button>
				<button onclick="logout();">로그아웃</button>
			</td>
		</tr>
	</table>
	
	
</body>
</html>







