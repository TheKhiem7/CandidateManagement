package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpCandidates extends Candidates{
    private int ExpInYear;
    private String ProSkill;

    public ExpCandidates(String CandidateID, String FirstName, String LastName, Date BirthDate, String Phone, String Email, int ExpInYear, String ProSkill) {
        super(CandidateID, FirstName, LastName, BirthDate, Phone, Email);
        this.ExpInYear = ExpInYear;
        this.ProSkill = ProSkill;
    }

    public int getExpInYear() {
        return ExpInYear;
    }

    public void setExpInYear(int ExpInYear) {
        this.ExpInYear = ExpInYear;
    }

    public String getProSkill() {
        return ProSkill;
    }

    public void setProSkill(String ProSkill) {
        this.ProSkill = ProSkill;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
        String formattedDob = sdf.format(super.getBirthDate());
        String formattedString = String.format("%-10s%-10s%-20s%-15s%-30s%-30s%-10s%-10s",
                super.getCandidateID(), super.getFirstName(), super.getLastName(), formattedDob, super.getPhone(), super.getEmail(), ExpInYear, ProSkill);
        return formattedString;
    }
}