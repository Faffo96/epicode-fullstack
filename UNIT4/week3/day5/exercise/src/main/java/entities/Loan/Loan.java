package entities.Loan;
import entities.Text.Text;
import entities.User.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_card_number")
    private User user;
    @OneToOne
    @JoinColumn(name = "text_isbn")
    private Text borrowedElement;
    @Column(name="loan_start_date")
    private LocalDate loanStartDate;
    @Column(name="return_date")
    private LocalDate returnDate;
    @Column(name="effective_return_date")
    private LocalDate effectiveReturnDate;

    public Loan(User user, Text borrowedElement) {
        this.user = user;
        this.borrowedElement = borrowedElement;
        this.loanStartDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(30);
    }

    public Loan() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Text getBorrowedElement() {
        return borrowedElement;
    }

    public void setBorrowedElement(Text borrowedElement) {
        this.borrowedElement = borrowedElement;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getEffectiveReturnDate() {
        return effectiveReturnDate;
    }

    public void setEffectiveReturnDate(LocalDate effectiveReturnDate) {
        this.effectiveReturnDate = effectiveReturnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "user=" + user +
                ", borrowedElement=" + borrowedElement +
                ", loanStartDate=" + loanStartDate +
                ", returnDate=" + returnDate +
                ", effectiveReturnDate=" + effectiveReturnDate +
                '}';
    }
}
