/**
 * This program uses a TreeMap to create a contact list that is customizable
 * based on user input.
 * Module 11 Assignment
 * @author Alex Sandberg-Bernard
 */

import java.io.*;
import java.util.*;

public class ContactList
{
    /**
     * Main program execution method. Accepts user input and directs program
     * flow.
     * @param args - not used
     */
    public static void main(String[] args)
    {
        // open Scanner
        Scanner input = new Scanner(System.in);

        // create sample contact list
        // create several new Contact objects
        Contact steve = new Contact("Steve", "Philips", "Oracle", "3018579974",
                "steve_philips@email.com");
        Contact megan = new Contact("Megan", "Hamilton", "Facebook",
                "4781903896", "megan_hamilton@email.com");
        Contact zoe = new Contact("Zoe", "Fitzgerald", "Tesla", "4108393902",
                "zoe_fitzgerald@email.com");
        Contact kevin = new Contact("Kevin", "Smith", "Google", "3891005671",
                "kevin_smith@email.com");

        // create TreeMap of contacts ordered by first name and store contacts
        TreeMap<String, Contact> contactsFirst = new TreeMap<>();
        contactsFirst.put(steve.getFullName(), steve);
        contactsFirst.put(megan.getFullName(), megan);
        contactsFirst.put(zoe.getFullName(), zoe);
        contactsFirst.put(kevin.getFullName(), kevin);

        // create TreeMap of contacts ordered by last name and store contacts
        TreeMap<String, Contact> contactsLast = new TreeMap<>();
        contactsLast.put(steve.getFullNameReverse(), steve);
        contactsLast.put(megan.getFullNameReverse(), megan);
        contactsLast.put(zoe.getFullNameReverse(), zoe);
        contactsLast.put(kevin.getFullNameReverse(), kevin);

        // prompt user for file name
        System.out.println();
        System.out.println("Please enter the name of the contacts file: ");
        String fileName = input.nextLine();

        // create new File from user input
        File contactsFile = new File(fileName);

        // file header
        String header = String.format("%15s%22s%20s%30s\n", "Name",
                "Company", "Phone number", "Email address");

        // write the contacts file ordered by first name
        writeContactsFirst(contactsFirst, header, contactsFile);

        // variable for user input
        int commandChoice = 0;

        // display command menu
        System.out.println("\nCommands:");
        commandMenu();

        // process user input with do-while and switch
        do
        {
            // prompt user to enter command
            System.out.println();
            System.out.println("Enter command: ");
            commandChoice = input.nextInt();

            // process user input
            switch(commandChoice)
            {
                case 1: // view commands
                {
                    System.out.println("Commands:");
                    commandMenu();
                    break;
                }
                case 2: // View contact list sorted by first name
                {
                    displayContactsFirst(contactsFirst, header);
                    break;
                }
                case 3: // View contact list sorted by last name
                {
                    displayContactsLast(contactsLast, header);
                    break;
                }
                case 4: // add entry
                {
                    // call addEntry method to make new Contact object
                    Contact newContact = addEntry(input);
                    // add new Contact to TreeMaps
                    contactsFirst.put(newContact.getFullName(), newContact);
                    contactsLast.put(newContact.getFullNameReverse(),
                            newContact);

                    String commandString;

                    // prompt user to update contacts file
                    do
                    {
                        System.out.println("Would you like to update the " +
                                "saved contacts file? (Y/N): ");
                        commandString = input.nextLine();

                        switch (commandString)
                        {
                            case "Y":
                            {
                                // if answer Y, prompt user to choose format
                                int command;
                                System.out.println("1. Sorted by first name.");
                                System.out.println("2. Sorted by last name.");
                                System.out.println("Please enter 1 or 2: ");
                                command = input.nextInt();
                                switch (command)
                                {
                                    case 1: // sorted by first name
                                    {
                                        writeContactsFirst(contactsFirst,
                                                header, contactsFile);
                                        System.out.println("\nFile updated!");
                                        break;
                                    }
                                    case 2: // sorted by last name
                                    {
                                        writeContactsLast(contactsLast,
                                                header, contactsFile);
                                        System.out.println("\nFile updated!");
                                        break;
                                    }
                                    default:
                                    {
                                        System.out.println(
                                                "\nInvalid response.\n");
                                    }
                                }
                            }
                            case "N": // don't update file
                            {
                                break;
                            }
                            default:
                            {
                                System.out.println("\nInvalid response.\n");
                            }
                        }
                    }
                    while (!(commandString.equals("Y")) &&
                            !(commandString.equals("N")));
                    break;
                }
                case 5: // remove entry
                {
                    input.nextLine();

                    // display contact list
                    displayContactsFirst(contactsFirst, header);

                    boolean userReponse;

                    // evaluate user entry
                    do
                    {
                        // prompt user for input
                        System.out.println("\nEnter full name of entry to " +
                                "remove, exactly as displayed above: ");
                        String removeContact = input.nextLine();

                        // attempt to find contact from user entry
                        userReponse = contactsFirst.containsKey(removeContact);

                        // print error if contact not found
                        if (!userReponse)
                        {
                            System.out.println("\nUnable to locate contact.\n");
                        }
                        else
                        {
                            // reformat for contactsLast, then remove reverse
                            Set<Map.Entry<String, Contact>> set =
                                    contactsFirst.entrySet();

                            // create set and iterate through to find contact
                            for(Map.Entry<String, Contact> me: set)
                            {
                                // store each contact in entry
                                Contact entry = me.getValue();

                                // get reverse full name for matching contact
                                if(entry.getFullName().equals(removeContact))
                                {
                                    String reverse = entry.getFullNameReverse();

                                    // remove contact from contactsLast
                                    contactsLast.remove(reverse);
                                }
                            }
                            // remove contact from contactsFirst
                            contactsFirst.remove(removeContact);

                            // print confirmation if successful
                            System.out.println("\nContact removed!\n");
                        }
                    }
                    while (!userReponse);

                    // prompt user to update contacts file
                    String commandString2;
                    do
                    {
                        System.out.println("Would you like to update the " +
                                "saved contacts file? (Y/N): ");
                        commandString2 = input.nextLine();

                        switch (commandString2)
                        {
                            case "Y":
                            {
                                // if answer Y, prompt user to choose format
                                int command;
                                System.out.println("1. Sorted by first name.");
                                System.out.println("2. Sorted by last name.");
                                System.out.println("Please enter 1 or 2: ");
                                command = input.nextInt();
                                switch (command)
                                {
                                    case 1: // sorted by first name
                                    {
                                        writeContactsFirst(contactsFirst,
                                                header, contactsFile);
                                        System.out.println("\nFile updated!");
                                        break;
                                    }
                                    case 2: // sorted by last name
                                    {
                                        writeContactsLast(contactsLast,
                                                header, contactsFile);
                                        System.out.println("\nFile updated!");
                                        break;
                                    }
                                    default:
                                    {
                                        System.out.println(
                                                "\nInvalid response.\n");
                                    }
                                }
                            }
                            case "N": // don't update file
                            {
                                break;
                            }
                            default:
                            {
                                System.out.println("\nInvalid response.\n");
                            }
                        }
                    }
                    while (!(commandString2.equals("Y")) &&
                            !(commandString2.equals("N")));

                    break;

                }
                case 6: // exit
                {
                }
                default:
            }
        }
        while(!(commandChoice==6));

    }

