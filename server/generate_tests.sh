cd ..
mkdir test_results

# getting temperature and humidity
node sensors/raspberry.js > test_results/raspberry_tests
# getting moisture and light
node sensors/arduino.js > test_results/arduino_tests

