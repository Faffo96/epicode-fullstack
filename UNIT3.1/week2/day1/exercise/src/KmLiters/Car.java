package KmLiters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Car {
    static Logger logger = LoggerFactory.getLogger("root");
    private Double km;
    private Double liters;

    public Car(Double km, Double liters) {
        this.km = km;
        this.liters = liters;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Double getLiters() {
        return liters;
    }

    public void setLiters(Double liters) {
            this.liters = liters;
    }

    public void getKmLiter() {
        try {
            if (this.liters != 0) {
                Double kmLiter = this.km / this.liters;
                String formattedKmLiter = String.format("%.2f", kmLiter);
                System.out.println(formattedKmLiter + " Kms were covered with each litre.");
            } else {
                throw new IllegalArgumentException("Division by zero. Liters cannot be zero.");
            }
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }
    }

}
