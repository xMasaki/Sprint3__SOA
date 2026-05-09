# CarePlus - Sprint 3 - API de Monitoramento de Estilo de Vida (MEV)

API REST para monitoramento de Mudanças no Estilo de Vida(MEV) de pacientes, permitindo o registro e acompanhamento de check-ins diários de alimentação, exercício, sono, estresse, interação social e uso de substâncias. Projetada para uso por médicos e pacientes.

## Integrantes do Grupo
Edson Leonardo Pacheco Navia | RM553737<br>
Eduardo Mazelli | RM553236<br>
Joseh Gabriel Trimboli Agra | RM553094<br>
Lucas Masaki Nagahama | RM553087<br>
Pedro Henrique de Assunção Lima | RM552746<br>

## Descrição do Projeto

O projeto é uma API desenvolvida com arquitetura hexagonal que centraliza o monitoramento de hábitos de saúde de pacientes. O sistema oferece:

- Cadastro e gerenciamento de médicos, pacientes e usuários
- Registro de check-ins em 6 pilares de estilo de vida
- Geração de relatórios e histórico por período
- Autenticação via JWT com controle de acesso baseado em perfis (ADMIN, MEDICO, PACIENTE)
- Validações de negócio automáticas nos check-ins (ex: risco de burnout, privação de sono, sedentarismo)


## Tecnologias Utilizadas

- Java 21
- Spring Boot 4.0.6
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT
- Flyway
- Lombok
- Jakarta Validation
- BCrypt


## Configuração e Execução

### 1. Clonar o repositório

git clone https://github.com/xMasaki/Sprint3__SOA.git<br><br>


### 2. Configurar o banco de dados
Crie o schema no MySQL:

CREATE DATABASE mev;<br><br>


### 3. Configurar variáveis de ambiente
Edite conforme seu ambiente:

spring.datasource.url=jdbc:mysql://localhost/mev <br>
spring.datasource.username=root<br>
spring.datasource.password=SUA_SENHA<br>

api.security.token.secret=${JWT_SECRET:12345678}<br><br>


### 4. Executar a aplicação

A API estará em http://localhost:8080<br><br>

### 5. Verificar funcionamento

GET http://localhost:8080/health-check
Resposta: MEV API - OK<br><br>


### Perfis de acesso
ADMIN - Acesso total ao sistema<br>
MEDICO - Gerencia pacientes e visualiza dados<br>
PACIENTE - Registra seus próprios check-ins<br><br>


## Exemplos de requisições e respostas

### Autenticação

#### POST /login

**Request:**<br>
{
  "login": "admin@careplus.com",
  "senha": "senha123"
}


**Response 200 OK:**<br>
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}<br><br>


### Usuários

#### POST /usuarios

**Request:**<br>
{
  "login": "joao@careplus.com",
  "senha": "senha123",
  "perfil": "MEDICO"
}


**Response 201 Created:**<br>
{
  "id": 1,
  "login": "joao@careplus.com",
  "perfil": "MEDICO"
}<br><br>


### Médicos

#### POST /medicos

**Request:**<br>
{
  "nome": "Dr. João Silva",
  "email": "dr.joao@careplus.com",
  "crm": "123456",
  "telefone": "11999999999",
  "usuarioId": 1
}

**Response 201 Created:**<br>
{
  "id": 1,
  "nome": "Dr. João Silva",
  "email": "dr.joao@careplus.com",
  "crm": "123456",
  "telefone": "11999999999"
}<br><br>


### Check-ins

#### POST /checkins/alimentacao

**Request:**<br>
{
  "idPaciente": 1,
  "dataRegistro": "2024-05-08T08:00:00",
  "refeicoes": 3,
  "saciedade": "SATISFEITO",
  "hidratacao": 2.5,
  "nivelAcucar": "MODERADO"
}<br><br>


#### POST /checkins/exercicio

**Request:**<br>
{
  "idPaciente": 1,
  "dataRegistro": "2024-05-08T07:00:00",
  "sessoes": 1,
  "intensidade": "MODERADA",
  "duracaoTotal": 45.0
}<br><br>


#### POST /checkins/sono

**Request:**<br>
{
  "idPaciente": 1,
  "dataRegistro": "2024-05-08T06:00:00",
  "horasDormidas": 7.5,
  "qualidade": "BOM",
  "despertares": 1
}<br><br>


