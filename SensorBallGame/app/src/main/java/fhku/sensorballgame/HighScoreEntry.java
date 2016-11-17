package fhku.sensorballgame;

/**
 * Created by tobibeck on 12.11.16.
 */

public class HighScoreEntry implements Comparable<HighScoreEntry> {

    String name;
    long time;

    @Override
    public int compareTo(HighScoreEntry o1) {
        return (int) (this.time-o1.time);
    }
}
