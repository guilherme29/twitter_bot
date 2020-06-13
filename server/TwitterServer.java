import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class TwitterServer {
    public static void main(String[] args) throws ParseException {
        //the Date and time at which you want to execute
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormatter.parse("2020-01-01 00:01:00");

        //Now create the time and schedule it
        Timer timer = new Timer();

        //Use this if you want to execute it once
        //timer.schedule(new MyTimeTask(), date);

        //Use this if you want to execute it repeatedly
        int normalPeriod = 3600_000;//1 hour
        int emergencyPeriod = 120_000; //2 minutes
        timer.schedule(new NormalTweet(), date, normalPeriod);
        timer.schedule(new EmergencyTweet(), date, emergencyPeriod);

    }

    private static class NormalTweet extends TimerTask {

        public void run() {
            int[] rasp;
            float[] ardu;

            while(true) try {
                rasp = readRaspberryTests();
                ardu = readArduinoTests();
                break;
            } catch (IOException e) {
                try {
                    sleep(2_000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }


            System.out.println(rasp[0]);
            System.out.println(rasp[1]);
            System.out.println(ardu[0]);
            System.out.println(ardu[1]);
        }
    }

    private static class EmergencyTweet extends TimerTask {

        public void run() {
            int[] rasp;
            float[] ardu;

            while(true) try {
                rasp = readRaspberryTests();
                ardu = readArduinoTests();
                break;
            } catch (IOException e) {
                try {
                    sleep(2_000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

            if(rasp[0] < 1000) System.out.println("EMERGENCY");
//            System.out.println(rasp[0]);
//            System.out.println(rasp[1]);
//            System.out.println(ardu[0]);
//            System.out.println(ardu[1]);
        }
    }


//        float[] tests = new float[4];
//        tests[0] = Float.parseFloat(testResultsArray[3]);
//        tests[1] = Float.parseFloat(testResultsArray[4]);
//        tests[2] = Float.parseFloat(testResultsArray[5]);
//        tests[3] = Float.parseFloat(testResultsArray[6]);

        //System.out.println("--->" + tests[0] + " " + tests[1] + " " + tests[2] + " " + tests[3]);



    /**
     * Reads the saved tests from the Raspberry.
     * @return Integer array of size 2.
     * Index 0 contains the temperature and index 1 the humidity.
     */
    private static int[] readRaspberryTests() throws IOException {
        String fileContents = readFile("../test_results/raspberry_tests");
        String[] contentsArray = fileContents.split("\n");
        int[] results = new int[2];
        results[0] = Integer.parseInt(contentsArray[0]);
        results[1] = Integer.parseInt(contentsArray[1]);
        return results;
    }

    /**
     * Reads the saved tests from the Arduino.
     * @return Float array of size 2.
     * Index 0 contains the moisture level and index 1 the light level.
     */
    private static float[] readArduinoTests() throws IOException {
        String fileContents = readFile("../test_results/arduino_tests");
        String[] contentsArray = fileContents.split("\n");
        float[] results = new float[2];
        results[0] = Float.parseFloat(contentsArray[3]);
        results[1] = Float.parseFloat(contentsArray[4]);
        return results;
    }


    /**
     * Reads the file provided to a String.
     * @param path Path to file we want to read.
     * @return String with the file's contents.
     * @throws IOException Throws exception if the file cannot be found.
     */
    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }
}








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
//        return tests;
//    }



