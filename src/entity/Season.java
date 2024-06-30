package entity;

import core.ComboItem;

import java.time.LocalDate;

public class Season {
    private int season_id;
    private int hotel_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private String season_type;
    private double price_parameter;

    public Season(int season_id, int hotel_id, LocalDate start_date, LocalDate end_date, String season_type, double price_parameter) {
        this.season_id = season_id;
        this.hotel_id = hotel_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.season_type = season_type;
        this.price_parameter = price_parameter;
    }

    public Season(){

    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getSeason_type() {
        return season_type;
    }

    public void setSeason_type(String season_type) {
        this.season_type = season_type;
    }

    public double getPrice_parameter() {
        return price_parameter;
    }

    public void setPrice_parameter(double price_parameter) {
        this.price_parameter = price_parameter;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getSeason_id(), "( Start Date: -> " + this.getStart_date() + " - "+" Finish Date: -> " + this.getEnd_date() + ")");
    }

    @Override
    public String toString() {
        return "Season{" +
                "season_id=" + season_id +
                ", hotel_id=" + hotel_id +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", season_type='" + season_type + '\'' +
                ", price_parameter=" + price_parameter +
                '}';
    }

}
