package de.hhn.it.devtools.apis.memory;

public class TimerDescriptor {

    public int time;
    public boolean run;

    public TimerDescriptor() {
        this.time = 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void resetTime() {
        this.time = 0;
    }

    public void startTime() {
        run = true;
    }

    public void stopTime() {
        run = false;
    }

}
