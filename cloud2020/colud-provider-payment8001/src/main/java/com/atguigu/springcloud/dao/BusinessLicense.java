package com.atguigu.springcloud.dao;

public class BusinessLicense {
    private  String biId;
    private  String picName;
    private  String picCidType;
    private  String picCidNo;
    private  String entName;
    private  String bizAddrStreet;
    private  String biPeriodS;
    private  String biPeriod;
    private  String ecoType;
    private  String  picAddrStreet;
    private  String  isLongbiPeriod;

    public String getPicAddrStreet() {
        return picAddrStreet;
    }

    public void setPicAddrStreet(String picAddrStreet) {
        this.picAddrStreet = picAddrStreet;
    }

    public String getIsLongbiPeriod() {
        return isLongbiPeriod;
    }

    public void setIsLongbiPeriod(String isLongbiPeriod) {
        this.isLongbiPeriod = isLongbiPeriod;
    }

    public String getBiId() {
        return biId;
    }

    public void setBiId(String biId) {
        this.biId = biId;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicCidType() {
        return picCidType;
    }

    public void setPicCidType(String picCidType) {
        this.picCidType = picCidType;
    }

    public String getPicCidNo() {
        return picCidNo;
    }

    public void setPicCidNo(String picCidNo) {
        this.picCidNo = picCidNo;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getBizAddrStreet() {
        return bizAddrStreet;
    }

    public void setBizAddrStreet(String bizAddrStreet) {
        this.bizAddrStreet = bizAddrStreet;
    }

    public String getBiPeriodS() {
        return biPeriodS;
    }

    public void setBiPeriodS(String biPeriodS) {
        this.biPeriodS = biPeriodS;
    }

    public String getBiPeriod() {
        return biPeriod;
    }

    public void setBiPeriod(String biPeriod) {
        this.biPeriod = biPeriod;
    }

    public String getEcoType() {
        return ecoType;
    }

    public void setEcoType(String ecoType) {
        this.ecoType = ecoType;
    }

    @Override
    public String toString() {
        return "BusinessLicense{" +
                "biId='" + biId + '\'' +
                ", picName='" + picName + '\'' +
                ", picCidType='" + picCidType + '\'' +
                ", picCidNo='" + picCidNo + '\'' +
                ", entName='" + entName + '\'' +
                ", bizAddrStreet='" + bizAddrStreet + '\'' +
                ", biPeriodS='" + biPeriodS + '\'' +
                ", biPeriod='" + biPeriod + '\'' +
                ", ecoType='" + ecoType + '\'' +
                ", picAddrStreet='" + picAddrStreet + '\'' +
                ", isLongbiPeriod='" + isLongbiPeriod + '\'' +
                '}';
    }
}
