<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바 보안코딩 기반 스프링 웹 개발자 양성과정 카페</title>
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/member.css">
<link rel="stylesheet" href="resources/css/sns.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/js/go.js"></script>
<script type="text/javascript" src="resources/js/validCheck.js"></script>
<script type="text/javascript" src="resources/js/check.js"></script>
<script type="text/javascript" src="resources/js/jsbswp_jQuery.js"></script>
</head>
<body>
	
	<table id="siteTitleArea">
		<tr>
			<td id="siteMenuArea" align="center">
				<table id="siteMenu">
					<tr>
						<td align="center"><a href="sns.go"><img src="resources/img/sns.png"></a></td>
						<td align="center"><a href=""><img src="resources/img/dataroom.png"></a></td>
						<td align="center"><a href=""><img src="resources/img/gallery.png"></a></td>
						<td align="center"><a href=""><img src="resources/img/community.png"></a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td id="siteTitleArea2" align="center">
				<table id="siteTitle">
					<tr>
						<td onclick="goHome();">자바 보안코딩 기반 스프링 웹 개발자 양성과정 카페
						<span id="resultArea">${result }</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table id="siteLoginArea">
		<tr>
			<td><jsp:include page="${loginPage }"></jsp:include> </td>
		</tr>
	</table>
	<table id="siteContentArea">
		<tr>
			<td align="center">
				<jsp:include page="${contentPage }"></jsp:include>
			</td> 
		</tr>
	</table>
</body>
</html>





