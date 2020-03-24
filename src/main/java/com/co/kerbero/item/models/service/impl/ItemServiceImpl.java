package com.co.kerbero.item.models.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.co.kerbero.item.models.Item;
import com.co.kerbero.item.models.Producto;
import com.co.kerbero.item.models.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clientRest;
	
	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clientRest.getForObject("http://service-hefestos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables= new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clientRest.getForObject("http://service-hefestos/detalle/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto); 
		ResponseEntity<Producto> response = clientRest.exchange("http://service-hefestos/crear", HttpMethod.POST, body, Producto.class);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		Map<String, String> pathVariables= new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		ResponseEntity<Producto> response = clientRest.exchange("http://service-hefestos/editar/{id}", HttpMethod.PUT, body, Producto.class, pathVariables);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables= new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		clientRest.delete("http://service-hefestos/eliminar/{id}", pathVariables);
	}

}
