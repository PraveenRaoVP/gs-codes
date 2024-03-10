package exercism;


public class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
        return this.birdsPerDay;
    }

    public int getToday() {
        return this.birdsPerDay[this.birdsPerDay.length-1];
    }

    public void incrementTodaysCount() {
        this.birdsPerDay[this.birdsPerDay.length-1] = this.birdsPerDay[this.birdsPerDay.length-1] + 1;
    }

    public boolean hasDayWithoutBirds() {
        for(int i: this.birdsPerDay) {
            if(i==0) return true;
        }
        return false;
    }

    public int getCountForFirstDays(int numberOfDays) {
        int sum = 0;
        if(numberOfDays>=this.birdsPerDay.length) {
            numberOfDays = this.birdsPerDay.length;
        }
        for(int i=0;i<numberOfDays;i++) {
            sum+=this.birdsPerDay[i];
        }
        return sum;
    }

    public int getBusyDays() {
        int countNoOfBusyDays = 0;
        for(int i=0;i<this.birdsPerDay.length;i++) {
            if(this.birdsPerDay[i]>=5) {
                countNoOfBusyDays++;
            }
        }
        return countNoOfBusyDays;
    }
}