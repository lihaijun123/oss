package com.focustech.oss2008.model;

public class Telephone {
    // fields
    private String nationalNo;
    private String countryNo;
    private String telNo;
    private String ext;

    // Con
    public Telephone() {
    }

    public Telephone(String nationalNo, String countryNo, String telNo, String ext) {
        if ("".equals(nationalNo) && "".equals(countryNo) && "".equals(telNo) && "".equals(ext)) {
            this.nationalNo = "";
            this.countryNo = "";
            this.telNo = "";
            this.ext = "";
        }
        else {
            this.nationalNo = cutZero(nationalNo).equals("") ? "86" : cutZero(nationalNo);
            this.countryNo = cutZero(countryNo).equals("") ? "000" : cutZero(countryNo);
            this.telNo = telNo;
            this.ext = ext;
        }
    }

    //
    public Telephone(String tel, String format) {
        if (null != tel) {
            String[] tels = tel.split(format);
            if (tels.length < 3) {
                this.nationalNo = "";
                this.countryNo = "";
                this.telNo = "";
                this.ext = "";
            }
            else {
                this.nationalNo = cutZero(tels[0]).equals("") ? "86" : cutZero(tels[0]);
                this.countryNo = cutZero(tels[1]).equals("") ? "000" : cutZero(tels[1]);
                this.telNo = tels[2];
                this.ext = tels.length > 3 ? tels[3] : "";
            }
        }
        else {
            this.nationalNo = "";
            this.countryNo = "";
            this.telNo = "";
            this.ext = "";
        }
    }

    // getter && setter
    public String getNationalNo() {
        return nationalNo;
    }

    public void setNationalNo(String nationalNo) {
        this.nationalNo = cutZero(nationalNo).equals("") ? "86" : cutZero(nationalNo);
    }

    public String getCountryNo() {
        return countryNo;
    }

    public void setCountryNo(String countryNo) {
        this.countryNo = cutZero(countryNo).equals("") ? "000" : cutZero(countryNo);
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    // 截斷首位'0'
    private String cutZero(String str) {
        if (null != str) {
            return str.startsWith("0") ? cutZero(str.substring(1)) : str;
        }
        else {
            return "";
        }
    }

    // 改寫toString方法
    @Override
    public String toString() {
        return "".equals(this.telNo.trim()) ? "" : this.nationalNo + "-" + this.countryNo + "-" + this.telNo
                + ("".equals(this.ext) ? "" : "-" + this.ext);
    }
}
