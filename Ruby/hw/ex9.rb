#our first ARGV, which is the name of the text file we're trying to open
filename = ARGV.first
#txt is our unpacked filename, the filename we declared on run
txt = open(filename)
#reads back your file's name
puts "Here's your file #{filename}:"
#prints out your txt document
print txt.read

print "\nType the filename again: "
file_again = $stdin.gets.chomp

txt_again = open(file_again)

print txt_again.read