    /**
     * Displays menu of command options.
     */
    public static void commandMenu()
    {
        System.out.println();
        System.out.println("View commands: 1");
        System.out.println("View contact list sorted by first name: 2");
        System.out.println("View contact list sorted by last name: 3");
        System.out.println("Add an entry: 4");
        System.out.println("Remove an entry: 5");
        System.out.println("Exit: 6");
        System.out.println();
    }

    /**
     * Creates new Contact object to be added to contact list.
     * @param input - Scanner reference used to obtain user input.
     * @return - new Contact object returned for addition to contact list.
     */
    public static Contact addEntry(Scanner input)
    {
        // create new Contact
        Contact newContact = new Contact();

        // method variables
        String firstName, lastName, company, phone, email;

        input.nextLine();

        // prompt user to enter contact information
        System.out.println("Enter first name: ");
        firstName = input.nextLine();
        newContact.setFirstName(firstName);

        System.out.println("Enter last name: ");
        lastName = input.nextLine();
        newContact.setLastName(lastName);

        System.out.println("Enter company: ");
        company = input.nextLine();
        newContact.setCompanyName(company);

        System.out.println("Enter phone number: ");
        phone = input.nextLine();
        newContact.setPhoneNum(phone);

        System.out.println("Enter email address: ");
        email = input.nextLine();
        newContact.setEmail(email);

        // set full name
        newContact.setFullName(firstName, lastName);
        newContact.setFullNameReverse(firstName, lastName);

        return newContact;
    }

