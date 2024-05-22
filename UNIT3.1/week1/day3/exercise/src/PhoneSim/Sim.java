package PhoneSim;

public class Sim {
    private int phoneNumber;
    private double credit;
    private Call[] calls;

    public Sim(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void getSimInfos(){
        System.out.println("The sim's number is: " + this.phoneNumber + ". The credit is: " + this.credit + " and the calls made are: " + this.calls.length);
        for (int i = 0; i < calls.length; i++){
            System.out.println("Call " + i + " to the " + calls[i].getCalledNumber() + " number lasted " + calls[i].getDurationInSeconds() + " seconds." );
        }
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Call[] getCalls() {
        return calls;
    }

    public void setCalls(Call[] calls) {
        this.calls = calls;
    }


}
