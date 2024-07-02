package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;

import java.util.ArrayList;

/**
 * Pansiyon yöneticisi sınıfı, pansiyon verilerinin işlemlerini yönetir.
 */
public class PensionManager {
    private final PensionDao pensionDao = new PensionDao();

    /**
     * Belirli bir pansiyon ID'sine göre pansiyon bilgisini getirir.
     *
     * @param id Pansiyon ID'si
     * @return Belirtilen ID'ye sahip pansiyon nesnesi
     */
    public Pension getById(int id) {
        return this.pensionDao.getByID(id);
    }

    /**
     * Tüm pansiyonları getirir.
     *
     * @return Tüm pansiyonların listesi
     */
    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    /**
     * Belirli bir otel ID'sine göre pansiyon listesini getirir.
     *
     * @param id Otel ID'si
     * @return Belirtilen otel ID'sine sahip pansiyonların listesi
     */
    public ArrayList<Pension> getPensionByHotelId(int id) {
        return this.pensionDao.getPensionByOtelId(id);
    }

    /**
     * Tablo görüntülemesi için belirli bir boyuta göre pansiyonları nesne dizisi olarak getirir.
     *
     * @param size     Satır boyutu
     * @param pensions Pansiyonların listesi
     * @return Tablo görüntülemesi için uygun nesne dizilerinin listesi
     */
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for (Pension obj : pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_type();
            pensionList.add(rowObject);
        }
        return pensionList;
    }

    /**
     * Yeni bir pansiyon kaydeder.
     *
     * @param pension Kaydedilecek pansiyon nesnesi
     * @return Kaydetme işlemi başarılıysa true, aksi halde false
     */
    public boolean save(Pension pension) {
        if (pension.getPension_id() != 0) {
            Helper.showMsg("error");
        }
        return this.pensionDao.save(pension);
    }

    /**
     * Belirli bir pansiyon nesnesini günceller.
     *
     * @param pension Güncellenecek pansiyon nesnesi
     * @return Güncelleme işlemi başarılıysa true, aksi halde false
     */
    public boolean update(Pension pension) {
        if (this.getById(pension.getPension_id()) == null) {
            Helper.showMsg(pension.getPension_id() + " No registered pension with ID found!");
            return false;
        }
        return this.pensionDao.update(pension);
    }

    /**
     * Belirli bir pansiyon ID'sine göre pansiyonu siler.
     *
     * @param id Pansiyon ID'si
     * @return Silme işlemi başarılıysa true, aksi halde false
     */
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " No registered pension with ID found!");
            return false;
        }
        return this.pensionDao.delete(id);
    }
}
