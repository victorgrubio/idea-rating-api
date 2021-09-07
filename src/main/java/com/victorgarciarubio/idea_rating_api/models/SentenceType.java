package com.victorgarciarubio.idea_rating_api.models;

public enum SentenceType {
    PRO("PRO"), CON("CON");

    String type;

    SentenceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
