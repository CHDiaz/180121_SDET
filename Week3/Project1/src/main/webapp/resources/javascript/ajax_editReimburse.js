function goBack() {
	window.history.back();
}

function denyRe() {
	var r = document.getElementById("response").value;
	
	if(r == 0){
		var rd = document.getElementById("resdiv");
		
		var input = document.createElement("input");
		input.type = 'text';
		input.id = 'denyres';
		input.required = true;
		rd.appendChild(input);
	} else {
		var i = document.getElementById("denyres");
		if(i != null) {
			i.remove();
		}
	}
}

function appResponse() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../ApproveReimburse");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("update approval info");
		}
	}
	
	var id = document.getElementById("rid").innerHTML;
	var r = document.getElementById("response").value;
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + id + "&response=" + r); //app response & emp id
	
	// TODO not back but like redirect
	window.history.back();
}

function readPageChanges() {
	var grade = document.getElementById("grade").value;
	var r = document.getElementById("response");
	var ai = document.getElementById("reqinfo");
	var addinfo = document.getElementById("addinfo");
	var pr = document.getElementById("projreimb");

	
	if(grade != "") {
		aPostGradeUpdate();
	}
	if(r != null && r.value != -1) {
		var dr = document.getElementById('denyres');
		console.log(dr);
		if(dr == null) {
			appResponse();
		} else if(r.value == 0 && dr.value == ""){
			document.getElementById('denyres').value = "MUST ENTER A REASON!";
		} else if(r.value == 0 && dr.value != "") {
			insertAddInfo();
		}
		appResponse();
	}	
	if(ai != null && ai != "") {
		insertAddInfo();
		updateInfoReq();
	}
	if(addinfo != null) {
		insertAddInfo();
	}
	if(projreimb != null) {
		
	}
}

function aPostGradeUpdate() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../UpdateGradeServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("update grade");
		}
	}
	
	var r = document.getElementById("rid").innerHTML;
	var g = document.getElementById("grade").value;
	g = parseInt(g);
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + r + "&grade=" + g);
}

// TODO parameterize function
function insertAddInfo() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../InsertNewAddedInfoServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("info added/req");
		}
	}
	
	var r = document.getElementById("response");
	var ai = document.getElementById("reqinfo");
	var addinfo = document.getElementById("addinfo");
	
	var rid = document.getElementById("rid").innerHTML;
	parseInt(rid);
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	if(r != null && r.value == 0) {
		// TODO BUG message posting twice when denied
		var dr = document.getElementById("denyres").value;
		dr = "DENIED: " + dr;
		xhr.send("rid=" + rid + "&info=" + dr);
	} else if(ai != null && ai.value != ""){
		var ai = ai.value;
		xhr.send("rid=" + rid + "&info=" + ai);
	} else if(addinfo != null && addinfo.value != "") {
		xhr.send("rid=" + rid + "&info=" + addinfo.value);
	}
}

function updateInfoReq() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../UpdateInfoReqServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("update info req empid");
		}
	}
	
	var r = document.getElementById("rid").innerHTML;
	var rei = document.getElementById("reqinfoemp").value;
	r = parseInt(r);
	rei = parseInt(rei);
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + r + "&info_empid=" + rei);
}

function updateProjReimb() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../-"); // TODO create servlet for updateProjReimb()
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("update projReimb");
		}
	}
	
	var r = document.getElementById("rid").innerHTML;
	var pr = document.getElementById("projreimb").innerHTML;
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + r + "&projreimb=" + pr); 
}