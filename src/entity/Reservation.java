package entity;

import java.time.LocalDate;

public class Reservation {
    private int reservation_id;
    private int room_id;
    private LocalDate check_in_date;
    private LocalDate check_out_date;
    private double total_price;
    private int guest_count;
    private String guest_name;
    private String guess_citizen_id;
    private String guess_phone;
    private String guess_email;

    public Reservation(int reservation_id, int room_id, LocalDate check_in_date, LocalDate check_out_date, double total_price, int guest_count, String guest_name, String guess_citizen_id, String guess_phone, String guess_email) {
        this.reservation_id = reservation_id;
        this.room_id = room_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.total_price = total_price;
        this.guest_count = guest_count;
        this.guest_name = guest_name;
        this.guess_citizen_id = guess_citizen_id;
        this.guess_phone = guess_phone;
        this.guess_email = guess_email;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getGuest_count() {
        return guest_count;
    }

    public void setGuest_count(int guest_count) {
        this.guest_count = guest_count;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuess_citizen_id() {
        return guess_citizen_id;
    }

    public void setGuess_citizen_id(String guess_citizen_id) {
        this.guess_citizen_id = guess_citizen_id;
    }

    public String getGuess_phone() {
        return guess_phone;
    }

    public void setGuess_phone(String guess_phone) {
        this.guess_phone = guess_phone;
    }

    public String getGuess_email() {
        return guess_email;
    }

    public void setGuess_email(String guess_email) {
        this.guess_email = guess_email;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", room_id=" + room_id +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", total_price=" + total_price +
                ", guest_count=" + guest_count +
                ", guest_name='" + guest_name + '\'' +
                ", guess_citizen_id='" + guess_citizen_id + '\'' +
                ", guess_phone='" + guess_phone + '\'' +
                ", guess_email='" + guess_email + '\'' +
                '}';
    }
}
