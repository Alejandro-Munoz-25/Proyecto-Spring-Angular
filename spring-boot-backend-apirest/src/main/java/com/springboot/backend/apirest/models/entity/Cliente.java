package com.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "{NotBlank.cliente.nombre}")
	@Size(min = 4, max = 15, message = "{NotBlank.cliente.nombre.size}")
	private String nombre;
	@NotBlank(message = "{NotBlank.cliente.apellido}")
	private String apellido;

	@Column(nullable = false, unique = true)
	@Email(message = "{Email.cliente.email}")
	@NotBlank(message = "{NotBlank.cliente.email}")
	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "{NotNull.cliente.createAt}")
	private Date createAt;

	private String foto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotNull(message = "{NotNull.cliente.region}")
	private Region region;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "cliente", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	// se ignora cliente para que no se cree un loop al obtener los datos y se
	// permiten los setters para que no ocurra error 400 al actualizar el cliente
	private List<Factura> facturas;

//	@PrePersist
//	public void prePersist() {
//		this.createAt = new Date();
//	}

	public Cliente() {
		this.facturas = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

}
