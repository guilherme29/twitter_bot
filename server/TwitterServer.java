import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        int normalPeriod = 14400_000;//4 hour
        int emergencyPeriod = 120_000; //2 minutes
        timer.schedule(new NormalTweet(), date, normalPeriod);
        timer.schedule(new EmergencyTweet(), date, emergencyPeriod);

    }

    private static class NormalTweet extends TimerTask {

        public void run() {
            try {
                int[] rasp = readRaspberryTests();
                float[] ardu = readArduinoTests();

                normalTweet(ardu, rasp);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Given the test values tweets them in the normal periodic tweet format.
         * @param ardu Test results from the arduino.
         * @param rasp Test results from the raspberry.
         */
        private static void normalTweet(float[] ardu, int[] rasp){
            float moisture = ardu[0];
            float light = ardu[1];
            int temperature = rasp[1];
            int humidity = rasp[1];
            String tweet = "soil moisture: " + moisture + "%\n" +
                    "light: " + light + "%\n" +
                    "temperature: " + temperature + "ºC\n" +
                    "humidity: " + humidity + "%\n";

            writeFile(tweet, "../twitter/tweet");
            try {
                String command = "node ../twitter/bot.js ../twitter/tweet";
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class EmergencyTweet extends TimerTask {

        public void run() {
            try {

                int[] rasp = readRaspberryTests();
                float[] ardu = readArduinoTests();
                emergencyTweet(ardu, rasp);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Given the test values tweets if and only if any of the values feels out of place.
         * @param ardu Test results from the arduino.
         * @param rasp Test results from the raspberry.
         */
        private static void emergencyTweet(float[] ardu, int[] rasp) {
            float moisture = ardu[0];
            float light = ardu[1];
            int temperature = rasp[1];
            int humidity = rasp[1];
            String tweet = "";

            boolean flag = false;

            if(moisture < 20){
                tweet += "Preciso duma rega, humidade do solo é " + moisture + "%\n";
                flag = true;
            }
            else if(moisture > 95){
                tweet += "Também não era preciso regar tanto. O solo está " + moisture + "% húmido\n";
                flag = true;
            }

            if(temperature < 0){
                tweet += "Está mesmo frio! " + temperature + "ºC! brr\n";
                flag = true;
            }
            else if(temperature > 30){
                tweet += "Que tosta, " + temperature + "ºC\n";
                flag = true;
            }


            Calendar cal = Calendar.getInstance(); //Create Calendar-Object
            cal.setTime(new Date());               //Set the Calendar to now
            int hour = cal.get(Calendar.HOUR_OF_DAY); //Get the hour from the calendar
            if(hour <= 22 && hour >= 8){              // Check if hour is between 8 am and 22
                if (light < 20){
                    tweet += "alguém desligou a luz?\n";
                    flag = true;
                }
            }

            if(flag){ //tweets only if there's something important to tweet
                writeFile(tweet, "../twitter/emergencyTweet");
                try {
                    String command = "node ../twitter/bot.js ../twitter/emergencyTweet";
                    Runtime.getRuntime().exec(command);
                    try {
                        sleep(32_400_000); //8 horas
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




    /**
     * Reads the saved tests from the Raspberry.
     * @return Integer array of size 2.
     * Index 0 contains the temperature and index 1 the humidity.
     */
    private static int[] readRaspberryTests() throws IOException {
        while(isFileEmpty("../test_results/raspberry_tests")){
            try {
                sleep(20_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        while(isFileEmpty("../test_results/arduino_tests")){
            try {
                sleep(20_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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


    /**
     * Writes the provided string to the file given.
     * @param text String to write in the file.
     * @param path Path to the file we want to write to.
     */
    private static void writeFile(String text, String path){
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Given the path to a file checks if the file is empty.
     * @return True if file is empty, false if not.
     */
    private static boolean isFileEmpty(String pathToFile){
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));
            if (br.readLine() == null) {
                return true;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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



