import java.util.*;

class Room {
    int roomNumber;
    String category; // Standard, Deluxe, Suite
    double price;
    boolean isBooked;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | " + category + " | ₹" + price + " | " + (isBooked ? "Booked" : "Available");
    }
}

class Booking {
    String customerName;
    Room room;
    String checkInDate;
    String checkOutDate;

    public Booking(String customerName, Room room, String checkInDate, String checkOutDate) {
        this.customerName = customerName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Booking for " + customerName + " | Room: " + room.roomNumber +
               " (" + room.category + ") | From: " + checkInDate + " To: " + checkOutDate +
               " | Total: ₹" + room.price;
    }
}

public class HotelReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    // Add some sample rooms
    static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 2500));
        rooms.add(new Room(102, "Standard", 2500));
        rooms.add(new Room(201, "Deluxe", 4000));
        rooms.add(new Room(202, "Deluxe", 4000));
        rooms.add(new Room(301, "Suite", 6000));
    }

    static void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println(room);
            }
        }
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter check-in date (dd-mm-yyyy): ");
        String checkIn = scanner.nextLine();

        System.out.print("Enter check-out date (dd-mm-yyyy): ");
        String checkOut = scanner.nextLine();

        System.out.print("Enter preferred category (Standard/Deluxe/Suite): ");
        String category = scanner.nextLine();

        boolean found = false;

        for (Room room : rooms) {
            if (!room.isBooked && room.category.equalsIgnoreCase(category)) {
                // Reserve the first available room of the chosen category
                room.isBooked = true;
                Booking booking = new Booking(name, room, checkIn, checkOut);
                bookings.add(booking);
                System.out.println("\nRoom Booked Successfully!");
                System.out.println(booking);
                processPayment(room.price);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No available rooms in the selected category.");
        }
    }

    static void processPayment(double amount) {
        System.out.println("\n--- Payment Processing ---");
        System.out.println("Amount to pay: ₹" + amount);
        System.out.print("Enter card number (dummy): ");
        scanner.nextLine(); // Simulate card number entry
        System.out.print("Enter CVV (dummy): ");
        scanner.nextLine(); // Simulate CVV entry
        System.out.println("Payment successful! ✅");
    }

    static void viewAllBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking b : bookings) {
                System.out.println(b);
            }
        }
    }

    public static void main(String[] args) {
        initializeRooms();

        int choice;

        do {
            System.out.println("\n===== Hotel Reservation Menu =====");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View All Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewAllBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);
    }
}