# Desafio 1
Para a tarefa de incluir um novo produto no sistema de gestão de estoque, vocês serão responsáveis por desenvolver uma funcionalidade de back-end em uma aplicação Java Spring Boot. Essa funcionalidade deve permitir a inclusão de novos produtos no banco de dados através de uma API REST. O código fornecido acima é a base para a implementação dessa funcionalidade, e vocês deverão seguir os padrões e estruturas já estabelecidos.

### Descrição da Tarefa

1. **Objetivo Geral:** Desenvolver uma funcionalidade para incluir um novo produto no sistema, utilizando uma API REST.

2. **Entradas e Validacões:**
    - A funcionalidade deve receber como entrada os dados do produto a ser incluído através de um `POST` request no endpoint `/incluir`.
    - Os dados do produto incluem `codigo`, `descricao`, `estoqueMinimo`, `lucro`, `precoDeCompra`, `quantidade` e `precoDeVenda`.
    - Antes de incluir o produto, o sistema deve verificar se já existe um produto com o mesmo código no banco de dados. Caso exista, deve lançar uma exceção de `ProdutoJaExisteException`.
    - O sistema deve validar os dados de entrada, garantindo que nenhum campo obrigatório esteja vazio ou com valores inválidos (ex.: valores negativos para `codigo`, `estoqueMinimo`, `lucro`, `precoDeCompra`, `quantidade`, e `precoDeVenda`). Em caso de dados inválidos, deve lançar uma exceção de `DadosInvalidosException`.

3. **Processamento e Armazenamento:**
    - Após a validação, os dados do produto devem ser mapeados para um objeto `Produto` e salvos no banco de dados.
    - Utilize o `ProdutoRepository` para interagir com o banco de dados.

4. **Resposta:**
    - Se o produto for incluído com sucesso, a API deve retornar uma resposta com status `201 CREATED` e uma mensagem indicando sucesso.
    - Em caso de falha na inclusão do produto (por exemplo, se as validações falharem ou ocorrer um erro no servidor), a API deve retornar uma resposta com status `500 INTERNAL SERVER ERROR` e uma mensagem de erro adequada.

### Diretrizes Adicionais

- Siga as boas práticas de programação e padrões de codificação Java e Spring Boot.
- Garanta que o código seja limpo, bem organizado e documentado, facilitando a manutenção e compreensão.
- Desenvolva testes unitários para a funcionalidade, assegurando que todas as validações e o processo de inclusão do produto funcionem corretamente.

### Entrega

O código deverá ser desenvolvido tanto com o auxílio do GitHub Copilot quanto sem ele, permitindo uma comparação direta em termos de produtividade e qualidade do código produzido. Documente o processo de desenvolvimento, incluindo quaisquer desafios encontrados e como foram solucionados, e faça uma reflexão crítica sobre a experiência de usar o Copilot para esta tarefa.
