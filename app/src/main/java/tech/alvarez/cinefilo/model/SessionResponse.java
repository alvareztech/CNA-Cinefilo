package tech.alvarez.cinefilo.model;

import com.google.gson.annotations.SerializedName;

public class SessionResponse {

    private boolean success;
    @SerializedName("session_id")
    private String sessionId;

    public boolean isSuccess() {
        return success;
    }

    public String getSessionId() {
        return sessionId;
    }
}
