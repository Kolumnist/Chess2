package de.hhn.it.devtools.apis.memory;

public class TimerDescriptor implements Runnable {

    private int time;
    boolean run;

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
        new Thread(this::run).start();
    }

    public void stopTime() {
        run = false;
    }

    @Override
    public void run() {
        while (run) {
            time++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
