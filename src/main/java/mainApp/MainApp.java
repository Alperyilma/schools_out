package mainApp;

import dataBaseConnectors.*;
import model.*;
import model.Module;

import java.time.LocalDate;

public class MainApp {
    public static void main(String[] args) {

        CourseDAO courseDAO = new CourseDAO();
        ExamDAO examDAO = new ExamDAO();
        ModuleDAO moduleDAO = new ModuleDAO();
        PersonDAO personDAO = new PersonDAO();
        UserDAO userDAO = new UserDAO();


        Course course1 = new Course();
        course1.setName("Java IOT");
        course1.setDescription("Java-powered IoT devices are more effective in exchanging and retrieving information." +
                "\n Using Java makes IoT devices more integrated.");
        course1.setCode("#1111");
        course1.setImageURL("/images/JavaIOT");
        course1.setActive(true);

        Course course2 = new Course();
        course2.setName("Front-End Developer");
        course2.setDescription("You will learn HTML, CSS and JavaScript");
        course2.setCode("#2222");
        course2.setImageURL("/images/FrontEnd");
        course2.setActive(true);

        //Create person1
        Person person1 = new Person();
        person1.setFirstName("Alper");
        person1.setFamilyName("Yilmaz");
        person1.setGender(Gender.MALE);
        person1.setCourse(course1);

        //Create person2
        Person person2 = new Person();
        person2.setFirstName("Ashley");
        person2.setFamilyName("Carson");
        person2.setGender(Gender.FEMALE);
        person2.setCourse(course2);

        //Create person4
        Person person3 = new Person();
        person3.setFirstName("John");
        person3.setFamilyName("Wick");
        person3.setGender(Gender.MALE);
        person3.setCourse(course1);

        //Create person4
        Person person4 = new Person();
        person3.setFirstName("Ahmet");
        person3.setFamilyName("Demir");
        person3.setGender(Gender.MALE);
        person3.setCourse(course2);


        Person person5 = new Person();
        person5.setFirstName("Mick");
        person5.setFamilyName("James");
        person5.setGender(Gender.MALE);
        person5.setCourse(course1);

        Module module = new Module("Microsoft Teams","Lessons will be op MS Teams",course1);

        Exam exam1 = new Exam();
        exam1.setName("OCA");
        exam1.setDescription("The Java SE 8 Oracle Certified Associate (OCA) certification helps you build " +
                "\na foundational understanding of Java");
        exam1.setDate(LocalDate.now());

        examDAO.addExam(exam1);

        personDAO.addPerson(person1);
        personDAO.addPerson(person2);
        personDAO.addPerson(person3);
        personDAO.addPerson(person4);
    }
}
