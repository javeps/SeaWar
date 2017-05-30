function ready_mid_left(id){
	var xml_left = 'xml/left.xml';
	var cont = $("#"+id);
	cont.empty();
	cont.append($('<ul>',{"id":"mn_mid_lt_menu","class":"menu_container"}));
	XMLLoad.loadXML(xml_left,left_xml);
}

function left_xml(xml){
	var data = $('cell', xml).get();
	var ul = $("#mn_mid_lt_menu");
	$(data).each(function(){
		var li = left_cell($(this));
		ul.append(li);
	});
	left_menu_init();
}

function left_cell(cell){
	var name = cell.find("name").text();
	var li_return = $('<li>');
	var menu_title = $('<div>',{"class":"menu_title"}).append($('<a>',{"text":name}));
	li_return.append(menu_title);
	var ul_cell = $('<ul>',{"class":"MM","ishide":"true"});
	ul_cell.append($('<li>',{"class":"split_img"}).append($('<img>',{'src':"images/menu_topline.gif"})));
	cell.find('menu').each(function(){
		var urlname = $(this).find('title').text();
		var url = $(this).find('requrl').text();
		var tp = $(this).find('type').text();
		var li = $('<li>');
		var isnull = url == "";
		var li_a = $('<a>',{"target":"_parent","href":"javascript:void(0)","url" : url,"text":urlname,"type":tp});//_top
		li_a.click(function(){
			left_cell_click($(this))
		});
		li.append(li_a);
		ul_cell.append(li);
	});
	li_return.append(ul_cell);
	return li_return;
}

function left_cell_click(cell){
	var type = cell.attr("type");
	type = parseInt(type,10);
	var name = cell.text();
	if(type >= 200)
		alert("没有权限!");
	switch(type){
		case 101:
			RM.chnInfo(RM.session,"cm",3000,name);
		break;
		case 102:
			RM.chnInfo(RM.session,"ty",3000,name);
		break;
	}
}

function left_menu_init(){
	var mu_tit = $(".menu_title");
	mu_tit.each(function(){
		var cur = $(this);
		var next = cur.next(".MM");
		next.attr("ishide","true").hide();
		cur.click(function(){
			var ishide = next.attr("ishide");
			if(ishide == "true"){
				var pre = $('ul[ishide][ishide=false]');
				pre.attr("ishide","true").slideUp(200);
				next.attr("ishide","false").slideDown(200);
			}else{
				next.attr("ishide","true").slideUp(200);
			}
		});
	});
	var show_cur = mu_tit.get(0);
	$(show_cur).next(".MM").attr("ishide","false").slideDown(300);
}