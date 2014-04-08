# Simple makefile
# not spaces.
# Note: lines that start with a pound symbol are comments

#Compile source code by typing the command make compile
compile:
	javac *.java
#Run the Hello java main class by typing the command make run
run:
	java P4Arcade
#Clean the class files
clean:
	rm *.class