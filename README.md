Treinamento Web Services RESTful e Spring

Aula 2.1 - Criando o projeto
- Criação do projeto: https://start.spring.io/

Aula 2.2 - Modelando nosso primeiro recurso
Resource = Controller
- Criamos uma classe LivrosResources e anotamos com @RestController 
- Criamos um método chamado listar que retorna uma String (“Livro 1, Livro 2”)
- Mapeamos esse método com @RequestMapping(value=”/livros”, method=RequestMethod.GET).

Aula 2.3 - Criando uma representação para o recurso Livro
- Criamos uma classe chamada Livro e uma classe chamada Comentario que é um dos atributos de Livro.
- Alteramos o método listar da classe LivrosResources fazendo com que o seu retorno agora seja List<Livro> e alteramos a implementação para retornar uma lista com dois livro com o nome preenchido.

