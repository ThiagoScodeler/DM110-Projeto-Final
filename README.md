# Trabalho Final Disciplina DM110 Inatel- Desenvolvimento Java EE

## Propósito

Criar uma aplicação capaz de fazer a varredura de uma rede de computadores para identificar os equipamentos que estão ativos, dada uma faixa de IP (endereço de rede/máscara CIDR).

### Observação

Para ativar o suporte a JMS no WildFly, verifique o Guia de Configuração do JMS publicado neste repositório.

### Informações do ambiente:

* Nome do Banco de Dados: `dm110_projeto`
* Script do Banco de Dados: verifique o [Script do BD](database/db.sql) publicado neste repositório.
* Nome JNDI dos DataSources: `java:/ProjetoFinalDS`
* Nomes JNDI das filas ou tópicos JMS: `java:/jms/queue/EquipamentoQueue`

## Interface de serviço

### Comando de início de varredura:

* Tipo da requisição: GET
* URL: `<context-root>/api/poller/start/{IP}/{Mask}`
* Funcionamento:
  * Calcula todos os endereços da rede (desconsiderando o endereço de rede e de broadcast).
  * Cria mensagens JMS sendo que cada mensagem deverá conter uma lista de no máximo 10 endereços IP.
  * Insere mensagens na fila.
  * Deverá existir um MDB consumindo mensagens desta fila e fazendo a verificação do status do equipamento referente ao endereço IP.
  * Para cada endereço verificado, deverá salvar uma linha em uma tabela na base de dados contendo, pelo menos, o endereço IP verificado e o status (Ativo ou Inativo).

**- URL Parâmetros (Mandatório):**
```sh
IP
Mask
```

**- URL Exemplo:**
```sh
http://localhost:8080/projeto-final/api/poller/start/192.168.15.3/24
```

**- Successo Resposta:**

**Code:** 204 No Content

**- Requisição:**
```sh
curl -X GET \
  http://localhost:8080/projeto-final/api/poller/start/192.168.15.3/24
```

### Verificação do status do equipamento

* Tipo de requisição: GET
* URL: `<context-root>/api/poller/status/{IP}`
* Funcionamento: Busca o equipamento na base de dados e retorna o status.

**- URL Parâmetros (Mandatório):**
```sh
IP
```

**- URL Exemplo:**
```sh
http://localhost:8080/projeto-final/api/poller/status/192.168.15.125
```

**- Successo Resposta:**

**Code:** 200 OK

**Conteúdo Resposta:**
```sh
{
    "enderecoIP": "192.168.15.125",
    "status": "Inativo"
}
```

**- Requisição:**
```sh
curl -X GET \
  http://localhost:8080/projeto-final/api/poller/status/192.168.15.125
```