#### POST /checkins/estresse

**Request:**<br>
{
  "idPaciente": 1,
  "dataRegistro": "2024-05-08T18:00:00",
  "nivelEstresse": "MODERADO"
}<br><br>


#### POST /checkins/social

**Request:**<br>
{
  "idPaciente": 1,
  "dataRegistro": "2024-05-08T19:00:00",
  "interacaoPositiva": true
}<br><br>


#### POST /checkins/substancias

**Request:**<br>
{
  "idPaciente": 1,
  "dataRegistro": "2024-05-08T20:00:00",
  "doses": 1.0,
  "cigarros": 0
}<br><br>


### Histórico e Relatórios

#### GET /relatorios/{1}?dataInicio=2024-05-01&dataFim=2024-05-31

**Response 200 OK:**<br>
{
  "idPaciente": 1,
  "alimentacao": { "mediaRefeicoes": 3.0, "mediaHidratacao": 2.3 },
  "exercicio": { "totalSessoes": 10, "mediaIntensidade": "MODERADA" },
  "sono": { "mediaHoras": 7.2, "qualidadeMedia": "BOM" },
  "estresse": { "nivelMedio": "MODERADO" },
  "social": { "percentualInteracoesPositivas": 85.0 },
  "substancias": { "mediaDoses": 0.5, "mediaCigarros": 0 }
}<br><br>


### Erros comuns

**Exemplo de erro 400:**<br>
{
  "campo": "email",
  "mensagem": "não deve ser nulo"
}<br><br><br>





## Diagrama de Entidades (ER)

```mermaid
erDiagram
    USUARIOS {
        BIGINT id PK
        VARCHAR login
        VARCHAR senha
        VARCHAR perfil
        BOOLEAN ativo
    }

    MEDICOS {
        BIGINT id PK
        VARCHAR nome
        VARCHAR email
        VARCHAR crm
        VARCHAR telefone
        BOOLEAN ativo
        BIGINT usuario_id FK
    }

    PACIENTES {
        BIGINT id PK
        VARCHAR nome
        VARCHAR email
        VARCHAR cpf
        DATE data_nascimento
        VARCHAR telefone
        BOOLEAN ativo
        BIGINT medico_id FK
        BIGINT usuario_id FK
    }

    CHECKINS_ALIMENTACAO {
        BIGINT id PK
        BIGINT paciente_id FK
        DATETIME data_registro
        INT refeicoes
        VARCHAR saciedade
        DOUBLE hidratacao
        VARCHAR nivel_acucar
    }

    CHECKINS_EXERCICIO {
        BIGINT id PK
        BIGINT paciente_id FK
        DATETIME data_registro
        INT sessoes
        VARCHAR intensidade
        DOUBLE duracao_total
    }

    CHECKINS_SONO {
        BIGINT id PK
        BIGINT paciente_id FK
        DATETIME data_registro
        DOUBLE horas_dormidas
        VARCHAR qualidade
        INT despertares
    }

    CHECKINS_ESTRESSE {
        BIGINT id PK
        BIGINT paciente_id FK
        DATETIME data_registro
        VARCHAR nivel_estresse
    }

    CHECKINS_SOCIAL {
        BIGINT id PK
        BIGINT paciente_id FK
        DATETIME data_registro
        BOOLEAN interacao_positiva
    }

    CHECKINS_SUBSTANCIAS {
        BIGINT id PK
        BIGINT paciente_id FK
        DATETIME data_registro
        DOUBLE doses
        INT cigarros
    }

    USUARIOS ||--o| MEDICOS : "possui"
    USUARIOS ||--o| PACIENTES : "possui"
    MEDICOS ||--o{ PACIENTES : "acompanha"
    PACIENTES ||--o{ CHECKINS_ALIMENTACAO : "registra"
    PACIENTES ||--o{ CHECKINS_EXERCICIO : "registra"
    PACIENTES ||--o{ CHECKINS_SONO : "registra"
    PACIENTES ||--o{ CHECKINS_ESTRESSE : "registra"
    PACIENTES ||--o{ CHECKINS_SOCIAL : "registra"
    PACIENTES ||--o{ CHECKINS_SUBSTANCIAS : "registra"
```
