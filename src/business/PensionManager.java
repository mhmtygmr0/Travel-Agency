package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    private PensionDao pensionDao = new PensionDao();

    public Pension getById(int id) {
        return this.pensionDao.getByID(id);
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public ArrayList<Pension> getPensionByOtelId(int id) {
        return this.pensionDao.getPensionByOtelId(id);
    }

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

    public boolean save(Pension pension) {
        if (pension.getPension_id() != 0) {
            Helper.showMsg("error");
        }
        return this.pensionDao.save(pension);
    }

    public boolean update(Pension pension) {
        if (this.getById(pension.getPension_id()) == null) {
            Helper.showMsg(pension.getPension_id() + " No registered hotel with ID found!");
            return false;
        }
        return this.pensionDao.update(pension);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " No registered hotel with ID found!");
            return false;
        }
        return this.pensionDao.delete(id);
    }
}
