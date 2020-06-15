import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class TestingServer {
    public static void main(String[] args) throws ParseException {

        //the Date and time at which you want to execute
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormatter.parse("2020-01-01 00:00:00");

        //Now create the time and schedule it
        Timer timer = new Timer();

        //Use this if you want to execute it once
        //timer.schedule(new MyTimeTask(), date);

        //Use this if you want to execute it repeatedly
        int period = 60_000;//60 segs
        timer.schedule(new RaspberryTestRunner(), date, period);
        timer.schedule(new ArduinoTestRunner(), date, period);

    }

    private static class RaspberryTestRunner extends TimerTask {

        public void run() {
            //do the test until it isn't empty anymore
            do{
                try {
                    testRaspberry();
                    sleep(12_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!isFileEmpty("../tests/rasp_temp"));

            //copy to a "permanent file"
            try{
                String command = "cp ../tests/rasp_temp ../tests/rasp_last";
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void testRaspberry() {
            try {
                String command = "./test_raspberry.sh";
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ArduinoTestRunner extends TimerTask {

        public void run() {
            //do the test until it isn't empty anymore
            do{
                try {
                    testArduino();
                    sleep(12_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!isFileEmpty("../tests/ardu_temp"));

            //copy to a "permanent file"
            try{
                String command = "cp ../tests/ardu_temp ../tests/ardu_last";
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void testArduino() {
            try {
                String command = "./test_arduino.sh";
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            //if it doesn't find the file return true;
            return true;
        }
        return false;
    }
}
