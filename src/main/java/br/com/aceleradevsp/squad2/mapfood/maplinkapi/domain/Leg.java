package br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain;

import java.util.List;

public class Leg {

    private Integer distance;
    private Integer nominalDuration;
    private Double averageSpeed;
    private Integer speedProfilePenalty;
    private List<PointMap> pointMaps;

    public Leg() {
    }

    public Leg(Integer distance, Integer nominalDuration, Double averageSpeed, Integer speedProfilePenalty, List<PointMap> pointMaps) {
        this.distance = distance;
        this.nominalDuration = nominalDuration;
        this.averageSpeed = averageSpeed;
        this.speedProfilePenalty = speedProfilePenalty;
        this.pointMaps = pointMaps;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getNominalDuration() {
        return nominalDuration;
    }

    public void setNominalDuration(Integer nominalDuration) {
        this.nominalDuration = nominalDuration;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Integer getSpeedProfilePenalty() {
        return speedProfilePenalty;
    }

    public void setSpeedProfilePenalty(Integer speedProfilePenalty) {
        this.speedProfilePenalty = speedProfilePenalty;
    }

    public List<PointMap> getPointMaps() {
        return pointMaps;
    }

    public void setPointMaps(List<PointMap> pointMaps) {
        this.pointMaps = pointMaps;
    }
}
