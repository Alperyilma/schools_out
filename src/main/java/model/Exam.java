package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private int weight;
    private int total;
    @ManyToOne
    private Module module;
    @ManyToOne
    private Exam examGroup;
    @ManyToMany
    private List<Exam> subExams;

    public Exam() {
    }

    public Exam(String name , String description , LocalDate date , int weight , int total) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.weight = weight;
        this.total = total;
    }

    public Exam(String name , String description , LocalDate date , int weight , int total , Module module) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.weight = weight;
        this.total = total;
        this.module = module;
    }

    public Exam getExamGroup() {
        return examGroup;
    }

    public void setExamGroup(Exam examGroup) {
        this.examGroup = examGroup;
    }

    public List<Exam> getSubExams() {
        return subExams;
    }

    public void setSubExams(List<Exam> subExams) {
        this.subExams = subExams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", weight=" + weight +
                ", total=" + total +
                ", module=" + module +
                '}';
    }
}
