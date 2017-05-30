document.write('<script src="js/option/request.js" type="text/javascript" ></script>');
document.write('<script src="js/option/tools.js" type="text/javascript" ></script>');
document.write('<script src="js/load.js" type="text/javascript" ></script>');
document.write('<script src="js/head.js" type="text/javascript" ></script>');
document.write('<script src="js/foot.js" type="text/javascript" ></script>');
document.write('<script src="js/mid.js" type="text/javascript" ></script>');
document.write('<script src="js/ready.js" type="text/javascript" ></script>');
var invalId = -1;

function ready(){
	var sess = getQueryString("session");
	RM.session = sess;
	setInterval("checkSession()",200);
	if(!RM.session){
		return;
	}
	
	ready_foot("mn_bot");
	ready_head("mn_top",getQueryString("name"));
	ready_mid("mn_mid");
}

