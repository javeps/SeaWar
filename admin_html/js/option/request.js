var RM = {};
RM.session = 0;
RM.name = "";
RM.URL = "http://118.144.78.173:20001/";
RM.login = function(){
	var that = this,args = arguments;
	var lgid = args[0],lgpwd = args[1];
	
	var data = {lgid:lgid,lgpwd:lgpwd,datatype:"jsonp"};
	var callBack = function(back){
		if(back.status && back.status < 0){
			alert(back.tip);
			return;
		}
		that.session = escape(back.session);
		that.name = escape(back.name);
		window.location.href = "index.html?session="+that.session +"&name="+that.name;
	};
	requestObj.JqueryReq(that.URL+"lgAdmin",data,callBack);
	/*
	if(lgid == adminId && lgpwd == adminPwd){
		that.session = 12345670;
		var form = $("#login_form");
		form.attr("action","index.html?session="+RM.session);
	}
	*/
}

RM.chnInfo = function(){
	var that = this,args = arguments;
	var session = that.session || args[0],chn = args[1],svcid = args[2],title = args[3];
	that.session = session;
	var data = {session:session,chn:chn,svcid:svcid,datatype:"jsonp"};
	var callBack = function(back){
		if(back.status && back.status < 0){
			alert(back.tip);
			return;
		}
		console.info(back);
		mid_rt_data(title,chn,back);
	};
	requestObj.JqueryReq(that.URL+"chnInfo",data,callBack);
}