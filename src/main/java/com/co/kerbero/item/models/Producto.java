package com.co.kerbero.item.models;

import lombok.Data;

@Data
public class Producto {

	private Long id;
	private String nombre;
	private Double precio;
	private String create_at;
}
