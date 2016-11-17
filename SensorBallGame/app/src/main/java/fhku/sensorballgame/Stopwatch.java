package fhku.sensorballgame;

/**
 * Created by tobibeck on 12.11.16.
 */

public class Stopwatch {

    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;


    public void start() {
        if(this.running==false) {
            this.startTime = System.currentTimeMillis();
            this.running = true;
        }
    }


    public void stop() {
        if(this.running==true) {
            this.stopTime = System.currentTimeMillis();
            this.running = false;

        }
    }


    // elaspsed time in milliseconds
    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        }
        return stopTime - startTime;
    }


    // elaspsed time in seconds
    public long getElapsedTimeSecs() {
        if (running) {
            return ((System.currentTimeMillis() - startTime) / 1000);
        }
        return ((stopTime - startTime) / 1000);
    }




    // sample usage
    public static void main(String[] args) {
        Stopwatch s = new Stopwatch();
        s.start();
        // code you want to time goes here
        s.stop();
        System.out.println("elapsed time in milliseconds: " + s.getElapsedTime());
    }

}
