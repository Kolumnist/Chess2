package de.hhn.it.devtools.apis.ttrpgsheets;

public class DescriptionDescriptor {
    private DescriptionType descriptionType;
    private String text;

    public DescriptionDescriptor(DescriptionType descriptionType, String text) {
        this.descriptionType = descriptionType;
        this.text = text;
    }

    public DescriptionType getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(DescriptionType descriptionType) {
        this.descriptionType = descriptionType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
