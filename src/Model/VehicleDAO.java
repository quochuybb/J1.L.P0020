package Model;

import Data.Vehicle;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

public class VehicleDAO implements IVehicleDAO{
    public static List<Vehicle> vehicleList = new ArrayList<>();
    String line;
    List<String> vehicleDeleted = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    @Override
    public void addNewVehicle(int root) {
        boolean isCountinue = true;
        while (isCountinue) {
            try {
                if (root == 1){
                    System.out.println("Input information vehicle.");
                    String id_vehicle = Validator.getString("Input ID Vehicle:", "Please enter according to format: C0001,...", "^C\\d+$");
                    String name_Vehicle = Validator.getString("Input Name Vehicle:", "Please enter name", "\\b[a-zA-Z0-9]*((\\s+[a-zA-Z0-9]*)+)?\\b");
                    String color_Vehicle = Validator.getString("Input Color Vehicle:", "Please enter according to format: Red,...", "^[A-Za-z]*$");
                    float price_Vehicle = Float.parseFloat(Validator.getString("Input Price Vehicle:", "Please enter number", "^\\d+$"));
                    String brand_Vehicle = Validator.getString("Input Brand Vehicle:", "Please enter according to format: Roll Royce,...", "\\b[A-Z][a-zA-Z]*\\b");
                    String type = Validator.getString("Input Type Vehicle:", "Please enter according to format: SUV,...", "\\b[a-zA-Z]*((\\s+[a-zA-Z]*)+)?\\b");
                    int productYear = Integer.parseInt(Validator.getString("Input Product Year Vehicle:", "Please enter year", "^\\d{1,4}$"));
                    if (productYear >= 2024){
                        System.out.println("Please enter year smaller 2024");
                        continue;
                    }
                    if (!checkExist(id_vehicle)){
                        Vehicle vehicle = new Vehicle(id_vehicle, name_Vehicle, price_Vehicle, brand_Vehicle, productYear, type, color_Vehicle);
                        vehicleList.add(vehicle);
                    }
                    else {
                        System.out.println("User " + id_vehicle + " exist!!!");
                        continue;
                    }
                    isCountinue = Validator.getBoolen("Continue add (Y/N)");
                }
                else {
                    System.out.println("Update information vehicle.");
                    String id_vehicle = Validator.getString("Input ID Vehicle:", "Please enter according to format: C0001,...", "^(C\\d+)?$");
                    String name_Vehicle = Validator.getString("Input Name Vehicle:", "Please enter name", "^\\b[a-zA-Z0-9]*((\\s+[a-zA-Z0-9]*)+)?\\b$|^$");
                    String color_Vehicle = Validator.getString("Input Color Vehicle:", "Please enter according to format: Red,...", "^[A-Za-z]*$");
                    String str_price_Vehicle = Validator.getString("Input Price Vehicle:", "Please enter number", "^(\\d+)?$");
                    float price_Vehicle;
                    if (str_price_Vehicle.equals("")){
                        price_Vehicle = 0;
                    }
                    else {
                        price_Vehicle = Float.parseFloat(str_price_Vehicle);
                    }
                    String brand_Vehicle = Validator.getString("Input Brand Vehicle:", "Please enter according to format: Roll Royce,...", "\\b[A-Z][a-zA-Z]*\\b|^$");
                    String type = Validator.getString("Input Type Vehicle:", "Please enter according to format: SUV,...", "^\\b[a-zA-Z]*((\\s+[a-zA-Z]*)+)?\\b$|^$");
                    String str_productYear = Validator.getString("Input Product Year Vehicle:", "Please enter year", "^(\\d{1,4})?$");
                    int productYear;
                    if (str_productYear.equals("")){
                        productYear = 0;
                    }
                    else {
                        productYear = Integer.parseInt(str_productYear);
                    }
                    if (productYear >= 2024){
                        System.out.println("Please enter year smaller 2024");
                        continue;
                    }
                    if (!checkExist(id_vehicle)){
                        Vehicle vehicle = new Vehicle(id_vehicle, name_Vehicle, price_Vehicle, brand_Vehicle, productYear, type, color_Vehicle);
                        vehicleList.add(vehicle);
                    }
                    else {
                        System.out.println("Vehicle " + id_vehicle + " exist!!!");
                        continue;
                    }
                    isCountinue = false;
                }

            }
            catch (Exception e) {
                System.out.println("Error input");
            }
        }
    }
    @Override
    public void checkExistVehicle() {
        boolean isContinue = true;
        while (isContinue){
            String id_vehicle = Validator.getString("Input ID Vehicle:", "Please enter according to format: C0001,...", "^C\\d+$");
            if (checkExist(id_vehicle)){
                System.out.println("Exist Vehicle");
            }
            else {
                System.out.println("No Vehicle Found!");
            }
            isContinue = Validator.getBoolen("Continue check exist (Y/N)");
        }


    }
    @Override
    public boolean checkExist(String id_Vehicle) {
        boolean isFound = false;
        try {
            FileReader fileReader = new FileReader("Vehicle.dat");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(" - ");
                if (id_Vehicle.equals(parts[0])){
                    isFound = true;
                    break;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  isFound;
    }

    @Override
    public void deleteVehicle(String id_vehicle, int root) {
        List<List<String>> lines = new ArrayList<>();
        if (!checkExist(id_vehicle)){
            System.out.println("Vehicle does not exist");
            return;
        }
        if (root == 1){
            if (Validator.getBoolen("Continue delete (Y/N)")){
                return;
            }
        }
        try {
            FileReader fileReader = new FileReader("Vehicle.dat");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(" - ");
                if (id_vehicle.equals(parts[0])){
                    vehicleDeleted = Arrays.asList(parts);
                    continue;
                }

                lines.add(Arrays.asList(parts));
            }
            FileWriter clearFile = new FileWriter("Vehicle.dat", false);
            clearFile.flush();
            clearFile.close();
            for (List<String> strings : lines) {
                saveDataToFile(strings);
            }
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Fail");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveDataToFile(List<String> line) {
        if (!checkExist(line.get(0))){
            for (int i =0 ; i < line.size(); i++){
                try {
                    FileWriter printWrite = new FileWriter("Vehicle.dat", true);
                    if (i == line.size() - 1){
                        printWrite.write(line.get(i) + "\n");
                    }
                    else {
                        printWrite.write(line.get(i) + " - ");
                    }
                    printWrite.flush();
                    printWrite.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        else {
            System.out.println("Vehicle " + line.get(0) + " exist!!!");
        }
    }

    @Override
    public void searchByID() {
        try {
            FileReader fileReader = new FileReader("Vehicle.dat");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("Enter id vehicle:");
            String Name = scanner.next();
            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(" - ");
                if (parts[0].toUpperCase().contains(Name.toUpperCase()) ){
                    System.out.println(line);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void searchByName() {
        try {
            FileReader fileReader = new FileReader("Vehicle.dat");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("Enter name vehicle:");
            String Name = scanner.next();
            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(" - ");
                if (parts[1].toUpperCase().contains(Name.toUpperCase()) ){
                    System.out.println(line);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void searchVehicle(String searchBy){
        if (searchBy.equals("1")){
            searchByName();
        }
        else {
            searchByID();
        }
    }
    @Override
    public void updateVehicle()  {
        List<List<String>> lines = new ArrayList<>();
        String id_vehicle = Validator.getString("Input ID Vehicle:", "Please enter according to format: C0001,...", "^C\\d+$");
        deleteVehicle(id_vehicle,2);
        addNewVehicle(2);
        for (Vehicle vehicle : vehicleList){
            Field[] fields = vehicle.getClass().getDeclaredFields();
            List<String> list = new ArrayList<>();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = null;
                try {
                    value = field.get(vehicle);
                    if (value.toString().equals("")){
                        if (fieldName.equals("ID_Vehicle")){
                            value = vehicleDeleted.get(0);
                        } else if (fieldName.equals("Name_Vehicle")) {
                            value = vehicleDeleted.get(1);
                        } else if (fieldName.equals("price_Vehicle")) {
                            value = vehicleDeleted.get(2);
                        } else if (fieldName.equals("brand_Vehicle")) {
                            value = vehicleDeleted.get(3);
                        } else if (fieldName.equals("productYear")) {
                            value = vehicleDeleted.get(4);
                        } else if (fieldName.equals("type")) {
                            value = vehicleDeleted.get(5);
                        } else if (fieldName.equals("color_Vehicle")) {
                            value = vehicleDeleted.get(6);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                list.add(value.toString());
            }
            saveDataToFile(list);

        }
    }

    @Override
    public void showAllDescendingPrice() {
        HashMap<Object,List<String>> mapVehicle = new HashMap<>();
        Object price = null;
        for (Vehicle vehicle : vehicleList)
        {
            List<String> line = new ArrayList<>();
            Field[] fields = vehicle.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                try {
                    Object value = field.get(vehicle);
                    if (fieldName.equals("price_Vehicle")){
                        price = value.toString();
                    }
                    System.out.println(value.toString());
                    line.add(value.toString());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(price);
            System.out.println(line.get(0));
            mapVehicle.put(price,line);
        }
        TreeMap<Object, List<String>> sortedMap = new TreeMap<>(mapVehicle);
        for (Map.Entry<Object, List<String>> entry : sortedMap.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++){
                if (i == entry.getValue().size() -1){
                    System.out.println(entry.getValue().get(i));
                }
                else {
                    System.out.print(entry.getValue().get(i) + " - ");
                }
            }

        }

    }

    @Override
    public void showAllList() {
        for (Vehicle vehicle : vehicleList){
            Field[] fields = vehicle.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                try {
                    Object value = field.get(vehicle);
                    if (fieldName.equals("color_Vehicle")){
                        System.out.println(value);
                    }
                    else {
                        System.out.print(value + " - ");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @Override
    public void showList(String showBy){
        if (showBy.equals("1")){
            showAllList();
        }
        else {
            showAllDescendingPrice();
        }
    }

    @Override
    public void printAllDescendingPrice() {
        try {
            HashMap<Float, String> listVehicle = new HashMap<>();
            FileReader fileReader = new FileReader("Vehicle.dat");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(" - ");
                listVehicle.put(Float.parseFloat(parts[2]),String.join(" - ",parts));
            }
            TreeMap<Float, String> sortedMap = new TreeMap<>(listVehicle);
            for (Map.Entry<Float, String> entry : sortedMap.entrySet()) {
                System.out.println(entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printAllList() {
        try {
            FileReader fileReader = new FileReader("Vehicle.dat");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printList(String printBy) {
        if (printBy.equals("1")){
            printAllList();
        }
        else {
            printAllDescendingPrice();
        }
    }
}
