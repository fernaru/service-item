package com.co.kerbero.item.models.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.co.kerbero.item.clientes.ProductoClientRest;
import com.co.kerbero.item.models.Item;
import com.co.kerbero.item.models.Producto;
import com.co.kerbero.item.models.service.ItemService;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductoClientRest clientFeign;

	@Override
	public List<Item> findAll() {
		return clientFeign.listar().stream().map(p-> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clientFeign.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return clientFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return clientFeign.editar(producto, id);
	}

	@Override
	public void delete(Long id) {
		clientFeign.eliminar(id);
	}

}
