package br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain;

final class URLMaplink {

    private URLMaplink() {
        throw new IllegalStateException("Utility class");
    }

    public static final String URL_LOGIN = "https://lbslocal-prod.apigee.net/oauth/client_credential/accesstoken?grant_type=client_credentials";
    public static final String URL_PROBLEM = "https://lbslocal-prod.apigee.net/trip/v1/problems";
    public static final String URL_JOB_BY_ID = "https://lbslocal-prod.apigee.net/trip/v1/jobs/";
    public static final String URL_SOLUTION_BY_ID = "https://lbslocal-prod.apigee.net/trip/v1/solutions/";
}
