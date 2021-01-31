package com.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.apirest.models.entity.Factura;
import com.springboot.backend.apirest.models.entity.Producto;
import com.springboot.backend.apirest.models.service.IClientesService;

@CrossOrigin(origins = {"http://192.168.1.75:4200","*"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {

	@Autowired
	private IClientesService clienteService;

	@GetMapping(value = "/facturas/{id}")
	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@ResponseStatus(code = HttpStatus.OK)
	public Factura showFactura(@PathVariable Long id) {
		return clienteService.findFacturaById(id);
	}

	@DeleteMapping(value = "/facturas/{id}")
	@Secured(value = { "ROLE_ADMIN" })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteFactura(@PathVariable Long id) {
		clienteService.deleteFacturaById(id);
	}

	@GetMapping(value = "/facturas/filtar-productos/{palabra}")
	@Secured(value = { "ROLE_ADMIN" })
	@ResponseStatus(code = HttpStatus.OK)
	public List<Producto> filtrarProducto(@PathVariable String palabra) {
		return clienteService.findProductoByNombre(palabra);
	}

	@PostMapping(value = "/facturas")
	@Secured(value = { "ROLE_ADMIN" })
	@ResponseStatus(code = HttpStatus.CREATED)
	public Factura crearFactura(@RequestBody Factura factura) {
		return clienteService.saveFactura(factura);
	}

}
