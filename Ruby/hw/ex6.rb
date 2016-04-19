print "Purchase Amount?"
purchase = gets.chomp.to_f


tax = 0.075
tax_amount = purchase * tax
total = tax_amount + purchase
puts "Your total is #{format("%.2f", total)}, with a tax of #{format("%.2f", tax_amount)}"