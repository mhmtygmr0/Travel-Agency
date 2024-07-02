package view;

import business.ReservationManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.Pension;
import entity.Reservation;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class UpdateReservationView extends Layout {
    private JPanel container;
    private JRadioButton rb_wifi;
    private JRadioButton rb_car_park;
    private JRadioButton rb_spa;
    private JRadioButton rb_gym;
    private JRadioButton rb_fitness;
    private JRadioButton rb_tv;
    private JRadioButton rb_swimming;
    private JRadioButton rb_room_service;
    private JRadioButton rb_game_console;
    private JRadioButton rb_projection;
    private JRadioButton rb_minibar;
    private JRadioButton rb_cash_safe;
    private JTextField txt_end_date;
    private JTextField txt_bed_capacity;
    private JTextField txt_m2;
    private JTextField txt_guest_name;
    private JTextField txt_guest_id;
    private JTextField txt_guest_phone;
    private JTextField txt_guest_email;
    private JTextField txt_guest_total_price;
    private JTextField txt_total_people;
    private JTextField txt_pension;
    private JTextField txt_room_type;
    private JTextField txt_hotel_name;
    private JTextField txt_hotel_adress;
    private JTextField txt_hotel_star;
    private JButton btn_save;
    private JTextField txt_start_date;
    private ReservationManager reservationManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private Season season;
    private Pension pension;
    private Reservation reservation;
    private Room room;
    private String star_date;
    private String end_date;

    public UpdateReservationView(Room room, String star_date, String end_date, int adult_count, int child_count, Reservation reservation) {
        this.add(container);
        this.guiInitilize(800, 650);

        this.reservationManager = new ReservationManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();

        this.room = room;
        this.star_date = star_date;
        this.end_date = end_date;

        if (this.reservation == null) ;
        {
            this.reservation = this.reservationManager.getById(reservation.getReservation_id());
            this.roomManager = new RoomManager();

        }
        int guest_count = adult_count + child_count;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(star_date, formatter);
        LocalDate endDate = LocalDate.parse(end_date, formatter);
        long day_count = ChronoUnit.DAYS.between(startDate, endDate);

        double total_price = (int) ((int) ((this.room.getAdult_price() * adult_count + this.room.getChild_price() * child_count)) * day_count);

        this.txt_hotel_name.setText(this.room.getHotel().getHotel_name());
        this.txt_hotel_adress.setText(this.room.getHotel().getHotel_address());
        this.txt_hotel_star.setText(this.room.getHotel().getHotel_star());
        this.txt_room_type.setText(String.valueOf((Room.RoomType) this.room.getRoom_type()));
        this.txt_bed_capacity.setText(String.valueOf(this.room.getBed_capacity()));
        this.txt_pension.setText(String.valueOf((Pension.PensionType) this.room.getPension().getPension_type()));
        this.txt_m2.setText(String.valueOf(this.room.getSquare_meter()));
        this.txt_start_date.setText(String.valueOf(this.star_date));
        this.txt_end_date.setText(String.valueOf(this.end_date));
        this.txt_guest_total_price.setText(String.valueOf(total_price));
        this.txt_total_people.setText(String.valueOf(guest_count));
        this.rb_car_park.setSelected(this.room.getHotel().isHotel_car_park());
        this.rb_wifi.setSelected(this.room.getHotel().isHotel_wifi());
        this.rb_swimming.setSelected(this.room.getHotel().isHotel_pool());
        this.rb_gym.setSelected(this.room.getHotel().isHotel_fitness());
        this.rb_spa.setSelected(this.room.getHotel().isHotel_spa());
        this.rb_room_service.setSelected(this.room.getHotel().isHotel_room_service());
        this.rb_tv.setSelected(this.room.isTelevision());
        this.rb_game_console.setSelected(this.room.isGame_console());
        this.rb_cash_safe.setSelected(this.room.isCash_box());
        this.rb_projection.setSelected(this.room.isProjection());
        this.rb_minibar.setSelected(this.room.isMinibar());
        this.reservation.setGuest_phone(this.txt_guest_phone.getText());

        if (reservation != null) {
            this.reservation.setGuest_citizen_id(this.txt_guest_id.getText());
            this.reservation.setGuest_name(this.txt_guest_name.getText());
            this.reservation.setGuest_email(this.txt_guest_email.getText());
            this.reservation.setGuest_phone(this.txt_guest_phone.getText());
        }

        this.btn_save.addActionListener(e -> {
            JTextField[] checkfieldEmpty = {this.txt_guest_name, this.txt_guest_id, this.txt_guest_email, this.txt_guest_phone};
            if (Helper.isFieldListEmpty(checkfieldEmpty)) {
                Helper.showMsg("fill");
            } else {
                boolean result;

                this.reservation.setGuest_citizen_id(this.txt_guest_id.getText());
                this.reservation.setGuest_name(this.txt_guest_name.getText());
                this.reservation.setGuest_email(this.txt_guest_email.getText());
                this.reservation.setGuest_phone(this.txt_guest_phone.getText());


                if (this.reservation.getReservation_id() != 0) {
                    result = this.reservationManager.update(this.reservation);
                } else {
                    result = false;
                }
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}