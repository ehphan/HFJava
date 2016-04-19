class LeapYearCalc
	
	def go
		puts "starting year?"
		start_year_string = gets.chomp
		start_year = start_year_string.to_i
		puts "end year?"
		fin_year_string = gets.chomp
		fin_year = fin_year_string.to_i
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
