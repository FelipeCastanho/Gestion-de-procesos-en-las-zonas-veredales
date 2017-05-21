/*El nomnbre de la base de datos sera: manejoZonasVeredales y el tipo utf8_bin*/
/*Información sobre las zonas veredales*/
CREATE TABLE zonaVeredal(
	idZonaVeredal	 			INTEGER NOT NULL AUTO_INCREMENT,
	nombreZonaVeredal			VARCHAR(30),
	ubicacionZonaVeredal		VARCHAR(100),
	cantidadMaximaResidentes	INTEGER,
	PRIMARY KEY(idZonaVeredal)
);

/*Información sobre los integrantes de las fuerzas armadas que ingresan a las zonas veredales*/
CREATE TABLE autoridad(
	idAutoridad		INTEGER,
	nombreAutoridad	VARCHAR(30),
	PRIMARY KEY(idAutoridad)
);

/*Información sobre los ingresos de una autoridad a una zona veredal (Se relaciona con autoridad y zona veredal)*/
CREATE TABLE autoridadIngresaZonaVeredal(
	idIngreso		INTEGER,
	idZonaVeredal	INTEGER,
	idAutoridad		INTEGER,
	fechaIngreso	DATETIME,
	fechaSalida		DATETIME,
	motivoIngreso	VARCHAR(100),
	PRIMARY KEY(idIngreso),
	FOREIGN KEY(idZonaVeredal) REFERENCES zonaVeredal(idZonaVeredal),
	FOREIGN KEY(idAutoridad) REFERENCES autoridad(idAutoridad)
);

/*Información sobre un integrante en la zona veredal, en su mayoria informacion para la creacion de una cédula*/
CREATE TABLE integrante(
	cedulaIntegrante			INTEGER,
	nombreIntegrante			VARCHAR(30),
	apellidosIntegrante			VARCHAR(30),
	alturaIntegrante			DOUBLE,
	fechaNacimientoIntegrante	DATE,
	lugarNacimientoIntegrante	VARCHAR(30),
	sexoIntegrante				VARCHAR(10),
	tipoSangreIntegrante		VARCHAR(10),
	idZonaVeredal 				INTEGER,
	PRIMARY KEY(cedulaIntegrante),
	FOREIGN KEY(idZonaVeredal) REFERENCES zonaVeredal(idZonaVeredal)
);

/*Información sobre las capacitaciones que se brindaran en las zonas veredales*/
CREATE TABLE capacitacion(
	idCapacitacion					INTEGER NOT NULL AUTO_INCREMENT,
	nombreCapacitacion				VARCHAR(30),
	numeroIntegrantesCapacitacion	INTEGER,
	nombreEncargadoCapacitacion		VARCHAR(30),
	jornadaCapacitacion				VARCHAR(30),
	PRIMARY KEY(idCapacitacion)
);

/*Información sobre las capacitaciones a las que asisten los integrantes (Se relaciona con integrante y capacitación)*/
CREATE TABLE integranteAsisteCapacitacion(
	idAsiste			INTEGER NOT NULL AUTO_INCREMENT,
	idCapacitacion	 	INTEGER,
	cedulaIntegrante	INTEGER,
	estado				VARCHAR(10),
	PRIMARY KEY(idAsiste),
	FOREIGN KEY(idCapacitacion) REFERENCES capacitacion(idCapacitacion),
	FOREIGN KEY(cedulaIntegrante) REFERENCES integrante(cedulaIntegrante)
);

/*Información sobre la pena que pagará un integrante de terminado (Se relaciona con integrante)*/
CREATE TABLE pena(
	idPena				INTEGER NOT NULL AUTO_INCREMENT,
	cedulaIntegrante	INTEGER,
	modalidadPena		VARCHAR(30),
	anosPena			INTEGER,
	mesesPena			INTEGER,
	PRIMARY KEY(idPena),
	FOREIGN KEY(cedulaIntegrante) REFERENCES integrante(cedulaIntegrante)
);

/*Información sobre datos que podrían entregar un integrante, en concreto, información sobre la ubicación de las minas antipersonales(MAP),
  artefactos explosivos improvisados (AEI) y municiones sin explotar (MUSE) o restos explosivos de guerra (REG) en general. Por esta razon
  se usan campos como cantidad y ubicacion. (Se relaciona con integrante y capacitación)*/
CREATE TABLE informacion(
	idInformacion			INTEGER NOT NULL AUTO_INCREMENT,
	cedulaIntegrante		INTEGER,
	descripcionInformacion	VARCHAR(500),
	ubicacionInformacion	VARCHAR(100),
	cantidadInformacion		INTEGER,
	estadoInformacion		VARCHAR(10),
	PRIMARY KEY(idInformacion),
	FOREIGN KEY(cedulaIntegrante) REFERENCES integrante(cedulaIntegrante)
);

/*Información sobre las salidas que los integrantes podrían hacer de las zonas veredales (Se relaciona con integrante y capacitación)*/
CREATE TABLE permisoSalida(
	idSalida			INTEGER NOT NULL AUTO_INCREMENT,
	cedulaIntegrante	INTEGER,
	motivoSalida		VARCHAR(100),
	fechaSalida			DATETIME,
	fechaRetornoSalida	DATETIME,
	estadoSalida		VARCHAR(10),
	PRIMARY KEY(idSalida),
	FOREIGN KEY(cedulaIntegrante) REFERENCES integrante(cedulaIntegrante)
);

/*Información sobre la entrega de armas por parte de los integrantes (Se relaciona con integrante y capacitación)*/
CREATE TABLE arma(
	idArma				INTEGER NOT NULL AUTO_INCREMENT,
	cedulaIntegrante	INTEGER,
	matriculaArma		VARCHAR(30),
	estadoArma			VARCHAR(10),
	calibreArma			VARCHAR(30),
	cantidadArma		INTEGER,
	frenteArma			VARCHAR(30),
	PRIMARY KEY(idArma),
	FOREIGN KEY(cedulaIntegrante) REFERENCES integrante(cedulaIntegrante)
);

