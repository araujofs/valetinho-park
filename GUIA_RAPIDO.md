# 🚀 Guia Rápido - Valetinho Park

## Passo a Passo para Rodar o Projeto

### 1️⃣ Verificar Requisitos

Abra o PowerShell ou Terminal e execute:

```powershell
# Verificar Java (deve ser versão 21+)
java -version

# Verificar Maven
mvn -version

# Verificar Docker
docker --version
```

**Se Maven não estiver instalado:**
- Baixe em: https://maven.apache.org/download.cgi
- Ou instale via Chocolatey: `choco install maven`
- Reinicie o terminal após instalar

---

### 2️⃣ Iniciar o Banco de Dados

```powershell
# Na pasta do projeto
cd C:\Users\davi.bezerra\Documents\GitHub\valetinho-park

# Iniciar PostgreSQL no Docker
docker-compose up -d

# Verificar se está rodando
docker ps
```

✅ Você deve ver um container do PostgreSQL rodando na porta 5432

---

### 3️⃣ Compilar o Projeto

```powershell
# Compilar o projeto
mvn clean compile
```

⏱️ Pode demorar alguns minutos na primeira vez (baixa dependências)

---

### 4️⃣ Executar a Aplicação

#### Opção A: Interface Gráfica (Recomendado)

```powershell
mvn exec:java -Dexec.mainClass="br.com.valetinho.appswing.TelaPrincipal"
```

🎨 Abrirá uma janela com menus:
- **Veículo**: Gerenciar veículos
- **Estacionamento**: Gerenciar estacionamentos
- **Bilhete**: Gerenciar bilhetes
- **Consulta**: Realizar consultas

#### Opção B: Console (Cadastrar Dados)

```powershell
# Cadastrar dados de exemplo
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar"
```

---

## 📱 Como Usar a Interface Gráfica

### Primeiro Uso

1. Clique no menu **Veículo** → Crie veículos:
   - Placa: `ABC-1234`
   - Placa: `DEF-5678`

2. Clique no menu **Estacionamento** → Crie estacionamentos:
   - Nome: `Shopping`, Localização: `10.0, 20.0`
   - Nome: `Centro`, Localização: `15.0, 25.0`

3. Clique no menu **Bilhete** → Crie bilhetes:
   - Data: `26/01/2026 14:30:00`
   - Valor: `15.50`
   - Placa: `ABC-1234`
   - Estacionamento: `Shopping`

4. Clique no menu **Consulta** → Teste as consultas

---

## 🎯 Operações Principais

### 🚗 Gerenciar Veículos

**Criar:**
- Menu Veículo → Preencha "placa" → Clique "Criar"

**Atualizar:**
- Selecione um veículo na tabela
- Preencha "nova placa"
- Clique "Atualizar"

**Apagar:**
- Selecione um veículo
- Clique "Apagar"
- ⚠️ Apaga o veículo e todos os seus bilhetes

---

### 🅿️ Gerenciar Estacionamentos

**Criar:**
- Menu Estacionamento → Preencha:
  - Nome: `Aeroporto`
  - Localização: `30.0, 40.0`
- Clique "Criar"

**Atualizar:**
- Selecione um estacionamento
- Modifique nome ou localização
- Clique "Atualizar"

**Apagar:**
- Selecione um estacionamento
- Clique "Apagar"
- ⚠️ Apaga o estacionamento e todos os seus bilhetes

---

### 🎫 Gerenciar Bilhetes

**Criar:**
- Menu Bilhete → Preencha:
  - Data: `26/01/2026 14:30:00` (formato: dd/MM/yyyy HH:mm:ss)
  - Valor: `15.50`
  - Placa: Escolha uma placa existente
  - Estacionamento: Escolha um estacionamento existente
- Clique "Criar"

**Apagar:**
- Selecione um bilhete na tabela
- Clique "Apagar"

---

### 🔍 Consultas

**1. Bilhetes com valor maior que X:**
- Selecione a consulta no dropdown
- Clique "Consultar"
- Digite o valor (ex: `10`)

**2. Veículos em data específica:**
- Selecione a consulta
- Clique "Consultar"
- Digite a data (formato: dd/MM/yyyy, ex: `26/01/2026`)
- Digite o nome do estacionamento

**3. Veículos com mais de N bilhetes:**
- Selecione a consulta
- Clique "Consultar"
- Digite a quantidade mínima (ex: `2`)

---

## 🔧 Comandos Console Úteis

### Cadastrar dados de exemplo
```powershell
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar"
```
Cria 6 estacionamentos, 6 veículos e 12 bilhetes

### Listar todos os dados
```powershell
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar"
```

### Executar consultas
```powershell
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Consultar"
```

### Demonstrar alterações
```powershell
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Alterar"
```

### Demonstrar exclusões
```powershell
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Apagar"
```

---

## ❌ Solução de Problemas

### "mvn não é reconhecido"
```powershell
# Instale o Maven
choco install maven
# OU baixe em: https://maven.apache.org/download.cgi
# Depois reinicie o terminal
```

### "Cannot connect to database"
```powershell
# Reinicie o container
docker-compose restart

# Ou recrie do zero
docker-compose down -v
docker-compose up -d
```

### "Erro de compilação"
```powershell
# Limpe e recompile
mvn clean compile
```

### Interface não abre
- Verifique se o banco está rodando: `docker ps`
- Verifique os logs: `docker-compose logs`

---

## 🛑 Parar o Projeto

```powershell
# Parar o banco de dados
docker-compose down

# Parar e apagar os dados
docker-compose down -v
```

---

## 📝 Formatos Importantes

### Datas
- **Com hora**: `dd/MM/yyyy HH:mm:ss` → `26/01/2026 14:30:00`
- **Sem hora**: `dd/MM/yyyy` → `26/01/2026`

### Placas
- Formato: `ABC-1234` ou `ABC1D23`

### Localização
- Formato: `x, y` → `10.0, 20.0`

### Valores
- Formato: `15.50` (use ponto, não vírgula)

---

## 🎓 Estrutura de Dados

```
Veiculo (placa)
   ↓
Bilhete (data, hora, valor)
   ↓
Estacionamento (nome, localização)
```

**Relações:**
- Um veículo tem vários bilhetes
- Um estacionamento tem vários bilhetes
- Um bilhete pertence a um veículo e um estacionamento

---

## 💡 Dicas

1. ✅ Sempre crie veículos e estacionamentos antes de criar bilhetes
2. ✅ Use o botão "Listar" para ver os dados atualizados
3. ✅ Use o botão "Limpar" para limpar o formulário
4. ⚠️ Ao apagar um veículo/estacionamento, todos os bilhetes são apagados
5. 📅 Use datas no formato correto (dd/MM/yyyy HH:mm:ss)

---

## 📞 Ajuda Adicional

- Leia o README.md completo para mais detalhes
- Verifique os logs do Docker: `docker-compose logs -f`
- Teste primeiro com a aplicação console: `Cadastrar` → `Listar`
