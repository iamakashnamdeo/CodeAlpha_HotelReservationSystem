import java.util.ArrayList;
import java.util.Scanner;

// ==========================================
// 1. ROOM OBJECT BLUEPRINT
// ==========================================
class Room {
    private int roomNumber;
    private String category;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}

// ==========================================
// 2. RESERVATION OBJECT BLUEPRINT
// ==========================================
class Reservation {
    private static int idCounter = 1001; 
    private int reservationId;
    private String guestName;
    private Room bookedRoom;
    private int durationDays;
    private double totalAmount;

    public Reservation(String guestName, Room bookedRoom, int durationDays) {
        this.reservationId = idCounter++; 
        this.guestName = guestName;
        this.bookedRoom = bookedRoom;
        this.durationDays = durationDays;
        this.totalAmount = bookedRoom.getPricePerNight() * durationDays;
    }

    public int getReservationId() { return reservationId; }
    public String getGuestName() { return guestName; }
    public Room getBookedRoom() { return bookedRoom; }
    public int getDurationDays() { return durationDays; }
    public double getTotalAmount() { return totalAmount; }
}

// ==========================================
// 3. MAIN CORE ENGINE
// ==========================================
public class HotelSystem {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>(); 

    // Constructor: Automatically populates inventory
    public HotelSystem() {
        rooms.add(new Room(101, "Standard", 1200.0));
        rooms.add(new Room(102, "Standard", 1200.0));
        rooms.add(new Room(103, "Deluxe", 1500.0));
        rooms.add(new Room(104, "Deluxe", 1500.0));
        rooms.add(new Room(105, "Luxury", 2000.0));
    }

    // Module 1: Display Available Rooms
    public void displayAvailableRooms() {
        System.out.println("\n┌────────────────────────────────────────────────────────┐");
        System.out.println("│              * CURRENT VACANT INVENTORY *           │");
        System.out.println("├───────────┬───────────────────┬────────────────────────┤");
        System.out.printf("│ %-9s │ %-17s │ %-22s │\n", "ROOM NO.", "CATEGORY", "TARIFF (PER NIGHT)");
        System.out.println("├───────────┼───────────────────┼────────────────────────┤");

        boolean found = false;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.printf("│    %-6d │ %-17s │ Rs. %-18.2f │\n", 
                    room.getRoomNumber(), room.getCategory(), room.getPricePerNight());
                found = true;
            }
        }

        if (!found) {
            System.out.println("│               NO ROOMS AVAILABLE AT THE MOMENT         │");
        }
        System.out.println("└───────────┴───────────────────┴────────────────────────┘");
    }

    // Module 2: Process Bookings
    public void bookRoom(Scanner scanner) {
        System.out.println("\n┌────────────────────────────────────────────────────────┐");
        System.out.println("│              NEW STAY REGISTRATION ENGINE             │");
        System.out.println("└────────────────────────────────────────────────────────┘");
        
        System.out.print("- Enter Guest Full Name: ");
        String name = scanner.nextLine();

        System.out.print("- Enter Room Number to Book: ");
        int selectedNumber = scanner.nextInt();
        
        System.out.print("- Enter Duration of Stay (Nights): ");
        int nights = scanner.nextInt();
        scanner.nextLine(); 

        Room targetRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == selectedNumber && room.isAvailable()) {
                targetRoom = room;
                break; 
            }
        }

        if (targetRoom != null) {
            targetRoom.setAvailable(false);
            Reservation newBooking = new Reservation(name, targetRoom, nights);
            reservations.add(newBooking);

            System.out.println("\n┌────────────────────────────────────────────────────────┐");
            System.out.println("│             - RESERVATION RECEIPT DETAILS  -           │");
            System.out.println("├────────────────────────────────────────────────────────┤");
            System.out.printf("│  BOOKING ID    : #%-34d   │\n", newBooking.getReservationId());
            System.out.printf("│  GUEST NAME    : %-36s  │\n", newBooking.getGuestName());
            System.out.printf("│  ROOM ASSIGNED : Room %-3d (%-19s)        │\n", targetRoom.getRoomNumber(), targetRoom.getCategory());
            System.out.printf("│  STAY DURATION : %-2d Nights                             │\n", newBooking.getDurationDays());
            System.out.println("├────────────────────────────────────────────────────────┤");
            System.out.printf("│ * NET AMOUNT    : Rs. %-31.2f  │\n", newBooking.getTotalAmount());
            System.out.println("├────────────────────────────────────────────────────────┤");
            System.out.println("│       ✅ STATUS: CONFIRMED & READY FOR CHECK-IN         │");
            System.out.println("└────────────────────────────────────────────────────────┘");
        } else {
            System.out.println("\n❌ ERROR: Room is either invalid or already occupied by another guest.");
        }
    }

    // Module 3: Live Transactions Ledger
    public void displayReservations() {
        System.out.println("\n┌────────────────────────────────────────────────────────┐");
        System.out.println("│              ** LIVE RESERVATION LEDGER                │");
        System.out.println("├──────────┬──────────────────────┬──────────┬───────────┤");
         System.out.printf("│ %-8s │ %-20s │ %-8s │ %-9s │\n", "RES ID", "GUEST NAME", "ROOM NO", "TOTAL DUE");
        System.out.println("├──────────┼──────────────────────┼──────────┼───────────┤");

        if (reservations.isEmpty()) {
            System.out.println("│          NO ACTIVE BOOKINGS FOUND IN THE SYSTEM        │");
        } else {
            for (Reservation res : reservations) {
                System.out.printf("│ #%-6d │ %-20s │ %-8d │ Rs. %-5.0f │\n",
                    res.getReservationId(), res.getGuestName(), res.getBookedRoom().getRoomNumber(), res.getTotalAmount());
            }
        }
        System.out.println("└──────────┴──────────────────────┴──────────┴───────────┘");
    }

    // Execution Core Menu Terminal Loop
    public static void main(String[] args) {

        HotelSystem system = new HotelSystem();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n======================================================== ");
            System.out.println("         :W E L C O M E  T O  H O T E L  S K Y :     ");
            System.out.println("                   *RESORT & SPA *                 ");
            System.out.println("  ======================================================== ");
            System.out.println("     [1] B R O W S E   V A C A N T   R O O M S");
            System.out.println("     [2] B O O K   A   N E W   S T A Y");
            System.out.println("     [3] V I E W   M Y   R E S E R V A T I O N S");
            System.out.println("     [4] C H E C K O U T   &   E X I T");
            System.out.println(" ─────────────────────────────────────────────────────────");
            System.out.print(" ~ SELECT AN OPTION (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    system.displayAvailableRooms();
                    break;
                case 2:
                    system.bookRoom(scanner);
                    break;
                case 3:
                    system.displayReservations();
                    break;
                case 4:
                    System.out.println("\n┌────────────────────────────────────────────────────────┐");
                    System.out.println("│ Thank you for choosing Hotel Sky. Have a safe journey! │");
                    System.out.println("└────────────────────────────────────────────────────────┘");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\n❌ ERROR: Invalid choice! Please select an option between 1 and 4.");
            }
        }
    }
}
