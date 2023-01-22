# O que é esse projeto?
Um projeto do 1° ano da Faculdade que consiste em uma aplicação web para controle de finanças pessoais.

# Funcionalidades
- Totalmente responsivo;
- CRUD (Adicionar, Ler, Atualizar, Deletar)
  - Investimentos
  - Receitas
  - Despesas
  - Objetivos financeiros
  - Usuário
- Sistema de cadastro e autenticação para usar o sistema 
  
# Tecnologias usadas
- HTML, CSS e JS
- Bootstrap para responsividade
- Java com Servlets e JSTL
- Oracle Database XE

# Padrões de código
- MVC
- DAO e DAOfactory
- BEM para as classes css
- Singleton em algumas classes

# Documentação
## Para o backend
Javadoc do projeto em (basta acessar o index.html): <https://github.com/YanGidorini/Fintech/tree/main/doc> 

Para ter uma noção de como as classes se relacionam há o diagrama de classes da camada model em: <https://github.com/YanGidorini/Fintech/blob/main/doc/ClassesModel.PNG>

Para o banco de dados há o modelo relacional em: <https://github.com/YanGidorini/Fintech/blob/main/BancoDados/ModeloRelacional.png>

# Configurações
A aplicação foi feita em localhost usando Apache Tomcat v10.0.27, Java 8 (jre 1.8.0_361 e jdk 18) e Oracle Database 21c Express Edition.

A configuração padrão do banco dados segue abaixo, porém você pode configurar alterando a classe NOME_CLASSE
- Usuário: System
- Senha: 160104
- Host: localhost
- Porta: 1521
- SID: xe

---
# Overview do projeto
<a href="{video-url}" title="Link Title"><img src="{image-url}" alt="Alternate Text" /></a>