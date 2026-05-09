CREATE TABLE checkins_alimentacao (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    data_registro DATETIME NOT NULL,
    refeicoes INT NOT NULL,
    saciedade VARCHAR(20) NOT NULL,
    hidratacao DECIMAL(4,2) NOT NULL,
    nivel_acucar VARCHAR(20) NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);