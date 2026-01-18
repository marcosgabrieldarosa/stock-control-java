📦 Sistema de Controle de Estoque — Java POO

Projeto desenvolvido como laboratório prático de Programação Orientada a Objetos em Java, criado durante o Bootcamp Accenture – Desenvolvimento Java & Cloud com o objetivo de consolidar conceitos.

A proposta foi transformar o estudo em algo concreto, modelando um domínio realista de produtos e estoque, aplicando boas práticas de POO, regras de negócio e organização de código.

🎯 Objetivo do Projeto

Consolidar conceitos fundamentais de Java e POO

Praticar modelagem de domínio e regras de negócio

Criar um projeto claro, legível e evolutivo para portfólio

Demonstrar evolução técnica e capacidade de aprendizado contínuo

Este projeto não é um CRUD simples, e sim um estudo focado em responsabilidade das classes, validações e comportamento do domínio.

🧠 Conceitos Aplicados

Programação Orientada a Objetos (POO)

Encapsulamento e abstração

Validações de domínio

Uso de BigDecimal para valores monetários

equals() e hashCode() 

Separação de responsabilidades

Uso de enum para regras de negócio

Serviços de domínio (Service)

Código limpo e legível

🏗️ Estrutura do Projeto
application
└── Main.java

domain
├── product
│   └── Product.java
└── stock
├── Stock.java
├── StockMovement.java
└── StockService.java


application: ponto de entrada da aplicação

domain: contém toda a lógica de negócio, sem dependência de frameworks

Separação clara entre orquestração e regras de domínio

📦 Principais Entidades
🔹 Product

Representa um produto do sistema, contendo:

Identidade baseada em SKU

Tipo e categoria do produto

Preço de custo e venda

Regras como:

Produto ativo/inativo

Validação de preços

Cálculo de lucro e margem

🔹 Stock

Representa o estoque de um produto:

Quantidade atual

Quantidade mínima

Valida saldo negativo

Indica quando o estoque está abaixo do mínimo

🔹 StockMovement

Registra movimentações de estoque:

Entrada ou saída

Origem do movimento (compra, venda, ajuste)

Validações específicas por tipo de movimento

Data automática da movimentação

🔹 StockService

Responsável por:

Registrar entradas e saídas

Validar operações

Delegar regras ao domínio

▶️ Exemplo de Execução

O projeto possui uma classe Main que demonstra um fluxo completo:

Criação de produto

Definição de preços

Criação de estoque

Registro de entrada (compra)

Registro de saída (venda)

Exibição do estado final do estoque

Isso facilita a leitura e entendimento do funcionamento geral do sistema.

🚀 Próximos Passos (Evolução Planejada)

Este projeto foi pensado para evoluir gradualmente, aplicando novos conhecimentos à medida que forem estudados:

Testes unitários (JUnit / Mockito)

Integração com banco de dados

API REST com Spring Boot

Validações com Bean Validation

Camadas de aplicação (Controller / Service / Repository)

Dockerização

Boas práticas de arquitetura

👨‍💻 Sobre o Autor

Projeto desenvolvido por Marcos Gabriel, estudante de Java em transição e em constante evolução, com foco em desenvolvimento backend e boas práticas de código.

📌 Objetivo profissional: demonstrar evolução técnica, aprendizado consistente e readiness para atuar como desenvolvedor Java júnior.

📄 Observação Final

Este projeto representa um ponto da jornada, não o destino final.
Cada melhoria futura será feita sobre uma base sólida de conceitos bem compreendidos.