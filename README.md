# 🎬 StreamApp

> Plataforma de streaming desenvolvida como Avaliação Final da disciplina de **Linguagem de Programação II**

<br>

## 📌 Sobre o Projeto

O **StreamApp** é um sistema web completo de catálogo de mídias digitais. Permite cadastrar, gerenciar e recomendar conteúdos dos tipos **Filme**, **Série**, **Podcast** e **Live**, com cálculo dinâmico de recomendações por diferentes critérios.

O projeto foi desenvolvido com foco na **qualidade arquitetural**: separação de responsabilidades em camadas, aplicação correta de Design Patterns e persistência de dados com Spring Data JPA — sem uma linha de SQL manual.

<br>

## ✨ Funcionalidades

- 📋 **Catálogo completo** com listagem, busca por título e gênero
- ➕ **Cadastro e edição** de mídias com formulário dinâmico por tipo
- 🔍 **Detalhe** de cada mídia com informações específicas da subclasse
- 📊 **Dashboard** com estatísticas e sistema de recomendações intercambiáveis
- 🌐 **API REST** completa com tratamento de erros e status HTTP semânticos

<br>

## 🏗️ Arquitetura e Design Patterns

### 🔷 Repository Pattern
O `MidiaService` depende exclusivamente da interface `MidiaRepository`, sem nenhum acoplamento a implementações concretas de infraestrutura.

### 🔷 Strategy Pattern
A interface `RecomendacaoStrategy` encapsula três algoritmos de recomendação intercambiáveis:

| Estratégia | Critério |
|---|---|
| `RecomendacaoPorAvaliacaoStrategy` | Melhores avaliados pelos usuários |
| `RecomendacaoMaisRecenteStrategy` | Lançamentos mais recentes |
| `RecomendacaoPorGeneroStrategy` | Gênero mais popular do catálogo |

### 🔷 Factory Method
`MidiaFactory.criar(dto)` centraliza toda a criação de objetos polimórficos — nenhum `if/instanceof` de construção está espalhado em Service ou Controller.

<br>

## 🧬 Modelo Orientado a Objetos

```
Midia (classe abstrata)
├── Filme      → duração, diretor, classificação indicativa
├── Serie      → temporadas, episódios, status
├── Podcast    → apresentador, periodicidade, episódios
└── Live       → apresentador, plataforma, ao vivo
```

Cada subclasse implementa de forma polimórfica:
- `getTipoMidia()` — retorna o tipo como String
- `getInfoEspecifica()` — retorna descrição detalhada dos atributos próprios
- `getIcone()` — retorna o ícone Bootstrap correspondente

<br>

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.2.5 | Framework web |
| Spring Data JPA | 3.2.5 | Persistência sem SQL manual |
| Thymeleaf | 3.1 | Templates HTML server-side |
| H2 Database | 2.x | Banco em arquivo (persiste entre reinicializações) |
| Lombok | 1.18.x | Redução de boilerplate |
| Bootstrap | 5.3.3 | Interface responsiva via CDN |
| Maven | 3.x | Gerenciamento de dependências |

<br>

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.8 ou superior

### Passos

```
mvn spring-boot:run
```

Acesse no navegador: **http://localhost:8080/dashboard**

### Console do Banco H2
Disponível em: **http://localhost:8080/h2-console**
```
JDBC URL: jdbc:h2:file:./data/streaming
Usuário:  sa
Senha:    (deixar em branco)
```

> ⚠️ O banco persiste em arquivo na pasta `./data/` — os dados **não são perdidos** ao reiniciar a aplicação.

<br>

## 🌐 API REST

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/api/midias` | Listar todas (suporta `?busca=`) | 200 |
| `GET` | `/api/midias/{id}` | Buscar por ID | 200 / 404 |
| `POST` | `/api/midias` | Criar nova mídia | 201 |
| `PUT` | `/api/midias/{id}` | Atualizar mídia | 200 / 404 |
| `DELETE` | `/api/midias/{id}` | Remover mídia | 204 / 404 |
| `GET` | `/api/midias/recomendacoes` | Recomendações (`?criterio=&limite=`) | 200 |

Critérios disponíveis para recomendações: `TOP_AVALIADOS` · `MAIS_RECENTES` · `GENERO_POPULAR`

<br>

## 📁 Estrutura de Pacotes

```
br.com.streaming
├── model/
│   ├── Midia.java                              ← classe abstrata (contrato da hierarquia)
│   ├── Filme.java                              ← subclasse concreta
│   ├── Serie.java                              ← subclasse concreta
│   ├── Podcast.java                            ← subclasse concreta
│   └── Live.java                               ← subclasse concreta
├── dto/
│   └── MidiaDTO.java                           ← objeto de transferência para forms e API
├── repository/
│   └── MidiaRepository.java                    ← Repository Pattern (extends JpaRepository)
├── factory/
│   └── MidiaFactory.java                       ← Factory Method
├── strategy/
│   ├── RecomendacaoStrategy.java               ← interface Strategy
│   ├── RecomendacaoPorAvaliacaoStrategy.java   ← implementação A
│   ├── RecomendacaoMaisRecenteStrategy.java    ← implementação B
│   └── RecomendacaoPorGeneroStrategy.java      ← implementação C
├── service/
│   └── MidiaService.java                       ← @Service, @Transactional
├── controller/
│   ├── MidiaController.java                    ← @Controller (Thymeleaf)
│   ├── MidiaRestController.java                ← @RestController (API REST)
│   └── DashboardController.java                ← @Controller (Dashboard)
├── exception/
│   ├── MidiaNaoEncontradaException.java
│   └── GlobalExceptionHandler.java             ← @RestControllerAdvice
└── StreamingApplication.java                   ← @SpringBootApplication
```

<br>

## 🖥️ Telas do Sistema

| Rota | Tela |
|---|---|
| `/dashboard` | Dashboard com estatísticas e recomendações dinâmicas |
| `/midias` | Catálogo com cards e busca por título/gênero |
| `/midias/nova` | Formulário de cadastro dinâmico por tipo de mídia |
| `/midias/{id}/editar` | Formulário de edição pré-preenchido |
| `/midias/{id}` | Detalhe completo da mídia |

<br>

---

<div align="center">
  Desenvolvido para a disciplina de <strong>Linguagem de Programação II</strong> · 2026
</div>