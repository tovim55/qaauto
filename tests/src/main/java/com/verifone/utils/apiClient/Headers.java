package com.verifone.utils.apiClient;

public enum Headers {

    CONTENT_TYPE("Content-Type"),
    AUTHORIZATION("Authorization"),
    CORRELATION_ID("X-VFI-CORRELATION-ID"),
    ACCEPT("Accept"),
    ORIGIN("Origin"),
    REFERER("Referer");

    private String header;

    public String get() {
        return this.header;
    }

    Headers(String action) {
        this.header = action;
    }

}
