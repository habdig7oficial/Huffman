ARGS ?=

compile:
	javac -d classes/ src/*.java

run: compile
	cd classes && java Program $(ARGS)