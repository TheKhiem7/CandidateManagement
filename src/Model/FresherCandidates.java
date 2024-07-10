package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FresherCandidates extends Candidates{
    private Date Graduation_date;
    private String Graduation_rank;
    private String Education;

    public FresherCandidates(String CandidateID, String FirstName, String LastName, Date BirthDate, String Phone, String Email, Date Graduation_date, String Graduation_rank, String Education) {
        super(CandidateID, FirstName, LastName, BirthDate, Phone, Email);
        this.Graduation_date = Graduation_date;
        this.Graduation_rank = Graduation_rank;
        this.Education = Education;
    }

    public Date getGraduation_date() {
        return Graduation_date;
    }

    public void setGraduation_date(Date Graduation_date) {
        this.Graduation_date = Graduation_date;
    }

    public String getGraduation_rank() {
        return Graduation_rank;
    }

    public void setGraduation_rank(String Graduation_rank) {
        this.Graduation_rank = Graduation_rank;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String Education) {
        this.Education = Education;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
        String formattedDob = sdf.format(super.getBirthDate());
        String formattedGD = sdf.format(Graduation_date);
        String formattedString = String.format("%-10s%-10s%-20s%-15s%-30s%-30s%-15s%-10s%-10s",
                super.getCandidateID(), super.getFirstName(), super.getLastName(), formattedDob, super.getPhone(), super.getEmail(), formattedGD, Graduation_rank, Education);
        return formattedString;
    }
}