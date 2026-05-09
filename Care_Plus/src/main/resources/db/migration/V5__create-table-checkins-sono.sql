CREATE TABLE checkins_sono (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    data_registro DATETIME NOT NULL,
    horas_dormidas DECIMAL(4,2) NOT NULL,
    qualidade VARCHAR(20) NOT NULL,
    despertares INT NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);