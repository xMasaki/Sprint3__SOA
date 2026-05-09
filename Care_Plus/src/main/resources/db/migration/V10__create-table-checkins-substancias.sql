CREATE TABLE checkins_substancias (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    data_registro DATETIME NOT NULL,
    doses DECIMAL(5,2) NOT NULL,
    cigarros INT NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);