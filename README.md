Clojure Lectures
----------------

What are we going to need?
==========================

We are going to need 3 pieces of software:

    clojure
    leiningen
    gorilla-repl

However, items 2 and 3 is enough as you will see:

### Leiningen

[Leiningen][1] is a clojure scaffolding tool. It automates creation of a project, it downloads all necessary java and clojure libraries (including the clojure jar file itself) ne eded to run your application.

Go to [leiningen project page][1], download it (you only need one file) and put in where you keep your system-wide or userspace executable files. Don't run it just yet.

### Gorilla-REPL

Go find [gorilla-REPL's project page][2] to read more. Clone it into your user directory from [github][3]. In short, run

      git clone https://github.com/JonyEpsilon/gorilla-repl.git

in where ever you keep your cloned github repositories. Once it is done, cd into it and ru n

      lein run

follow the prompt. You'll see something like

      Gorilla-REPL: develop
      Started nREPL server on port 42290
      Running at http://127.0.0.1:8990/worksheet.html .
      Ctrl+C to exit.

the actual port number will be different in your case. Point your browser to that URL, and done. You ar e ready to play with clojure interactively under gorilla-repl.

Now, save the following gorilla-REPL notebooks. Then point your browsers to http://127.0.0.1:XXXX/worksheet.html?filename=Lecture1.clj for th e first lecture. You need to rewrite XXXX as the port number from gorilla-REPL.

[1]: http://leiningen.org/
[2]: http://gorilla-repl.org/
[3]: http://github.com

