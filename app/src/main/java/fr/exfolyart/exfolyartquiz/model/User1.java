package fr.exfolyart.exfolyartquiz.model;

/**
 * Created by Exfolyart on 22/12/2020.
 */
public class User1 {
    private String mFirstname;

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstname='" + mFirstname + '\'' +
                '}';
    }
}
