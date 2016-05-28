Treinamento Web Services RESTful e Spring

Cada aula é representada por um commit que está identificado com o nome/número da aula.

Aula 2.1 - Criando o projeto
- Criação do projeto: https://start.spring.io/

Aula 2.2 - Modelando nosso primeiro recurso
- Criamos uma classe LivrosResources e anotamos com @RestController 
- Criamos um método chamado listar que retorna uma String (“Livro 1, Livro 2”)
- Mapeamos esse método com @RequestMapping(value=”/livros”, method=RequestMethod.GET).

Aula 2.3 - Criando uma representação para o recurso Livro
- Criamos uma classe chamada Livro e uma classe chamada Comentario que é um dos atributos de Livro.
- Alteramos o método listar da classe LivrosResources fazendo com que o seu retorno agora seja "List<Livro>" e alteramos a implementação para retornar uma lista com dois livros com o nome preenchido.

Aula 2.4 - Utilizando a anotação @JsonInclude
- Alteramos a classe Livro inserido acima de cada atributo a anotação @JsonInclude(Include.NON_NULL) para suprimir o campo do JSON retornado caso o valor seja nulo
