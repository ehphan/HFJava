class Name
	def greet
		puts "first name?"
			first_name = gets.chomp
		puts "last name?"
			last_name = gets.chomp
	puts "Hey " + first_name + " " + last_name + "!"
	end
end

s = Name.new
s.greet