ARGS ?=

compile:
	javac -d classes/ src/lib/*.java src/*.java

run: compile
	cd classes && java Program $(ARGS)