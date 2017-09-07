package tech.alvarez.cinefilo.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    private boolean success;
    @SerializedName("request_token")
    private String token;

    @SerializedName("status_code")
    private String statusCode;

    @SerializedName("status_message")
    private String statusMessage;

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
