document.write('<script src="js/left.js" type="text/javascript" ></script>');
document.write('<script src="js/right.js" type="text/javascript" ></script>');

function ready_mid(id){
	var xml_bm = 'xml/mid.xml';
	var cont = $("#"+id);
	XMLLoad.loadTxt(xml_bm,function(txt){
		cont.append(txt);
		ready_right("mid_rt");
		ready_mid_left("mid_lt");
	});
}