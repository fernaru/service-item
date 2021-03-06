package com.co.kerbero.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.co.kerbero.item.models.Producto;




@FeignClient(name="service-hefestos")
public interface ProductoClientRest {

	@GetMapping("/listar")
	public List<Producto>listar();
	
	@GetMapping("/detalle/{id}")
	public Producto detalle(@PathVariable("id") Long id);
	
	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	public Producto editar(@RequestBody Producto producto, @PathVariable("id") Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id);
}
