package com.rj.bd.appeal.entity;

public class Appeal {

    private Long aid;
    private Long uid;
    private String ainfo;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAinfo() {
        return ainfo;
    }

    public void setAinfo(String ainfo) {
        this.ainfo = ainfo;
    }

    @Override
    public String toString() {
        return "Appeal{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", ainfo='" + ainfo + '\'' +
                '}';
    }
}
