package com.italika.etbackend.test_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class Producto {

    @Id
    @GeneratedValue
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer cantidad;
    private Boolean bloqueo;    

}
