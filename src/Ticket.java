public class Ticket {
    // Initializing 5 instant variables including person variable of Person type
    private int row;
    private int seat;
    private double price;
    private Person person;

    // Creating a constructor with 4 parameters, including a Person object
    public Ticket(int rowInput, int seatInput, double priceInput, Person personInput) {
        row = rowInput;
        seat = seatInput;
        price = priceInput;
        person = personInput;
    }

    // Creating 3 getters
    public int getRow() {
        return row;
    }
    public int getSeat() {
        return seat;
    }
    public double getPrice() {
        return price;
    }

    // Creating a print() method to access PersonInfo arraylist and print their information in a specific format
    public String print() {
        String info = "Customer Name: " + person.getName() + " " + person.getSurname() + " \n";
        info += "Customer email: " + person.getEmail() + " \n";
        info += "Row Number : " + row + "     " + "Seat Number: " + seat + "\n";
        info += "Price: $ " + price + "\n";
        return info;
    }
}
