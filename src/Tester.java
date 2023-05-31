import Menu.AdminMenu;
import Menu.MainMenu;
import model.Coustomer;

public class Tester {
    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        AdminMenu adminMenu = new AdminMenu();
        mainMenu.Run();
    }
}
