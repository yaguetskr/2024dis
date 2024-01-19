package com.examen1.backend;

public class Row {
    String _id;
    String mscode;
    String year;
    String estCode;
    Float estimate;
    Float se;
    Float lowerCIB;
    Float upperCIB;
    String flag;

    public Row(){

    }
    public Row(String _id, String mscode, String year, String estCode, Float estimate, Float se, Float lowerCIB, Float upperCIB, String flag) {
        this._id = _id;
        this.mscode = mscode;
        this.year = year;
        this.estCode = estCode;
        this.estimate = estimate;
        this.se = se;
        this.lowerCIB = lowerCIB;
        this.upperCIB = upperCIB;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Row{" +
                "_id='" + _id + '\'' +
                ", mscode='" + mscode + '\'' +
                ", year='" + year + '\'' +
                ", estCode='" + estCode + '\'' +
                ", estimate=" + estimate +
                ", se=" + se +
                ", lowerCIB=" + lowerCIB +
                ", upperCIB=" + upperCIB +
                ", flag='" + flag + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMscode() {
        return mscode;
    }

    public void setMscode(String mscode) {
        this.mscode = mscode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEstCode() {
        return estCode;
    }

    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }

    public Float getEstimate() {
        return estimate;
    }

    public void setEstimate(Float estimate) {
        this.estimate = estimate;
    }

    public Float getSe() {
        return se;
    }

    public void setSe(Float se) {
        this.se = se;
    }

    public Float getLowerCIB() {
        return lowerCIB;
    }

    public void setLowerCIB(Float lowerCIB) {
        this.lowerCIB = lowerCIB;
    }

    public Float getUpperCIB() {
        return upperCIB;
    }

    public void setUpperCIB(Float upperCIB) {
        this.upperCIB = upperCIB;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
