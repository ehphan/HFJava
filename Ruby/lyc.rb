class LeapYearCalc
	
	def go
		puts "starting year?"
		start_year = gets.chomp.to_i
		puts "end year?"
		fin_year = gets.chomp.to_i
		i = start_year
		leap_years = Array.new
		for i in start_year..fin_year
			if (i%400 == 0 and i%100 == 0)
				leap_years << i
			elsif (i%4 == 0 and i%100 != 0)
				leap_years << i
			end
		end
	puts leap_years
	end
end

c = LeapYearCalc.new
c.go
