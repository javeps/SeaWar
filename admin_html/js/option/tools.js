function getMonthKeyBy(index){
	var arr = ["january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"];
	var len = arr.length;
	if(index >= len)
		index = len -1;
	index = index < 0 ? 0 : index;
	return arr[index];
}

function getMonthStrBy(index){
	var arr = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];
	var len = arr.length;
	if(index >= len)
		index = len -1;
	index = index < 0 ? 0 : index;
	return arr[index];
}

function getChnName(chn){
	var map = {"cm":"移动MM", "ty":"天翼空间"};
	var r = map[chn];
	if(!r)
		r = "";
	return r;
}