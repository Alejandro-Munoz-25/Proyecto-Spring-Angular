package com.springboot.backend.apirest.models.service;

import com.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}
