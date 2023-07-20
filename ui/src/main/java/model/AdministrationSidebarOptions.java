package model;
//please, rename package as models
public enum AdministrationSidebarOptions {
    USERS("Users");

    String text;

    AdministrationSidebarOptions(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
