#!/bin/bash

JDK=/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre
MAVEN=/Users/diego/Desa/apache-maven-3.5.0
NODE=/Users/diego/Desa/node-v6.11.4-darwin-x64

# JAVA
JAVA_HOME=$JDK
PATH=$JAVA_HOME/bin:$PATH

# Maven
MAVEN_HOME=$MAVEN
PATH=$MAVEN_HOME/bin:$PATH

# PhantomJS
# phantomjs.binary.path
# set OS to macos or linux
OS=macos
PATH=./webdriver/$OS/:$PATH

export PATH
