## Academic_sys

Um sistema academico que permite adicionar alunos, cursos e suas disciplinas, além de criar matriculas.
Totalmente escrito na linguagem Java.
---
Para o GUI, foram utilizados os metodos da classe javax.swing.JOptionPane. É recomendado que você não utilize os botões cancel das caixas de input, uma vez que os resultados dele não são tratados. Isso ocorre porque esse projeto é focado em back-end, no entanto, o front-end pode ser aprimorado com tecnologias mais robustas em futuras atualizações.

- Para usar o sistema:
Antes de executá-lo, é necessario definir os campos da classe ConnectionFactory, umas vez que são essenciais para a montagem da connectionString. Para isso, basta criar um arquivo chamado config.properties na raiz do diretorio (fora do src/) e adicionar os elementos dessa forma:

```config.properties
        USER=<usuario>
        PASSWORD=<senha>
        HOST=<host>
        PORT=<port>
        DATABASE=<nome do banco de dados>

Em seguida, você pode utilizar o VScode com a _Extension Pack for Java_ e executar o sistema