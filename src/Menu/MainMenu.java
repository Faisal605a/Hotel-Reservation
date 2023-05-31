package Menu;

import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {

    private AdminMenu adminMenu;
    private static ReservationService reservationService ;
    private static CustomerService customerService ;

    public boolean Run() {
        adminMenu = new AdminMenu();
        reservationService = ReservationService.getInstance();
        customerService = CustomerService.getInstance();
        Scanner scanner = new Scanner(System.in);
        int menuInput =0;

        try{

            while((menuInput > 5 || menuInput < 1)) {
                System.out.println("MainMenu");
                System.out.println("--------------------------------------------");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                System.out.println("--------------------------------------------");
                System.out.println("Please select a number for the menu option");

                menuInput = Integer.parseInt(scanner.nextLine());
                if (menuInput > 5 || menuInput < 1)
                    System.out.println("Please enter a valid number");
            }
            switch (menuInput){



                case 1: {
                    String email = "";
                    System.out.println("--------------------------------------------");
                    System.out.println("Do you have an account ?(y/n)");
                    char inputC = scanner.next().charAt(0);

                    while (!(inputC == 'y' || inputC == 'n')) {
                        System.out.println("Please enter 'y' OR 'n' ");
                        inputC = scanner.next().charAt(0);
                    }

                    if (inputC == 'y') {
                        System.out.println("Please enter your email : eg. (name@domain.com)");
                        email = scanner.next();
                        if (customerService.getCustomer(email) == null) {
                            System.out.println("Account not found.");
                            Run();
                            break;

                        }
                    }
                    if (inputC == 'n') {
                        System.out.println("Please  create account :");
                        Run();
                        break;
                    }

                    String dateFormat = "dd/MM/yyyy";
                    System.out.println("Please enter the starting date which you wanna book.(dd/mm/yyyy)");
                    Date inputDSart = (new SimpleDateFormat(dateFormat).parse(scanner.next()));

                    System.out.println("Please enter the ending date which you wanna book.(dd/mm/yyyy)");
                    Date inputDEnd = (new SimpleDateFormat(dateFormat).parse(scanner.next()));


                    while (inputDSart.after(inputDEnd)) {
                        System.out.println("Please enter valid dates.");
                        System.out.println("Please enter the starting date which you wanna book.(dd/mm/yyyy)");
                        inputDSart = (new SimpleDateFormat(dateFormat).parse(scanner.next()));

                        System.out.println("Please enter the ending date which you wanna book.(dd/mm/yyyy)");
                        inputDEnd = (new SimpleDateFormat(dateFormat).parse(scanner.next()));
                    }


                    Collection<Room> roomImples = reservationService.findRoom(inputDSart, inputDEnd);
                    if (roomImples.isEmpty()) {
                        System.out.println("No Rooms found for this date: " + inputDSart + "-" + inputDEnd);
                        inputDSart = new Date(inputDSart.getTime() + 7 * 24 * 60 * 60 * 1000);
                        inputDEnd = new Date(inputDEnd.getTime() + 7 * 24 * 60 * 60 * 1000);
                        roomImples = reservationService.findRoom(inputDSart, inputDEnd);
                        System.out.println("But we wil  try to find Rooms for this date: " + inputDSart + "-" + inputDEnd);
                    }
                    if (roomImples.isEmpty()){
                        System.out.println("No rooms found.");
                        Run();
                        break;
                    }
                    System.out.println("--------------------------------------------");
                    System.out.println("Rooms available:");
                    int roomsNum = 1;
                    for (Room roomImple : roomImples) {

                        System.out.println(roomsNum + ". " + roomImple);
                        roomsNum++;
                    }

                    System.out.println("--------------------------------------------");
                    System.out.println("Choice a room: ");
                    System.out.println("To cancel your reservation press  0");
                    roomsNum = Integer.parseInt(scanner.next());
                    if(roomsNum == 0) {
                        Run();
                        break;
                    }
                    Object[] arrayRooms = roomImples.toArray();
                    try{
                        reservationService.reserveRoom(customerService.getCustomer(email), (Room)arrayRooms[(roomsNum-1)],inputDSart,inputDEnd);

                    }catch (IllegalArgumentException e){
                        System.out.println("The email you entered is invalid");
                        Run();
                        break;
                    }
                    System.out.println( (Room)arrayRooms[(roomsNum-1)]+" is booked for "+inputDSart+"-"+inputDEnd);
                    Run();
                    break;

                } case 2:{
                    System.out.println("--------------------------------------------");
                    System.out.println("Do you have an account ?(y/n)");
                    char inputC = scanner.next().charAt(0);

                    while(!(inputC == 'y' || inputC == 'n')) {
                        System.out.println("Please enter 'y' OR 'n' ");
                        inputC = scanner.next().charAt(0);
                    }
                    if(inputC == 'y' ){
                        System.out.println("--------------------------------------------");
                        System.out.println("Please enter your email : eg. (name@domain.com)");
                        String email = scanner.next();

                        try {
                            Collection<Reservation> reservations= reservationService.getCoustomerReservations(customerService.getCustomer(email));
                            for(Reservation re : reservations){
                                System.out.println(re.toString());
                            }


                        }catch (NullPointerException nullpointer_exception){
                            System.out.println("The email you entered doesn't exist");

                        }catch (IllegalArgumentException e ){
                            System.out.println("The email you entered is invalid");
                            Run();
                            break;
                        }



                    }if(inputC == 'n' ){
                        System.out.println("--------------------------------------------");
                        System.out.println("Please create account.");
                        Run();
                        break;
                    }
                    System.out.println("--------------------------------------------");
                    System.out.println("1. Back to Main menu");
                    System.out.println("2. Exit");
                    int ro = Integer.parseInt(scanner.next());
                    if (ro == 1)
                        Run();
                    break;

                }case 3:{
                    System.out.println("Please enter your email :eg. (name@domain.com)");
                    String email = scanner.next();
                    System.out.println("Please enter your First Name :");
                    String firsName = scanner.next();
                    System.out.println("Please enter your Last Name :");
                    String lastName = scanner.next();

                    customerService.addCustomer(email,firsName,lastName);
                    System.out.println("--------------------------------------------");
                    Run();
                    break;
                } case 4:{
                    adminMenu.Run();
                    break;
                } case 5:
                    break;


            }



        }catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Something went wrong");
            Run();
        }

        return true;
    }

    }
