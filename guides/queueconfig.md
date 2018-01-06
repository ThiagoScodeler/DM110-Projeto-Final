# Configuração do JMS

## Subindo o WildFly como standalone-full

Para criação de filas ou tópicos JMS, é necessário subir o WildFly no modo standoalone-full. Para isso, execute os procedimentos abaixo:

* Localize os arquivos `standalone.xml` e `standalone-full.xml` em: `${WildFlyHomeDir}/standalone/configuration`

* Copie os trechos delimtados pela tag `<datasources></datasources>` do arquivo `standalone.xml` para o arquivo `standalone-full.xml`, substituindo o trecho delimitado pela mesma tag no segundo arquivo.
  * Fazendo isso, você estará levando as configurações de DataSource e driver JDBC do primeiro arquivo para o segundo.

* Abra o prompt de comando na pasta `${WildFlyHomeDir}\bin` e execute o seguinte comando para executar o WildFly:
  * No Windows:
    ```
    standalone.bat -c standalone-full.xml
    ```
  * No Linux ou Mac:
    ```
    ./standalone.sh -c standalone-full.xml
    ```

## Configuração da fila no WildFly 10

* Acessar o administration console do WildFly

* Acessar: Configuration -> Subsystems -> Messaging - ActiveMQ

* Clicar em "default" e em "Queue/Topics"

* Escolher "Add" e inserir as configurações de nome e JNDI, por exemplo:
  * Name: `DM110Queue`
  * JNDI Names: `java:/jms/queue/dm110queue`
