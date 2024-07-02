package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;

import java.util.ArrayList;

/**
 * Oda yöneticisi sınıfı, oda verilerinin işlemlerini yönetir.
 */
public class RoomManager {
    RoomDao roomDao = new RoomDao();

    /**
     * Belirli bir oda ID'sine göre oda bilgisini getirir.
     *
     * @param id Oda ID'si
     * @return Belirtilen ID'ye sahip oda nesnesi
     */
    public Room getById(int id) {
        return this.roomDao.getByID(id);
    }

    /**
     * Tüm odaları getirir.
     *
     * @return Tüm odaların listesi
     */
    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    /**
     * Belirli bir otel ID'sine göre odaların listesini getirir.
     *
     * @param id Otel ID'si
     * @return Belirtilen otel ID'sine sahip odaların listesi
     */
    public ArrayList<Room> getRoomByOtelId(int id) {
        return this.roomDao.getRoomByOtelId(id);
    }

    /**
     * Tablo görüntülemesi için belirli bir boyuta göre odaları nesne dizisi olarak getirir.
     *
     * @param size  Satır boyutu
     * @param rooms Odaların listesi
     * @return Tablo görüntülemesi için uygun nesne dizilerinin listesi
     */
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room obj : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getRoom_type();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdult_price();
            rowObject[i++] = obj.getChild_price();
            rowObject[i++] = obj.getBed_capacity();
            rowObject[i++] = obj.getSquare_meter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGame_console();
            rowObject[i++] = obj.isCash_box();
            rowObject[i++] = obj.isProjection();
            rowObject[i++] = obj.isGym();
            roomList.add(rowObject);
        }
        return roomList;
    }

    /**
     * Belirli kriterlere göre odaları arar.
     *
     * @param hotelName Otel adı
     * @param cityName  Şehir adı
     * @param startDate Başlangıç tarihi
     * @param endDate   Bitiş tarihi
     * @return Arama sonucu bulunan odaların listesi
     */
    public ArrayList<Room> searchForTable(String hotelName, String cityName, String startDate, String endDate) {
        return roomDao.searchForTable(hotelName, cityName, startDate, endDate);
    }

    /**
     * Yeni bir oda kaydeder.
     *
     * @param room Kaydedilecek oda nesnesi
     * @return Kaydetme işlemi başarılıysa true, aksi halde false
     */
    public boolean save(Room room) {
        if (room.getRoom_id() != 0) {
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }

    /**
     * Oda stokunu günceller.
     *
     * @param room Güncellenecek oda nesnesi
     * @return Güncelleme işlemi başarılıysa true, aksi halde false
     */
    public boolean updateStock(Room room) {
        if (this.getById(room.getRoom_id()) == null) {
            return false;
        }
        return this.roomDao.updateStock(room);
    }

    /**
     * Belirli bir oda ID'sine göre odanın silinmesini sağlar.
     *
     * @param id Oda ID'si
     * @return Silme işlemi başarılıysa true, aksi halde false
     */
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " No registered room with ID found!");
            return false;
        }
        return this.roomDao.delete(id);
    }

    /**
     * Belirli bir oda nesnesini günceller.
     *
     * @param room Güncellenecek oda nesnesi
     * @return Güncelleme işlemi başarılıysa true, aksi halde false
     */
    public boolean update(Room room) {
        if (this.getById(room.getRoom_id()) == null) {
            Helper.showMsg(room.getRoom_id() + " No registered room with ID found!");
            return false;
        }
        return this.roomDao.update(room);
    }
}
