package services;

import dataBaseConnectors.PersonDAO;
import dataBaseConnectors.UserDAO;
import model.Gender;
import model.Person;
import model.User;

import java.util.Scanner;

public class PersonService {
    private PersonDAO personDAO;
    private Scanner scan;
    private UserDAO userDAO;

    public PersonService() {
        personDAO = new PersonDAO();
        scan = new Scanner(System.in);
        userDAO = new UserDAO();
    }

    //HARD
    //1.Zorg ervoor dat een persoon ook aangemaakt wordt
    //2.Maak een controle methode die vraagt om een gebruiker een passwoord twee keer in te geven
    //3. Het wachtwoord wordt hier geincrypteerd
    public void createPerson(){
        System.out.println("Create your user");
        System.out.println("Give your firstname");
        Person person = new Person();
        person.setFirstName(scan.next());
        System.out.println("Give your lastname");
        person.setFamilyName(scan.next());
        System.out.println("Choose your gender. \n1. Other\n2. Female\n3. Male");
        int choice = scan.nextInt();
        switch (choice){
            case 1: person.setGender(Gender.OTHER); break;
            case 2: person.setGender(Gender.FEMALE); break;
            case 3: person.setGender(Gender.MALE); break;
            default:
        }
        personDAO.addPerson(person);
    }

    //EASY
    //Als er geen User terug gegeven wordt, stuur als bericht "User does not exist"
    public User getOnePersonByName(){
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
    public void getAllPerson(){
        userDAO.getAllUser().forEach(System.out::println);
    }

    //EASY
    //Een username mag niet aangepast worden
    //Bonus HARD
    //De Person hoef je niet te updaten (als je dat wilt, doe je dat best via een aparte personservice,
    // via een aprte updatePersonMethode)
    public void updatePerson(){
        System.out.println("Which user do you want to change?");
        User user = userDAO.getUserById(scan.nextLine());

        System.out.println("Change password? Y/N");
        String answer = scan.next();
        if (answer.equals("Y")){
            user.setPasswordHash(checkPassword());
        }

        System.out.println("Do you want to change activity status of the account? Y/N");
        answer = scan.next();
        if (answer.equals("Y")) {
            user.setActive(!user.isActive());
        }
    }

    //MEDIUM
    //Vraag de User een passwoord in te geven voor dat hij zijn account kan verwijderen.
    //De Person moet ook mee gedeleted worden
    public void deletePerson(){
        personDAO.getAllPersons ().forEach (System.out::println);
        System.out.println ("Give id of person for delete:");
        int personId = giveExistingPersonId ();
        System.out.println("Are you sure?");
        System.out.println(personDAO.getPersonById (personId));
        System.out.println("Y/N");
        String answer = scan.next ();
        if (answer.toUpperCase().equals ("Y")){
            personDAO.deletePerson (personDAO.getPersonById (personId));
            System.out.println ("Person has been deleted");
        }else {
            System.out.println ("Person has not been deleted");
        }
    }

    private int giveExistingPersonId() {
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (personDAO.getPersonById (currentId)!= null) exist = true;
            else System.out.println("Person doesn't exist");
        }
        return currentId;
    }

    private int makeACorrectId() {
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

    //----
    //extra private methodes hieronder
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

    public void updatePerson(Person person){
        System.out.println("Change firstname? Y/N");
        String answer = scan.next();
        if (answer.equals("Y")){
            person.setFirstName(scan.next());
        }

        System.out.println("Change lastname? Y/N");
        answer = scan.next();
        if (answer.equals("Y")){
            person.setFamilyName(scan.next());
        }

        System.out.println("Change gender? Y/N");
        answer = scan.next();
        if (answer.equals("Y")){
            System.out.println("Choose your gender. \n1. Other\n2. Female\n3. Male");
            int choice = scan.nextInt();
            switch (choice){
                case 1: person.setGender(Gender.OTHER); break;
                case 2: person.setGender(Gender.FEMALE); break;
                case 3: person.setGender(Gender.MALE); break;
                default:
            }
            System.out.println("What do you want your login to be");
            User user = new User();
            user.setLogin(scan.nextLine());
            user.setPasswordHash(checkPassword());
            user.setActive(true);
            user.setPerson(person);
            userDAO.addUser(user);
        }
        personDAO.updatePerson(person);
    }
}
