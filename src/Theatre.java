import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Theatre {
    // Initializing the 3 arrays that used for 3 rows in theatre
    static int[] row1 = new int[12];
    static int[] row2 = new int[16];
    static int[] row3 = new int[20];
    static ArrayList<Ticket> PersonInfo = new ArrayList<>(); // Creating an Arraylist to store user's information

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the New Theatre");

        boolean exit_1 = true;
        while (exit_1) {
            System.out.println("-------------------------------------------------");
            System.out.println("Please Select an option");
            System.out.println("    1) Buy a ticket");
            System.out.println("    2) Print seating area");
            System.out.println("    3) Cancel ticket");
            System.out.println("    4) List available seats");
            System.out.println("    5) Save to file");
            System.out.println("    6) Load from file");
            System.out.println("    7) Print ticket information and total price");
            System.out.println("    8) Sort tickets by price");
            System.out.println("    0) Quit");
            System.out.println("-------------------------------------------------");

            // Validation of input option
            int option;
            while (true) { // Loop until the option input is valid
                try {
                    System.out.print("Enter an option: ");
                    option = Integer.parseInt(input.next());
                    if (option < 0 || option > 8) {     // Using an if statement to check whether option is in between 0 and 8
                        System.out.println("Option must be between 0 and 8!");
                    }
                    else {
                        break;
                    }
                } catch (NumberFormatException e) {     // Using a try-catch to solve the error if user enters a string to option input
                    System.out.println("Invalid input, please enter an integer.");
                }
            }

            // Using a switch case to apply the methods according to the user's input option
            boolean exit_2 = true;
            while (exit_2) {
                switch (option) {
                    case 0:
                        System.out.println("Quitting");
                        exit_1 = false;
                        break;
                    case 1:
                        buy_ticket();
                        break;
                    case 2:
                        print_seating_area();
                        break;
                    case 3:
                        cancel_ticket();
                        break;
                    case 4:
                        show_available();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        load();
                        break;
                    case 7:
                        show_info();
                        break;
                    case 8:
                        sort_ticket(PersonInfo);
                        break;
                    default:
                        System.out.println("Enter a valid input");

                }
                exit_2 = false;
            }
        }
    }

    static void buy_ticket() {
        Scanner input = new Scanner(System.in);
        String nameInput, surnameInput, emailInput, destination = null; // Initializing inputs of name, surname and email
        while (true){       //Validation of First name
            System.out.print("Enter your First name: ");
            nameInput = input.next();
            boolean invalidChar = false;

            // Using a for loop to check user's name character by character, that name only includes a-z & A-Z characters
            for (int i = 0; i < nameInput.length(); i++){
                char j = nameInput.charAt(i);
                if ((j < 'a' || j > 'z') && (j < 'A' || j > 'Z')){
                    invalidChar = true;
                    break;
                }
            }
            if (!invalidChar){
                break;
            }
            else {
                System.out.println("Invalid input. Name should only include letters.");
            }
        }
        while (true){       // Validation of last name
            System.out.print("Enter your Last name: ");
            surnameInput = input.next();
            boolean invalidChar = false;
            for (int i = 0; i < surnameInput.length(); i++){
                char j = surnameInput.charAt(i);
                if ((j < 'a' || j > 'z') && (j < 'A' || j > 'Z')){
                    invalidChar = true;
                    break;
                }
            }
            if (!invalidChar){
                break;
            }
            else {
                System.out.println("Invalid input. Name should only include letters.");
            }
        }
        while (true) {      // Validation of email address
            System.out.print("Enter your Email address: ");
            emailInput = input.next();
            if (emailInput.contains("@") && emailInput.contains(".")) {     // Checking that if email contains '@' and '.'
                break;
            } else {
                System.out.println("Invalid email address. Please try again.");
            }
        }
        System.out.println("----- Ticket Prices ------\nRow 1 = $ 30\nRow 2 = $ 20\nRow 3 = $ 10");

        // Validation of row number input
        int rowInput;
        while (true) {
            try {
                System.out.print("Enter a row number (1-3): ");
                rowInput = Integer.parseInt(input.next());
                if (rowInput < 1 || rowInput > 3) {     // Checking whether row input is between 1 and 3
                    System.out.println("Row number must be between 1 and 3!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {     //Using a try-catch to handle the error if user enters a string to row number
                System.out.println("Input entered is not a valid integer.");
            }
        }

        // Validation of seat number input
        int seatInput;
        while (true) {
            try {       //Using a try-catch to handle the error if user enters a string to seat number
                System.out.print("Enter a seat number: ");
                seatInput = Integer.parseInt(input.next());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Input entered is not a valid integer.");
            }
        }
        if (rowInput == 1) {
            while (seatInput < 1 || seatInput > 12) {       // Checking that seat number is in between 1 and 12 because it is in Row 1
                System.out.println("Entered seat number is invalid!");
                System.out.print("Enter a seat number between 1 and 12: ");
                seatInput = input.nextInt();
            }

            // Creating a new instance called ticket from the two classes Ticket & Person
            // It includes the parameters of inputs of row, seat, seat price, name & email
            Ticket ticket = new Ticket(rowInput, seatInput, seatPrice(rowInput), new Person(nameInput, surnameInput, emailInput));

            if (row1[seatInput-1] == 1){        // Using an if statement to check that user's preferable seat is previously booked
                System.out.println("This seat is booked!\nTry another seat number.");
                buy_ticket();                   // If the seat is booked, buy_ticket method is invoking again
            }
            else {
                row1[seatInput - 1] = 1;            // Replacing the preferred seat number array element with a 1
                PersonInfo.add(ticket);             // Adding the above-mentioned parameters to the array list
                System.out.println("Your seat is reserved successfully.");
            }

        } else if (rowInput == 2) {
            while (seatInput < 1 || seatInput > 16) {
                System.out.println("Entered seat number is invalid!");
                System.out.print("Enter a seat number between 1 and 16: ");
                seatInput = input.nextInt();
            }
            Ticket ticket = new Ticket(rowInput, seatInput, seatPrice(rowInput), new Person(nameInput, surnameInput, emailInput));
            if (row2[seatInput-1] == 1){
                System.out.println("This seat is booked!\nTry another seat number.");
                buy_ticket();
            }
            else{
                row2[seatInput - 1] = 1;
                PersonInfo.add(ticket);
                System.out.println("Your seat is reserved successfully.");
            }
        } else {
            while (seatInput < 1 || seatInput > 20) {
                System.out.println("Entered seat number is invalid!");
                System.out.print("Enter a seat number between 1 and 20: ");
                seatInput = input.nextInt();
            }
            Ticket ticket = new Ticket(rowInput, seatInput, seatPrice(rowInput), new Person(nameInput, surnameInput, emailInput));
            if (row3[seatInput-1] == 1){
                System.out.println("This seat is booked!\nTry another seat number.");
                buy_ticket();
            }
            else {
                row3[seatInput - 1] = 1;
                PersonInfo.add(ticket);
                System.out.println("Your seat is reserved successfully.");
            }
        }
    }

    public static void print_seating_area() {
        System.out.println("     ***********");
        System.out.println("     *  STAGE  *");
        System.out.println("     ***********");
        System.out.print("    ");
        for (int i = 0; i < 6; i++) {               // Using 2 for loops to print the seat details in a one row
            if (row1[i] == 0) {                     // Checking that relevant element in row array is '0' or '1'. if it is '1', program prints 'X'
                System.out.print(row1[i]);
            } else {
                System.out.print("X");
            }
        }
        System.out.print(" ");
        for (int j = 6; j < 12; j++) {
            if (row1[j] == 0) {
                System.out.print(row1[j]);
            } else {
                System.out.print("X");
            }
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            if (row2[i] == 0) {
                System.out.print(row2[i]);
            } else {
                System.out.print("X");
            }
        }
        System.out.print(" ");
        for (int j = 8; j < 16; j++) {
            if (row2[j] == 0) {
                System.out.print(row2[j]);
            } else {
                System.out.print("X");
            }
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            if (row3[i] == 0) {
                System.out.print(row3[i]);
            } else {
                System.out.print("X");
            }
        }
        System.out.print(" ");
        for (int j = 10; j < 20; j++) {
            if (row3[j] == 0) {
                System.out.print(row3[j]);
            } else {
                System.out.print("X");
            }
        }
        System.out.println();
    }
    public static void cancel_ticket(){
        Scanner input = new Scanner(System.in);
        int rowInput;
        while (true) {              // Validation of seat number & row number is same as in the buy_ticket() method
            try {
                System.out.print("Enter a row number (1-3): ");
                rowInput = Integer.parseInt(input.next());
                if (rowInput < 1 || rowInput > 3) {
                    System.out.println("Row number must be between 1 and 3!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input entered is not a valid integer.");
            }
        }
        int seatInput;
        while (true) {
            try {
                System.out.print("Enter a seat number: ");
                seatInput = Integer.parseInt(input.next());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Input entered is not a valid integer.");
            }
        }
        if (rowInput == 1) {
            while (seatInput < 1 || seatInput > 12) {
                System.out.println("Entered seat number is invalid!");
                System.out.print("Enter a seat number between 1 and 12: ");
                seatInput = input.nextInt();
            }
            if (row1[seatInput-1] == 1){                            // Checking that stated seat number element in row array is '1' or '0'
                row1[seatInput-1] = 0;
                for (int i = 0; i< PersonInfo.size(); i++) {        // Using a for loop to access the array list and remove the relevant person information
                    if ((rowInput == PersonInfo.get(i).getRow()) && (seatInput == PersonInfo.get(i).getSeat())) {
                        PersonInfo.remove(i);
                    }
                }
                System.out.println("Ticket canceled");
            }
            else {
                System.out.println("Seat is not booked!");
            }
        }
        else if (rowInput == 2) {
            while (seatInput < 1 || seatInput > 16) {
                System.out.println("Entered seat number is invalid!");
                System.out.print("Enter a seat number between 1 and 16: ");
                seatInput = input.nextInt();
            }
            if (row2[seatInput-1] == 1){
                row2[seatInput-1] = 0;
                for (int i = 0; i< PersonInfo.size(); i++) {
                    if ((rowInput == PersonInfo.get(i).getRow()) && (seatInput == PersonInfo.get(i).getSeat())) {
                        PersonInfo.remove(i);
                    }
                }
                System.out.println("Ticket canceled");
            }
            else {
                System.out.println("Seat is not booked!");
            }
        }
        else {
            while (seatInput < 1 || seatInput > 20) {
                System.out.println("Entered seat number is invalid!");
                System.out.print("Enter a seat number between 1 and 20: ");
                seatInput = input.nextInt();
            }
            if (row3[seatInput - 1] == 1) {
                row3[seatInput - 1] = 0;
                for (int i = 0; i< PersonInfo.size(); i++) {
                    if ((rowInput == PersonInfo.get(i).getRow()) && (seatInput == PersonInfo.get(i).getSeat())) {
                        PersonInfo.remove(i);
                    }
                }
                System.out.println("Ticket canceled");
            } else {
                System.out.println("Seat is not booked!");
            }
        }
    }
    public static void show_available(){
        // Using 3 for loops to travel throughout 3 row arrays and print out only the '0' elements

        System.out.print("Seats available in row 1: ");
        for (int i = 0; i < 12; i++){
            if (row1[i] == 0){
                System.out.print(i+1);
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.print("Seats available in row 2: ");
        for (int i = 0; i < 16; i++){
            if (row2[i] == 0){
                System.out.print(i+1);
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.print("Seats available in row 3: ");
        for (int i = 0; i < 20; i++){
            if (row3[i] == 0){
                System.out.print(i+1);
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    public static void save(){
        try {
            FileWriter myWriter = new FileWriter("Theatre.txt");            // Creating a text file and writing 3 row information line by line
            myWriter.write(Arrays.toString(row1) + System.lineSeparator());
            myWriter.write(Arrays.toString(row2) + System.lineSeparator());
            myWriter.write(Arrays.toString(row3) + System.lineSeparator());
            myWriter.close();
            System.out.println("Successfully wrote to the file");
            }
        catch (IOException e) {
            System.out.println("File cannot be created!");
        }
    }
    public static void load(){

        //Creating an instance called br in class BufferedReader to read the file line by line
        String line1 = "",line2 = "",line3 = "";
        try (BufferedReader br = new BufferedReader(new FileReader("Theatre.txt"))) {
            line1 = br.readLine();      // Assigning 3 rows in text file to 3 different string variables
            line2 = br.readLine();
            line3 = br.readLine();
            int j = 0;
            for (int i = 1; i <= line1.length(); i+=3){                     // Using 3 for loops to travel throughout above 3 variables character by character
                row1[j] = Character.getNumericValue(line1.charAt(i));       // Converting those characters to integers and stores in 3 main row arrays
                j++;
            }
            j = 0;
            for (int i = 1; i <= line2.length(); i+=3){
                row2[j] = Character.getNumericValue(line2.charAt(i));
                j++;
            }
            j = 0;
            for (int i = 1; i <= line3.length(); i+=3){
                row3[j] = Character.getNumericValue(line3.charAt(i));
                j++;
            }
            System.out.println("File loading successful!");
        }
        catch (IOException e){
            System.out.println("Error while reading the file");
        }
    }
    public static int seatPrice(int rowInput){      // Method created to assign the relevant seat prices
        if (rowInput == 1){
            return 30;
        }
        if (rowInput == 2 ){
            return 20;
        }
        else {
            return 10;
        }
    }
    public static void show_info(){
        double total=0;                                         // Initializing a variable to keep the total price of all tickets
        for (int i = 0; i <  PersonInfo.size(); i++) {          // Using a for loop to access arraylist PersonInfo and printing the previously stored information
            Ticket t = PersonInfo.get(i);
            System.out.println((i+1) + ") " + t.print());
            total += t.getPrice();                              // Adding the new ticket's price to total variable
        }
        System.out.println("Total price of sold tickets:"+total);
    }
    public static void sort_ticket(ArrayList<Ticket> PersonInfo){
        int minIndex;
        Ticket temp;
        ArrayList<Ticket> PersonInfoSorted = new ArrayList<Ticket>(PersonInfo);     // Creating a new arraylist called PersonInfoSorted to store the information sorted according to seat price
        for (int start = 0;start < PersonInfoSorted.size() - 1; start++){           // Using selection sort method to sort the arraylist
            minIndex = start;
            for (int i = start + 1; i < PersonInfoSorted.size(); i++){
                if (PersonInfoSorted.get(i).getPrice() < PersonInfoSorted.get(minIndex).getPrice()){
                    minIndex = i;
                }
            }
            temp = PersonInfoSorted.get(start);
            PersonInfoSorted.set(start, PersonInfoSorted.get(minIndex));
            PersonInfoSorted.set(minIndex, temp);
        }
        System.out.println("\nSorting successful.\n----------------------------------");
        double total=0;
        for (int i = 0; i <  PersonInfoSorted.size(); i++) {           // Using a for loop to access new array list and print the information respectively
            Ticket t = PersonInfoSorted.get(i);
            System.out.println((i+1) + ") " + t.print());
            total += t.getPrice();
        }
        System.out.println("Total price of sold tickets:"+total);
    }
}

