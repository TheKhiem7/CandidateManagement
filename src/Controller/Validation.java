package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public boolean checkUserName(String inp) {
        if (inp.isEmpty()) {
            System.out.println("This Can Not Be Empty!");
            return false;
        }
        if (inp.matches("^.*[0-9!@#$%^&*(){}_+-=*/.<>?|].*")) {
            System.out.println("This Can Only Contain Characters");
            return false;
        }

        if (!FileHandler.checkDuplicate(inp, SignIn.fileSignUp) || !FileHandler.checkDuplicate(inp, SignIn.fileSignIn)) {
            System.out.println("Duplicated Username!");
            return false;
        }
        return true;
    }

    public boolean checkID(String inp) {
        if (inp.matches("C\\d{3}")) {
            return true;
        }
        return false;
    }
    public boolean checkName (String inp) {
        if (inp.isEmpty()) {
            System.out.println("This Can Not Be Empty!");
            return false;
        }
        if (inp.matches("^.*[0-9!@#$%^&*(){}_+-=*/.<>?|].*")) {
            System.out.println("This Can Only Contain Characters");
            return false;
        }
        return true;
    }
    
    public boolean checkPhone(String inp) {
        if (inp.isEmpty()) {
            System.out.println("Phone Can Not Be Empty!");
            return false;
        }
        
        if (!inp.matches("^[0-9]+$")) {
            System.out.println("Phone Numbers Can Only Contain Digits");
            return false;
        }

        if (!inp.matches("^0[0-9]{9}$")) {
            System.out.println("Invalid Phone Number Format. It Should Start With 0 And Followed By 9 Digits");
            return false;
        }
        
        if (inp.length() != 10) {
            System.out.println("Phone Must Have 10 Characters");
            return false;
        }

        if (!FileHandler.duplicate(inp, Management.fileExp) || !FileHandler.duplicate(inp, Management.fileFresher) || !FileHandler.duplicate(inp, Management.fileIntern)) {
            System.out.println("Duplicated Phone Number!");
            return false;
        }
        return true;
    }
    
    public boolean checkEmail(String inp) {
        if (inp.isEmpty()) {
            System.out.println("Email Can Not Be Empty!");
            return false;
        }
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(!inp.matches(emailRegex)) {
            System.out.println("Email Is Not Valid!");
            return false;
        }

        if (!FileHandler.duplicate(inp, Management.fileExp) || !FileHandler.duplicate(inp, Management.fileFresher) || !FileHandler.duplicate(inp, Management.fileIntern)) {
            System.out.println("Duplicated Email!");
            return false;
        }
        return true;
    }
    
    public boolean checkDate (String dateString) {
        Pattern dateRegexPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
           
        Matcher dateMatcher = dateRegexPattern.matcher(dateString);

        if (dateMatcher.matches()) {

           dateMatcher.reset();

           if (dateMatcher.find()) {
               String day = dateMatcher.group(1);
               String month = dateMatcher.group(2);
               int year = Integer.parseInt(dateMatcher.group(3));

               if ("31".equals(day) && 
                  ("4".equals(month) || "6".equals(month) || "9".equals(month) ||
                   "11".equals(month) || "04".equals(month) || "06".equals(month) || 
                   "09".equals(month))) {
                   return false; // 1, 3, 5, 7, 8, 10, 12 has 31 days
               } else if ("2".equals(month) || "02".equals(month)) {
                    //leap year
                    if (year % 4 == 0) {
                        return !"30".equals(day) && !"31".equals(day);
                    } else {
                        return !"29".equals(day) && !"30".equals(day) && !"31".equals(day);
                    }
               } else {
                   return true;
               }
           } else {
               return false;
           }
        } else {
            return false;
        }
    }

    public boolean checkType (String inp) {
        try {
            int x = Integer.parseInt(inp);
            if (x == 1 || x == 2 || x == 3) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public boolean checkInt (String inp) {
        try {
            Integer.parseInt(inp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkRank(String inp) {
        String [] data = new String[]{"Excellent", "Good", "Bad"};
        for (String s : data) {
            if (s.equals(inp)) {
                return true;
            }
        }
        return false;
    }
}