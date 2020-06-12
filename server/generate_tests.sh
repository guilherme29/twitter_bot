cd ..
mkdir tests_results

# getting temperature and humidity
node sensors/raspberry.js > tests_results/respberry_tests
# getting moisture and light
node sensors/arduino.js > tests_results/arduino_tests

