package com.wuhanyunzhong.itass.util;

import java.util.Objects;

public class JtlDate {
    String fname ;
    String sname;
    double phoneTime;
    double phoneAmount;
    double phoneDone;
    double amount30;
    double amount60;
    double time10;
    double time11;
    double time12;
    double time15;
    double time16;
    double time17;
    double time18;
    double time20;


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public double getPhoneTime() {
        return phoneTime;
    }

    public void setPhoneTime(double phoneTime) {
        this.phoneTime = phoneTime;
    }

    public double getPhoneAmount() {
        return phoneAmount;
    }

    public void setPhoneAmount(double phoneAmount) {
        this.phoneAmount = phoneAmount;
    }

    public double getPhoneDone() {
        return phoneDone;
    }

    public void setPhoneDone(double phoneDone) {
        this.phoneDone = phoneDone;
    }

    public double getAmount30() {
        return amount30;
    }

    public void setAmount30(double amount30) {
        this.amount30 = amount30;
    }

    public double getAmount60() {
        return amount60;
    }

    public void setAmount60(double amount60) {
        this.amount60 = amount60;
    }

    public double getTime10() {
        return time10;
    }

    public void setTime10(double time10) {
        this.time10 = time10;
    }

    public double getTime11() {
        return time11;
    }

    public void setTime11(double time11) {
        this.time11 = time11;
    }

    public double getTime12() {
        return time12;
    }

    public void setTime12(double time12) {
        this.time12 = time12;
    }

    public double getTime15() {
        return time15;
    }

    public void setTime15(double time15) {
        this.time15 = time15;
    }

    public double getTime16() {
        return time16;
    }

    public void setTime16(double time16) {
        this.time16 = time16;
    }

    public double getTime17() {
        return time17;
    }

    public void setTime17(double time17) {
        this.time17 = time17;
    }

    public double getTime18() {
        return time18;
    }

    public void setTime18(double time18) {
        this.time18 = time18;
    }

    public double getTime20() {
        return time20;
    }

    public void setTime20(double time20) {
        this.time20 = time20;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JtlDate jtlDate = (JtlDate) o;
        return Double.compare(jtlDate.phoneTime, phoneTime) == 0 &&
                Double.compare(jtlDate.phoneAmount, phoneAmount) == 0 &&
                Double.compare(jtlDate.phoneDone, phoneDone) == 0 &&
                Double.compare(jtlDate.amount30, amount30) == 0 &&
                Double.compare(jtlDate.amount60, amount60) == 0 &&
                Double.compare(jtlDate.time10, time10) == 0 &&
                Double.compare(jtlDate.time11, time11) == 0 &&
                Double.compare(jtlDate.time12, time12) == 0 &&
                Double.compare(jtlDate.time15, time15) == 0 &&
                Double.compare(jtlDate.time16, time16) == 0 &&
                Double.compare(jtlDate.time17, time17) == 0 &&
                Double.compare(jtlDate.time18, time18) == 0 &&
                Double.compare(jtlDate.time20, time20) == 0 &&
                fname.equals(jtlDate.fname) &&
                sname.equals(jtlDate.sname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, sname, phoneTime, phoneAmount, phoneDone, amount30, amount60, time10, time11, time12, time15, time16, time17, time18, time20);
    }

    @Override
    public String toString() {
        return "JtlDate{" +
                "fname='" + fname + '\'' +
                ", sname='" + sname + '\'' +
                ", phoneTime=" + phoneTime +
                ", phoneAmount=" + phoneAmount +
                ", phoneDone=" + phoneDone +
                ", amount30=" + amount30 +
                ", amount60=" + amount60 +
                ", time10=" + time10 +
                ", time11=" + time11 +
                ", time12=" + time12 +
                ", time15=" + time15 +
                ", time16=" + time16 +
                ", time17=" + time17 +
                ", time18=" + time18 +
                ", time20=" + time20 +
                '}';
    }
}
