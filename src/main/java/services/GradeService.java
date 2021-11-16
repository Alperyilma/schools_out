package services;

import dataBaseConnectors.ExamDAO;
import dataBaseConnectors.GradeDAO;
import dataBaseConnectors.PersonDAO;
import dataBaseConnectors.UserDAO;
import model.Exam;
import model.Grade;
import model.User;
import org.hibernate.boot.model.source.spi.PluralAttributeElementSourceManyToAny;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.plaf.IconUIResource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class GradeService {
    private EntityManagerFactory emf;
    private GradeDAO gradeDAO;
    private UserDAO userDAO;
    private PersonDAO personDAO;
    private Scanner scan;
    private ExamDAO examDAO;

    public GradeService(){
        gradeDAO = new GradeDAO();
        userDAO = new UserDAO();
        personDAO = new PersonDAO();
        scan = new Scanner(System.in);
        examDAO = new ExamDAO();
    }

    //SUPER HARD
    //De datum staat automatisch op vandaag
    //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
    //Je gaat een Exam moeten oproepen van de bestaande lijst van examens, Eman mag dus niet op null staan
    //Je gaat een Person moeten terugvinden met User
    public void createGrade(User user){
        examDAO.getAllExam().forEach(System.out::println);

        System.out.println("Which one of these exam do you want to use? Type in a number");
        long examId = scan.nextLong();

        personDAO.getAllPersons().forEach(System.out::println);
        System.out.println("Which one of these person do you want to use? Type in a number");
        int personId = scan.nextInt();
        personDAO.getPersonById(personId);

        System.out.println("Insert grade");
        BigDecimal grade = scan.nextBigDecimal();
        System.out.println("Insert comment");
        String comment = scan.next();
        System.out.println("Insert internal comment");
        String internalComment = scan.next();
        System.out.println("Choose a absent(true/false)");
        boolean absent = scan.nextBoolean();
        System.out.println("Choose a postponed(true/false)");
        boolean postponed = scan.nextBoolean();

        LocalDate dateTime = LocalDate.now();

        Grade grade1 = new Grade(personDAO.getPersonById(personId), examDAO.getExamById((long)examId),
                grade,comment,internalComment,absent,postponed,dateTime);
        gradeDAO.addGrade(grade1);
    }

    //EASY
    public void getOneGradeById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give one id");
        Long id = scanner.nextLong();
        gradeDAO.getGradeById(id);
    }

    //MEDIUM
    //Controleer eerst of de user niet 'null' is
    //Gebruik een user.getPerson methode, en maak een extra methode in je DAO/repository om resultaten op te vragen met person
    public void getAllGradeByPerson(User user){
        gradeDAO.getAllGrade().forEach(System.out::println);
    }

    //HARD
    //Controleer eerst of de user niet 'null' is
    //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
    //Enkel de gradeValue en de comment mogen aangepast worden
    //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
    public void updateGrade(User user){
        if (user != null) {
            System.out.println(user);
        }
        getAllGradeByPerson(user);

        int count = 0;
        do {
            System.out.println("Choose one user you want to update grade");
            Grade grade = gradeDAO.getGradeById(scan.nextLong());
            System.out.println("Put new grade!");
            BigDecimal newGrade = scan.nextBigDecimal();
            grade.setGradeValue(newGrade);
            if (newGrade.floatValue()<0){
                System.out.println("Invalid grade. Try again !");
                count++;
            }else {
                grade.setGradeValue(newGrade);
                gradeDAO.updateGrade(grade);
                count=3;
            }
        }while (count<3);
    }

    //EASY
    //Controleer eerst of de user niet 'null' is
    //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
    public void deleteGrade(User user){
        if (user!=null){
            System.out.println(user);
        }
        getAllGradeByPerson(user);

        System.out.println("Choose one user you want to delete grade");
        Grade grade = gradeDAO.getGradeById(scan.nextLong());
        gradeDAO.deleteGrade(grade);
    }
}
