package entity;

import core.ComboItem;

public class Hotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_address;
    private String hotel_email;
    private String hotel_phone;
    private String hotel_star;
    private boolean hotel_wifi;
    private boolean hotel_car_park;
    private boolean hotel_pool;
    private boolean hotel_fitness;
    private boolean hotel_concierge;
    private boolean hotel_spa;
    private boolean hotel_room_service;

    public Hotel() {
    }

    public Hotel(int hotel_id, String hotel_name, String hotel_address, String hotel_email, String hotel_phone, String hotel_star, boolean hotel_wifi, boolean hotel_car_park, boolean hotel_pool, boolean hotel_fitness, boolean hotel_concierge, boolean hotel_spa, boolean hotel_room_service) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.hotel_email = hotel_email;
        this.hotel_phone = hotel_phone;
        this.hotel_star = hotel_star;
        this.hotel_wifi = hotel_wifi;
        this.hotel_car_park = hotel_car_park;
        this.hotel_pool = hotel_pool;
        this.hotel_fitness = hotel_fitness;
        this.hotel_concierge = hotel_concierge;
        this.hotel_spa = hotel_spa;
        this.hotel_room_service = hotel_room_service;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotel_email() {
        return hotel_email;
    }

    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(String hotel_star) {
        this.hotel_star = hotel_star;
    }

    public boolean isHotel_wifi() {
        return hotel_wifi;
    }

    public void setHotel_wifi(boolean hotel_wifi) {
        this.hotel_wifi = hotel_wifi;
    }

    public boolean isHotel_car_park() {
        return hotel_car_park;
    }

    public void setHotel_car_park(boolean hotel_car_park) {
        this.hotel_car_park = hotel_car_park;
    }

    public boolean isHotel_pool() {
        return hotel_pool;
    }

    public void setHotel_pool(boolean hotel_pool) {
        this.hotel_pool = hotel_pool;
    }

    public boolean isHotel_fitness() {
        return hotel_fitness;
    }

    public void setHotel_fitness(boolean hotel_fitness) {
        this.hotel_fitness = hotel_fitness;
    }

    public boolean isHotel_concierge() {
        return hotel_concierge;
    }

    public void setHotel_concierge(boolean hotel_concierge) {
        this.hotel_concierge = hotel_concierge;
    }

    public boolean isHotel_spa() {
        return hotel_spa;
    }

    public void setHotel_spa(boolean hotel_spa) {
        this.hotel_spa = hotel_spa;
    }

    public boolean isHotel_room_service() {
        return hotel_room_service;
    }

    public void setHotel_room_service(boolean hotel_room_service) {
        this.hotel_room_service = hotel_room_service;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getHotel_id(), this.getHotel_name());
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_address='" + hotel_address + '\'' +
                ", hotel_email='" + hotel_email + '\'' +
                ", hotel_phone='" + hotel_phone + '\'' +
                ", hotel_star='" + hotel_star + '\'' +
                ", hotel_wifi=" + hotel_wifi +
                ", hotel_car_park=" + hotel_car_park +
                ", hotel_pool=" + hotel_pool +
                ", hotel_fitness=" + hotel_fitness +
                ", hotel_concierge=" + hotel_concierge +
                ", hotel_spa=" + hotel_spa +
                ", hotel_room_service=" + hotel_room_service +
                '}';
    }
}
