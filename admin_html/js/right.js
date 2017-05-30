function ready_right(id){
	var xml_bm = 'xml/right.xml';
	var cont = $("#"+id);
	XMLLoad.loadTxt(xml_bm,function(txt){
		cont.append(txt);
	});
}

function mid_rt_data(){
	var args = arguments;
	var title = args[0];
	var chn = args[1];
	var map = args[2];
	map.chnname = getChnName(chn);
	mid_rt_top(title);
	mid_rt_mid(map);
}

function mid_rt_top(){
	var args = arguments;
	var title = args[0];
	var rtTop = $("div.rt_top_md").empty();
	if(!title){
		return;
	}
	rtTop.append(
		$('<span>',{"class":"tittxt","text":title})
	)
}

function mid_rt_mid(map){
	$("#cont_welcome").hide();
	var ul = $("#cont_m_ul").empty();
	ul.append(
		$("<ul>").append(
			$('<li>',{"class":"head_title"}).append($("<span>",{"text":map.chnname}))
		)
	);
	var ul_head = $("<ul>");
	var li_head = $('<li>').append(ul_head);
	ul.append(li_head);
	ul_head.append(
		$('<li>',{"class":"ul_li_head w_01"}).append($("<span>",{"text":"当前时间"})),
		$('<li>',{"class":"ul_li_head w_02"}).append($("<span>",{"text":"在线人数"})),
		$('<li>',{"class":"ul_li_head w_02"}).append($("<span>",{"text":"注册人数"})),
		$('<li>',{"class":"ul_li_head w_03"}).append($("<span>",{"text":"充值信息"}))
	);
	
	var ul_cont = $("<ul>");
	var li_cont = $('<li>').append(ul_cont);
	ul.append(li_cont);
	
	//map.totalMap = {"january" : 12, "february" : 12, "march" : 12, "april": 12, "may": 12, "june": 12, "july": 12, "august": 12, "september": 12, "october": 12, "november": 12, "december": 12}
	
	ul_cont.append(
		$('<li>',{"class":"ul_li_cont w_01"}).append($("<span>",{"text":map.now_time})),
		$('<li>',{"class":"ul_li_cont w_02"}).append($("<span>",{"text":map.online})),
		$('<li>',{"class":"ul_li_cont w_02"}).append($("<span>",{"text":map.regist}))
	);
	
	if(map.totalMap){
		 var curDate = new Date();
		 var month = curDate.getMonth();
		 var li_month = $('<li>',{"class":"ul_li_cont w_03"}).appendTo(ul_cont);
		 var li_m_uh = $("<ul>",{"class":"ul_month"}).appendTo(li_month);
		 var li_m_uc = $("<ul>",{"class":"ul_month"}).appendTo(li_month);
		 var list = new Array();
		 var bg = month - 5;
		 var ed = month;
		 bg = bg < 0 ? 0 : bg;
		 
		 if(bg == ed){
		    list[0] = month;
		 }else{
			for(var i = 0; i < 5; i++){
				list[i] = bg + i;
			}
		 }
		 
		for(var i = 0; i < list.length; i++){
			var li_list = list[i];
			li_m_uh.append(
				$('<li>',{"class":"month"}).append($("<span>",{"text":getMonthStrBy(li_list)}))
			);
			
			li_m_uc.append(
				$('<li>',{"class":"month"}).append($("<span>",{"text":map.totalMap[getMonthKeyBy(li_list)]}))
			);
		}
		 
		li_month.append(
			
		)
	}else{
		$('<li>',{"class":"ul_li_cont w_03"}).append($("<span>",{"text":"暂无"})).appendTo(ul_cont);
	}
}