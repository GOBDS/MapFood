package br.com.aceleradevsp.squad2.mapfood.maplinkapi.exceptions;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }
}
