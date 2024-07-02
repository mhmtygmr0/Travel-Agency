package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

/**
 * Mevsim yöneticisi sınıfı, mevsim verilerinin işlemlerini yönetir.
 */
public class SeasonManager {
    SeasonDao seasonDao = new SeasonDao();

    /**
     * Belirli bir mevsim ID'sine göre mevsim bilgisini getirir.
     *
     * @param id Mevsim ID'si
     * @return Belirtilen ID'ye sahip mevsim nesnesi
     */
    public Season getById(int id) {
        return this.seasonDao.getByID(id);
    }

    /**
     * Belirli bir otel ID'sine göre mevsim listesini getirir.
     *
     * @param id Otel ID'si
     * @return Belirtilen otel ID'sine sahip mevsimlerin listesi
     */
    public ArrayList<Season> getSeasonsByHotelId(int id) {
        return this.seasonDao.getSeasonsByOtelId(id);
    }

    /**
     * Tüm mevsimleri getirir.
     *
     * @return Tüm mevsimlerin listesi
     */
    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    /**
     * Tablo görüntülemesi için belirli bir boyuta göre mevsimleri nesne dizisi olarak getirir.
     *
     * @param size    Satır boyutu
     * @param seasons Mevsimlerin listesi
     * @return Tablo görüntülemesi için uygun nesne dizilerinin listesi
     */
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season obj : seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getStart_date();
            rowObject[i++] = obj.getEnd_date();
            rowObject[i++] = obj.getPrice_parameter();
            seasonList.add(rowObject);
        }
        return seasonList;
    }

    /**
     * Belirli bir mevsim ID'sine göre fiyat parametresini getirir.
     *
     * @param id Mevsim ID'si
     * @return Belirtilen ID'ye sahip mevsimin fiyat parametresi
     */
    public double returnPriceParameter(int id) {
        return this.seasonDao.returnPriceParameter(id);
    }

    /**
     * Yeni bir mevsim kaydeder.
     *
     * @param season Kaydedilecek mevsim nesnesi
     * @return Kaydetme işlemi başarılıysa true, aksi halde false
     */
    public boolean save(Season season) {
        if (season.getSeason_id() != 0) {
            Helper.showMsg("error");
        }
        return this.seasonDao.save(season);
    }

    /**
     * Belirli bir mevsim ID'sine göre mevsimi siler.
     *
     * @param id Mevsim ID'si
     * @return Silme işlemi başarılıysa true, aksi halde false
     */
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " No registered season with ID found!");
            return false;
        }
        return this.seasonDao.delete(id);
    }
}
