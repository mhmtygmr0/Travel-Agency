package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    ReservationDao reservationDao = new ReservationDao();

    public Reservation getById(int id) {
        return this.reservationDao.getByID(id);
    }

    public ArrayList<Reservation> getReservationByHotelId(int id) {
        return this.reservationDao.getReservationByHotelId(id);
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> resList = new ArrayList<>();
        for (Reservation obj : reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getReservation_id();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getCheck_in_date();
            rowObject[i++] = obj.getCheck_out_date();
            rowObject[i++] = obj.getTotal_price();
            rowObject[i++] = obj.getGuest_count();
            rowObject[i++] = obj.getGuest_name();
            rowObject[i++] = obj.getGuest_citizen_id();
            rowObject[i++] = obj.getGuest_email();
            rowObject[i++] = obj.getGuest_phone();

            resList.add(rowObject);
        }
        return resList;
    }

    public boolean save(Reservation reservation) {
        if (reservation.getReservation_id() != 0) {
            Helper.showMsg("error");
        }
        return this.reservationDao.save(reservation);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(" Reservation not found!");
            return false;
        }
        for (Reservation reservation : this.reservationDao.getByListReservationId(id)) {
            this.reservationDao.delete(reservation.getReservation_id());
        }
        return this.reservationDao.delete(id);
    }

    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getReservation_id()) == null) {
            Helper.showMsg("Reservation " + reservation.getReservation_id() + "not found!");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

}


