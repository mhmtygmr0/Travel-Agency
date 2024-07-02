package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

/**
 * Rezervasyon yöneticisi sınıfı, rezervasyon verilerinin işlemlerini yönetir.
 */
public class ReservationManager {
    ReservationDao reservationDao = new ReservationDao();

    /**
     * Belirli bir rezervasyon ID'sine göre rezervasyon bilgisini getirir.
     *
     * @param id Rezervasyon ID'si
     * @return Belirtilen ID'ye sahip rezervasyon nesnesi
     */
    public Reservation getById(int id) {
        return this.reservationDao.getByID(id);
    }

    /**
     * Belirli bir otel ID'sine göre rezervasyon listesini getirir.
     *
     * @param id Otel ID'si
     * @return Belirtilen otel ID'sine sahip rezervasyonların listesi
     */
    public ArrayList<Reservation> getReservationByHotelId(int id) {
        return this.reservationDao.getReservationByHotelId(id);
    }

    /**
     * Tüm rezervasyonları getirir.
     *
     * @return Tüm rezervasyonların listesi
     */
    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    /**
     * Tablo görüntülemesi için belirli bir boyuta göre rezervasyonları nesne dizisi olarak getirir.
     *
     * @param size         Satır boyutu
     * @param reservations Rezervasyonların listesi
     * @return Tablo görüntülemesi için uygun nesne dizilerinin listesi
     */
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

    /**
     * Yeni bir rezervasyon kaydeder.
     *
     * @param reservation Kaydedilecek rezervasyon nesnesi
     * @return Kaydetme işlemi başarılıysa true, aksi halde false
     */
    public boolean save(Reservation reservation) {
        if (reservation.getReservation_id() != 0) {
            Helper.showMsg("error");
        }
        return this.reservationDao.save(reservation);
    }

    /**
     * Belirli bir rezervasyon ID'sine göre rezervasyonu siler.
     *
     * @param id Rezervasyon ID'si
     * @return Silme işlemi başarılıysa true, aksi halde false
     */
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(" Reservation not found!");
            return false;
        }
        // Belirli bir rezervasyon ID'sine sahip tüm rezervasyonları siler.
        for (Reservation reservation : this.reservationDao.getByListReservationId(id)) {
            this.reservationDao.delete(reservation.getReservation_id());
        }
        return this.reservationDao.delete(id);
    }

    /**
     * Belirli bir rezervasyon nesnesini günceller.
     *
     * @param reservation Güncellenecek rezervasyon nesnesi
     * @return Güncelleme işlemi başarılıysa true, aksi halde false
     */
    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getReservation_id()) == null) {
            Helper.showMsg("Reservation " + reservation.getReservation_id() + " not found!");
            return false;
        }
        return this.reservationDao.update(reservation);
    }
}
