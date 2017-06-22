package com.example.owned.ownedlock;

public class SettingsResource {

    private boolean Switch_12h;
    private boolean Switch_24h;
    private boolean Switch_Reverse;
    private boolean Switch_Hours;
    private boolean Switch_Minutes;
    private boolean Switch_Seconds;

    public SettingsResource(boolean Time_12h, boolean Time_24h, boolean Reverse, boolean Hours, boolean Minutes, boolean Seconds) {
        this.Switch_12h = Time_12h;
        this.Switch_24h = Time_24h;
        this.Switch_Reverse = Reverse;
        this.Switch_Hours = Hours;
        this.Switch_Minutes = Minutes;
        this.Switch_Seconds = Seconds;
    }
/////////////////////////////////////////////////////////////////////////////
    protected boolean isSwitch_12h() {
        return Switch_12h;
    }

    protected void setSwitch_12h(boolean Time_12h) {
        this.Switch_12h = Time_12h;
    }
/////////////////////////////////////////////////////////////////////////////
    protected boolean isSwitch_24h() {
        return Switch_24h;
    }
/////////////////////////////////////////////////////////////////////////////
    protected void setSwitch_24h(boolean Time_24h) {
        this.Switch_24h = Time_24h;
    }
/////////////////////////////////////////////////////////////////////////////
    protected boolean isSwitch_Reverse() {
        return Switch_Reverse;
    }
    protected void setSwitch_Reverse(boolean Reverse) {
        this.Switch_Reverse = Reverse;
    }
/////////////////////////////////////////////////////////////////////////////
    protected boolean isSwitch_Hours() {
        return Switch_Hours;
    }
    protected void setSwitch_Hours(boolean Hours) {
        this.Switch_Hours = Hours;
    }
/////////////////////////////////////////////////////////////////////////////
    protected boolean isSwitch_Minutes() {
        return Switch_Minutes;
    }
    protected void setSwitch_Minutes(boolean Minutes) {
        this.Switch_Minutes = Minutes;
    }
/////////////////////////////////////////////////////////////////////////////
    protected boolean isSwitch_Seconds() {
        return Switch_Seconds;
    }
/////////////////////////////////////////////////////////////////////////////
    protected void setSwitch_Seconds(boolean Seconds) {
        this.Switch_Seconds = Seconds;
    }
/////////////////////////////////////////////////////////////////////////////
}