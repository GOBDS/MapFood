package br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain;

public class Job {

    private String id;
    private String status;
    private String step;
    private String percent;
    private Long createdAt;

    public Job() {
    }

    public Job(String id, String status, String step, String percent, Long createdAt) {
        this.id = id;
        this.status = status;
        this.step = step;
        this.percent = percent;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
