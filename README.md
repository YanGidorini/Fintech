# Fintech - Controle de Finanças Pessoais

## Menu de Navegação
- [Introdução](#introdução)
- [Funcionalidades](#funcionalidades)
- [Tecnologias usadas](#tecnologias-usadas)
- [Padrões de código](#padrões-de-código)
- [Documentação](#documentação)
  - [Para o backend](#para-o-backend)
  - [Para o banco de dados](#para-o-banco-de-dados)
- [Configurações](#configurações)
- [Review do projeto](#review-do-projeto)

### **Introdução**
Este projeto é um trabalho de 1° ano da Faculdade, que consiste em uma aplicação web para controle de finanças pessoais.

### **Funcionalidades**
- Totalmente responsivo
- CRUD (Adicionar, Ler, Atualizar, Deletar)
  - Investimentos
  - Receitas
  - Despesas
  - Objetivos financeiros
  - Usuário (deletar não é possível)
- Sistema de cadastro e autenticação para usar o sistema 

### **Tecnologias usadas**
- HTML, CSS e JS
- Bootstrap para responsividade
- Java com Servlets e JSTL
- Oracle Database XE

### **Padrões de código**
- MVC
- DAO e DAOfactory
- BEM para as classes css
- Singleton em algumas classes

### **Documentação**
#### Para o backend
- Javadoc do projeto: [Acessar](https://github.com/YanGidorini/Fintech/tree/main/doc)
- Diagrama de classes da camada model: [Visualizar](https://github.com/YanGidorini/Fintech/blob/main/doc/ClassesModel.PNG)

#### Para o banco de dados
- Modelo relacional: [Visualizar](https://github.com/YanGidorini/Fintech/blob/main/BancoDados/ModeloRelacional.png)

### **Configurações**
- A aplicação foi desenvolvida em localhost usando:
  - Apache Tomcat v10.0.27
  - Java 8 (jre 1.8.0_361 e jdk 18) 
  - Oracle Database 21c Express Edition.
- A configuração padrão do banco dados é:
  - Usuário: System
  - Senha: 160104
  - Host: localhost
  - Porta: 1521
  - SID: xe

### **Review do projeto**
Veja como o projeto funciona clicando na imagem abaixo:

<a href="https://youtu.be/7r-M2QhHcVg" title="Fintech review" target="_blank"><img src="https://media.discordapp.net/attachments/730415785608085565/1066822756890579104/Capturar.PNG?width=1369&height=670" alt="Project review" /></a>
