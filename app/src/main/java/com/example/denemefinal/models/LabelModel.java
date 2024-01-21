package com.example.denemefinal.models;

public class LabelModel {
    private String description;
    private String labelName;

    public LabelModel() {
    }

    public LabelModel(String description, String labelName) {
        this.description = description;
        this.labelName = labelName;
    }

    public String getDescription() {
        return description;
    }

    public String getLabelName() {
        return labelName;
    }
}
