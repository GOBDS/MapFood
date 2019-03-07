package br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain;

import java.util.List;

public class Solution {

    private String id;
    private String clientId;
    private Integer totalDistance;
    private Integer totalNominalDuration;
    private Double averageSpeed;
    private Integer totalSpeedProfilePenalty;
    private List<Leg> legs;
    private Long createdAt;

    public Solution() {
    }

    public Solution(String id, Integer totalDistance, Integer totalNominalDuration, Double averageSpeed, Integer totalSpeedProfilePenalty, List<Leg> legs, Long createdAt) {
        this.id = id;
        this.totalDistance = totalDistance;
        this.totalNominalDuration = totalNominalDuration;
        this.averageSpeed = averageSpeed;
        this.totalSpeedProfilePenalty = totalSpeedProfilePenalty;
        this.legs = legs;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Integer getTotalNominalDuration() {
        return totalNominalDuration;
    }

    public void setTotalNominalDuration(Integer totalNominalDuration) {
        this.totalNominalDuration = totalNominalDuration;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Integer getTotalSpeedProfilePenalty() {
        return totalSpeedProfilePenalty;
    }

    public void setTotalSpeedProfilePenalty(Integer totalSpeedProfilePenalty) {
        this.totalSpeedProfilePenalty = totalSpeedProfilePenalty;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
