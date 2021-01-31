package com.springboot.backend.apirest.models.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.springboot.backend.apirest.models.entity.FirebaseCredential;

@Service
public class UploadFileServiceImp implements IUploadFileService {

//	private final static String DirectorioUpload = "uploads";
	private final Environment environment;

	private StorageOptions storageOptions;
	private String bucketName;
	private String projectId;

	public UploadFileServiceImp(Environment environment) {
		this.environment = environment;
	}

	@PostConstruct
	private void initializeFirebase() throws Exception {
		bucketName = environment.getRequiredProperty("FIREBASE_BUCKET_NAME");
		projectId = environment.getRequiredProperty("FIREBASE_PROJECT_ID");

		InputStream firebaseCredential = createFirebaseCredential();
		this.storageOptions = StorageOptions.newBuilder().setProjectId(projectId)
				.setCredentials(GoogleCredentials.fromStream(firebaseCredential)).build();

	}

//	Se utiliza en servidor que permita el almacenamiento de archivos
//	@Override
//	public Resource cargar(String nombreFoto) throws MalformedURLException {
//		Path rutaArchivo = getPath(nombreFoto);
//		Resource recurso = null;
//
//		recurso = new UrlResource(rutaArchivo.toUri());
//
//		if (!recurso.exists() && !recurso.isReadable()) {
//			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("user.png").toAbsolutePath();
//			try {
//				recurso = new UrlResource(rutaArchivo.toUri());
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return recurso;
//	}

	@Override
	public String guardar(MultipartFile archivo) throws IOException {

//		String[] format = archivo.getContentType().split("\\/");
//		String nombreArchivo = UUID.randomUUID().toString().concat(".").concat(format[1]);// archivo.getOriginalFilename();
//		Path rutaArchivo = getPath(nombreArchivo);
//
//		Files.copy(archivo.getInputStream(), rutaArchivo);
//
//		return nombreArchivo;

		File fileP = convertMultiPartToFile(archivo);
		Path filePath = fileP.toPath();
		String[] format = archivo.getContentType().split("\\/");
		String objectName = UUID.randomUUID().toString().concat(".").concat(format[1]);
		BlobId blobId = BlobId.of(bucketName, "images/" + objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(archivo.getContentType()).build();
		Storage storage = storageOptions.getService();
		Blob blob = storage.create(blobInfo, Files.readAllBytes(filePath));
		return objectName;
	}

	@Override
	public Boolean eliminar(String nombreFoto) {

//		if (nombreFoto != null && nombreFoto.length() > 0) {
//			Path rutaFotoAnterior = getPath(nombreFoto);
//			File fotoAnterior = rutaFotoAnterior.toFile();
//			if (fotoAnterior.exists() && fotoAnterior.canRead()) {
//				fotoAnterior.delete();
//				return true;
//			}
//
//		}
//
//		return false;

		BlobId blobId = BlobId.of(bucketName, "images/" + nombreFoto);

		if (blobId != null) {
			Storage storage = storageOptions.getService();
			if (storage.delete(bucketName, "images/" + nombreFoto)) {
				return true;
			}
		}
		return false;
	}

//	Se utiliza en servidor que permita el almacenamiento de archivos
//	@Override
//	public Path getPath(String nombreFoto) {
//		return Paths.get(DirectorioUpload).resolve(nombreFoto).toAbsolutePath();
//	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return convertedFile;
	}

//	Configuración de firestorage
	private InputStream createFirebaseCredential() throws Exception {
		FirebaseCredential firebaseCredential = new FirebaseCredential();
		// private key
		String privateKey = environment.getRequiredProperty("FIREBASE_PRIVATE_KEY").replace("\\n", "\n");
		firebaseCredential.setType(environment.getRequiredProperty("FIREBASE_TYPE"));
		firebaseCredential.setProject_id(projectId);
		firebaseCredential.setPrivate_key_id("FIREBASE_PRIVATE_KEY_ID");
		firebaseCredential.setPrivate_key(privateKey);
		firebaseCredential.setClient_email(environment.getRequiredProperty("FIREBASE_CLIENT_EMAIL"));
		firebaseCredential.setClient_id(environment.getRequiredProperty("FIREBASE_CLIENT_ID"));
		firebaseCredential.setAuth_uri(environment.getRequiredProperty("FIREBASE_AUTH_URI"));
		firebaseCredential.setToken_uri(environment.getRequiredProperty("FIREBASE_TOKEN_URI"));
		firebaseCredential.setAuth_provider_x509_cert_url(
				environment.getRequiredProperty("FIREBASE_AUTH_PROVIDER_X509_CERT_URL"));
		firebaseCredential.setClient_x509_cert_url(environment.getRequiredProperty("FIREBASE_CLIENT_X509_CERT_URL"));

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(firebaseCredential);

		InputStream targetStream = new ByteArrayInputStream(jsonString.getBytes());
		return targetStream;
	}

}
