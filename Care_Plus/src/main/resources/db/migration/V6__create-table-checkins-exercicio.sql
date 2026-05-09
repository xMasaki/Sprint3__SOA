CREATE TABLE checkins_exercicio (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    data_registro DATETIME NOT NULL,
    sessoes INT NOT NULL,
    intensidade VARCHAR(20) NOT NULL,
    duracao_total DECIMAL(4,2) NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);
