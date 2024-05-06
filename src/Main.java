import Data.Vehicle;
import Model.Validator;
import Model.VehicleDAO;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String input;
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        VehicleDAO vehicleDAO = new VehicleDAO();
        while (isRunning){
            try {
                System.out.println("--------------------------------------");
                System.out.println("| Vehicle Management Program         |");
                System.out.println("| 1. Add new vehicle                 |");
                System.out.println("| 2. Check exist vehicle             |");
                System.out.println("| 3. Update vehicle                  |");
                System.out.println("| 4. Delete vehicle                  |");
                System.out.println("| 5. Search vehicle                  |");
                System.out.println("| 6. Display vehicle list            |");
                System.out.println("| 7. Save to file                    |");
                System.out.println("| 8. Print vehicle list              |");
                System.out.println("| 9. Exit                            |");
                System.out.println("--------------------------------------");
                input = scanner.next();
                int choice = Integer.parseInt(input);
                switch (choice){
                    case 1:
                        vehicleDAO.addNewVehicle(1);
                        break;
                    case 2:
                        vehicleDAO.checkExistVehicle();
                        break;
                    case 3:
                        vehicleDAO.updateVehicle();
                        break;
                    case 4:
                        String id_vehicle = Validator.getString("Input ID Vehicle:", "Please enter according to format: C0001,...", "^C\\d+$");
                        vehicleDAO.deleteVehicle(id_vehicle, 1);
                        break;
                    case 5:
                        System.out.println("--------------------------------------");
                        System.out.println("| 1. Search by Name vehicle          |");
                        System.out.println("| 2. Search by ID vehicle            |");
                        System.out.println("--------------------------------------");
                        String searchBy = Validator.getString("Input your choice: ", "Please enter 1 or 2", "[12]");
                        vehicleDAO.searchVehicle(searchBy);
                        break;
                    case 6:
                        System.out.println("-------------------------------------------------");
                        System.out.println("| 1. Show all                                   |");
                        System.out.println("| 2. Show all (descending by price_vehicle)     |");
                        System.out.println("-------------------------------------------------");
                        String showBy = Validator.getString("Input your choice: ", "Please enter 1 or 2", "[12]");
                        vehicleDAO.showList(showBy);
                        break;
                    case 7:
                        for (Vehicle vehicle : VehicleDAO.vehicleList){
                            Field[] fields = vehicle.getClass().getDeclaredFields();
                            List<String> list = new ArrayList<>();
                            for (Field field : fields) {
                                field.setAccessible(true);
                                String fieldName = field.getName();
                                Object value = field.get(vehicle);
                                list.add(value.toString());
                            }
                            vehicleDAO.saveDataToFile(list);

                        }
                        break;
                    case 8:
                        System.out.println("-------------------------------------------------");
                        System.out.println("| 1. Print all                                  |");
                        System.out.println("| 2. Print all (descending by price_vehicle)    |");
                        System.out.println("-------------------------------------------------");
                        String printBy = Validator.getString("Input your choice: ", "Please enter 1 or 2", "[12]");
                        vehicleDAO.printList(printBy);
                        break;
                    case 9:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Error");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage() + ". You must enter integer");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}