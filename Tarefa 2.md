# Tarefa 2
Para a tarefa de buscar estoques vencidos no sistema de gestão de estoque, vocês serão responsáveis por desenvolver uma funcionalidade de back-end em uma aplicação Java Spring Boot. Essa funcionalidade deve permitir a consulta de fornecedores com produtos em estoque que estão vencidos, através de uma API REST. O código fornecido acima serve como base para a implementação dessa funcionalidade, seguindo os padrões e estruturas já estabelecidos.

### Descrição da Tarefa

1. **Objetivo Geral:** Desenvolver uma funcionalidade que permita a consulta de fornecedores com produtos em estoque vencidos, utilizando uma API REST.

2. **Funcionamento e Estrutura:**
    - A funcionalidade deve ser acessada através de um `GET` request no endpoint `/estoque-vencido`.
    - Deve consultar o banco de dados para identificar produtos em estoque que estão vencidos e os fornecedores correspondentes.
    - Utilize o `EstoqueRepository` para realizar a consulta necessária.

3. **Processamento de Dados:**
    - A consulta deve retornar uma lista de objetos que contêm informações sobre os fornecedores e os produtos vencidos.
    - Para cada par de fornecedor e produto vencido, o sistema deve estruturar os dados de forma que incluam o CNPJ e o nome do fornecedor, bem como o código do produto, o estoque mínimo e a quantidade em estoque.

4. **Resposta da API:**
    - A API deve retornar uma lista dos fornecedores com produtos vencidos no estoque. Cada item da lista deve ser um mapa contendo as informações estruturadas do fornecedor e do produto.
    - Se não houver estoques vencidos, a API deve retornar uma lista vazia.
    - A resposta deve ser enviada com o status `200 OK`.
5. **Nome dos arquivos:**
    - O arquivo da classe de serviço deve ser nomeado `BuscarEstoquesVencidos.java`.
    - exemplo de saida esperada:
```json
[
  {
      "produto": {
        "id": null,
        "codigo": 103,
        "quantidade": 20,
        "estoqueMinimo": 8,
        "descricao": null,
        "precoDeCompra": null,
        "precoDeVenda": null,
        "lucro": null
      },
      "fornecedor": {
       "cnpj": "56.789.123/0001-11",
       "nome": "Fornecedor C"
      }
  }
]
```
### Diretrizes Adicionais

- Adote as boas práticas de programação e padrões de codificação Java e Spring Boot, assegurando a clareza, eficiência e manutenibilidade do código.
- Documente claramente o código, incluindo comentários explicativos quando necessário, para facilitar o entendimento da lógica implementada.

### Entrega

Você deve desenvolver essa funcionalidade tanto com o auxílio do GitHub Copilot quanto sem ele, para uma comparação direta de produtividade e qualidade. Documente seu processo de desenvolvimento, incluindo desafios encontrados e suas soluções, além de refletir sobre a experiência de utilizar o Copilot para este projeto.
