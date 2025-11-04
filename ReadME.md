# 📘 Motomap – Monitoramento Inteligente de Motos

📌 **Autores:** 
Felipe Camargo - RM 556325,
Caio Amarante - RM558640, 
Caio Marques - RM555997    
---

## 🎯 Sobre o Projeto

O **Motomap** é um sistema web desenvolvido para monitoramento inteligente de motos da Mottu. A aplicação permite:

- Cadastro e gerenciamento de motos
- Definição de zonas virtuais de monitoramento
- Registro e consulta de histórico de movimentações
- Sistema de autenticação e autorização com diferentes níveis de acesso (ADMIN e OPERADOR)

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.4.10** - Framework principal
  - Spring Data JPA - Persistência de dados
  - Spring Security - Autenticação e autorização
  - Spring Web - Desenvolvimento web
  - Thymeleaf - Template engine para frontend
- **SQL Server Database** - Banco de dados em nuvem na Azure (integração com DevOps) 
- **Flyway** - Gerenciamento de migrations
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências
- **Jackson** - Processamento JSON

## 📦 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

- **Java JDK 21** ou superior
  - [Download do Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
  - Verifique a instalação: `java -version`

- **Maven 3.6+** (opcional, o projeto inclui Maven Wrapper)
  - Verifique a instalação: `mvn -version`

- **Git** (para clonar o repositório)
  - [Download do Git](https://git-scm.com/downloads)

## 💻 Instalação

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/motomap.git
cd motomap
```

### 2. Verifique as Configurações

O arquivo `src/main/resources/application.properties` já está configurado para desenvolvimento:

```properties
# Configuração do H2 Database (em memória)
spring.datasource.url= SEU_JDBC_URL_AQUI
spring.datasource.username=SEU_USUARIO_AQUI
spring.datasource.password=SEU_SENHA_AQUI
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# JPA e Flyway configurados automaticamente
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true
```

### 3. Compile o Projeto

No diretório raiz do projeto, execute:

#### No Windows (PowerShell):
```powershell
.\mvnw.cmd clean install
```

Ou se você tiver o Maven instalado globalmente:
```bash
mvn clean install
```

## ▶️ Execução

### Método Usando Maven Global

```bash
mvn spring-boot:run
```

## 🌐 Acesso à Aplicação

### Interface Principal

Após iniciar a aplicação, acesse no navegador:

**URL:** `http://localhost:8080`

Você será redirecionado para a página de login.

## 🔐 Credenciais de Acesso

### Usuário Administrador

- **Email:** `admin@mottomap.com`
- **Senha:** `admin123`
- **Permissões:** Acesso total (leitura e escrita)

### Usuário Operador

- **Email:** `oper@mottomap.com`
- **Senha:** `oper123`
- **Permissões:** Apenas leitura

## 📁 Estrutura do Projeto

```
Motomap/
├── src/
│   ├── main/
│   │   ├── java/br/com/fiap/
│   │   │   ├── MotomapApplication.java          # Classe principal
│   │   │   ├── Controller/                       # Controllers REST e MVC
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── MotoController.java
│   │   │   │   ├── ZonaVirtualController.java
│   │   │   │   └── HistoricoMovimentacaoController.java
│   │   │   ├── Model/                            # Entidades JPA
│   │   │   │   ├── Moto.java
│   │   │   │   ├── ZonaVirtual.java
│   │   │   │   ├── HistoricoMovimentacao.java
│   │   │   │   ├── Usuario.java
│   │   │   │   └── Role.java
│   │   │   ├── Repository/                       # Repositórios JPA
│   │   │   │   ├── MotoRepository.java
│   │   │   │   ├── ZonaVirtualRepository.java
│   │   │   │   ├── HistoricoMovimentacaoRepository.java
│   │   │   │   ├── UsuarioRepository.java
│   │   │   │   └── RoleRepository.java
│   │   │   ├── Security/                         # Configuração de segurança
│   │   │   │   └── SecurityConfig.java
│   │   │   └── Service/                          # Regras de negócio
│   │   └── resources/
│   │       ├── application.properties            # Configurações da aplicação
│   │       ├── db/migration/                     # Scripts Flyway
│   │       │   ├── V1__create_table_moto.sql
│   │       │   ├── V2__create_table_zona_virtual.sql
│   │       │   ├── V3__create_table_historico_movimentacao.sql
│   │       │   ├── V4__seed_data.sql
│   │       │   ├── V5__create_users_roles.sql
│   │       │   └── V6__seed_users.sql
│   │       ├── static/                           # Arquivos estáticos
│   │       │   └── css/style.css
│   │       └── templates/                        # Templates Thymeleaf
│   │           ├── index.html
│   │           ├── auth/
│   │           ├── motos/
│   │           ├── zonas/
│   │           └── historicos
```

## ✨ Funcionalidades

### 🔒 Autenticação e Autorização

- Sistema de login seguro com Spring Security
- Dois níveis de acesso:
  - **ADMIN**: Acesso completo (CRUD)
  - **OPERADOR**: Somente leitura

### 🏍️ Gestão de Motos

- Cadastro de novas motos
- Listagem de todas as motos
- Atualização de informações
- Exclusão de motos (somente ADMIN)

### 📍 Zonas Virtuais

- Criação de zonas de monitoramento
- Visualização de zonas cadastradas
- Edição e exclusão (somente ADMIN)

### 📊 Histórico de Movimentações

- Registro de movimentações de motos
- Consulta de histórico completo
- Rastreabilidade de ações

## 🔄 Migrations do Banco de Dados

O projeto utiliza **Flyway** para gerenciar as migrations do banco de dados. As migrations são executadas automaticamente ao iniciar a aplicação.

### Scripts de Migration:

1. **V1**: Criação da tabela `tb_moto`
2. **V2**: Criação da tabela `tb_zona_virtual`
3. **V3**: Criação da tabela `tb_historico_movimentacao`
4. **V4**: Inserção de dados iniciais (seed)
5. **V5**: Criação das tabelas de usuários e roles
6. **V6**: Inserção de usuários padrão
```

## 📝 Endpoints Principais

### Páginas Web

- `GET /` - Página inicial
- `GET /login` - Página de login
- `GET /motos` - Lista de motos
- `GET /motos/novo` - Formulário de nova moto (ADMIN)
- `GET /zonas` - Lista de zonas virtuais
- `GET /historicos` - Lista de histórico

---
 
