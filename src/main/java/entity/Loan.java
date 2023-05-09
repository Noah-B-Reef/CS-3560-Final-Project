package entity;

import jakarta.persistence.*;

import javax.swing.plaf.synth.SynthMenuBarUI;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Entity
public class Loan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "number")
    private int number;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "due_date")
    private Date dueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student studentByStudentId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_code", referencedColumnName = "code")
    private Item itemByItemCode;

    public Loan(Date date, Date dueDate)
    {
        this.date = date;
        this.dueDate = dueDate;
    }

    public Loan()
    {
        this.date = new Date(System.currentTimeMillis());
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStudentId() {
        return studentByStudentId.getId();
    }

    public void setStudentId(String studentId) {
        this.studentByStudentId.setId(studentId);
    }

    public String getItemCode() {
        return itemByItemCode.getCode();
    }

    public void setItemCode(String itemCode) {
        this.itemByItemCode.setCode(itemCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return number == loan.number && Objects.equals(date, loan.date) && Objects.equals(dueDate, loan.dueDate) && Objects.equals(studentByStudentId, loan.studentByStudentId) && Objects.equals(itemByItemCode, loan.itemByItemCode);
    }

    public Student getStudentByStudentId() {
        return studentByStudentId;
    }

    public void setStudentByStudentId(Student studentByStudentId) {
        this.studentByStudentId = studentByStudentId;
    }

    public Item getItemByItemCode() {
        return itemByItemCode;
    }

    public void setItemByItemCode(Item itemByItemCode) {
        this.itemByItemCode = itemByItemCode;
    }

    public double computeFine()
    {
        return Math.pow(itemByItemCode.getDailyPrice() * .10, (System.currentTimeMillis() - dueDate.getTime())/ TimeUnit.DAYS.toMillis(1));
    }

    public double computeFine(Date date)
    {
        double price = itemByItemCode.getDailyPrice();
        return Math.pow((price * .10), (date.getTime() - dueDate.getTime())/ TimeUnit.DAYS.toMillis(1)) - price;
    }

}
