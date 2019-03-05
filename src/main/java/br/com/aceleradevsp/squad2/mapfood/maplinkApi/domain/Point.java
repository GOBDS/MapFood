package br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain;

public class Point {

    private Double latitude;
    private Double longitude;
    private String siteId;

    public Point() {
    }

    public Point(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Point(Double latitude, Double longitude, String siteId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.siteId = siteId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
