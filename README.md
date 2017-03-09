Clojure Lectures
----------------

What are we going to need?
==========================

We are going to need 3 pieces of software:

* clojure
* leiningen
* gorilla-repl

However, leiningen is enough as you will see:

### Leiningen

[Leiningen][1] is a clojure scaffolding tool. It automates creation of a project, it downloads all necessary java and clojure libraries (including the clojure jar file itself) needed to run your application.

Go to [leiningen project page][1], download it (you only need one file) and put in where you keep your system-wide or userspace executable files. Don't run it just yet.

### Gorilla-REPL

[Gorilla-REPL][2] is a notebook interface similar to [jupyter][4]. I prefer gorilla-repl because its installation is simpler than jupyter.  You don't have to do anything to run gorilla-repl. If you already
cloned this git repository, then just run

      lein gorilla

in your local directory where you cloned this, and follow the prompt. You'll see something like

      Gorilla-REPL: develop
      Started nREPL server on port 42290
      Running at http://127.0.0.1:8990/worksheet.html .
      Ctrl+C to exit.

the actual port number will be different in your case. Point your browser to that URL, and done. You ar e ready to play with clojure interactively under gorilla-repl.

Now, save the following gorilla-REPL notebooks. Then point your browsers to 

      http://127.0.0.1:XXXX/worksheet.html?filename=Lecture1.clj 

for the first lecture. You need to rewrite XXXX as the port number from gorilla-REPL.

### Gorilla-REPL Online View

You can also view these lectures online:

* [Lecture 1][8]
* [Lecture 2][6]
* [Lecture 3][7]

You can publish your own clojure/gorilla notebooks online too. For details check out [Gorilla-REPL][5]'s site.

[1]: http://leiningen.org/
[2]: http://gorilla-repl.org/
[3]: http://github.com
[4]: http://jupyter.org/
[5]: http://gorilla-repl.org/viewer.html
[8]: http://viewer.gorilla-repl.org/view.html?source=github&user=kaygun&repo=Clojure_Lectures&path=Lecture1.clj
[6]: http://viewer.gorilla-repl.org/view.html?source=github&user=kaygun&repo=Clojure_Lectures&path=Lecture2.clj
[7]: http://viewer.gorilla-repl.org/view.html?source=github&user=kaygun&repo=Clojure_Lectures&path=Lecture3.clj
