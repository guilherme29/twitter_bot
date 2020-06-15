
#cleanup any previous mess
rm server/*.class
pkill java
mkdir tests
rm tests/*

#recompiling
javac server/TestingServer.java server/TwitterServer.java


