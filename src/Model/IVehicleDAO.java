package Model;

import java.util.List;

public interface IVehicleDAO {
    public void addNewVehicle(int root);
    public void checkExistVehicle();

    void searchVehicle(String searchby);

    public void updateVehicle();

    boolean checkExist(String id_Vehicle);

    public void searchByName();
    public void searchByID();
    public void showAllList();
    public void showAllDescendingPrice();

    public void deleteVehicle(String id_vehicle, int root);

    public void saveDataToFile(List<String> line);

    public void showList(String showBy);
    public void printAllList();
    public void printAllDescendingPrice();
    public void printList(String showBy);
}
