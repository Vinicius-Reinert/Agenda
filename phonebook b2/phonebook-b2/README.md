
#PT-BR
# Agenda

essa compilação requer o maven para ser executada. se você não tem o intellij, eu sugiro realizar o download (é gratis e o maven já possui). 

Instruções de instalação 
1. Clone o repo chamado "phonebook-b2.zip"
2. Inicie o servidor local com "mvn spring-boot:run"
3. Abra o navegador e vá para localhost:8080 
4. Use os formulários e botões para interagir com o banco de dados.
5. recomendamos também o download do POSTMAN (https://www.getpostman.com/) para fazer chamadas de API diretamente para o serviço em vez de usar o HTML frontend mas o mesmo não é necessario para executar o projeto.


Detalhes do código-fonte: 
* o pacote de domínio tem todas as classes que especificam o mapeamento Java POJO para a tabela de banco de dados 
* o pacote DAO tem todas as classes DAO que manipulam as interações do banco de dados.
* O pacote de serviço tem todas as classes que lidam com a lógica de negócios (como a validação).
* O pacote springboot tem todas as classes que expõem pontos de extremidade REST para manipular os dados 
* Hibernate.cfg.xml é o arquivo de configuração do Hibernate. Eu configurei para usar h2database como teste que é um no banco de dados de memória, mas se quisermos mudar para outra coisa é só fazer as alterações direto no arquivo.



#EN-US
# Phonebook 
This requires maven to run. If you don't have intellij, I would suggest downloading it (it's free and has maven built in). 

Installation Instructions
1. Clone repo named "phonebook-b2.zip"
2. Start local server with "mvn spring-boot:run"
3. Open browser and go to localhost:8080
4. Use the forms and buttons to interact with the database.
5. I would recommend downloading POSTMAN (https://www.getpostman.com/) to make direct API calls to the service instead of using the html frontend.


Source code details:
* The domain package has all of the classes that specify the mapping from Java POJO to Database table
* The dao package has all of the dao classes which handle the database interactions.
* The service package has all of the classes that handle business logic (like the validation).
* The springboot package has all the classes which expose REST endpoints to manipulate the data
* Hibernate.cfg.xml is the hibernate configuration file. I have set it up to use h2database, an in memory database but if you want to change to something else, you would make that change here.




by Vinicius Sipoli Reinert.