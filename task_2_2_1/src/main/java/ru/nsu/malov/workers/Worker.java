package ru.nsu.malov.workers;

public abstract class Worker implements Runnable{
    private final int workerId;
    private final int workerInfo;
    private boolean isWorking;

    public Worker(int workerId, int workerInfo) {
        this.workerId = workerId;
        this.workerInfo = workerInfo;
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
