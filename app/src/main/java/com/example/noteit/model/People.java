package com.example.noteit.model;

public class People {

    public String headerTxt;
    public String infoTxt;
    public String date;
    public String sectionName;
    public boolean section = false;
    public boolean isNotes = true;

    public String getHeaderTxt() {
        return headerTxt;
    }

    public void setHeaderTxt(String headerTxt) {
        this.headerTxt = headerTxt;
    }

    public String getInfoTxt() {
        return infoTxt;
    }

    public void setInfoTxt(String infoTxt) {
        this.infoTxt = infoTxt;
    }

    public boolean isSection() {
        return section;
    }

    public void setSection(boolean section) {
        this.section = section;
    }

    public boolean isNotes() {
        return isNotes;
    }

    public void setNotes(boolean notes) {
        isNotes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
