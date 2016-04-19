function animate_string(id){
	var element = document.getElementById(id);
	var textNode = element.childNodes[0];
	var text = textNode.data;
	var text2 = document.getElementById(id).childNodes[0].data;


setInterval(function()
{
	//text = text[text.length - 1] + text.substring(0, text.length - 1);
	text2 = text2[1] + text2.substring(2,text2.length) + text2[0];
		textNode.data = text2;
}, 100);
}