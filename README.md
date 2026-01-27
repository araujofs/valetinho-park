# Valetinho Park - Sistema de Gerenciamento de Estacionamento

Sistema de gerenciamento de estacionamentos desenvolvido em Java com JPA/Hibernate e PostgreSQL. Oferece interfaces console e Swing para interação.

## 🚀 Tecnologias

- **Java 21**
- **Maven** (gerenciador de dependências)
- **JPA/Hibernate 7.2** (persistência)
- **PostgreSQL 42.7** (banco de dados)
- **Java Swing** (interface gráfica)
- **Docker Compose** (containerização do banco)

## 📋 Pré-requisitos

- Java JDK 21 ou superior
- Maven 3.6 ou superior
- Docker e Docker Compose (para o banco de dados)

### Verificar Java

```bash
java -version
```

Deve mostrar Java 21 ou superior.

### Instalar Maven (se não estiver instalado)

**Windows:**
1. Baixe o Maven em: https://maven.apache.org/download.cgi
2. Extraia o arquivo ZIP em `C:\Program Files\Apache\maven`
3. Adicione ao PATH do sistema:
   - Pesquise "Variáveis de Ambiente" no Windows
   - Em "Variáveis do Sistema", edite "Path"
   - Adicione: `C:\Program Files\Apache\maven\bin`
4. Abra um novo terminal e verifique:
   ```bash
   mvn -version
   ```

**Alternativa - Chocolatey (Windows):**
```bash
choco install maven
```

**Linux/Mac:**
```bash
# Ubuntu/Debian
sudo apt install maven

# MacOS (Homebrew)
brew install maven
```

## 🔧 Instalação e Configuração

### Passo 1: Clone o Repositório

```bash
git clone https://github.com/seu-usuario/valetinho-park.git
cd valetinho-park
```

### Passo 2: Inicie o Banco de Dados

O projeto usa PostgreSQL em container Docker. Para iniciar:

```bash
docker-compose up -d
```

Isso criará um container PostgreSQL com:
- **Usuário**: pob
- **Senha**: pob
- **Database**: pob
- **Porta**: 5432

Para verificar se o container está rodando:

```bash
docker ps
```

Para parar o container:

```bash
docker-compose down
```

### Passo 3: Compile o Projeto

```bash
mvn clean compile
```

## ▶️ Como Executar

### 1. Aplicação Console

O projeto possui várias classes console para diferentes operações:

#### Cadastrar Dados de Exemplo

```bash
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar"
```

Esta classe cria:
- 6 estacionamentos (Shopping Center, Aeroporto, Centro, Praia, Hospital, Universidade)
- 6 veículos (ABC-1234, DEF-5678, GHI-9012, JKL-3456, MNO-7890, PQR-1122)
- 12 bilhetes associando veículos a estacionamentos

#### Listar Todos os Dados

```bash
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar"
```

Exibe:
- Todos os estacionamentos com seus bilhetes
- Todos os veículos com seus bilhetes
- Todos os bilhetes cadastrados

#### Consultar Dados Específicos

```bash
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Consultar"
```

Executa 3 consultas:
1. Bilhetes com valor pago > 10
2. Veículos estacionados em data específica
3. Veículos com mais de 1 bilhete

#### Alterar Dados

```bash
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Alterar"
```

Demonstra:
- Alteração de placa de veículo
- Alteração de nome e localização de estacionamento

#### Apagar Dados

```bash
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Apagar"
```

Demonstra:
- Exclusão de bilhete
- Exclusão de veículo (com seus bilhetes em cascata)

### 2. Aplicação Swing (Interface Gráfica)

Para iniciar a interface gráfica:

```bash
mvn exec:java -Dexec.mainClass="br.com.valetinho.appswing.TelaPrincipal"
```

A interface possui 4 menus:

#### Menu Veículo
- **Listar**: exibe todos os veículos cadastrados
- **Criar**: cadastra novo veículo (informe a placa)
- **Atualizar**: altera a placa de um veículo existente
- **Apagar**: remove veículo e seus bilhetes
- Mostra a quantidade de bilhetes de cada veículo

#### Menu Estacionamento
- **Listar**: exibe todos os estacionamentos
- **Criar**: cadastra novo estacionamento (nome e localização x,y)
- **Atualizar**: altera nome e localização
- **Apagar**: remove estacionamento e seus bilhetes
- Mostra os bilhetes associados a cada estacionamento

#### Menu Bilhete
- **Listar**: exibe todos os bilhetes cadastrados
- **Criar**: cadastra novo bilhete
  - Formato da data: `dd/MM/yyyy HH:mm:ss` (ex: 02/12/2025 14:30:00)
  - Informe placa do veículo existente
  - Informe nome do estacionamento existente
  - Informe o valor pago
- **Apagar**: remove bilhete selecionado
- **Limpar**: limpa os campos do formulário

#### Menu Consulta
Oferece 3 tipos de consultas:
1. **Bilhetes com valor maior que X**: informe um valor em reais
2. **Veículos estacionados em data X no estacionamento Y**: 
   - Formato da data: `dd/MM/yyyy` (ex: 01/12/2025)
   - Informe o nome do estacionamento
3. **Veículos com mais de N bilhetes**: informe a quantidade mínima

