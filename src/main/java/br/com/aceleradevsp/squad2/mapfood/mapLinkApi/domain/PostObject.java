package br.com.aceleradevsp.squad2.mapfood.mapLinkApi.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "routes")
public class PostObject {

    private List<Point> points;
    private String profileName;
    private String calculationMode;
    private Integer startDate;
    private Boolean useRealSpeeds;

    public PostObject() {
    }

    public PostObject(List<Point> points, String profileName, String calculationMode, Integer startDate, Boolean useRealSpeeds) {
        this.points = points;
        this.profileName = profileName;
        this.calculationMode = calculationMode;
        this.startDate = startDate;
        this.useRealSpeeds = useRealSpeeds;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getCalculationMode() {
        return calculationMode;
    }

    public void setCalculationMode(String calculationMode) {
        this.calculationMode = calculationMode;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Boolean getUseRealSpeeds() {
        return useRealSpeeds;
    }

    public void setUseRealSpeeds(Boolean useRealSpeeds) {
        this.useRealSpeeds = useRealSpeeds;
    }
}
