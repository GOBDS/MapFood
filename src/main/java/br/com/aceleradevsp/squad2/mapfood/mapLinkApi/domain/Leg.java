package br.com.aceleradevsp.squad2.mapfood.mapLinkApi.domain;

import java.util.List;

public class Leg {

    public Integer distance;
    public Integer nominalDuration;
    public Double averageSpeed;
    public Integer speedProfilePenalty;
    public List<Point> points;

    public Leg() {
    }

    public Leg(Integer distance, Integer nominalDuration, Double averageSpeed, Integer speedProfilePenalty, List<Point> points) {
        this.distance = distance;
        this.nominalDuration = nominalDuration;
        this.averageSpeed = averageSpeed;
        this.speedProfilePenalty = speedProfilePenalty;
        this.points = points;
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

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
