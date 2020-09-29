package com.wuhanyunzhong.itass.util;

import lombok.Data;

import java.util.Objects;

@Data
public class DepartDate {
    String firstDepart;
    String second;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartDate that = (DepartDate) o;
        return Objects.equals(firstDepart, that.firstDepart) &&
                Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstDepart, second);
    }

    public String getFirstDepart() {
        return firstDepart;
    }

    public void setFirstDepart(String firstDepart) {
        this.firstDepart = firstDepart;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
