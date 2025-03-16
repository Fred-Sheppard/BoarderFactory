#!/bin/bash

# Clean output directory
rm -rf out
mkdir -p out

# Compile all source files including tests
javac -d out -cp "lib/*:src" src/simulation/*.java src/test/unit/*.java

# Run all tests using JUnit Platform
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path out --select-package unit