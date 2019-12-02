function joinCheck() {
	var idInput = document.joinForm.jm_id;
	var pwInput = document.joinForm.jm_pw;
	var pwChkInput = document.joinForm.jm_pwChk;
	var nameInput = document.joinForm.jm_name;
	var addr1Input = document.joinForm.jm_addr1;
	var addr2Input = document.joinForm.jm_addr2;
	var addr3Input = document.joinForm.jm_addr3;
	var photoInput = document.joinForm.jm_photo;

	if (isEmpty(idInput) || containsHS(idInput)) {
		alert("ID?");
		idInput.value = "";
		idInput.focus();
		return false;
	} else if (isEmpty(pwInput) || notEquals(pwInput, pwChkInput)
			|| notContains(pwInput, "1234567890")) {
		alert("PW?");
		pwInput.value = "";
		pwChkInput.value = "";
		pwInput.focus();
		return false;
	} else if (isEmpty(nameInput)) {
		alert("이름확인");
		nameInput.value = "";
		nameInput.focus();
		return false;
	} else if (isEmpty(addr1Input) || isEmpty(addr2Input)
			|| isEmpty(addr2Input)) {
		alert("주소확인");
		addr1Input.value = "";
		addr2Input.value = "";
		addr3Input.value = "";
		addr1Input.focus();
		return false;
	} else if (isEmpty(photoInput)
			|| (isNotType(photoInput, "png") && isNotType(photoInput, "gif")
					&& isNotType(photoInput, "jpg") && isNotType(photoInput,
					"bmp"))) {
		alert("사진확인");
		return false;
	}

	return true;
}

function loginCheck() {
	var idInput = document.loginForm.jm_id;
	var pwInput = document.loginForm.jm_pw;

	if (isEmpty(idInput) || isEmpty(pwInput)) {
		alert("?");
		idInput.value = "";
		pwInput.value = "";
		idInput.focus();
		return false;
	}

	return true;
}

function snsWriteCheck() {
	var txtInput = document.snsWriteForm.js_txt;

	if (isEmpty(txtInput)) {
		alert("입력하지 않았습니다.");
		txtInput.focus();
		return false;
	}
	return true;
}

function updateMemberCheck() {
	var pwInput = document.updateMemberForm.jm_pw;
	var pwChkInput = document.updateMemberForm.jm_pwChk;
	var nameInput = document.updateMemberForm.jm_name;
	var addr1Input = document.updateMemberForm.jm_addr1;
	var addr2Input = document.updateMemberForm.jm_addr2;
	var addr3Input = document.updateMemberForm.jm_addr3;
	var photoInput = document.updateMemberForm.jm_photo;

	if (isEmpty(pwInput) || notEquals(pwInput, pwChkInput)
			|| notContains(pwInput, "1234567890")) {
		alert("PW?");
		pwInput.value = "";
		pwChkInput.value = "";
		pwInput.focus();
		return false;
	} else if (isEmpty(nameInput)) {
		alert("이름확인");
		nameInput.value = "";
		nameInput.focus();
		return false;
	} else if (isEmpty(addr1Input) || isEmpty(addr2Input)
			|| isEmpty(addr2Input)) {
		alert("주소확인");
		addr1Input.value = "";
		addr2Input.value = "";
		addr3Input.value = "";
		addr1Input.focus();
		return false;
	} else if (isEmpty(photoInput)) {
		return true;
	} else if (isNotType(photoInput, "png") && isNotType(photoInput, "gif")
			&& isNotType(photoInput, "jpg") && isNotType(photoInput, "bmp")) {
		alert("사진확인");
		return false;
	}
	return true;
}

function snsCommentWriteCheck(f) {
	var txtInput = f.txt;
	
	if (isEmpty(txtInput)) {
		alert("채워넣으세요");
		txtInput.focus();
		return false;
	}
	return true;
	
}