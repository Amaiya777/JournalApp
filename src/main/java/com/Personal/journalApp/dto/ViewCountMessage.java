package com.Personal.journalApp.dto;

public class ViewCountMessage {

    private String journalId;
    private int views;

    public ViewCountMessage() {}

    public ViewCountMessage(String journalId, int views) {
        this.journalId = journalId;
        this.views = views;
    }

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}