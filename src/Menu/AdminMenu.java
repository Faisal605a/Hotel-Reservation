package Menu;

import model.Room;
import service.ReservationService;
import model.RoomType;

import java.text.ParseException;
import java.util.Scanner;

public class AdminMenu {

    private MainMenu mainMenu ;
    private static ReservationService reservationService ;


    public boolean Run() throws ParseException {
        mainMenu =new MainMenu();
        reservationService = ReservationService.getInstance();
        Scanner scanner = new Scanner(System.in);
        int menuInput =0;
    try {
        while ((menuInput > 5 || menuInput < 1)) {
            System.out.println("AdminMenu");
            System.out.println("--------------------------------------------");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            System.out.println("--------------------------------------------");
            System.out.println("Please select a number for the menu option");
            menuInput = Integer.parseInt(scanner.nextLine());
        }

        switch (menuInput) {
            case 1: {
                System.out.println("Customers are :" + reservationService.getAllCustomers());
                System.out.println("1. Back to admin menu");
                System.out.println("2. Exit");
                int ro = Integer.parseInt(scanner.nextLine());
                if (ro == 1)
                    Run();
                break;
            }
            case 2: {
                System.out.println("Rooms are :" + reservationService.getAllRooms().toString());
                System.out.println("1. Back to admin menu");
                System.out.println("2. Exit");
                int ro = Integer.parseInt(scanner.nextLine());
                if (ro == 1)
                    Run();
                break;
            }

            case 3: {
                reservationService.printAllReservations();
                System.out.println("1. Back to admin menu");
                System.out.println("2. Exit");
                int ro = Integer.parseInt(scanner.nextLine());
                if (ro == 1)
                    Run();
                break;

            }
            case 4: {
                RoomType typeRoom = RoomType.SINGLE;
                String roomNumber;
                Double price;
                boolean roomAdding = true;

                while (roomAdding) {
                    System.out.println("What is the type of the room 's/d'.(s Single, d Double)");
                    char inputC = scanner.next().charAt(0);
                    while (!(inputC == 's' || inputC == 'd')) {
                        System.out.println("Please enter 's' OR 'd' ");
                        inputC = scanner.next().charAt(0);
                    }
                    if (inputC == 's') {
                        typeRoom = RoomType.SINGLE;
                    } else if (inputC == 'd') {
                        typeRoom = RoomType.DOUBLE;
                    }
                    System.out.println("What is the Number of the room: ");
                    roomNumber = scanner.next();
                    System.out.println("What is the Price of the room: ");
                    price = Double.parseDouble(scanner.next());

                    reservationService.addRoom(new Room(roomNumber, typeRoom, price));

                    System.out.println("Would you like to add another room y/n: ");
                    char inputRepeat = scanner.next().charAt(0);

                    while (!(inputRepeat == 'y' || inputRepeat == 'n')) {
                        System.out.println("Please enter 'y' OR 'n' ");
                        inputRepeat = scanner.next().charAt(0);
                    }
                    if (inputRepeat == 'n')
                        roomAdding = false;
                }
                Run();
                break;
            }

            case 5:
                mainMenu.Run();
                break;
        }
    }catch (Exception e){
        System.out.println("Something went wrong");
        Run();

    }
        return true;
    }

}
