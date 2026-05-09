CREATE TABLE checkins_social (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    data_registro DATETIME NOT NULL,
    interacao_positiva TINYINT(1) NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);