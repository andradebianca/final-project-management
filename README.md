<div align="left">

# SGTCC: TCC ON

**API REST para gerenciamento de Trabalhos de Conclusão de Curso**
Centraliza a orientação entre alunos, orientadores e coordenação.

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?logo=swagger&logoColor=black)

Projeto da disciplina de **Programação Orientada a Objetos II** · **Universidade Vila Velha**
Status: **em desenvolvimento**

</div>

<!-- Adicionar um print do Swagger UI: ![Swagger UI](docs/img/swagger.png) -->

---

## Índice

- [Sobre o projeto](#sobre-o-projeto)
- [Destaques técnicos](#destaques-técnicos)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Como executar](#como-executar)
- [Documentação da API](#documentação-da-api)
- [Arquitetura](#arquitetura)
- [Segurança](#segurança)
- [Deploy em produção](#deploy-em-produção)
- [Roadmap](#roadmap)
- [Documentação de estudo](#documentação-de-estudo)
- [Autores](#autores)

---

## Sobre o projeto

O TCCON propõe uma plataforma única para registrar quem orienta quem, acompanhar o andamento de cada trabalho e manter o histórico do processo de desenvolvimento do Trabalho de conclusão de curso. O princípio que guia o projeto é que **a ferramenta só será adotada se reduzir o trabalho de quem a usa**, sem criar retrabalho.

### Como o problema foi levantado

Antes de escrever código, o grupo mapeou o processo atual e entrevistou **9 stakeholders** (4 professores orientadores, 2 coordenadores de curso, 1 membro da equipe administrativa e 2 alunos). Os problemas mais recorrentes:

- Informações dispersas em várias ferramentas, sem registro institucional.
- Pouca visibilidade, para orientadores e coordenaçã
o, sobre o andamento de cada aluno.
- Dificuldade do aluno em encontrar um orientador com afinidade ao tema.
- Falta de evidências formais das orientações realizadas.
- Controle manual de vínculos aluno–orientador, prazos e cobranças.

### Perfis de usuário

| Perfil | Necessidades principais |
|---|---|
| **Aluno** | Encontrar orientador, enviar entregas, acompanhar o status das atividades |
| **Orientador** | Acompanhar orientandos, revisar entregas, registrar orientações |
| **Coordenação / Administrativo** | Saber quem orienta quem, acompanhar o andamento e consultar o histórico |

---

## Destaques técnicos

- **Arquitetura em camadas** (`controller → service → repository`), com regras de negócio fora dos controllers.
- **Autenticação stateless com JWT** (Spring Security + `java-jwt`), com geração de token isolada em um `TokenService`.
- **DTOs de entrada e saída** (`Request` / `Response`) para não expor entidades diretamente. 
- **Tratamento centralizado de erros** com `ApiExceptionHandler` e exceções de domínio.
- **Documentação interativa** da API com OpenAPI/Swagger.
- **Perfis de configuração** (`application.properties` e `application-dev.properties`) e carga de dados iniciais para desenvolvimento.
- **Modelo de domínio** com perfis de usuário e áreas de interesse, base para recomendar orientadores por tema.

---

## Funcionalidades

Legenda: ✅ implementado · 🚧 em andamento · 📋 planejado

| Módulo | Situação |
|---|---|
| Autenticação com JWT (login) | ✅ |
| Cadastro e consulta de alunos | ✅ |
| Cadastro e consulta de orientadores | ✅ |
| Áreas de interesse (alunos e orientadores) | ✅  |
| Cadastro e vínculo de TCC | 🚧 ⚠️ |
| Controle de acesso por perfil | 🚧 ⚠️ |
| Recomendação de orientadores por tema | 📋 |
| Solicitação formal de orientação | 📋 |
| Atividades em kanban com etiquetas de status | 📋 |
| Entregas, versões de documentos e comentários | 📋 |
| Histórico de orientações (evidências) | 📋 |
| Agendamento de orientações e bancas | 📋 |
| Notificações e alertas | 📋 |

---

## Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 17 | Linguagem |
| Spring Boot | 4.1.1 | Framework da API |
| Spring Web MVC | – | Camada REST |
| Spring Data JPA | – | Persistência (ORM) |
| Spring Security + java-jwt (Auth0) | 4.4.0 | Autenticação e autorização |
| Bean Validation | – | Validação de dados de entrada |
| H2 | – | Banco em memória, apenas desenvolvimento e testes  |
| PostgreSQL | – | Banco relacional de produção |
| springdoc OpenAPI | 2.8.5 | Swagger UI |
| Maven Wrapper | – | Build e dependências |

---

## Como executar

**Pré-requisitos:** JDK 17 e Git. Não é preciso instalar o Maven, pois o projeto inclui o wrapper.

```bash
# 1. Clonar o repositório
git clone https://github.com/andradebianca/final-project-management.git
cd final-project-management

# 2. Subir a aplicação em modo desenvolvimento  
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

No Windows, use `mvnw.cmd`. A API sobe em **http://localhost:8080**.

Para rodar os testes:

```bash
./mvnw test
```

---

## Documentação da API

Com a aplicação rodando, a documentação interativa fica em:

**http://localhost:8080/swagger-ui/index.html** 

---

## Arquitetura

```
src/main/java/tccapi/
├── config/        # Configurações e carga de dados iniciais
├── controller/    # Endpoints REST (camada HTTP)
├── dto/           # Objetos de entrada e saída da API
├── exception/     # Exceções de domínio e handler global de erros
├── model/         # Entidades JPA e enums
├── repository/    # Interfaces Spring Data JPA
├── security/      # Configuração do Spring Security
└── service/       # Regras de negócio e geração de token
```

Fluxo de uma requisição:

```
Cliente → Controller → Service → Repository → Banco de dados
              │
              └─ DTO de resposta
```

### Modelo de domínio

<!--  Rascunho com base nos nomes das classes. Ajustar relações e cardinalidades. 

```mermaid
classDiagram
    Usuario <|-- Aluno
    Usuario <|-- Orientador
    Usuario <|-- Administrador
    Orientador "1"  "*" Tcc : orienta
    Aluno "1..2" "1" Tcc : participa
    Aluno "*" -> "*" AreaInteresse
    Orientador "*" -> "*" AreaInteresse --> 
```

---

## Segurança

## Deploy em produção

A API foi pensada para rodar em um servidor linux, conectada ao front-end e a um banco PostgreSQL. Em produção, toda configuração sensível vem de variáveis de ambiente.

```
---

## Documentação de estudo

Material explicativo produzido pelo grupo, na pasta [`docs/`](docs/):
(SENDO REVISADO!)
- [Levantamento e problemática](docs/levantamento.md) 
- [Conceitos de REST](docs/conceitos-rest.md)
- [Arquitetura](docs/arquitetura.md)
- [Segurança e JWT](docs/seguranca-jwt.md)

---

## Autores

| Nome | Responsabilidade | Contato |
|---|---|---|
| Bianca Andrade | [papel] Desenvolvimento Backend | [GitHub](https://github.com/andradebianca) · [LinkedIn](https://www.linkedin.com/in/andradebianca/) |
| Guilherme Suave| [papel] Desenvolvimento Frontend [GitHub](https://github.com/GGSuave) |

## Licença

Projeto acadêmico, sem fins comerciais.