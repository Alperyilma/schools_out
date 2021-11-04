package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Exam exam;
    private BigDecimal gradeValue;
    private String comment;
    private String internalComment;
    private boolean absent;
    private boolean postponed;
    private LocalDate date;

    public Grade() {
    }

    public Grade( Person person , Exam exam , BigDecimal gradeValue , String comment , String internalComment , boolean absent , boolean postponed , LocalDate date) {
        this.person = person;
        this.exam = exam;
        this.gradeValue = gradeValue;
        this.comment = comment;
        this.internalComment = internalComment;
        this.absent = absent;
        this.postponed = postponed;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public BigDecimal getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(BigDecimal gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInternalComment() {
        return internalComment;
    }

    public void setInternalComment(String internalComment) {
        this.internalComment = internalComment;
    }

    public boolean isAbsent() {
        return absent;
    }

    public void setAbsent(boolean absent) {
        this.absent = absent;
    }

    public boolean isPostponed() {
        return postponed;
    }

    public void setPostponed(boolean postponed) {
        this.postponed = postponed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", person=" + person +
                ", exam=" + exam +
                ", gradeValue=" + gradeValue +
                ", comment='" + comment + '\'' +
                ", internalComment='" + internalComment + '\'' +
                ", absent=" + absent +
                ", postponed=" + postponed +
                ", date=" + date +
                '}';
    }
}
