package com.example.continentalm;

public class Autos {
    private String descripcion;
    private int id_auto;
    private String marca;
    private String modelo;
    private String precio;
    private String foto;

    public Autos(String descripcion, int id_auto, String marca, String modelo, String precio, String foto) {
        this.descripcion = descripcion;
        this.id_auto = id_auto;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId_auto() {
        return id_auto;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPrecio() {
        return precio;
    }

    public String getFoto() {
        return foto;
    }
}
