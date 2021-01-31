INSERT INTO regiones (id,nombre) VALUES(1,'Sudamérica');
INSERT INTO regiones (id,nombre) VALUES(2,'Centroamérica');
INSERT INTO regiones (id,nombre) VALUES(3,'Norteamérica');
INSERT INTO regiones (id,nombre) VALUES(4,'Europa');
INSERT INTO regiones (id,nombre) VALUES(5,'Asia');
INSERT INTO regiones (id,nombre) VALUES(6,'Africa');
INSERT INTO regiones (id,nombre) VALUES(7,'Oceanía');
INSERT INTO regiones (id,nombre) VALUES(8,'Antártida');

INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(3,'Alejandro', 'Muñoz', 'aleja@mail.com', '2020-08-28');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(3,'Pedro', 'Lara', 'pedrola@gmail.com', '2020-08-28');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(2,'Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(3,'John', 'Doe', 'john.doe@gmail.com', '2017-08-02');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(4,'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(5,'Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(6,'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(7,'Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(8,'Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(8,'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(7,'John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(6,'James', 'Gosling', 'james.gosling@gmail.com', '2017-08-10');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(5,'Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(4,'Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(3,'John', 'Roe', 'john.roe@gmail.com', '2017-08-13');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(2,'Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(1,'Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(2,'Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(3,'Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(4,'Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(5,'Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19');  
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(6,'Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20'); 
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(7,'Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(8,'John', 'Smith', 'john.smith@gmail.com', '2017-08-22');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(7,'Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(6,'John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24');
INSERT INTO clientes (region_id,nombre, apellido, email, create_at) VALUES(5,'Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25');

INSERT INTO usuarios (username,password,enable,nombre,apellido,email) VALUES ('user','$2a$10$8uqGMuG9CZXwwlLqpmuoZ.lAIrsXWNzpvJ7UzS8Wf5UCr1BW9VLye',true,'Alejandro','Muñoz','alejandro@mail.com');                      
INSERT INTO usuarios (username,password,enable,nombre,apellido,email) VALUES ('admin','$2a$10$PdJ6poYLWNwS7S2MTzNMe.khFdp6XbqhlvxC8BM5IWBlDoeVBDuFi',true,'Administrador','Maestro','admin@mail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (2,1);

INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 2599, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 1234, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 14999, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 3799, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 6999, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 699, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 2999, NOW());

/* Invoice */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);