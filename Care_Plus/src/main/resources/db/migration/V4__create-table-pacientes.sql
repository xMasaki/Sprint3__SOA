CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ativo TINYINT DEFAULT 1,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(20),
    medico_id BIGINT,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (medico_id) REFERENCES medicos(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
