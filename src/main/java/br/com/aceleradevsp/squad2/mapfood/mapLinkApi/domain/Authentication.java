package br.com.aceleradevsp.squad2.mapfood.mapLinkApi.domain;

public class Authentication {

    private String refreshTokenExpiresIn;
    private String apiProductList;
    private String tokenType;
    private String clientId;
    private String accessToken;
    private String applicationName;
    private String expiresIn;
    private String refreshCount;
    private String status;

    public Authentication() {
    }

    public Authentication(String refreshTokenExpiresIn, String apiProductList, String tokenType, String clientId, String accessToken, String applicationName, String expiresIn, String refreshCount, String status) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        this.apiProductList = apiProductList;
        this.tokenType = tokenType;
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.applicationName = applicationName;
        this.expiresIn = expiresIn;
        this.refreshCount = refreshCount;
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
