package com.wuhanyunzhong.itass.util;

import lombok.Data;

import java.util.Objects;

@Data
public class DepartDate {
    String fname ;
    String sname ;

    @Override
    public String toString() {
        return "DepartDate{" +
                "fname='" + fname + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartDate that = (DepartDate) o;
        return Objects.equals(fname, that.fname) &&
                Objects.equals(sname, that.sname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, sname);
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
}
