package mainApp;

import dataBaseConnectors.*;
import model.*;
import model.Module;

import java.time.LocalDate;

public class MainApp {
    public static void main(String[] args) {

        CourseDAO courseDAO = new CourseDAO();
        ExamDAO examDAO = new ExamDAO();
        GradeDAO gradeDAO = new GradeDAO();
        ModuleDAO moduleDAO = new ModuleDAO();
        PersonDAO personDAO = new PersonDAO();
        UserDAO userDAO = new UserDAO();




        Course course1 = new Course();
        course1.setName("Java IOT");
        course1.setDescription("Java-powered IoT devices are more effective in exchanging and retrieving information." +
                "\n Using Java makes IoT devices more integrated. Java has the ability of automatic up-gradation, " +
                "\nwhich makes it cost-effective and an excellent choice for IoT systems." +
                "\nIn this course you will learn happy coding");
        course1.setCode("#1111");
        course1.setImageURL("/images/JavaIOT");
        course1.setActive(true);

        Person person1 = new Person();
        person1.setFirstName("Alper");
        person1.setFamilyName("Yilmaz");
        person1.setGender(Gender.MEN);
        person1.setCourse(course1);

        Person person2 = new Person();
        person1.setFirstName("Ashley");
        person1.setFamilyName("Carson");
        person1.setGender(Gender.WOMAN);
        person1.setCourse(course1);

        User user = new User();
        user.setPasswordHash("12345");
        user.setActive(true);
        user.setPerson(person1);

        Module module = new Module();
        module.setName("Microsoft Teams");
        module.setDescription("Lessons will be op MS Teams");
        module.setCourse(course1);

        Exam exam = new Exam();
        exam.setName("OCA");
        exam.setDescription("The Java SE 8 Oracle Certified Associate (OCA) certification helps you build " +
                "\na foundational understanding of Java, and gaining this certification credential is the first " +
                "\nof two steps in demonstrating you have the high-level skills needed to become a professional Java developer.");
        exam.setDate(LocalDate.now());

        personDAO.addPerson(person1);
        personDAO.addPerson(person2);
























    }
}
