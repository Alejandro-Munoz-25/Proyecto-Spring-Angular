package com.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.backend.apirest.models.dao.IClienteDao;
import com.springboot.backend.apirest.models.dao.IFacturasDao;
import com.springboot.backend.apirest.models.dao.IProductoDao;
import com.springboot.backend.apirest.models.entity.Cliente;
import com.springboot.backend.apirest.models.entity.Factura;
import com.springboot.backend.apirest.models.entity.Producto;
import com.springboot.backend.apirest.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClientesService {

	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private IFacturasDao facturasDao;
	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {

		return clienteDao.findAll(pageable);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturasDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public Factura saveFactura(Factura factura) {
		return facturasDao.save(factura);
	}

	@Override
	@Transactional()
	public void deleteFacturaById(Long id) {
		facturasDao.deleteById(id);
	}

	@Override
	public List<Producto> findProductoByNombre(String nombre) {
		return productoDao.findByNombreContainingIgnoreCase(nombre);
	}

}
