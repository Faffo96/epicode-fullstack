package entities.Magazine;
import Enums.Periodicity;
import entities.Text.Text;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.IOException;
import java.time.LocalDate;

@Entity
@Table(name = "magazines")
public class Magazine extends Text {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;


    public Magazine(String ISBNcode, String title, LocalDate publicationDate, Integer pageNumber, Periodicity periodicity) throws IOException {
        super(ISBNcode, title, publicationDate, pageNumber);
        this.periodicity = periodicity;
    }

    public Magazine() {
        super();
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", periodicity=" + periodicity +
                '}';
    }
}
