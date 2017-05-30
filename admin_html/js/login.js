document.write('<script src="js/option/request.js" type="text/javascript" ></script>');
document.write('<script src="js/load.js" type="text/javascript" ></script>');
document.write('<script src="js/foot.js" type="text/javascript" ></script>');
document.write('<script src="js/ready.js" type="text/javascript" ></script>');
var adminId = "admin";
var adminPwd = "admin";

function ready(){
	initHead();
	initMidd();
	initFoot();
}

function initHead(){
	var xml_lg_hd = 'xml/login_head.xml';
	XMLLoad.loadTxt(xml_lg_hd,function(txt){
		$("#lgc_top").append(txt);
	});
}

function initMidd(){
	var xml_lg_md = 'xml/login.xml';
	XMLLoad.loadTxt(xml_lg_md,function(txt){
		$("#lgc_mid").append(txt);
		setInterval("reload_screen()",200);
	});
}

function initFoot(){
	ready_foot("lgc_bot");
}

function onsubmit_login(){
	var r = false;
	var lgid = $("#login_id");
	var lgpd = $("#login_pwd");
	var lgidval = lgid.val();
	var lgpdval = lgpd.val();
	if(lgidval.length > 0 && lgpdval.length >0){
		RM.login(lgidval,lgpdval);
		r = false;
	}
	return r;
}

function cancel_login(){
	var lgid = $("#login_id");
	var lgpd = $("#login_pwd");
	lgid.val("");
	lgpd.val("");
}

function reload_screen(){
	var height = winHeight - 115;
	$("#login_middle .lg_mid_lt_rt").css("height",height);
}

var winWidth = 0;
var winHeight = 0;
function findDimensions() //函数：获取尺寸
{
	 //获取窗口宽度
	 if (window.innerWidth)
		   winWidth = window.innerWidth;
	 else if ((document.body) && (document.body.clientWidth))
		   winWidth = document.body.clientWidth;
	 //获取窗口高度
	 if (window.innerHeight)
		   winHeight = window.innerHeight;
	 else if ((document.body) && (document.body.clientHeight))
		   winHeight = document.body.clientHeight;
   
	 //通过深入Document内部对body进行检测，获取窗口大小
	 if (document.documentElement  && document.documentElement.clientHeight &&
										  document.documentElement.clientWidth)
	 {
		 winHeight = document.documentElement.clientHeight;
		 winWidth = document.documentElement.clientWidth;
	 }
}
findDimensions();                  //调用函数，获取数值
window.onresize=findDimensions;