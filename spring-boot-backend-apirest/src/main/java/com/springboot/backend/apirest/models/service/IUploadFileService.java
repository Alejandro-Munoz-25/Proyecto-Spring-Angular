package com.springboot.backend.apirest.models.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public String guardar(MultipartFile archivo) throws IOException;

	public Boolean eliminar(String nombreFoto);
//	Se utiliza en servidor que permita el almacenamiento de archivos
//	public Resource cargar(String nombreFoto) throws MalformedURLException;
//
//	public Path getPath(String nombreFoto);
}
