/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.firebaseesp32;

/**
 *
 * @author ASUS
 */
public class studentModel {
    String mssv;
    String name;
    String time;
    byte status;
    
    public studentModel(String mssv, String name, String time, byte status) {
        super();
        this.mssv = mssv;
        this.name = name;
        this.time = time;
        this.status = status;
    }
    
    public String getMssv(){
        return mssv;
    }
    
    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTime(){
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public byte getStatus(){
        return status;
    }
    
    public void setStatus(byte status) {
        this.status = status;
    }
    
    
}
