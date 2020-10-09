package com.wuhanyunzhong.itass.util;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Objects;

public class JtlDate {
    @ExcelProperty(index = 0)
    String fname ;
    @ExcelProperty(index = 1)
    String sname;
    @ExcelProperty(index = 2)
    int phoneTime;
    @ExcelProperty(index = 3)
    int phoneAmount;
    @ExcelProperty(index = 4)
    int phoneDone;
    @ExcelProperty(index = 5)
    int amount30;
    @ExcelProperty(index = 6)
    int amount60;
    @ExcelProperty(index = 7)
    int time10 ;
    @ExcelProperty(index = 18)
    int time11;
    @ExcelProperty(index = 19)
    int time12;
    @ExcelProperty(index = 20)
    int time15;
    @ExcelProperty(index = 21)
    int time16;
    @ExcelProperty(index = 22)
    int time17;
    @ExcelProperty(index = 23)
    int time18;
    @ExcelProperty(index = 24)
    int time20;

    int upType;

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
                ", upType=" + upType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JtlDate jtlDate = (JtlDate) o;
        return phoneTime == jtlDate.phoneTime &&
                phoneAmount == jtlDate.phoneAmount &&
                phoneDone == jtlDate.phoneDone &&
                amount30 == jtlDate.amount30 &&
                amount60 == jtlDate.amount60 &&
                time10 == jtlDate.time10 &&
                time11 == jtlDate.time11 &&
                time12 == jtlDate.time12 &&
                time15 == jtlDate.time15 &&
                time16 == jtlDate.time16 &&
                time17 == jtlDate.time17 &&
                time18 == jtlDate.time18 &&
                time20 == jtlDate.time20 &&
                upType == jtlDate.upType &&
                Objects.equals(fname, jtlDate.fname) &&
                Objects.equals(sname, jtlDate.sname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, sname, phoneTime, phoneAmount, phoneDone, amount30, amount60, time10, time11, time12, time15, time16, time17, time18, time20, upType);
    }

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

    public int getPhoneTime() {
        return phoneTime;
    }

    public void setPhoneTime(int phoneTime) {
        this.phoneTime = phoneTime;
    }

    public int getPhoneAmount() {
        return phoneAmount;
    }

    public void setPhoneAmount(int phoneAmount) {
        this.phoneAmount = phoneAmount;
    }

    public int getPhoneDone() {
        return phoneDone;
    }

    public void setPhoneDone(int phoneDone) {
        this.phoneDone = phoneDone;
    }

    public int getAmount30() {
        return amount30;
    }

    public void setAmount30(int amount30) {
        this.amount30 = amount30;
    }

    public int getAmount60() {
        return amount60;
    }

    public void setAmount60(int amount60) {
        this.amount60 = amount60;
    }

    public int getTime10() {
        return time10;
    }

    public void setTime10(int time10) {
        this.time10 = time10;
    }

    public int getTime11() {
        return time11;
    }

    public void setTime11(int time11) {
        this.time11 = time11;
    }

    public int getTime12() {
        return time12;
    }

    public void setTime12(int time12) {
        this.time12 = time12;
    }

    public int getTime15() {
        return time15;
    }

    public void setTime15(int time15) {
        this.time15 = time15;
    }

    public int getTime16() {
        return time16;
    }

    public void setTime16(int time16) {
        this.time16 = time16;
    }

    public int getTime17() {
        return time17;
    }

    public void setTime17(int time17) {
        this.time17 = time17;
    }

    public int getTime18() {
        return time18;
    }

    public void setTime18(int time18) {
        this.time18 = time18;
    }

    public int getTime20() {
        return time20;
    }

    public void setTime20(int time20) {
        this.time20 = time20;
    }

    public int getUpType() {
        return upType;
    }

    public void setUpType(int upType) {
        this.upType = upType;
    }
}
