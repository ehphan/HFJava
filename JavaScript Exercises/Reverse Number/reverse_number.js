function reverse_number(id){
	var text = document.getElementById(id).innerHTML.data;

	text = text + "";
	return text.split("").reverse().join("");
	window.alert(text);
	textNode.data = text

}