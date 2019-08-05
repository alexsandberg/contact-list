/**
 * This class creates Contact objects for use in a contacts list.
 * Module 11 Assignment
 * @author Alex Sandberg-Bernard
 */
public class Contact
{
    // class variables
    private String firstName, lastName, fullName, fullNameReverse, companyName,
            phoneNum, email;

    // class constructor called when supplied with String parameters
    public Contact(String firstName, String lastName, String companyName,
                   String phoneNum, String email)
    {
        // set class variables
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.phoneNum = phoneNum;
        this.email = email;
        fullName = firstName + " " + lastName;
        fullNameReverse = lastName + ", " + firstName;
    }

    // class constructor when no parameters are supplied
    public Contact()
    {
        firstName = "first name";
        lastName = "last name";
        companyName = "company";
        phoneNum = "phone number";
        email = "email address";
    }

    // setter method for Contact firstName
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    // setter method for Contact lastName
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    // setter method for Contact fullName
    public void setFullName(String firstName, String lastName)
    {
        fullName = firstName + " " + lastName;
    }

    // setter method for Contact fullNameReverse
    public void setFullNameReverse(String firstName, String lastName)
    {
        fullNameReverse = lastName + ", " + firstName;
    }

    // setter method for Contact companyName
    public void setCompanyName(String companyName)
    {
        this.companyName= companyName;
    }

    // setter method for Contact phoneNum
    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    // setter method for Contact email
    public void setEmail(String email)
    {
        this.email = email;
    }

    // getter method for Contact firstName
    public String getFirstName()
    {
        return firstName;
    }

    // getter method for Contact lastName
    public String getLastName()
    {
        return lastName;
    }

    // getter method for Contact fullName
    public String getFullName()
    {
        return fullName;
    }

    // getter method for Contact fullNameReverse
    public String getFullNameReverse()
    {
        return fullNameReverse;
    }

    // getter method for Contact companyName
    public String getCompanyName()
    {
        return companyName;
    }

    // getter method for Contact phoneNum
    public String getPhoneNum()
    {
        return phoneNum;
    }

    // getter method for Contact email
    public String getEmail()
    {
        return email;
    }

    // toString method returns Contact info using fullName
    public String toString()
    {
        return fullName + "; " + companyName + "; " + phoneNum + "; " + email;
    }

    // toString that returns contact info using fullNameReverse
    public String toStringReverse()
    {
        return fullNameReverse + "; " + companyName + "; " + phoneNum + "; " + email;
    }
}

