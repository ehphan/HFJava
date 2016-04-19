puts "You enter a dark room, 2 doors. door 1 or door 2?"

print "> "
door = $stdin.gets.chomp

if door == "1"
	puts "choose 1 or 2 again"
	puts "1. die in hell"
	puts "2. die twice in hell"

	print"> "
	bear = $stdin.gets.chomp

	if bear == "1"
		puts "fuck you 1"
	elsif bear == "2"
		puts "fuck you 2"
	else
		puts "Well, you typed %s. fucker" % bear
	end
	
elsif door == "2"
	puts "You chose 2"
	puts "1. fuck"
	puts "2. shit"
	puts "3. caca"

	print "> "
	insanity = $stdin.gets.chomp

	if insanity == "1" || insanity == "2"
		puts "you shitty guy you die"
	else
		puts "nobody cares what choices you make"
	end	

else
	puts "jesus can't you follow simple instructions"
	
end