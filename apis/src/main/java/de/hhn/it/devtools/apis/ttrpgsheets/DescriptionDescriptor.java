package de.hhn.it.devtools.apis.ttrpgsheets;

public class DescriptionDescriptor {
    private String playerName;
    private String characterName;
    private String nickname;
    private String age;
    private String race;
    private String height;
    private String weight;
    private String skinColor;
    private String hairColor;
    private String eyeColor;
    private String characterClass;
    private String other;

    public DescriptionDescriptor(String playerName, String characterName, String nickname, String age, String race, String height, String weight, String skinColor, String hairColor, String eyeColor, String characterClass, String other) {
        this.playerName = playerName;
        this.characterName = characterName;
        this.nickname = nickname;
        this.age = age;
        this.race = race;
        this.height = height;
        this.weight = weight;
        this.skinColor = skinColor;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.characterClass = characterClass;
        this.other = other;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
