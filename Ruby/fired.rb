class Fired
	def fire
		puts "WHAT DO YOU WANT UNDERLING?!"
			request = gets.chomp
		puts "WHAT DO YOU MEAN \"" + request.upcase + "\" YOU'RE FUCKING FIRED"
	end
end

s = Fired.new
s.fire