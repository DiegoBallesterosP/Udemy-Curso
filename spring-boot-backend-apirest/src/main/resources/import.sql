INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica');
INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica');
INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica');
INSERT INTO regiones (id, nombre) VALUES (4, 'Europa');
INSERT INTO regiones (id, nombre) VALUES (5, 'Asia');
INSERT INTO regiones (id, nombre) VALUES (6, 'Africa');
INSERT INTO regiones (id, nombre) VALUES (7, 'Oceanía');
INSERT INTO regiones (id, nombre) VALUES (8, 'Antártida');


INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (1, 'Manolo', 'Diaz', 'mdiaz@gmail.com', '2021/09/20')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (1, 'Laura', 'Mejia','lmejia@yahoo.co','2021/08/08')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (2, 'Adrian', 'Monsalve', ' amonsal@outlook.es', '2021/06/12')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (2, 'Vanessa', 'Gutierrez', 'vgutie1@outlook.es', '2021/10/26')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (3, 'Cristian', 'Castellanos', 'ccastel@hotmail.com', '2021/07/20')

INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (3, 'Katherine', 'Suarez', 'lmejia@gmail.com', '2020/10/20')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (4, 'Liz', 'Laura','lluna@yahoo.com','2019/06/09')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (4, 'Oscar', 'Quiroga', ' OscarQuiroga@outlook.com', '2021/11/02')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (5, 'Cristina', 'Monsalve', 'cristinaMonsalve1911@outlook.es', '2020/01/12')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (5, 'Johana', 'Palacios', 'johana111@hotmail.com', '2021/04/04')


INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (6, 'Adrian', 'Gimenez', 'AdriGim@Outlook.com', '2019/05/05')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (6, 'Karim', 'Santos','ksantos@yahoo.com','2015/07/12')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (7, 'Maria', 'Monsalve', ' Monsalve@hotmail.com', '2012/12/07')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (7, 'William', 'Zapata', 'Wilimam@outlook.es', '2018/02/27')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (8, 'Erik', 'Montaña', 'Erik@hotmail.com', '2021/07/15')

INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (1, 'Lauren', 'Viña', 'Lauren@yahoo.com', '2019/05/05')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (2, 'Karla', 'Giraldo','Giraldo@worldoffices.com','2015/07/12')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (3, 'Mati', 'Aldemar', ' viña@outlook.com', '2012/12/07')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (4, 'Venito', 'Casas', 'venicasas@outlook.es', '2018/02/27')
INSERT INTO clientes (region_id, nombre, apellidos, email, create_at) VALUES (5, 'Pepe', 'Yusepe', 'pepe@hotmail.com', '2021/07/15')

INSERT INTO usuarios (username, password, enabled, nombre, apellidos, email) VALUES ('diego','$2a$10$FcIMRIma6tQ9GNmNRj8XCu.VcGwB8EIogVRde6nebq7XtN3KAVZRO',1, "Manolo", "Diaz", "mdiaz@gmail.com" );
INSERT INTO usuarios (username, password, enabled, nombre, apellidos, email) VALUES ('admin','$2a$10$wiR1OsXY9yXQ9W5top07AugV7ZQp2jzRu0Mhh/tPkowkSmH9kiOtG',1, "laura", "Mejia", "lmejia@yahoo.co");

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);


INSERT INTO productos (nombre, precio, create_at) VALUES('Motorola One Action', 780000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Motorola g60s', 1400000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Samsung Galaxy a31', 999000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Iphone X', 2500000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Xiaomi Mi A11', 1780000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Samsung s21', 3000000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Xiaomi Redmi Note 8', 600000, NOW());

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Facturas Celulares', null, 1, NOW());

INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 2);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 4);
