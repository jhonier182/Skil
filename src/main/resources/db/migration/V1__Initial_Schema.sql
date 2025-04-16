-- Tabla de estados
CREATE TABLE IF NOT EXISTS estado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);

-- Tabla de fichas
CREATE TABLE IF NOT EXISTS ficha (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_ficha BIGINT NOT NULL UNIQUE,
    nombre_ficha VARCHAR(100) NOT NULL,
    programa VARCHAR(100) NOT NULL
);

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    correo VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    doc_identidad VARCHAR(20),
    rol VARCHAR(20) NOT NULL,
    ficha BIGINT,
    estado BIGINT,
    profile_picture VARCHAR(255),
    FOREIGN KEY (ficha) REFERENCES ficha(id),
    FOREIGN KEY (estado) REFERENCES estado(id)
);

-- Insertar estados iniciales
INSERT INTO estado (descripcion) VALUES ('Activo');
INSERT INTO estado (descripcion) VALUES ('Inactivo');

-- Insertar admin por defecto (password encriptado por Spring Security)
INSERT INTO user (nombre, correo, contraseña, rol) 
VALUES ('Admin', 'admin@skillmaster.com', '$2a$10$1TxPKdTL1CqvU0r8RvYwZ.fLyNJNZaC/E1pMogC7avHnlw9pXs1HS', 'ADMIN'); 