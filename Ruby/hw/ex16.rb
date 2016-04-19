filename = ARGV.first

puts "We're going to erase #{filename}"
puts "If you don't want that, hit ctrl - c."
puts "If you do want that, hit RETURN"

$stdin.gets

puts "Opening the file..."
target = open(filename, 'w')

puts "Truncating the file. Goodbye!"
target.truncate(0)

puts "Now i'm going to ask you for three lines"

print "line 1:"
line1 = $stdin.gets.chomp
print "line 2:"
line2 = $stdin.gets.chomp
print "line 3:"
line3 = $stdin.gets.chomp

puts "I'm going to write these to a file."

lines = line1 + "\n" + line2 + "\n" + line3

target.write(lines)

puts "and finally, we close it."
target.close