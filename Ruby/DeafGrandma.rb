class DeafGrandma
	def speak
		say = gets.chomp
		bye_counter = 0
		while (bye_counter != 3)
			if (say == say.upcase)
				if say == "BYE"
					bye_counter = bye_counter + 1
				
					puts "hrm"
					say = gets.chomp
				else
					year = 1930 + rand(21)
					puts "NOT SINCE " + year.to_s + "!"
					say = gets.chomp
				end
				
			else
				puts "WHAT"
				say = gets.chomp
			end
		end
		puts "L8er nerd"
	end
end

s = DeafGrandma.new
s.speak