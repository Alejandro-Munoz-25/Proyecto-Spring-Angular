package com.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.backend.apirest.models.entity.Factura;

public interface IFacturasDao extends CrudRepository<Factura, Long> {

}
