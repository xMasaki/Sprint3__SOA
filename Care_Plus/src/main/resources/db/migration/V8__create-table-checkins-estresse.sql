CREATE TABLE checkins_estresse (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    data_registro DATETIME NOT NULL,
    nivel_estresse VARCHAR(20) NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);
