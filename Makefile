ARGS ?=

compile:
	javac -d classes/ src/lib/*.java src/*.java

run: compile
	cd classes && java Program $(ARGS)


# make run ARGS="../test/input1.txt"