    /**
     * Diplays contact list sorted by first name.
     * @param contactsFirst - TreeMap of contacts sorted by first name
     * @param header - String of header information for display
     */
    public static void displayContactsFirst(TreeMap<String,
            Contact> contactsFirst, String header)
    {
        System.out.println(header);
        // get set of entries
        Set<Map.Entry<String, Contact>> set = contactsFirst.entrySet();

        for(Map.Entry<String, Contact> me: set)
        {
            //bw.write(String.format(contactsFirst.toString()));

            Contact entry = me.getValue();
            //String entryString = entry.toStringReverse();
            String entryString = String.format("%15s%22s%20s%30s",
                    entry.getFullName(), entry.getCompanyName(),
                    entry.getPhoneNum(), entry.getEmail());
            System.out.println(entryString);
        }
    }

    /**
     * Diplays contact list sorted by last name.
     * @param contactsLast - TreeMap of contacts sorted by last name
     * @param header - String of header information for display
     */
    public static void displayContactsLast(TreeMap<String,
            Contact> contactsLast, String header)
    {
        System.out.println(header);
        // get set of entries
        Set<Map.Entry<String, Contact>> set = contactsLast.entrySet();

        for(Map.Entry<String, Contact> me: set)
        {
            //bw.write(String.format(contactsFirst.toString()));

            Contact entry = me.getValue();
            //String entryString = entry.toStringReverse();
            String entryString = String.format("%15s%22s%20s%30s",
                    entry.getFullNameReverse(), entry.getCompanyName(),
                    entry.getPhoneNum(), entry.getEmail());
            System.out.println(entryString);
        }
    }

    /**
     * Writes external contact file using TreeMap of contacts sorted by first
     * name.
     * @param contactsFirst - TreeMap of contacts sorted by first name
     * @param header - String of header information for display
     * @param contactsFile - references external contacts file
     */
    public static void writeContactsFirst(TreeMap<String,
            Contact> contactsFirst, String header, File contactsFile)
    {
        // use try/catch for IOExceptions
        // use BufferedWriter to write new file
        try(BufferedWriter bw =
                    new BufferedWriter(new FileWriter(contactsFile)))
        {
            // write header to file
            bw.write(header);

            // get set of entries
            Set<Map.Entry<String, Contact>> set = contactsFirst.entrySet();

            // iterate through set and write to file
            for(Map.Entry<String, Contact> me: set)
            {
                // load each entry into Contact
                Contact entry = me.getValue();

                // create formatted String from each Contact
                String entryString = String.format("%15s%22s%20s%30s\n",
                        entry.getFullName(), entry.getCompanyName(),
                        entry.getPhoneNum(), entry.getEmail());

                // write formatted string to file
                bw.write(entryString);
            }
        }
        catch (IOException exception)
        {
            System.out.println("Exception: " + exception);
        }
    }

    /**
     * Writes external contact file using TreeMap of contacts sorted by last
     * name.
     * @param contactsLast - TreeMap of contacts sorted by last name
     * @param header - String of header information for display
     * @param contactsFile - references external contacts file
     */
    public static void writeContactsLast(TreeMap<String,
            Contact> contactsLast, String header, File contactsFile)
    {
        // use try/catch for IOExceptions
        // use BufferedWriter to write new file
        try(BufferedWriter bw =
                    new BufferedWriter(new FileWriter(contactsFile)))
        {
            // write header to file
            bw.write(header);

            // get set of entries
            Set<Map.Entry<String, Contact>> set = contactsLast.entrySet();

            // iterate through set and write to file
            for(Map.Entry<String, Contact> me: set)
            {
                // load each entry into Contact
                Contact entry = me.getValue();

                // create formatted String from each Contact
                String entryString = String.format("%15s%22s%20s%30s\n",
                        entry.getFullNameReverse(), entry.getCompanyName(),
                        entry.getPhoneNum(), entry.getEmail());

                // write formatted string to file
                bw.write(entryString);
            }
        }
        catch (IOException exception)
        {
            System.out.println("Exception: " + exception);
        }
    }
}
