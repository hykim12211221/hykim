function bye() {
	var ok = confirm("탈퇴하시겠습니까?");
	if (ok) {
		location.href = "member.bye";
	}
}

function goHome() {
	location.href = "index.do";
}

function goJoin() {
	location.href = "member.join.go";
}

function goMemberInfo() {
	location.href = "member.info";
}

function logout() {
	var ok = confirm("로그아웃??");
	if (ok) {
		location.href = "member.logout";
	}
}

function updateSNS(n, t, p) {
	t = prompt("수정하실 내용을 작성하세요.", t);
	if (t != null && t.length > 0 && t.length < 250) {
		location.href = "sns.update?js_no=" + n + "&js_txt=" + t + "&p=" + p;
	}
}

function deleteSNS(n) {
	var ok = confirm("삭제하십니까?");
	if (ok) {
		location.href = "sns.delete?js_no=" + n;
	}
}

function deleteSNSComment(n, p) {
	var ok = confirm("삭제???");
	if (ok) {
		location.href = "sns.reply.delete?jsr_no=" + n + "&p=" +p;	
	}
}