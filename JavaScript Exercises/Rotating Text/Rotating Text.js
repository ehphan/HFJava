function animate_string(id){
	var element = document.getElementById(id);
	var textNode = element.childNodes[0];
	var text = textNode.data;
	var text_2 = document.getElementById(id).childNodes[0].data;


setInterval(function()
{
	text = text[text.length - 1] + text.substring(0, text.length - 1);
		textNode.data = text;
		window.alert(text.length - 1);
}, 10000);
}