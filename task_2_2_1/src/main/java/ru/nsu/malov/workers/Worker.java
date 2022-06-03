package ru.nsu.malov.workers;

public abstract class Worker implements Runnable{
    private final int workerId;
    private final int workerTime;
    private boolean isWorking;

    public Worker(int workerId, int workingTime) {
        this.workerId = workerId;
        this.workerTime = workingTime;
        isWorking = false;
    }

    @Override
    public void run() {
        isWorking = true;
        while (isWorking) {
            running();
        }
    }

    public void stopWorking(){
        isWorking = false;
    }

    public abstract void running();
}
