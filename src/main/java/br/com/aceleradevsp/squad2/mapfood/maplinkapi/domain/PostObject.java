package br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "routes")
public class PostObject {

    public static final String SHORT_WAY = "THE_SHORTEST";
    public static final String FASTEST_WAY = "THE_FASTEST";

    private String id;
    private List<PointMap> pointMaps;
    private String profileName = "BRAZIL";
    private String calculationMode = FASTEST_WAY;
    private Integer startDate = 0;
    private Boolean useRealSpeeds = true;

    public PostObject() {
    }

    public PostObject(List<PointMap> pointMaps) {
        this.pointMaps = pointMaps;
    }

    public PostObject(List<PointMap> pointMaps, String profileName, String calculationMode, Integer startDate, Boolean useRealSpeeds) {
        this.pointMaps = pointMaps;
        this.profileName = profileName;
        this.calculationMode = calculationMode;
        this.startDate = startDate;
        this.useRealSpeeds = useRealSpeeds;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PointMap> getPointMaps() {
        return pointMaps;
    }

    public void setPointMaps(List<PointMap> pointMaps) {
        this.pointMaps = pointMaps;
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
