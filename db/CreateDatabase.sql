-- Criação da tabela "Perfil"
CREATE TABLE perfil (
                        id INT PRIMARY KEY,
                        nome VARCHAR(250)
);



-- Criação da tabela "Usuario"
create table usuario (
                         id bigint not null auto_increment,
                         login varchar(100) not null,
                         senha varchar(255) not null,
                         nome varchar(255) not null,
                         email varchar(255) not null,
                         perfil_id int ,
                         primary key(id),
                         foreign key (perfil_id) references perfil(id)
);


INSERT INTO cadastro_usuario.perfil
(id, nome)
VALUES(1, 'ADM');


/* Senha padrão  : 123 */
INSERT INTO cadastro_usuario.usuario
(id, login, senha, nome, email, perfil_id)
VALUES(1, 'master', '$2a$12$CF9rwzE7manCSC2WMUMLUOOf6PSeWaWPOQcpX3ZLvYoINUAynizb2', 'master', 'master@gmail.com', 1);
