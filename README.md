![PicPay](https://user-images.githubusercontent.com/1765696/26998603-711fcf30-4d5c-11e7-9281-0d9eb20337ad.png)

# Desafio de Search

Primeiramente, obrigado pelo seu interesse em trabalhar na melhor plataforma de pagamentos do mundo! Abaixo você encontrará todas as informações necessárias para iniciar o seu teste.

## Avisos antes de começar

* Crie um repositório na sua conta do **GitHub** sem citar nada relacionado ao PicPay;
* Faça seus commits no seu repositório;
* Solicite ao **Recruter** que está acompanhando o seu processo o username do Github do avaliador, assim você poderá dar permissão de leitura no código; 
* Fique à vontade para perguntar qualquer dúvida aos recrutadores.
* Fique tranquilo, respire, assim como você, também já passamos por essa etapa. Boa sorte! :)


## Descrição

O desafio é criar uma API REST que busca usuários pelo nome e username a partir de uma palavra chave. Faça o download do arquivo [users.csv.gz](https://s3.amazonaws.com/careers-picpay/users.csv.gz) que contém o banco de dados que deve ser usado na busca. Ele contém os IDs, nomes e usernames dos usuários.

###### Exemplo
| ID                                   | Nome              | Username             |
|--------------------------------------|-------------------|----------------------|
| 065d8403-8a8f-484d-b602-9138ff7dedcf | Wadson marcia     | wadson.marcia        |
| 5761be9e-3e27-4be8-87bc-5455db08408  | Kylton Saura      | kylton.saura         |
| ef735189-105d-4784-8e2d-c8abb07e72d3 | Edmundo Cassemiro | edmundo.cassemiro    |
| aaa40f4e-da26-42ee-b707-cb81e00610d5 | Raimundira M      | raimundiram          |
| 51ba0961-8d5b-47be-bcb4-54633a567a99 | Pricila Kilder    | pricilakilderitaliani|



Também são fornecidas duas listas de usuários que devem ser utilizadas para priorizar os resultados da busca. A lista 1 tem mais prioridade que a lista 2. Ou seja, se dois usuarios casam com os critérios de busca, aquele que está na lista 1 deverá ser exibido primeiro em relação àquele que está na lista 2. Os que não estão em nenhuma das listas são exibidos em seguida.

As listas podem ser encontradas na raiz deste repositório ([lista_relevancia_1.txt](lista_relevancia_1.txt) e [lista_relevancia_2.txt](lista_relevancia_2.txt)).
Os resultados devem ser retornados paginados de 15 em 15 registros.

### Interface REST

Siga o formato abaixo para definir o endpoint.

#### GET `/search?query={term}`

Obtém todos os usuários em um formato `.json` que combinam com o termo pesquisado, de acordo com a regra de relevância das listas informada anteriormente.

| Parâmetro  | Descrição                   |
|------------|-----------------------------|
| query      | Termo a ser procurado.                                                                                           |
| from       | Posição da página. 0 é a primeira, 1 é a segunda e assim por diante.  Caso não informado, o valor default será 0.|
| size       | Quantidade de itens a ser retornado.  Caso não informado, o valor default será 15.                               |


Exemplo de uso: 

`$ curl http://localhost:8080/search?query=dragon`

Retorno:
```json
    {
        "from": 0,
        "size": 2,
        "data": [
            {
                "id": "aaaaaaaa-bbbb-cccc-dddd-111111111111",
                "name": "Dragon Ball",
                "username": "dragon.ball"
            },
            {
                "id": "xxxxxxxx-yyyy-cccc-aaaa-zzzzzzzzzzzz",
                "name": "Super Dragon",
                "username": "super.dragon"
            }
        ]
    }

```

## Premissas

### Escala estimada

* ~2M RPM
* ~10M de itens pesquisáveis

### Facilidade de uso

Simplicidade na configuração e execução da aplicação, conforme exemplo:

```
git clone meu_repositorio.git
cd meu_repositorio
make setup
make run
```

Fique livre para sugerir outras abordagens ;)

## O que será avaliado e valorizamos

* Documentação (usar o próprio README.md do projeto);
* Código limpo e organizado;
* Conhecimento de padrões de projeto;
* Aplicação rodar em um container **docker**;
* As linguagem e framework são livres, mas tenha em mente as premissas da solução;
* Respeitar as interfaces REST definidas;
* Teste unitários.


### Extras
* Proposta de melhoria da solução;
* Autorização ao invocar a API;
* Teste de integração;
* Usar alguma solução open-source que facilite a pesquisa por relevância;
* Seja Cloud native.
