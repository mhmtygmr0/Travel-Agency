package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    SeasonDao seasonDao = new SeasonDao();

    public Season getById(int id) {
        return this.seasonDao.getByID(id);
    }

    public ArrayList<Season> getSeasonsByOtelId(int id) {
        return this.seasonDao.getSeasonsByOtelId(id);
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

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

    public double returnPriceParameter(int id) {
        return this.seasonDao.returnPriceParameter(id);
    }

    public boolean save(Season season) {
        if (season.getSeason_id() != 0) {
            Helper.showMsg("error");
        }
        return this.seasonDao.save(season);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " No registered hotel with ID found!");
            return false;
        }
        return this.seasonDao.delete(id);
    }
}
