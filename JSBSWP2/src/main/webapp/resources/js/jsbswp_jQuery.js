function connectSNSWriteFormSummonEvent(){
	
	var snsWritevar = false;
	$("#snsWriteHideBtn").click(function(){
		if (snsWritevar) {
			$("#snsWriteArea").css("bottom", "-130px");
			$("#snsWriteHideBtn").text("▲");
		} else {
			$("#snsWriteArea").css("bottom", "10px");
			$("#snsWriteHideBtn").text("▼");
		}
		snsWritevar = !snsWritevar;
	});		
}

$(function() {
	connectSNSWriteFormSummonEvent();
});