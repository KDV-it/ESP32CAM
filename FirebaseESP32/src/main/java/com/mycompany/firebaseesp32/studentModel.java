/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.firebaseesp32;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class studentModel {

    String mssv;
    String name;
    Date time;
    int status;

    public studentModel(String mssv, String name, Date time, int status) {
        super();
        this.mssv = mssv;
        this.name = name;
        this.time = time;
        this.status = status;
    }

    public studentModel() {
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
