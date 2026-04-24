# 🚀 prjBootSpringRest

<p align="center">
  <img src="[https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)" />
  <img src="[https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)" />
  <img src="[https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)" />
  <img src="[https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge](https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge)" />
</p>

<p align="center">
  <strong>Projeto Acadêmico: Sistemas de Informação</strong><br />
  <i>Autenticação Spring Security e Gerenciamento de Departamentos</i>
</p>

---

## 📖 Sobre o projeto
Esta aplicação foi desenvolvida para consolidar conhecimentos em **Spring Boot** e arquitetura **REST**. O sistema gerencia o fluxo de autenticação de usuários e a manutenção de departamentos, utilizando controle de acesso por sessão e persistência em memória.

## 🛠 Tecnologias utilizadas
* **Linguagem:** Java 17
* **Framework:** Spring Boot (Web & Security)
* **Gerenciador:** Maven
* **Frontend:** HTML, CSS e JavaScript (Estático)

---

## ⚙️ Funcionalidades
- [x] Login com autenticação baseada em sessão.
- [x] Controle de rotas com **Spring Security**.
- [x] CRUD completo de departamentos.
- [x] Interface web para interação direta com a API.

---

## 🚀 Como executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/prjBootSpringRest.git
   ```
2. **Importe na IDE:** Abra como um projeto Maven (Eclipse, IntelliJ ou VS Code).
3. **Execute:** Rode a classe `controller.CtrlPrograma` como Java Application.
4. **Acesse:** Abra o navegador em `http://localhost:8081`.

> [!IMPORTANT]
> O projeto utiliza a porta **8081**. Certifique-se de que ela está disponível.

---

## 🔐 Credenciais de Teste

| Usuário | Senha |
| :--- | :--- |
| `lasalle` | `lasalle` |
| `alessandro` | `chato` |

---

## 🛣 Endpoints principais

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/auth/login` | Realiza o login do usuário |
| `GET` | `/depto/listar` | Lista todos os departamentos |
| `POST` | `/depto/incluir` | Cadastra um novo departamento |
| `PUT` | `/depto/alterar/{id}` | Atualiza um departamento |
| `DELETE` | `/depto/remover/{id}` | Remove um departamento |

---

## 📂 Estrutura do projeto
```text
src/main/java
├── controller/    # Camada de controle e rotas
├── model/         # Entidades de negócio
└── model/dao/     # Acesso a dados (Em memória)
```

---
<p align="center">
  Desenvolvido por <strong>Igor Silveira</strong>
</p>
