Arquitetura Event‑Driven com Spring Boot + Kafka + Outbox Pattern

Projeto backend construído com foco em arquitetura orientada a eventos, resiliência em mensageria e boas práticas utilizadas em ambientes reais de produção.

A aplicação implementa criação de pedidos com publicação confiável de eventos utilizando Outbox Pattern, tratamento automático de falhas com Retry + Backoff, envio para Dead Letter Queue (DLQ) e persistência de auditoria no MongoDB.

📐 Arquitetura da solução

Diagrama simplificado do fluxo de eventos:

flowchart LR


A[Client] --> B[REST Controller]
B --> C[Service Layer]
C --> D[(Pedido Database)]
C --> E[(Outbox Table)]


E --> F[Outbox Publisher Scheduler]
F --> G[Kafka Topic pedidos-criados]


G --> H[Kafka Consumer]
H --> I{Falha?}


I -- Não --> J[Processamento OK]
I -- Sim --> K[Retry com Backoff]


K --> L{Falhou novamente?}


L -- Sim --> M[DLQ pedidos-criados.DLQ]
M --> N[DLQ Consumer]
N --> O[(MongoDB Audit Store)]


L -- Não --> J

Essa arquitetura garante:

consistência eventual
tolerância a falhas
rastreabilidade completa de eventos
possibilidade de reprocessamento futuro

🧱 Stack utilizada
Tecnologia	Finalidade
Java 17	Runtime principal
Spring Boot	Framework backend
Spring Kafka	Integração com mensageria
Apache Kafka	Event streaming
MongoDB	Auditoria de eventos DLQ
Docker	Infraestrutura local
Maven	Build tool

📂 Estrutura do projeto

Organização baseada em separação por responsabilidades seguindo princípios de Clean Architecture:

controller
service
repository
infrastructure
messaging
outbox
config
filter

Responsabilidades principais:

Controller

Exposição da API REST.

Service

Orquestração das regras de negócio.

Repository

Persistência de dados.

Messaging

Consumers Kafka e tratamento de eventos.

Outbox

Publicação confiável de eventos assíncronos.

Config

Configurações Kafka, Retry e DLQ.

📦 Outbox Pattern

Garante consistência entre persistência do pedido e publicação do evento.

Fluxo:

Pedido salvo no banco
Evento salvo na tabela Outbox
Scheduler publica evento no Kafka
Evento marcado como processado

Evita perda de eventos em cenários de falha.

🔁 Retry automático com Backoff

Consumidores Kafka possuem retry automático configurado.

Características:

múltiplas tentativas automáticas
atraso progressivo entre tentativas
envio automático para DLQ após falha definitiva

☠️ Dead Letter Queue (DLQ)

Mensagens com falha definitiva são enviadas automaticamente para:

pedidos-criados.DLQ

Permite:

isolamento de falhas
troubleshooting
reprocessamento posterior

🧾 Auditoria de eventos com falha

Eventos enviados para DLQ são persistidos no MongoDB contendo:

payload original
mensagem de erro
tópico
partition
offset
timestamp

Facilita análise e observabilidade.

🆔 Correlation ID

Cada requisição recebe automaticamente um Correlation ID.

Benefícios:

rastreamento ponta‑a‑ponta
debugging facilitado
observabilidade distribuída

▶️ Como executar o projeto
Pré‑requisitos

Instalar:

Docker
Java 17
Maven
Subir infraestrutura
docker compose up -d

Serviços iniciados:

Kafka
MongoDB
Executar aplicação
mvn spring-boot:run
Criar pedido (teste)
curl -X POST http://localhost:8080/pedidos \
-H "Content-Type: application/json" \
-d '{
  "cliente": "Cliente Teste",
  "itens": [
    {
      "produtoId": "1",
      "nomeProduto": "Notebook",
      "quantidade": 1,
      "precoUnitario": 3500.00
    }
  ]
}'

🧪 Simulação de falha controlada

O consumer principal possui uma exceção proposital para demonstrar:

retry automático
envio para DLQ
persistência no MongoDB

🔎 Observabilidade

O sistema registra:

correlation id
eventos publicados
retries executados
mensagens enviadas para DLQ

🎯 Objetivo do projeto

Projeto desenvolvido com foco em estudo prático de:

arquitetura orientada a eventos
resiliência em mensageria
consistência eventual
padrões utilizados em sistemas distribuídos

📌 Evoluções futuras

Possíveis melhorias planejadas:

endpoint para reprocessamento da DLQ
métricas com Prometheus
tracing distribuído
testes de integração com Testcontainers

👨‍💻 Autor
Vinícius Burigatto Mota

Projeto desenvolvido como parte da formação prática em backend com foco em arquitetura distribuída e práticas DevOps.