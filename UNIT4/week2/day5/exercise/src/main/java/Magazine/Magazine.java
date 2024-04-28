package Magazine;

import Enums.Periodicity;
import Text.Text;

import java.io.IOException;
import java.time.LocalDate;

public class Magazine extends Text {
    private Periodicity periodicity;


    public Magazine(String ISBNcode, String title, LocalDate publicationDate, Integer pageNumber, Periodicity periodicity) throws IOException {
        super(ISBNcode, title, publicationDate, pageNumber);
        this.periodicity = periodicity;
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
