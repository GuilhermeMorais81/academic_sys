## Academic_sys

Um sistema academico que permite adicionar alunos, cursos e suas disciplinas, além de criar matriculas.
Totalmente escrito na linguagem Java.

Para o GUI, foram utilizados os metodos da classe javax.swing.JOptionPane. É recomendado que você não utilize os botões cancel das caixas de input, uma vez que os resultados dele não são tratados. Isso ocorre porque esse projeto é focado em back-end, no entanto, o front-end pode ser aprimorado com tecnologias mais robustas em futuras atualizações.

- Para usar o sistema:
Antes de executá-lo, é necessario definir os campos da classe ConnectionFactory, umas vez que são essenciais para a montagem da connectionString. Para isso, basta criar um arquivo chamado config.properties no diretorio raiz (fora do src/) e adicionar os elementos dessa forma:

```config.properties
        USER=<usuario>
        PASSWORD=<senha>
        HOST=<host>
        PORT=<port>
        DATABASE=<nome do banco de dados>
````
- dependencias:
A lombok e o Driver do postgreSQL foram utilizados nesse projeto. Essas dependencias podem ser adquiridas nesses links:
```
https://projectlombok.org/download
https://jdbc.postgresql.org/
````
Crie uma pasta _lib_ no diretorio raiz e inclua os arquivos _.jar_ baixados.

Em seguida, você pode utilizar o VScode com a _Extension Pack for Java_ e executar o sistema.



