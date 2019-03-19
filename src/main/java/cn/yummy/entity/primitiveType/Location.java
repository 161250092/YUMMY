package cn.yummy.entity.primitiveType;

import java.io.Serializable;

public class Location implements Serializable{
    private long locationId;
    private String account;
    private double lat;
    private double lng;
    private String address;

    public Location() {
    }

    public Location(double lat, double lng, String address) {
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }


    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getAccount() {
        return account;
    }



    public void setAccount(String account) {
        this.account = account;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getAddress() {
        return address;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
