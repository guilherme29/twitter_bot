public class TwitterServer {




//
//    /**
//     * Method to fake tests just for testing
//     * @return Fake test results
//     */
//    private static float[] getFakeTests(){
//        float[] tests = new float[4];
//        float moisture = 53;
//        float light = 91;
//        float humidity = 56;
//        float temperature = 24;
//
//        tests[0] = moisture;
//        tests[1] = light;
//        tests[2] = humidity;
//        tests[3] = temperature;
//        return tests;
//    }
//
//    /**
//     * Runs the testing script and gets the test results.
//     * The order of the tests is as follows: moisture soil level, light intensity, humidity, temperature.
//     * @return Test results.
//     */
//    private static float[] getTests(){
//        String testResults = "";
//        String[] testResultsArray = new String[1];
//        while(testResultsArray.length < 7) {
//            try {
//
//                String command = "test_raspberry.sh";
//                Process bash_script = Runtime.getRuntime().exec(command);
//                sleep(10000);
//
//                testResults = readFile("test_results.txt");
//
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//            //System.out.println("LEU : " + testResults);
//
//            testResultsArray = testResults.split("\n");
//
//        }
//        float[] tests = new float[4];
//
//
//        tests[0] = Float.parseFloat(testResultsArray[3]);
//        tests[1] = Float.parseFloat(testResultsArray[4]);
//        tests[2] = Float.parseFloat(testResultsArray[5]);
//        tests[3] = Float.parseFloat(testResultsArray[6]);
//
//        //System.out.println("--->" + tests[0] + " " + tests[1] + " " + tests[2] + " " + tests[3]);
//
//        return tests;
//    }
}


