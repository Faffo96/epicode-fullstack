package KmLiters;

public class UseCar {
    public static void main(String[] args) {
        Car c1 = new Car(11.5, 5.2);
        Car c2 = new Car(20.0, 10.0);
        Car c3 = new Car(30.0, 12.0);
        Car c4 = new Car(40.0, 20.0);

        c1.getKmLiter();
        c2.getKmLiter();
        c3.getKmLiter();
        c4.getKmLiter();
    }
}
