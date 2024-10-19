![badgeSpring](https://img.shields.io/badge/Spring-90E59A?labelColor=111111&style=flat&logo=spring&logoColor=90E59A)
![badgeJava](https://img.shields.io/badge/Java-red?style=flat&logo=java&logoColor=white)
![MySQL](https://shields.io/badge/MySQL-lightgrey?logo=mysql&style=plastic&logoColor=white&labelColor=blue)
![badgeSts](https://img.shields.io/badge/-Spring%20Tool%20Suite%204-6DB33F?style=flat&logo=eclipse&logoColor=white)

# CRUD de Produtos
<br/>
  
### Descrição
Projeto de Desenvolvimento de uma API REST para Cadastro de Produtos e Banco de Dados MySQL.
<br/>

### Pré-requisitos
- Java versão 17.
- MySql versão 8.4.
- Git.
- Maven.
<br/>

**OBS:** requer o maven instalado e configurado nas variáveis de ambiente(Windows).

<br/>
  
### A instalação do projeto pode seguir da seguinte forma:
1- Use o GIT para clonar o repositório para seu workspace:  
<code>git clone https://github.com/GuilhermeLapa/cadastro-produtos.git</code>
<br/>
  
2- Criar projeto no Spring Tool Suite(STS):
- No STS acesse *File>>Import*.
- Na janela que foi aberta selecione a opção *Maven>>Existing Maven Project*.
- Na nova janela Maven Projects, clique no botão **Browse**.
- Na nova janela de seleção do Windows, selecione a pasta onde o projeto foi clonado.
- Clique no botão **Selecionar pasta**.
- Clique no botão **Finish**.
<br/>

3- Configuração:
- No STS, no Package Explorer, abra o arquivo *application.properties*.
- Na linha **spring.datasource.username=** informe o usuário do seu banco MySql.
- Na linha **spring.datasource.password=** informe a senha do seu banco MySql.
<br/>

4- Executando a API:
- No STS, no Boot Dashboard, expanda a árvore da opção **local**.
- No STS, no Boot Dashboard, selecione clicando na opção **cadastro-produtos**.
- No STS, no Boot Dashboard, clique novamente na opção **cadastro-produtos** com o botão direito do mouse.
- Clique na opção **(Re)start**.
<br/>

5- Agora é possível acessar a documentação da API através do link do Swagger:  http://localhost:8080/swagger-ui/index.html.
<br/>
