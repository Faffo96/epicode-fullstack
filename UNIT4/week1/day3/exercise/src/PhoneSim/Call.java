package PhoneSim;

public class Call {
    private int calledNumber;
    private int durationInSeconds;

    public Call(int calledNumber, int durationInSeconds) {
        this.calledNumber = calledNumber;
        this.durationInSeconds = durationInSeconds;
    }

    public int getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(int calledNumber) {
        this.calledNumber = calledNumber;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}
