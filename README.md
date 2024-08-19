# Documentação do Projeto Ticketmaster

## Contexto
O projeto Ticketmaster é uma solução abrangente projetada para o gerenciamento de tickets, incluindo categorias e subcategorias, níveis de severidade e integração com uma API externa. Tem como objetivo agilizar o processo de gerenciamento de tickets, desde a criação até a resolução, garantindo um acompanhamento e tratamento eficientes dos problemas.

## Pilhas, Tecnologias e Frameworks

### Backend
- **Java 17**: Linguagem de programação principal.
- **Spring Boot (3.3.2)**: Framework para criar aplicações Spring autônomas, de grau de produção.
- **Spring Data JPA**: Para persistência de dados e operações CRUD.
- **PostgreSQL**: Banco de dados primário para armazenar os dados da aplicação.
- **H2 Database**: Banco de dados em memória usado para fins de testes.
- **MapStruct**: Para mapeamento de objetos, simplifica a implementação de mapeamentos entre tipos de bean Java.
- **Lombok**: Ferramenta de biblioteca Java para minimizar/remover código boilerplate.
- **OpenFeign**: Para criação declarativa de cliente REST, usado para integração de API externa.
- **JUnit**: Para testes unitários.
- **Mockito**: Para mock de objetos em testes.
- **Docker**: Para contêinerização da aplicação e suas dependências.

### Frontend
- **React**: Biblioteca JavaScript para construção de interfaces de usuário.
- **Node.js**: Ambiente de execução JavaScript server-side.
- **npm**: Gerenciador de pacotes para Node.js.
- **Webpack**: Empacotador de módulos JavaScript.
- **Babel**: Transpilador JavaScript.

### Ferramentas e Outras Tecnologias
- **Maven**: Gerenciamento de dependências e ferramenta de construção.
- **Git**: Sistema de controle de versão.
- **IntelliJ IDEA**: Ambiente de Desenvolvimento Integrado (IDE) recomendado.
- **Postman**: Para testar e interagir com APIs REST.

## Instalação e Configuração

### Pré-requisitos
- Java 17
- Maven
- Docker e Docker Compose
- Git (para controle de versão)
- Node.js
- npm

### Passos

1. **Clonar o Repositório**
   ```bash
   git clone https://github.com/lstavares/ticketmaster.git
   cd ticketmaster

2. **Construir a Aplicação**
   ```bash
   mvn clean install
   
3. **Docker**
- Certifique-se de que o Docker e o Docker Compose estão instalados no seu sistema.
- Navegue até o diretório raiz do projeto.
- Construa e inicie os contêineres:
    ```bash
   docker compose up --build
  
- Isso configurará o banco de dados PostgreSQL e executará a aplicação, assim como o swagger e também o frontend da aplicação.

### Configuração do Banco de Dados
- O arquivo docker-compose.yml inclui a configuração para o banco de dados PostgreSQL.
- Scripts de inicialização no diretório init-scripts são automaticamente executados para criar o esquema do banco de dados e inserir dados iniciais.

## Executando a Aplicação
- Após construir e iniciar os contêineres usando o Docker Compose, as APIs da aplicação backend devem estar acessível em http://localhost:8080/ticketmaster.
- Use o Postman ou qualquer outra ferramenta de teste de API para interagir com os endpoints disponíveis.

## Executando o Frontend
- Após construir e iniciar os contêineres usando o Docker Compose, a aplicação frontend deve estar acessível em http://localhost:3000.
- O frontend pode ser acessado diretamente pelo navegador após a inicialização dos contêineres.
- Para atualizar as dependências do projeto, execute `npm install

## Testes
- Testes unitários e de integração podem ser executados com Maven:
    ```bash
   mvn test

## Integração com API Externa
- A aplicação integra-se com uma API externa de usuários para buscar detalhes do usuário. A URL para o serviço de usuários é configurada no arquivo application.yml sob a propriedade api.users.url.

## Documentação
- Embora este documento forneça uma visão geral básica e instruções de configuração, documentações adicionais sobre endpoints da API e uso podem ser geradas usando ferramentas como Swagger para uma melhor visualização e interação da API.
- Para visualizar a documentação, após a construção e iniciação dos componentes, acesse o link http://localhost:8081.
- Opcionalmente você pode usar o arquivo [users-api.postman_collection.json](users-api.postman_collection.json) para importar e acessar os endpoints da API através do Postman.

## Monitoração
A inclusão das seguintes métricas em um dashboard proporcionará uma visão abrangente do desempenho e saúde da aplicação:
- Métricas de Desempenho da Aplicação;
- Métricas de Experiência do Usuário;
- Métricas para Equipe de Suporte;
- Métricas de Infraestrutura

## Licença
- Este projeto está licenciado sob a licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.