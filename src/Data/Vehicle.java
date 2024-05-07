package Data;

import java.time.Year;

public class Vehicle{
    String ID_Vehicle;
    String Name_Vehicle;
    float price_Vehicle;
    String brand_Vehicle;
    int productYear;
    String type;
    String color_Vehicle;
    public Vehicle(){}
    public Vehicle(String ID_Vehicle, String name_Vehicle, float price_Vehicle, String brand_Vehicle, int productYear, String type, String color_Vehicle) {
        this.ID_Vehicle = ID_Vehicle;
        this.Name_Vehicle = name_Vehicle;
        this.price_Vehicle = price_Vehicle;
        this.brand_Vehicle = brand_Vehicle;
        this.productYear = productYear;
        this.type = type;
        this.color_Vehicle = color_Vehicle;
    }

    public String getID_Vehicle() {
        return ID_Vehicle;
    }

    public String getName_Vehicle() {
        return Name_Vehicle;
    }

    public float getPrice_Vehicle() {
        return price_Vehicle;
    }

    public String getBrand_Vehicle() {
        return brand_Vehicle;
    }

    public int getProductYear() {
        return productYear;
    }

    public String getType() {
        return type;
    }

    public String getColor_Vehicle() {
        return color_Vehicle;
    }

    public void setID_Vehicle(String ID_Vehicle) {
        this.ID_Vehicle = ID_Vehicle;
    }

    public void setName_Vehicle(String name_Vehicle) {
        Name_Vehicle = name_Vehicle;
    }

    public void setPrice_Vehicle(float price_Vehicle) {
        this.price_Vehicle = price_Vehicle;
    }

    public void setBrand_Vehicle(String brand_Vehicle) {
        this.brand_Vehicle = brand_Vehicle;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor_Vehicle(String color_Vehicle) {
        this.color_Vehicle = color_Vehicle;
    }
}
