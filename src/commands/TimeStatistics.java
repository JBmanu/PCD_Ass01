package commands;

public class TimeStatistics {
    private long currentWallTime;
    private long startWallTime;
    private long endWallTime;
    private long averageTimeForStep;

    public TimeStatistics() {
        this.currentWallTime = 0;
        this.startWallTime = 0;
        this.endWallTime = 0;
        this.averageTimeForStep = 0;
    }

    public long currentWallTime() {
        return this.currentWallTime;
    }

    public long averageTimeForStep() {
        return this.averageTimeForStep;
    }

    public long totalWallTime() {
        return this.endWallTime - this.startWallTime;
    }

    public void setStartWallTime(long startWallTime) {
        this.startWallTime = startWallTime;
    }

    public void setCurrentWallTime(long currentWallTime) {
        this.currentWallTime = currentWallTime;
    }

    public void setEndWallTime(long endWallTime) {
        this.endWallTime = endWallTime;
    }

    public void setAverageTimeForStep(long averageTimeForStep) {
        this.averageTimeForStep = averageTimeForStep;
    }
}
