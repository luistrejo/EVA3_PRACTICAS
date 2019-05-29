package com.luist.eva3_12_clima_json_hilos;

public class Clima {

    private int imgClima;
    private String ciudad;
    private String clima;
    private String desc_clima;
    private double temp;

    public Clima() {
    }

    public Clima(int imgClima, String ciudad, String clima, String desc_clima, double temp) {
        this.imgClima = imgClima;
        this.ciudad = ciudad;
        this.clima = clima;
        this.desc_clima = desc_clima;
        this.temp = temp;
    }

    public int getImgClima() {
        return imgClima;
    }

    public void setImgClima(int imgClima) {
        this.imgClima = imgClima;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getDesc_clima() {
        return desc_clima;
    }

    public void setDesc_clima(String desc_clima) {
        this.desc_clima = desc_clima;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
