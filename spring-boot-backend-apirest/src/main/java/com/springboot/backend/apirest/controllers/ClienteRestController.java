package com.springboot.backend.apirest.controllers;

import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.backend.apirest.models.entity.Cliente;
import com.springboot.backend.apirest.models.entity.Region;
import com.springboot.backend.apirest.models.service.IClientesService;
import com.springboot.backend.apirest.models.service.IUploadFileService;

@CrossOrigin(origins = { "http://192.168.1.75:4200", "*" }) // Configuración del CORS, permite peticiones del dominio de
															// Angular
@RestController
@RequestMapping(path = "/api")
public class ClienteRestController {

	@Autowired
	private IClientesService clientesService;
	@Autowired
	private IUploadFileService uploadFileService;

	@RequestMapping(value = { "/", "/clientes" }, method = RequestMethod.GET)
	public List<Cliente> customersList() {
		return clientesService.findAll();
	}

	@RequestMapping(value = "/clientes/page/{page}", method = RequestMethod.GET)
	public Page<Cliente> customersPage(@PathVariable Integer page) {
		if (page < 0) {
			page = 0;
		}
		Pageable pageable = PageRequest.of(page, 4, Sort.by(Sort.Direction.ASC, "id"));
		Page<Cliente> pageS = clientesService.findAll(pageable);
		return (pageS.getTotalPages() > pageS.getNumber()) ? pageS : clientesService.findAll(pageable.first());
	}

	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping(value = "/clientes/{id}")
	public ResponseEntity<?> findCliente(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			cliente = clientesService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta em la BD");
			response.put("error", e.getMessage().concat(":	").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString()).concat(" No existe en la BD"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@Secured(value = "ROLE_ADMIN")
	@PostMapping(value = "/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

		Cliente clienteNuevo = null;
		Map<String, Object> response = new HashMap<String, Object>();
		if (result.hasErrors()) {
			Map<String, Object> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), err.getDefaultMessage());
			});

			return ResponseEntity.badRequest().body(errores);
		}

		try {

			clienteNuevo = clientesService.save(cliente);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar INSERT en la BD");
			response.put("error", e.getMessage().concat(":	").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		clienteNuevo.setNombre(clienteNuevo.getNombre().trim().replaceAll(" +", " "));
		clienteNuevo.setApellido(clienteNuevo.getApellido().trim().replaceAll(" +", " "));
		clienteNuevo.setEmail(clienteNuevo.getEmail().trim().replaceAll(" +", " "));
		response.put("cliente", clienteNuevo);
		response.put("mensaje", "Cliente Creado con Éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "clientes/{id}")
	@Secured(value = "ROLE_ADMIN")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente, BindingResult result) {

		Cliente clienteBuscar = clientesService.findById(id);
		Cliente clienteActualizado = null;
		Map<String, Object> response = new HashMap<String, Object>();

		if (result.hasErrors()) {
			Map<String, Object> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errores);
		}

		if (clienteBuscar == null) {

			response.put("mensaje", "Error: no se pudo editar, el cliente con ID: ".concat(id.toString())
					.concat(" No existe en la BD"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		try {

			clienteBuscar.setNombre(cliente.getNombre().trim().replaceAll(" +", " "));
			clienteBuscar.setApellido(cliente.getApellido().trim().replaceAll(" +", " "));
			clienteBuscar.setEmail(cliente.getEmail().trim().replaceAll(" +", " "));
			clienteBuscar.setCreateAt(cliente.getCreateAt());
			clienteBuscar.setRegion(cliente.getRegion());
			clienteActualizado = clientesService.save(clienteBuscar);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar UPDATE en la BD");
			response.put("error", e.getMessage().concat(":	").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("cliente", clienteActualizado);
		response.put("mensaje", "Cliente Actualizado con Éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured(value = "ROLE_ADMIN")
	@DeleteMapping(value = "clientes/{id}")
	public ResponseEntity<?> deleteMethodName(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Cliente cliente = clientesService.findById(id);
			String nombreFotoAnterior = cliente.getFoto();

			uploadFileService.eliminar(nombreFotoAnterior);

			clientesService.delete(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar DELETE en la BD");
			response.put("error", e.getMessage().concat(":	").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "Cliente Eliminado con Éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/clientes/upload")
	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	public ResponseEntity<?> uploadImage(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> tipos = Arrays.asList("png", "jpeg", "gif");
		Cliente cliente = clientesService.findById(id);
		if (!archivo.isEmpty()) {
			String[] format = archivo.getContentType().split("\\/");
			if (tipos.contains(format[1])) {
				String nombreArchivo = null;
				try {

					nombreArchivo = uploadFileService.guardar(archivo);
				} catch (IOException e) {
					response.put("mensaje", "Error al subir la imagen ".concat(nombreArchivo));
					response.put("error", e.getMessage().concat(":	").concat(e.getCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				String nombreFotoAnterior = cliente.getFoto();

				uploadFileService.eliminar(nombreFotoAnterior);

				cliente.setFoto(nombreArchivo);

				clientesService.save(cliente);

				response.put("cliente", cliente);
				response.put("Mensaje", "Se ha subido correctamente la imagen ".concat(nombreArchivo));
			} else {
				response.put("mensaje", "Error al subir la imagen ");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@GetMapping("/clientes/regiones")
	@Secured(value = "ROLE_ADMIN")
	public List<Region> listarRegiones() {
		return clientesService.findAllRegiones();
	}

}
