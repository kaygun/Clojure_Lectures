;; gorilla-repl.fileformat = 1

;; **
;;; # Clojure Lectures (1)
;;; 
;;; This is going to be a short series of talks introducing [clojure](http://clojure.org), and in general a short introduction to the family of languages called [lisp](http://en.wikipedia.org/wiki/Lisp_%28programming_language%29). 
;;; 
;;; I am going to do these lectures in part on a notebook designed to make clojure presentations called [gorilla-REPL](http://gorilla-repl.org/).
;;; 
;;; ## What is clojure?
;;; 
;;; Clojure is a language hosted on the JVM platform. This means, clojure code compiles down to java bytecode and runs on top of any java virtual machine. Plus, you can access all java libraries from within clojure. Think of it as java library and application ecosystem without the language.
;;; 
;;; As a language, clojure is from the lisp family. This means, it has a syntax that you are not going to be familar if your coding history includes only java, C++ or even python. For example:
;;; 
;; **

;; @@
(ns billowing-dove
  (:require [gorilla-plot.core :as plot]))

(+ 1 2 3)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>6</span>","value":"6"}
;; <=

;; **
;;; Use `Shift+Enter` to evaluate the box above.
;;; 
;;; Every expression in clojure is between two bracket signs. Hence, there is no need for `;` signs to indicate the end of an expression like in java and C++ or a newline like in python. The beginning of an expression is *always* a function. Here is an example in which I am going to use the random number generator from java from within clojure:
;;; 
;; **

;; @@
(def rng (java.util.Random. 42))
(.nextInt rng)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>-1170105035</span>","value":"-1170105035"}
;; <=

;; **
;;; I defined a variable called `rng` which is a random number generator created using a java library, and then I called it to generate a random integer. Before we go deeper into clojure:
;;; 
;;; ## What are we going to need?
;;; 
;;; We are going to need 3 pieces of software:
;;; 
;;; 1. clojure
;;; 2. leiningen
;;; 3. gorilla-repl
;;; 
;;; However, items 2 and 3 would be enough as you will see
;;; 
;;; ## Leiningen
;;; 
;;; [Leiningen](http://leiningen.org/) is a clojure scaffolding tool. It automates creation of a project, it downloads all necessary java and clojure libraries (including the clojure jar file itself) needed to run your application. 
;;; 
;;; Go to [leiningen](http://leiningen.org/) project page, download it (you only need one file) and put in where you keep your system-wide or userspace executable files. Don't run it just yet.
;;; 
;;; ## Gorilla-REPL
;;; 
;;; Go to [github](http://github.com) and find [gorilla-REPL](https://github.com/JonyEpsilon/gorilla-repl)'s project page. Clone it into your user directory. In other words, run
;;; 
;;;      
;;;           git clone https://github.com/JonyEpsilon/gorilla-repl.git
;;;     
;;; in where ever you keep your cloned github repositories. Once it is done, `cd` into it and run
;;; 
;;;           lein run
;;;           
;;; follow the prompt. You'll see something like
;;; 
;;; 
;;;           Gorilla-REPL: develop
;;;           Started nREPL server on port 42290
;;;           Running at http://127.0.0.1:8990/worksheet.html .
;;;           Ctrl+C to exit.
;;;           
;;; the actual port number will be different in your case. Point your browser to that URL, and done. You are ready to play with clojure interactively under gorilla-repl.
;;; 
;;; So, first order of business: download this lecture, and run it. To load a notebook, you are going to need `ctrl-g ctrl-l` and to save a notebook you are going to need `ctrl-g ctrl-s`.
;;; 
;;; ## A first program
;;; 
;;; Let us write a simple clojure function to calculate the greatest common divisor of two integers:
;; **

;; @@
(defn gcd [a b]
  (cond
    (< a b) (gcd b a)
    (< b 0) (gcd a (Math/abs b))
    (= b 0) a
    (= b 1) 1
    :else   (gcd b (mod a b))))

(gcd -129123 12312)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>9</span>","value":"9"}
;; <=
