function ready_head(id,adname){
	var xml_bm = 'xml/head.xml';
	var cont = $("#"+id);
	XMLLoad.loadTxt(xml_bm,function(txt){
		cont.append(txt);
		setAdminname(adname);
	});
}

function setAdminname(name){
	$("#head_admin_name").html(name);
}

function logout(){
	window.location.href = "login.html";
}

function checkSession(){
	if(!RM.session)
		logout();
}