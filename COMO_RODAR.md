# 📖 Como Rodar o Projeto - Resumo Executivo

## ⚡ Início Rápido (3 passos)

### Windows
```powershell
# 1. Iniciar banco
docker-compose up -d

# 2. Compilar
mvn clean compile

# 3. Executar interface gráfica
mvn exec:java -Dexec.mainClass="br.com.valetinho.appswing.TelaPrincipal"
```

**OU use o menu interativo:**
```powershell
.\run.bat
```

---

## 📋 Estrutura do Projeto

```
valetinho-park/
├── src/main/java/br/com/valetinho/
│   ├── appconsole/        → Aplicações de console
│   │   ├── Cadastrar.java → Cria dados de exemplo
│   │   ├── Listar.java    → Lista todos os dados
│   │   ├── Consultar.java → Executa consultas
│   │   ├── Alterar.java   → Demonstra alterações
│   │   └── Apagar.java    → Demonstra exclusões
│   │
│   ├── appswing/          → Interface gráfica
│   │   ├── TelaPrincipal.java
│   │   ├── TelaVeiculo.java
│   │   ├── TelaEstacionamento.java
│   │   ├── TelaBilhete.java
│   │   └── TelaConsulta.java
│   │
│   ├── modelo/            → Entidades JPA
│   │   ├── Veiculo.java
│   │   ├── Estacionamento.java
│   │   ├── Bilhete.java
│   │   └── Localizacao.java
│   │
│   ├── repositorio/       → Acesso a dados (DAO)
│   │   ├── VeiculoRepositorio.java
│   │   ├── EstacionamentoRepositorio.java
│   │   └── BilheteRepositorio.java
│   │
│   └── requisito/         → Lógica de negócio
│       └── Fachada.java   → API principal
│
├── README.md              → Documentação completa
├── GUIA_RAPIDO.md        → Tutorial passo a passo
├── run.bat               → Menu interativo (Windows)
└── compose.yml           → Configuração Docker
```

---

## 🎯 Principais Comandos

### Banco de Dados
```powershell
docker-compose up -d      # Iniciar
docker-compose down       # Parar
docker ps                 # Ver status
docker-compose logs -f    # Ver logs em tempo real
```

### Compilação
```powershell
mvn clean compile         # Compilar
mvn clean package         # Gerar JAR
```

### Executar
```powershell
# Interface Gráfica
mvn exec:java -Dexec.mainClass="br.com.valetinho.appswing.TelaPrincipal"

# Console - Cadastrar dados
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar"

# Console - Listar
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar"

# Console - Consultar
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Consultar"

# Console - Alterar
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Alterar"

# Console - Apagar
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Apagar"
```

---

## 🗃️ Modelo de Dados

```
┌─────────────┐         ┌─────────────┐         ┌──────────────────┐
│   Veiculo   │         │   Bilhete   │         │ Estacionamento   │
├─────────────┤         ├─────────────┤         ├──────────────────┤
│ id (PK)     │◄────────│ id (PK)     │────────►│ id (PK)          │
│ placa       │  1   N  │ data        │  N   1  │ nome             │
│             │         │ hora        │         │ localizacao (x,y)│
└─────────────┘         │ valorpago   │         └──────────────────┘
                        │ veiculo_id  │
                        │ estac_id    │
                        └─────────────┘
```

**Regras:**
- Um Veículo pode ter vários Bilhetes (1:N)
- Um Estacionamento pode ter vários Bilhetes (1:N)
- Um Bilhete pertence a um Veículo e um Estacionamento (N:1, N:1)
- Exclusões em cascata: apagar Veículo/Estacionamento apaga seus Bilhetes

---

## 🎨 Interface Gráfica - Menus

### Menu Veículo
- **Listar**: Mostra todos os veículos
- **Criar**: Adiciona novo veículo (placa única)
- **Atualizar**: Altera placa do veículo
- **Apagar**: Remove veículo e seus bilhetes
- **Limpar**: Limpa campos do formulário

### Menu Estacionamento
- **Listar**: Mostra todos os estacionamentos
- **Criar**: Adiciona novo estacionamento (nome e localização)
- **Atualizar**: Altera nome e localização
- **Apagar**: Remove estacionamento e seus bilhetes
- **Limpar**: Limpa campos do formulário

### Menu Bilhete
- **Listar**: Mostra todos os bilhetes
- **Criar**: Adiciona novo bilhete
  - Formato data: `dd/MM/yyyy HH:mm:ss`
  - Exemplo: `26/01/2026 14:30:00`
- **Apagar**: Remove bilhete selecionado
- **Limpar**: Limpa campos do formulário

