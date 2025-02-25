import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;
    String specialty;

    public Room(int roomNumber, String category, double price, String specialty) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
        this.price = price;
        this.specialty = specialty;
    }
}

public class HotelReservationSystem {
    private static final List<Room> rooms = new ArrayList<>();
    private static final Map<Integer, String> bookings = new HashMap<>();
    private static final Map<Integer, String> paymentStatus = new HashMap<>();
    private static final Map<Integer, Integer> ratings = new HashMap<>();
    private static final Map<String, Double> foodMenu = new HashMap<>();

    static {
        rooms.add(new Room(101, "Deluxe", 150.0, "King-sized bed, Ocean view, Free breakfast"));
        rooms.add(new Room(102, "Suite", 200.0, "Spacious, Living area, Private balcony, Jacuzzi"));
        rooms.add(new Room(103, "Standard", 100.0, "Cozy room, Queen-sized bed, City view"));
        
        foodMenu.put("Breakfast Combo", 20.0);
        foodMenu.put("Lunch Special", 35.0);
        foodMenu.put("Dinner Deluxe", 40.0);
        foodMenu.put("Snack Platter", 15.0);
    }

    public static void displayRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room " + room.roomNumber + " - " + room.category + " - $" + room.price);
                System.out.println("Specialties: " + room.specialty);
            }
        }
    }

    public static void displayFoodMenu() {
        System.out.println("Food Menu:");
        for (Map.Entry<String, Double> entry : foodMenu.entrySet()) {
            System.out.println(entry.getKey() + " - $" + entry.getValue());
        }
    }

    public static void bookRoom(int roomNumber, String guestName) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                room.isAvailable = false;
                bookings.put(roomNumber, guestName);
                paymentStatus.put(roomNumber, "Pending");
                System.out.println("Room " + roomNumber + " booked successfully for " + guestName);
                return;
            }
        }
        System.out.println("Room not available or invalid room number.");
    }

    public static void processPayment(int roomNumber) {
        if (bookings.containsKey(roomNumber)) {
            paymentStatus.put(roomNumber, "Completed");
            System.out.println("Payment completed for Room " + roomNumber);
        } else {
            System.out.println("Invalid room number or no booking found.");
        }
    }

    public static void rateExperience(int roomNumber, int rating) {
        if (bookings.containsKey(roomNumber) && rating >= 1 && rating <= 5) {
            ratings.put(roomNumber, rating);
            System.out.println("Thank you for rating Room " + roomNumber + " with " + rating + " stars!");
        } else {
            System.out.println("Invalid rating or no booking found.");
        }
    }

    public static void hotelQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hotel Quiz: What is the most important factor when choosing a hotel?");
        System.out.println("1. Price\n2. Location\n3. Amenities\n4. Reviews");
        System.out.print("Enter your choice: ");
        int answer = scanner.nextInt();
        switch (answer) {
            case 1: case 2: case 3: case 4:
                System.out.println("Great choice! Different guests prioritize different factors.");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Process Payment");
            System.out.println("4. Rate Experience");
            System.out.println("5. View Food Menu");
            System.out.println("6. Take Hotel Quiz");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayRooms();
                    break;
                case 2:
                    System.out.print("Enter Room Number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Guest Name: ");
                    String guestName = scanner.nextLine();
                    bookRoom(roomNumber, guestName);
                    break;
                case 3:
                    System.out.print("Enter Room Number to process payment: ");
                    int payRoom = scanner.nextInt();
                    processPayment(payRoom);
                    break;
                case 4:
                    System.out.print("Enter Room Number to rate: ");
                    int rateRoom = scanner.nextInt();
                    System.out.print("Enter Rating (1-5): ");
                    int rating = scanner.nextInt();
                    rateExperience(rateRoom, rating);
                    break;
                case 5:
                    displayFoodMenu();
                    break;
                case 6:
                    hotelQuiz();
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
