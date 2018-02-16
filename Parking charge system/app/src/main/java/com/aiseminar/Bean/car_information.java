package com.aiseminar.Bean;

/**
 * Created by 18852 on 2017/3/23.
 */

public class car_information {
    public   String plate;
    public  String username;
    public  String stream;
    public  String begintime;
    public  String endTime;
    public  String money;
    public  String chargeway;
    public  String color;
    public  String location;

    @Override
    public String toString() {
        return "car_information{" +
                "plate='" + plate + '\'' +
                ", username='" + username + '\'' +
                ", stream='" + stream + '\'' +
                ", begintime='" + begintime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", money='" + money + '\'' +
                ", chargeway='" + chargeway + '\'' +
                ", color='" + color + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getChargeway() {
        return chargeway;
    }

    public void setChargeway(String chargeway) {
        this.chargeway = chargeway;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
