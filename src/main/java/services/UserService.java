package services;

import dataBaseConnectors.CourseDAO;
import dataBaseConnectors.PersonDAO;
import dataBaseConnectors.UserDAO;
import model.Gender;
import model.Person;
import model.User;

import javax.persistence.spi.PersistenceUnitInfo;
import java.util.Scanner;

public class UserService {

    private UserDAO userDAO;
    private Scanner scan;
    private PersonService personService;
    private PersonDAO personDAO;
    private CourseDAO courseDAO;

    public UserService(){
        userDAO = new UserDAO();
        scan = new Scanner(System.in);
        personDAO = new PersonDAO();
        personService = new PersonService();
        courseDAO = new CourseDAO();
    }

    //HARD
    //1.Zorg ervoor dat een persoon ook aangemaakt wordt
    //2.Maak een controle methode die vraagt om een gebruiker een passwoord twee keer in te geven
    //3. Het wachtwoord wordt hier geincrypteerd
    public void createUser(){
        System.out.println("Insert first name");
        String firstName = scan.next();

        System.out.println("Insert family name");
        String familyName = scan.next();

        System.out.println("What do you want to look at? \n1: Male \n2: Female\n3: Other");
        boolean exists = false;
        int genderId =0;
        Gender genre = null;
        while (!exists) {
            genderId = makeACorrectId();
            if (genderId == 1) {
                genre = Gender.MALE; exists = true; }
            else if (genderId == 2) {
                genre = Gender.FEMALE; exists = true; }
            else if (genderId == 3) {
                genre = Gender.OTHER; exists = true; }
            else System.out.println("This genre doesn't exist.");
        }

        courseDAO.getAllCourses ().forEach (System.out::println);
        System.out.println("Which one of these course do you want to use? Type in a number.");
        int courseId = 0;
        while (!exists) {
            courseId = makeACorrectId();
            if (courseDAO.getCourseById ((long) courseId)!=null)
                exists = true;
            else System.out.println("This course doesn't exist.");
        }

        Person person = new Person (firstName,familyName,genre);
        User user = new User();
        user.setLogin(scan.nextLine());
        user.setPasswordHash(checkPassword());
        user.setActive(true);
        personDAO.addPerson (person);
        System.out.println("Person was made");
    }

    //EASY
    //Als er geen User terug gegeven wordt, stuur als bericht "User does not exist"
    public User getOneUserByName(){
        System.out.println("Enter the username");
        String username = scan.next();
        User user = userDAO.getUserById(username);

        if (username != null){
            System.out.println(username);
        }else {
            System.out.println("User doesn't exist!!");
        }
        return user;
    }

    //EASY
    //print een lijst uit van alle users.
    public void getAllUsers(){
        userDAO.getAllUser().forEach(System.out::println);
    }

    //EASY
    //Een username mag niet aangepast worden
    //Bonus HARD
    //De Person hoef je niet te updaten (als je dat wilt, doe je dat best via een aparte personservice,
    // via een aprte updatePersonMethode)
    public void updateUser(){
        personDAO.getAllPersons ().forEach (System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        boolean exist =false;
        int currentId = 0;
        while (!exist){
            currentId = makeACorrectId ();
            if (personDAO.getPersonById (currentId) != null) exist=true;
            else System.out.println ("Person doesn't exist");
        }
        Scanner scanner = new Scanner (System.in);
        Person person = personDAO.getPersonById (currentId);
        System.out.println("Do you want to change the first name? Yes/No");
        String answer = scanner.next ();
        if (!answer.toUpperCase().equalsIgnoreCase ("No")){
            System.out.println ("What is the new first name");
            String firstName = scanner.next ();
            person.setFirstName (firstName);
        }
        System.out.println("Do you want to change the family name? Yes/No");
        answer = scanner.next ();
        if (!answer.toUpperCase().equalsIgnoreCase ("No")){
            System.out.println ("What is the new family name?");
            String familyName = scanner.next ();
            person.setFamilyName (familyName);
        }
        System.out.println("Do you want to change the gender? Yes/No");
        answer = scanner.next ();
        if (!answer.toUpperCase().equalsIgnoreCase ("No")){
            System.out.println("What do you want to change it do? \n1: Male \n2: Female\n3: Other");
            boolean exists = false;
            int genderId = 0;

            Gender genre = null;

            while (!exists) {
                genderId = makeACorrectId();
                if (genderId == 1) {
                    genre = Gender.MALE; exists = true; }
                else if (genderId == 2) {
                    genre = Gender.FEMALE; exists = true; }
                else if (genderId == 3) {
                    genre = Gender.OTHER; exists = true; }
                else System.out.println("This genre doesn't exist.");
            }
            person.setGender (genre);
        }
        personDAO.updatePerson (person);
        System.out.println ("Person updated!");
    }

    //MEDIUM
    //Vraag de User een passwoord in te geven voor dat hij zijn account kan verwijderen.
    //De Person moet ook mee gedeleted worden
    public void deleteUser(){
        personDAO.getAllPersons ().forEach (System.out::println);
        System.out.println ("Give id of person you want to delete:");
        int personId = giveExistingPersonId ();
        System.out.println("Are you sure?");
        System.out.println(personDAO.getPersonById (personId));
        System.out.println("Y/N");
        String answer = scan.next ();
        if (answer.toUpperCase().equals ("Y")){
            personDAO.deletePerson (personDAO.getPersonById (personId));
            System.out.println ("Person has been deleted");
        }else System.out.println ("Person has not been deleted");
    }

    //----
    //extra private methodes hieronder
    private int giveExistingPersonId(){
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (personDAO.getPersonById (currentId)!= null) exist = true;
            else System.out.println("Person doesn't exist");
        }
        return currentId;
    }

    private String checkPassword(){
        boolean samePassword = false;
        String password = null;

        while (samePassword == false) {
            System.out.println("Type in your password");
            password = scan.next();
            System.out.println("Type in your password again");
            String password2 = scan.next();
            if (password.equals(password2)) {
                samePassword = true;
            }
        }
        return password;
    }

    private int makeACorrectId(){
        boolean rightInput;
        int id= 0;
        do {
            System.out.println("Insert Id");
            try {
                id = Integer.parseInt(scan.next());
                scan.nextLine();
                rightInput= true;
            } catch (NumberFormatException e) {
                rightInput =false;
                System.out.println("Id is not correct");
            }
        }while (!rightInput);
        return id;
    }
}