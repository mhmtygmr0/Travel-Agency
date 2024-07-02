package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

/**
 * Otel yöneticisi sınıfı, otel verilerinin işlemlerini yönetir.
 */
public class HotelManager {
    private final HotelDao hotelDao;

    /**
     * Otel yöneticisi sınıfının kurucu methodu.
     */
    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    /**
     * Tüm otelleri getirir.
     *
     * @return Tüm otellerin listesi
     */
    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    /**
     * Tablo görüntülemesi için belirli bir boyuta göre otelleri nesne dizisi olarak getirir.
     *
     * @param size   Satır boyutu
     * @param hotels Otel nesnelerinin listesi
     * @return Tablo görüntülemesi için uygun nesne dizilerinin listesi
     */
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for (Hotel obj : hotels) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getHotel_name();
            rowObject[i++] = obj.getHotel_address();
            rowObject[i++] = obj.getHotel_email();
            rowObject[i++] = obj.getHotel_phone();
            rowObject[i++] = obj.getHotel_star();
            rowObject[i++] = obj.isHotel_car_park();
            rowObject[i++] = obj.isHotel_wifi();
            rowObject[i++] = obj.isHotel_pool();
            rowObject[i++] = obj.isHotel_fitness();
            rowObject[i++] = obj.isHotel_concierge();
            rowObject[i++] = obj.isHotel_spa();
            rowObject[i++] = obj.isHotel_room_service();
            hotelList.add(rowObject);
        }
        return hotelList;
    }

    /**
     * Belirli bir otel ID'sine göre otel bilgisini getirir.
     *
     * @param id Otel ID'si
     * @return Belirtilen ID'ye sahip otel nesnesi
     */
    public Hotel getById(int id) {
        return this.hotelDao.getByID(id);
    }

    /**
     * Yeni bir otel kaydeder.
     *
     * @param hotel Kaydedilecek otel nesnesi
     * @return Kaydetme işlemi başarılıysa true, aksi halde false
     */
    public boolean save(Hotel hotel) {
        if (hotel.getHotel_id() != 0) {
            Helper.showMsg("error");
        }
        return this.hotelDao.save(hotel);
    }

    /**
     * Belirli bir otel ID'sine göre oteli siler.
     *
     * @param id Otel ID'si
     * @return Silme işlemi başarılıysa true, aksi halde false
     */
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " No registered hotel with ID found!");
            return false;
        }
        return this.hotelDao.delete(id);
    }

    /**
     * Belirli bir otel nesnesini günceller.
     *
     * @param hotel Güncellenecek otel nesnesi
     * @return Güncelleme işlemi başarılıysa true, aksi halde false
     */
    public boolean update(Hotel hotel) {
        if (this.getById(hotel.getHotel_id()) == null) {
            Helper.showMsg(hotel.getHotel_id() + " No registered hotel with ID found!");
            return false;
        }
        return this.hotelDao.update(hotel);
    }
}