### Menu Consulta
1. **Bilhetes com valor > X**: Digite o valor mínimo
2. **Veículos em data X no estacionamento Y**: 
   - Data: `dd/MM/yyyy` (ex: `26/01/2026`)
   - Nome do estacionamento
3. **Veículos com mais de N bilhetes**: Digite quantidade mínima

---

## 📝 Formatos de Dados

### Datas
```
Com hora:    dd/MM/yyyy HH:mm:ss  →  26/01/2026 14:30:00
Sem hora:    dd/MM/yyyy           →  26/01/2026
```

### Placas de Veículo
```
ABC-1234  ou  ABC1D23
```

### Localização de Estacionamento
```
x, y  →  10.0, 20.0
```

### Valores Monetários
```
15.50  (use ponto, não vírgula)
```

---

## 🔥 Workflow Típico

### 1. Primeira Execução
```powershell
# Iniciar banco
docker-compose up -d

# Compilar
mvn clean compile

# Cadastrar dados de exemplo
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar"

# Listar para verificar
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar"

# Abrir interface
mvn exec:java -Dexec.mainClass="br.com.valetinho.appswing.TelaPrincipal"
```

### 2. Uso Diário
```powershell
# Iniciar banco (se não estiver rodando)
docker-compose up -d

# Abrir interface
mvn exec:java -Dexec.mainClass="br.com.valetinho.appswing.TelaPrincipal"
```

### 3. Testar Funcionalidades
1. Abra a interface Swing
2. Menu Veículo → Crie "TEST-1234"
3. Menu Estacionamento → Crie "Teste" em (50.0, 50.0)
4. Menu Bilhete → Crie bilhete para "TEST-1234" no "Teste"
5. Menu Consulta → Teste as diferentes consultas

---

## ⚠️ Troubleshooting

| Problema | Solução |
|----------|---------|
| `mvn não reconhecido` | Instale Maven: `choco install maven` ou baixe em maven.apache.org |
| `Cannot connect to database` | Verifique Docker: `docker ps` e `docker-compose restart` |
| `Compilation error` | Execute: `mvn clean compile` |
| Interface não abre | 1. Verifique banco: `docker ps`<br>2. Veja logs: `docker-compose logs` |
| Erro ao criar bilhete | Verifique formato da data: `dd/MM/yyyy HH:mm:ss` |
| Tabelas não criadas | Normal na primeira vez, Hibernate cria automaticamente |

---

## 📞 Suporte

1. **README.md**: Documentação completa e detalhada
2. **GUIA_RAPIDO.md**: Tutorial passo a passo com exemplos
3. **run.bat**: Menu interativo para Windows
4. **Logs do Docker**: `docker-compose logs -f db`

---

## 🎓 Tecnologias

- **Java 21**: Linguagem de programação
- **Maven**: Gerenciador de dependências e build
- **JPA/Hibernate 7.2**: Framework ORM (Object-Relational Mapping)
- **PostgreSQL**: Banco de dados relacional
- **Docker**: Containerização do banco de dados
- **Swing**: Interface gráfica Java

---

## 🚀 Próximos Passos

Depois de rodar o projeto:

1. ✅ Explore a interface Swing (mais intuitiva)
2. ✅ Teste as operações CRUD (Create, Read, Update, Delete)
3. ✅ Experimente as consultas personalizadas
4. ✅ Veja o código da Fachada para entender a lógica de negócio
5. ✅ Examine os repositórios para ver as queries JPQL
6. ✅ Analise as entidades JPA e seus relacionamentos

---

## 💡 Dicas de Uso

1. Sempre crie Veículos e Estacionamentos antes de criar Bilhetes
2. Use o botão "Listar" para atualizar os dados na tela
3. Ao selecionar uma linha na tabela, os dados aparecem nos campos
4. Use "Limpar" para resetar o formulário
5. Confirme antes de apagar (operação irreversível)
6. As datas devem estar no formato correto

---

## 📊 Dados de Exemplo (após executar Cadastrar.java)

### Estacionamentos (6)
- Shopping Center (10.0, 20.0)
- Aeroporto (11.5, 21.5)
- Centro (12.0, 22.0)
- Praia (13.2, 23.4)
- Hospital (9.9, 19.8)
- Universidade (14.0, 24.0)

### Veículos (6)
- ABC-1234
- DEF-5678
- GHI-9012
- JKL-3456
- MNO-7890
- PQR-1122

### Bilhetes (12)
Diversos bilhetes associando veículos a estacionamentos em diferentes datas

---

**Desenvolvido para o curso de Persistência de Objetos - IFPB**
