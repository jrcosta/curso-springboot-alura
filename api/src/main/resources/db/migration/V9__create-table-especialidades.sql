CREATE TABLE especialidades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);
ALTER TABLE agendas
DROP COLUMN especialidade_id,
ADD COLUMN especialidade_id INT NOT NULL,
ADD FOREIGN KEY (especialidade_id) REFERENCES especialidades(id);
INSERT INTO especialidades (nome) VALUES ('ORTOPEDIA'), ('CARDIOLOGIA'), ('GINECOLOGIA'), ('DERMATOLOGIA');