## 📊 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/valetinho/
│   │       ├── appconsole/       # Aplicações console
│   │       │   ├── Cadastrar.java
│   │       │   ├── Listar.java
│   │       │   ├── Consultar.java
│   │       │   ├── Alterar.java
│   │       │   └── Apagar.java
│   │       ├── appswing/         # Interface gráfica Swing
│   │       │   ├── TelaPrincipal.java
│   │       │   ├── TelaVeiculo.java
│   │       │   ├── TelaEstacionamento.java
│   │       │   ├── TelaBilhete.java
│   │       │   └── TelaConsulta.java
│   │       ├── modelo/           # Entidades JPA
│   │       │   ├── Veiculo.java
│   │       │   ├── Estacionamento.java
│   │       │   ├── Bilhete.java
│   │       │   └── Localizacao.java
│   │       ├── repositorio/      # DAOs
│   │       │   ├── VeiculoRepositorio.java
│   │       │   ├── EstacionamentoRepositorio.java
│   │       │   └── BilheteRepositorio.java
│   │       ├── requisito/        # Camada de negócio
│   │       │   └── Fachada.java
│   │       └── util/
│   │           └── Util.java
│   └── resources/
│       ├── META-INF/
│       │   └── persistence.xml   # Configuração JPA
│       └── imagens/
├── pom.xml                       # Dependências Maven
└── compose.yml                   # Docker Compose
```

## 🗃️ Modelo de Dados

### Entidades

1. **Veiculo**
   - id (gerado automaticamente)
   - placa (único)
   - bilhetes (relacionamento One-to-Many)

2. **Estacionamento**
   - id (gerado automaticamente)
   - nome (único)
   - localização (x, y)
   - bilhetes (relacionamento One-to-Many)

3. **Bilhete**
   - id (gerado automaticamente)
   - data (LocalDate)
   - hora (LocalTime)
   - valorpago (Double)
   - veiculo (Many-to-One)
   - estacionamento (Many-to-One)

### Relacionamentos

- Um **Veículo** pode ter vários **Bilhetes**
- Um **Estacionamento** pode ter vários **Bilhetes**
- Um **Bilhete** pertence a um **Veículo** e um **Estacionamento**
- Exclusões em cascata: ao apagar um veículo ou estacionamento, seus bilhetes são removidos

## 🔍 Consultas Implementadas

### 1. Bilhetes com valor maior que X
```java
Fachada.consultarBilhetesValorMaiorX(10.0)
```

### 2. Veículos estacionados em data específica
```java
Fachada.consultarVeiculoEstacionadoDataX(data, "Shopping Center")
```

### 3. Veículos com mais de N bilhetes
```java
Fachada.consultarVeiculoMaisXBilhetes(1)
```

## 🛠️ Comandos Úteis

### Limpar e recompilar
```bash
mvn clean compile
```

### Executar testes
```bash
mvn test
```

### Gerar JAR
```bash
mvn package
```

### Ver logs do banco de dados
```bash
docker-compose logs -f db
```

### Parar e remover containers
```bash
docker-compose down -v
```

### Reiniciar o banco (apaga todos os dados)
```bash
docker-compose down -v
docker-compose up -d
```

## 📝 Fluxo de Uso Recomendado

### Primeira execução

1. Inicie o banco de dados:
   ```bash
   docker-compose up -d
   ```

2. Cadastre dados de exemplo:
   ```bash
   mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar"
   ```

3. Liste os dados para verificar:
   ```bash
   mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar"
   ```

4. Use a interface gráfica:
   ```bash
   mvn exec:java -Dexec.mainClass="br.com.valetinho.appswing.TelaPrincipal"
   ```

### Testando as funcionalidades

1. **No menu Veículo**: cadastre um novo veículo com placa "TEST-1234"
2. **No menu Estacionamento**: crie um novo estacionamento "Teste" em (50.0, 50.0)
3. **No menu Bilhete**: crie um bilhete para "TEST-1234" no "Teste" com data de hoje
4. **No menu Consulta**: teste as diferentes consultas

## ⚠️ Solução de Problemas

### Erro de conexão com o banco
- Verifique se o container Docker está rodando: `docker ps`
- Reinicie o container: `docker-compose restart`

### Erro de compilação
- Verifique a versão do Java: `java -version` (deve ser 21+)
- Limpe o cache do Maven: `mvn clean`

### Tabelas não criadas
- O Hibernate está configurado para `hibernate.hbm2ddl.auto=update`
- As tabelas são criadas automaticamente na primeira execução

### Formato de data incorreto na interface Swing
- Data e hora: `dd/MM/yyyy HH:mm:ss` (ex: 02/12/2025 14:30:00)
- Data apenas (consultas): `dd/MM/yyyy` (ex: 01/12/2025)

## 👨‍💻 Desenvolvimento

### Estrutura de pacotes

- `appconsole`: Aplicações de linha de comando
- `appswing`: Interface gráfica Swing
- `modelo`: Entidades JPA (Veículo, Estacionamento, Bilhete)
- `repositorio`: Camada de acesso a dados (DAOs)
- `requisito`: Camada de negócio (Fachada)
- `util`: Utilitários (gerenciamento de EntityManager)

### Padrões utilizados

- **Facade Pattern**: Classe `Fachada` centraliza operações de negócio
- **DAO Pattern**: Repositórios encapsulam acesso ao banco
- **JPA/Hibernate**: ORM para persistência
- **Cascade Operations**: Remoção em cascata de relacionamentos

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais.

## 👤 Autor

Desenvolvido para o curso de Persistência de Objetos - IFPB
