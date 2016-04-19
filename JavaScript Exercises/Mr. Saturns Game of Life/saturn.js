$(document).ready(function(){

	var starting_cells = [
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	[0,0,0,0,0,0,0,0,0,0],
	];


	for(var i = 0; i <= starting_cells.length; i++){
		for (var j = 0; j <= starting_cells[i].length; j++){
			$('#grid_container').append('<div class="grid_square"></div>');
		}
		$('#grid_container').append('<div class="new_row"></div>');
	}
	$('.grid_square').click(function(){
		$(this).addClass('saturn');
	});

});



function changeState(){
	if ($(this).hasClass('saturn')){
		$(this).removeClass('saturn');
	}
	else{
		$(this).addClass('saturn');
	}
}
/*
$('.grid_square').click(function(){
	$(this).addClass('saturn');
});
*/