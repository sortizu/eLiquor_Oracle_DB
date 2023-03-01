create database spvl;
use spvl; 
create table sistema(
	idSistema int AUTO_INCREMENT PRIMARY KEY NOT NULL,
	razonSocial varchar(45) NOT NULL,
    numeroTerminal int NOT NULL,
    RUC varchar(15) NOT NULL,
    telefono int NOT NULL,
    codigoTienda varchar(45) NOT NULL,
    ciudad varchar(45) NOT NULL,
    provincia varchar(45) NOT NULL,
    distrito varchar(45) NOT NULL,
    direccion varchar(45) NOT NULL,
    codigoPostal int NOT NULL
);

create table usuarios( 
	idUsuario int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre varchar(45) NOT NULL,
    PIN int NOT NULL,
    gestionarVentas bool NOT NULL,
    gestionarUsuarios bool NOT NULL,
    gestionarProveedores bool NOT NULL,
    gestionarClientes bool NOT NULL,
    gestionarInventario bool NOT NULL,
    generarReportes bool NOT NULL,
	estado tinyint NOT NULL,
    ultimoIngreso datetime,
    fechaRegistro datetime NOT NULL,
    estadoEliminacion tinyint NOT NULL
);

create table proveedor( 
	idProveedor int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    razonSocial varchar(45) NOT NULL,
    telefono int NOT NULL,
    correo varchar(45) NOT NULL,
    fechaRegistro datetime NOT NULL,
    estadoEliminacion tinyint NOT NULL
);

create table cliente(
	idCliente int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre varchar(45) NOT NULL,
    telefono int NOT NULL,
    correo varchar(45) NOT NULL,
    fechaRegistro datetime NOT NULL,
    estadoEliminacion tinyint NOT NULL
);

create table departamento(
	idDepartamento int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    fechaRegistro datetime NOT NULL,
    nombre varchar(45) NOT NULL,
    cantidad int NOT NULL
);

create table producto(
	idProducto int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre varchar(45) NOT NULL,
    precio double NOT NULL,
    costo double NOT NULL,
    stock int NOT NULL,
    precioVariable tinytext NOT NULL,
    activarDescuentos tinytext NOT NULL,
    mostrarEnCaja tinyint NOT NULL,
    fechaRegistro datetime NOT NULL,
    IGV tinyint NOT NULL,
    ISC tinyint NOT NULL,
    estadoEliminacion tinyint NOT NULL
);

create table ProveedorProducto(
	idProveedorProducto int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    costo double NOT NULL,
    cantidad int NOT NULL,
    fechaEntrega datetime NOT NULL,
    idProducto int,
    idProveedor int,
    constraint fk_producto foreign key (idProducto) references producto (idProducto) on update cascade on delete cascade,
    constraint fk_proveedor foreign key (idProveedor) references proveedor (idProveedor) on update cascade on delete cascade
);

create table DepartamentoProducto(
	idDepartamentoProducto int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    idDepartamento int NOT NULL,
    idProducto int NOT NULL,
    constraint fk_productoDepartamento foreign key (idProducto) references producto (idProducto) on update cascade on delete cascade,
    constraint fk_departamento foreign key (idDepartamento) references departamento (idDepartamento) on update cascade on delete cascade
);

create table venta(
	idVenta int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    fechaRegistro datetime NOT NULL,
    ventaBruta double NOT NULL,
    totalImpuestos double NOT NULL,
    totalDescuentos double NOT NULL,
    totalCosto double NOT NULL,
    pagoCliente double NOT NULL,
    cambio double NOT NULL,
    idCliente int ,
    idUsuario int NOT NULL,
    constraint fk_cliente foreign key (idCliente) references cliente (idCliente) on update cascade on delete cascade,
    constraint fk_usuario foreign key (idUsuario) references usuarios (idUsuario) on update cascade on delete cascade
);

create table ventaProducto(
	idVentaProducto int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    idVenta int NOT NULL,
    idProducto int NOT NULL,
    cantidadProducto int NOT NULL,
    constraint fk_venta foreign key (idVenta) references venta (idVenta) on update cascade on delete cascade,
    constraint fk_productoVP foreign key (idProducto) references producto (idProducto) on update cascade on delete cascade
);


show tables;
alter table departamento change mostrarEnCaja mostrarEnCaja bool NOT NULL;
select * from usuarios;
select * from departamentoproducto;
select * from producto;
select nombre, precio, costo, stock from producto where idProducto=2;
select * from departamento;

drop table departamentoProducto;

select idProducto from producto;
select idProducto from departamentoproducto where idDepartamento=2;
delete from departamento where idDepartamento=2;


insert into usuarios(nombre,PIN,gestionarVentas,gestionarUsuarios,gestionarProveedores,gestionarClientes,gestionarInventario,generarReportes) values
("alonso",2222,1,1,1,1,1,1);
select * from ProveedorProducto;
select * from departamentoProducto;
select * from departamento;
select * from producto;
select * from proveedor;
select * from cliente;
select * from usuarios;
select * from ventaproducto;
select * from venta;
select * from sistema;

delete from producto where idProducto = 3;
drop table departamentoProducto;
drop table producto;
drop table venta;
drop table departamento;
select * from usuarios where estadoEliminacion=0;
