import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class TweetServer {
    public static void main(String[] args) throws ParseException {
        float[] tests = getFakeTests();
        //the Date and time at which you want to execute
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormatter.parse("2020-01-01 00:00:00");

        //Now create the time and schedule it
        Timer timer = new Timer();

        //Use this if you want to execute it once
        //timer.schedule(new MyTimeTask(), date);

        //Use this if you want to execute it repeatedly
        int period = 60_000;//1 min
        timer.schedule(new MyTimeTask(), date, period );


    }


    //The task which you want to execute
    private static class MyTimeTask extends TimerTask {

        public void run() {
            float[] tests = getTests();

            for(float f : tests){
                System.out.println(f);
            }
            System.out.println("=====");
        }
    }

    /**
     * Runs the testing script and gets the test results.
     * The order of the tests is as follows: moisture soil level, light intensity, humidity, temperature.
     * @return Test results.
     */
    private static float[] getTests(){
        String testResults = "";
        String[] testResultsArray = new String[1];
        while(testResultsArray.length < 7) {
            try {

                String command = "./generate_tests.sh";
                Process bash_script = Runtime.getRuntime().exec(command);
                sleep(10000);

                testResults = readFile("test_results.txt");

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


            //System.out.println("LEU : " + testResults);

            testResultsArray = testResults.split("\n");

        }
        float[] tests = new float[4];


        tests[0] = Float.parseFloat(testResultsArray[3]);
        tests[1] = Float.parseFloat(testResultsArray[4]);
        tests[2] = Float.parseFloat(testResultsArray[5]);
        tests[3] = Float.parseFloat(testResultsArray[6]);

        //System.out.println("--->" + tests[0] + " " + tests[1] + " " + tests[2] + " " + tests[3]);

        return tests;
    }

    /**
     * Method to fake tests just for testing
     * @return Fake test results
     */
    private static float[] getFakeTests(){
        float[] tests = new float[4];
        float moisture = 53;
        float light = 91;
        float humidity = 56;
        float temperature = 24;

        tests[0] = moisture;
        tests[1] = light;
        tests[2] = humidity;
        tests[3] = temperature;
        return tests;
    }

    /**
     * Given a filename reads the file contents.
     * @param requestFileName Filename or path to file we want to read.
     * @return String with file contents.
     */
    private static String readFile(String requestFileName){
        try {
            Path path = Paths.get(requestFileName);
            return Files.readString(path, Charset.defaultCharset());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
