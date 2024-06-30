package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection connection;

    public SeasonDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.season";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public ArrayList<Season> getSeasonsByOtelId(int otelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.season WHERE hotel_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }

    public Season getByID(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public boolean save(Season season) {
        String query = "INSERT INTO public.season" +
                "(" +
                "hotel_id," +
                "start_date," +
                "end_date," +
                "price_parameter" +
                ")" +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getEnd_date()));
            pr.setDouble(4, season.getPrice_parameter());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotel_id) {
        try {
            String query = "DELETE FROM public.season WHERE season_id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public double returnPriceParameter(int id) {

        double priceParameter = 0.0;
        String query = "SELECT price_parameter FROM public.season WHERE season_id=?";

        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, id);

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                priceParameter = rs.getDouble("price_parameter");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return priceParameter;
    }

    public Season match(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setSeason_id(rs.getInt("season_id"));
        season.setHotel_id(rs.getInt("hotel_id"));
        season.setStart_date(LocalDate.parse(rs.getString("start_date")));
        season.setEnd_date(LocalDate.parse(rs.getString("end_date")));
        season.setPrice_parameter(rs.getDouble("price_parameter"));
        return season;
    }
}
