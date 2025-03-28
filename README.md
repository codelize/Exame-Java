# API de Eventos

## 📖 Descrição
Esta aplicação é uma API RESTfull para gerenciamento de eventos, desenvolvida em Java utilizando JAX-RS e JDBC para integração com um banco de dados Oracle. A API permite realizar operações básicas de CRUD (Create, Read, Update, Delete) em uma tabela de eventos.


---

## 🌐 Endpoints
### 1. **Cadastrar Evento**
- **URL:** `POST /eventos`
- **Descrição:** Adiciona um novo evento.
- **Body Exemplo (JSON):**
  ```json
  {
      "titulo": "Primeiro Evento",
      "descricao": "Este é um evento de teste.",
      "dataEvento": "2024-12-31"
  }
### 2. **Listar Todos os Eventos**
- **URL:** `GET /eventos`
- **Descrição:** Retorna a lista de todos os eventos.

---

### 3. **Buscar Evento por ID**
- **URL:** `GET /eventos/{id}`
- **Descrição:** Retorna os detalhes de um evento específico.

---

### 4. **Atualizar Evento**
- **URL:** `PUT /eventos/{id}`
- **Descrição:** Atualiza os dados de um evento existente.
- **Body Exemplo (JSON):**
  ```json
  {
      "titulo": "Evento Atualizado",
      "descricao": "Descrição atualizada do evento.",
      "dataEvento": "2025-01-01"
  }

### 4. **Remover Evento**
- **URL:** `DELETE /eventos/{id}`
- **Descrição:** Remove um evento pelo `ID`.
- **Parâmetros:**
  - **`id`**: ID do evento a ser removido.
- **Resposta Esperada:**
  - **Sucesso (204 No Content):** O evento foi removido com sucesso, sem corpo na resposta.
  - **Erro (404 Not Found):**
    ```json
    {
        "erro": "Evento com ID {id} não encontrado."
    }


### API/Endpoints

![1](https://github.com/user-attachments/assets/693db932-d5ce-4ff8-a74d-d03db5e16b16)
![GET1](https://github.com/user-attachments/assets/ce4fb5b3-d5e5-4b23-9e46-978fcfc833ed)
![GET2](https://github.com/user-attachments/assets/b011626e-45b2-4d7c-aec9-dfd779f98d00)
![PUT](https://github.com/user-attachments/assets/61300fc0-fbeb-49c0-8091-9fbba41145e0)
![DEL](https://github.com/user-attachments/assets/f09c6adb-4ef3-43a6-960e-da627a305b18)


