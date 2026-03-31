ARGS ?=

compile:
	javac -d classes/ src/lib/*.java src/*.java

compress: compile
	cd classes && java Program $(ARGS)

# make compress ARGS="../test/input1.txt"