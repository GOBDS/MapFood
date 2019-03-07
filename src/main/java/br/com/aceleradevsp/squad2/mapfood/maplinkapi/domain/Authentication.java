package br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authentication {

    @JsonProperty("refresh_token_expires_in")
    private String refreshTokenExpiresIn;
    @JsonProperty("api_product_list")
    private String apiProductList;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("issued_at")
    private String issuedAt;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("application_name")
    private String applicationName;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("refresh_count")
    private String refreshCount;
    @JsonProperty("status")
    private String status;

    public Authentication() {
    }

    public Authentication(String apiProductList, String tokenType, String accessToken, String applicationName, String expiresIn, String status) {
        this.apiProductList = apiProductList;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.applicationName = applicationName;
        this.expiresIn = expiresIn;
        this.status = status;
    }

    public String getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(String refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public String getApiProductList() {
        return apiProductList;
    }

    public void setApiProductList(String apiProductList) {
        this.apiProductList = apiProductList;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(String refreshCount) {
        this.refreshCount = refreshCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
