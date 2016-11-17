package fhku.sensorballgame;

/**
 * Created by tobibeck on 12.11.16.
 */

public class TimeDifference {

    long time;

    public TimeDifference(long t){
        this.time = t;
    }

    public void setTime(long t){
        this.time = t;
    }

    public long getTime(){

        return time;
    }
    public String printMin(){
        String min = ""+((int) ((time)/1000/60));
        min = (min.length()<2?"0"+min:min);
        return min;
    }

    public String printSec(){
        String sec = ""+(((int) (time/1000))-(((int) ((time)/1000/60))*60));
        sec = (sec.length()<2?"0"+sec:sec);
        return sec;
    }

    public String printMil(){
        String mil = ""+(time- (((int) ((time)/1000))*1000));


        switch(mil.length()){
            case 1:
                mil = "00"+mil;
                break;
            case 2:
                mil = "0"+mil;
                break;
        }
        return mil;
    }





}
