package View;

import java.util.Scanner;

public class MenuManagement {
    public void MenuLogin() {
        System.out.println("---Candidate Management System----------");
        System.out.println("[1] Sign In");
        System.out.println("[2] Sign Up");
        System.out.println("[0] Exit");
    }
    
    public void MenuManager() {
        System.out.println("---Candidate Management System----------");
        System.out.println("[1] Add Candidates");
        System.out.println("[2] Find Candidates");
        System.out.println("[3] Update Candidates");
        System.out.println("[4] Delete Candidates");
        System.out.println("[5] Show All Candidates");
        System.out.println("[0] Log Out");
    }

    public void MenuFind() {
        System.out.println("---Candidate Management System----------");
        System.out.println("[1] Find By ID");
        System.out.println("[2] Find By Name");
        System.out.println("[0] Back To Main Menu");
    }

    public void AdminMenuManager() {
        System.out.println("---Candidate Management System----------");
        System.out.println("[1] Add Candidates");
        System.out.println("[2] Find Candidates");
        System.out.println("[3] Update Candidates");
        System.out.println("[4] Delete Candidates");
        System.out.println("[5] Show All Candidates");
        System.out.println("----------------------------------------");
        System.out.println("[6] Granting Sign In");
        System.out.println("[7] Show All Sign Up User");
        System.out.println("[8] Show All Sign In User");
        System.out.println("[9] Show All Granting List");
        System.out.println("[0] Log Out");
    }
}