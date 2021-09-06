package com.victorgarciarubio.idea_rating_api.models;

public enum SentenceType {
    pro("pro"), con("con");

    String type;

    SentenceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
