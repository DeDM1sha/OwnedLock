package com.example.owned.ownedlock;

/**
 * Created by Owned on 16.05.2017.
 */

public class SettingsResource {
    private boolean first;
    private boolean second;
    private boolean third;

    public SettingsResource(boolean first, boolean second, boolean third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isSecond() {
        return second;
    }

    public void setSecond(boolean second) {
        this.second = second;
    }

    public boolean isThird() {
        return third;
    }

    public void setThird(boolean third) {
        this.third = third;
    }
}
