var start = function(option){
	$('#grid_container').html("");

	var input = prompt('type an int btwn 1-40');

	if (input >= 1 && input <= 128){
		var square_size = $('#grid_container').width()/input - 2;

		for(var i = 1; i <= input; i++){
			for(var j = 1; j <= input; j++){
				$('#grid_container').append('<div class="grid_square"></div>');
			}
			$('#grid_container').append('<div class="new_row"></div>');
		}
		$('.grid_square').css('width',square_size);
		$('.grid_square').css('height',square_size);

		$('.grid_square').click(function(){
			switch(option){
				case 1:
					$(this).addClass('saturn');
					break;
			}
		});
	}
}