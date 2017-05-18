package com.example.owned.ownedlock;

public class SettingsResource {

    private boolean Switch_12h;
    private boolean Switch_24h;
    private boolean Switch_Reverse;
    public SettingsResource(boolean Switch_12h, boolean setSwitch_24h, boolean Switch_Reverse) {
        this.Switch_12h = Switch_12h;
        this.Switch_24h = setSwitch_24h;
        this.Switch_Reverse = Switch_Reverse;
    }
    public boolean isSwitch_12h () {
        return Switch_12h;
    }
    public void setSwitch_12h (boolean Switch_12h) {
        this.Switch_12h = Switch_12h;
    }

    public boolean isSwitch_24h () {
        return Switch_24h;
    }
    public void setSwitch_24h (boolean setSwitch_24h) {
        this.Switch_24h = setSwitch_24h;
    }

    public boolean isReverse () {
        return Switch_Reverse;
    }
    public void setReverse (boolean Switch_Reverse) {
        this.Switch_Reverse = Switch_Reverse;
    }

}