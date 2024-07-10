package Controller;

import Model.*;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Management {
    String usefulID = "";
    int usefulType;
    public static String fileExp = "src/data/Exp.txt";
    public static String fileFresher = "src/data/Fresher.txt";
    public static String fileIntern = "src/data/Intern.txt";
    Scanner sc = new Scanner(System.in);
    Validation valid = new Validation();
    SignUp su = new SignUp();

    public int enterChoice() {
        String inp;
        do {
            System.out.print("Enter Choices: ");
            inp = sc.nextLine();
        } while(!valid.checkInt(inp));
        return Integer.parseInt(inp);
    }
    public String enterID() {
        String inp;
        do {
            System.out.print("Enter Id: ");
            inp = sc.nextLine();
        } while(!valid.checkID(inp));
        return inp;
    }
    public String enterName() {
        String name;
        do {
            System.out.print("Enter: ");
            name = sc.nextLine();
        } while (!valid.checkName(name));
        return name;
    }
    public String enterPhone() {
        String phone;
        do {
            System.out.print("Enter Phone: ");
            phone = sc.nextLine();
        } while (!valid.checkPhone(phone));
        return phone;
    }
    public String enterEmail() {
        String email;
        do{
            System.out.print("Enter Email: ");
            email = sc.nextLine();
        } while(!valid.checkEmail(email));
        return email;
    }
    public int enterType() {
        String inp;
        do {
            System.out.print("Enter Candidate Type (1.Experience, 2.Fresher, 3.Intern): ");
            inp = sc.nextLine();
        } while(!valid.checkType(inp));
        return Integer.parseInt(inp);
    }
    public int enterExpYear() {
        String inp;
        do {
            System.out.print("Enter Experience Year: ");
            inp = sc.nextLine();
        } while (!valid.checkInt(inp));
        return Integer.parseInt(inp);
    }
    public String enterSkill() {
        String inp;
        System.out.print("Enter Your Best Skill: ");
        inp = sc.nextLine();
        return inp;
    }
    public String enterRank() {
        String inp;
        do {
            System.out.println("Which One Is Your Rank: [Excellent] [Good] [Bad]");
            inp = sc.nextLine();
        } while(!valid.checkRank(inp));
        return inp;
    }
    public String enterEducation() {
        String inp;
        do {
            System.out.print("Enter Your Education: ");
            inp = sc.nextLine();
        } while(!valid.checkName(inp));
        return inp;
    }
    public String enterMajor() {
        String inp;
        do {
            System.out.print("Enter Your Major: ");
            inp = sc.nextLine();
        } while(!valid.checkName(inp));
        return inp;
    }
    public int enterSemester() {
        String inp;
        do {
            System.out.print("Enter Your Present Semester (A Number): ");
            inp = sc.nextLine();
        } while(!valid.checkInt(inp));
        return Integer.parseInt(inp);
    }
    public String enterUniversityName () {
        String inp;
        do {
            System.out.print("Enter Your University: ");
            inp = sc.nextLine();
        } while(!valid.checkName(inp));
        return inp;
    }
    
    public void add() {
        Candidates candidates = null;
        
        int expYear = 0;
        String proSkill = "";
        
        Date graduationDate = null;
        String graduationRank = "";
        String education = "";
        
        String major = "";
        int semester = 0;
        String UniversityName = "";
        
        System.out.print("First Name.");
        String first = enterName();
        System.out.print("Last Name. ");
        String last = enterName();
        Date DOB = null;
        String dob = "";
        boolean isEnterDob = true;
        while(isEnterDob) {
            do {
                System.out.print("Enter Dob (DD/MM/YYYY): ");
                dob = sc.nextLine();
            } while(!valid.checkDate(dob));
            SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
            try {
                DOB = sdf.parse(dob);
                isEnterDob = false;
            } catch (Exception e) {
                isEnterDob = true;
            }
        }
        String phone = enterPhone();
        String email = enterEmail();

        int type = enterType();
        if (type == 1) {
            expYear = enterExpYear();
            proSkill = enterSkill();
        } else if (type == 2) {
            String gD = "";
            boolean isEnterGraduationDate = true;
            while(isEnterGraduationDate) {
                do {
                    System.out.print("Enter Graduation Date (DD/MM/YYYY): ");
                    gD = sc.nextLine();
                } while(!valid.checkDate(gD));
                SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
                try {
                    graduationDate = sdf.parse(gD);
                    isEnterGraduationDate = false;
                } catch (Exception e) {
                    isEnterGraduationDate = true;
                }
            }
            graduationRank = enterRank();
            education = enterEducation();
        } else if (type == 3) {
            major = enterMajor();
            semester = enterSemester();
            UniversityName = enterUniversityName();
        }
        
        String fileName = "";
        if (type == 1) {
            fileName = fileExp;
        } else if (type == 2) {
            fileName = fileFresher;
        } else if (type == 3) {
            fileName = fileIntern;
        }

        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);
        
        String candidateID;
        if (check.isEmpty()) {
            candidateID = "C001";
        } else {
            int lastID = Integer.parseInt(check.get(check.size() - 1).getCandidateID().substring(1));
            candidateID = String.format("C%03d", lastID + 1);
        }

        if (type == 1) {
            candidates = new ExpCandidates(candidateID, first, last, DOB, phone, email, expYear, proSkill);
        } else if (type == 2) {
            candidates = new FresherCandidates(candidateID, first, last, DOB, phone, email, graduationDate, graduationRank, education);
        } else if (type == 3) {
            candidates = new InternCandidates(candidateID, first, last, DOB, phone, email, major, semester, UniversityName);
        }

        ArrayList<Candidates> list = new ArrayList<Candidates>();
        list.add(candidates);
        
        FileHandler.writeToFile(list, fileName);

        System.out.println("Added Successfully!");
    }
    
    public void findByID() {
        System.out.println("Which Candidates Do You Want To Dind: [1] Experience   [2] Fresher   [3] Intern   ?");
        String choice = "";
        do {
            System.out.print("Enter A Number: ");
            choice = sc.nextLine();
        } while(!valid.checkInt(choice));
        usefulType = Integer.parseInt(choice);
        
        String fileName = "";
        if (usefulType == 1) {
            fileName = fileExp;
        } else if (usefulType == 2) {
            fileName = fileFresher;
        } else if (usefulType == 3) {
            fileName = fileIntern;
        }
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);
        if(check.isEmpty()) {
            System.out.println("Empty File");
            return;
        }
        
        boolean found = false;
        usefulID = enterID();
        Candidates result = null;
        for (Candidates c : check) {
            if(c.getCandidateID().equals(usefulID)) {
                result = c;
                found = true;
            }
        }
        if (!found) {
            System.out.println("This: " + usefulID + " Is Not Found!");
        }
        System.out.println(result.toString());
    }

    public void findByName() {
        System.out.println("Which Candidates Do You Want To Find: [1] Experience   [2] Fresher   [3] Intern   ?");
        String choice = "";
        do {
            System.out.print("Enter A Number: ");
            choice = sc.nextLine();
        } while(!valid.checkInt(choice));
        int type = Integer.parseInt(choice);
        
        String fileName = "";
        if (type == 1) {
            fileName = fileExp;
        } else if (type == 2) {
            fileName = fileFresher;
        } else if (type == 3) {
            fileName = fileIntern;
        }
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);
        if(check.isEmpty()) {
            System.out.println("Empty File");
            return;
        }
        
        ArrayList<Candidates> result = new ArrayList<Candidates>();
        boolean found = false;
        System.out.print("Enter Name That You Want To Find (Name = First Name + Last Name): ");
        String name = enterName();
        for (Candidates c : check) {
            String temp = c.getFirstName() + " " + c.getLastName();
            if(temp.equals(name)) {
                found = true;
                result.add(c);
            }
        }
        if (!found) {
            System.out.println("No Name Is Found");
            return;
        }
        for (Candidates c : result) {
            System.out.println(c.toString());
        }
    }
    
    public void update() {
        findByID();
        
        System.out.println("What Information Do You Want To Update? [1] Name   [2] Phone   [3] Email   ?");
        String choice = "";
        do {
            System.out.print("Enter A Number: ");
            choice = sc.nextLine();
        } while (!valid.checkInt(choice) && Integer.parseInt(choice) <= 3 && Integer.parseInt(choice) >= 1);
        int type = Integer.parseInt(choice);
        
        String fileName = "";
        if (usefulType == 1) {
            fileName = fileExp;
        } else if (usefulType == 2) {
            fileName = fileFresher;
        } else if (usefulType == 3) {
            fileName = fileIntern;
        }
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);

        for (Candidates c : check) {
            if (c.getCandidateID().equals(usefulID)) {
                if (type == 1) {
                    System.out.print("New First Name. ");
                    String newFirst = enterName();
                    c.setFirstName(newFirst);

                    System.out.print("New Last Name. ");
                    String newLast = enterName();
                    c.setLastName(newLast);
                } else if (type == 2) {
                    System.out.print("New Phone. ");
                    String newPhone = enterPhone();
                    c.setPhone(newPhone);
                } else if (type == 3) {
                    System.out.print("New Email. ");
                    String newEmail = enterEmail();
                    c.setEmail(newEmail);
                }
                break;
            }
        }
        
        FileHandler.updateToFile(check, fileName);
        System.out.println("Update Successfully!");
    }
    
    public void delete() {
        show();
        
        String fileName = "";
        if (usefulType == 1) {
            fileName = fileExp;
        } else if (usefulType == 2) {
            fileName = fileFresher;
        } else if (usefulType == 3) {
            fileName = fileIntern;
        }
        System.out.print("ID That You Want To Delete. ");
        String id = enterID();
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);

        for (Candidates c : check) {
            if (c.getCandidateID().equals(id)) {
                check.remove(c);
                break;
            }
        }
        FileHandler.updateToFile(check, fileName);
        
        System.out.println("Delete Successfully!");
    }
    
    public void show() {
        System.out.println("Which Candidates Do You Want To Show: [1] Experience   [2] Fresher   [3] Intern   ?");
        String choice = "";
        do {
            System.out.print("Enter A Number: ");
            choice = sc.nextLine();
        } while(!valid.checkInt(choice));
        usefulType = Integer.parseInt(choice);
        
        String fileName = "";
        if (usefulType == 1) {
            fileName = fileExp;
        } else if (usefulType == 2) {
            fileName = fileFresher;
        } else if (usefulType == 3) {
            fileName = fileIntern;
        }
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);
        if(check.isEmpty()) {
            System.out.println("Empty File");
            return;
        }
        
        for(Candidates c : check) {
            System.out.println(c.toString());
        }
    }
    
    public void GrantingAccount() {
        ArrayList<User> addSignIn = new ArrayList<User>();
        
        showAllAccount(SignIn.fileGranting);
        ArrayList<User> check = FileHandler.read(SignIn.fileGranting);

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        boolean found = false;

        for (User user : check) {
            if (user.getUsername().equals(username)) {
                addSignIn.add(user);
                found = true;
                check.remove(user);
                break;
            }
        }

        if (found) {
            FileHandler.signin(addSignIn, SignIn.fileSignIn);
            FileHandler.updateFileGrating(check, SignIn.fileGranting);
            System.out.println("Granting Successfully!");
        } else {
            System.out.println("Username Not Found.");
        }
    }
    public void showAllAccount(String fileName) {
        System.out.println("Username | Password");
        ArrayList<User> check = FileHandler.read(fileName);
        for (User user : check) {
            System.out.println(user.toString());
        }
    }
}