package com.example.glsvn.crudandroid;

/*
  Created by utkuglsvn
  utku.glsvn@gmail.com 
*/
public class OgrenciModel {
    private int o_no;
    private String ad,soyad;
    public OgrenciModel(int o_no,String ad,String soyad)
    {
        this.o_no=o_no;
        this.ad=ad;
        this.soyad=soyad;
    }

    public int getNo() {
        return o_no;
    }

    public void setNo(int o_no) {
        this.o_no = o_no;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
}
