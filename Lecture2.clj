;; gorilla-repl.fileformat = 1

;; **
;;; # Clojure Lectures (2)
;;; 
;;; In the last lecture we dabbled a bit. Let us jump in:
;; **

;; @@
(ns fuscia-tsunami
  (:require [gorilla-plot.core :as plot]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; One of the concepts you must get used to with clojure is the idea of a "name space". If you did any C++ programming you already know what it is.  It is essentially a boxing mechanism for you to *not* pollute the global name space, or giving you the freedom to name your objects, functions and variables with any names you want without worrying too much about conflicts. This lecture's name space is `fuscia-tsunami`. I would like to take credit such a cool name, but gorilla-repl comes up with these cool names on its own.
;;; 
;;; ## Data structures
;;; 
;;; Clojure is a dynamically typed language, or you may even call it untyped because you don't need to declare their types explicitly.
;; **

;; @@
(def a 1.234567)
(def b 2)
(def c "this is a string")

(println a b c)
;; @@
;; ->
;;; 1.234567 2 this is a string
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; By the way, notice something in the boxes above. Everything in clojure, and in general in all lisps, returns something. If the expression, or the function has no return value (such as `println`) then it will return a `nil`.  
;;; 
;;; Now, you can go back and redefine these variables with different types of values:
;; **

;; @@
(def a 2)
(def b "this is a string")
(def c 1.234567)

(println a b c)
;; @@
;; ->
;;; 2 this is a string 1.234567
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; ### Container types
;;; 
;;; As expected, we have lists, vectors, maps and sets. More interestingly, we have literals in the language for us to write these objects within the language.
;; **

;; @@
(def a-list '(1 2 3 4 5))
(def a-vector [1 2 3 4 5])
(def a-map {:key1 2 :key2 1.23456 :key3 "this is a string"})
(def a-set #{10 20 30 40})
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-tsunami/a-set</span>","value":"#'fuscia-tsunami/a-set"}
;; <=

;; **
;;; I am not going to go into the semantics of each type, but the on the syntax level the first 3 (lists, vectors and maps) are ordered. Sets are not ordered. They all do have a `first` method and a `rest` method, but `a-set` will **not** return what you'd expect.
;; **

;; @@
(list (first a-list) (first a-vector) (first a-map))
(list (rest a-list) (rest a-vector) (rest a-map))
(list (first a-set) (rest a-set))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>20</span>","value":"20"},{"type":"html","content":"<span class='clj-unkown'>(40 30 10)</span>","value":"(40 30 10)"}],"value":"(20 (40 30 10))"}
;; <=

;; **
;;; The syntax of getting to a specific element in each container type is similar:
;; **

;; @@
   #{ (get a-vector 1) (get a-map :key2) }
    [ (nth a-vector 1) (nth a-list 2)    ]
(list (get a-set 10) (get a-set 3)       )
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>10</span>","value":"10"},{"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}],"value":"(10 nil)"}
;; <=

;; **
;;; In the case of sets, `get` checks if the argument is in the set, and will return it back if it really is.  Otherwise, it will return `nil` as expected. Also, notice that I can specify literally which containers the results can go.
;;; 
;;; Maps, however, are special. You can pass a key and ask for a return value. If the key is in the map you'll get the corresponding value.
;; **

;; @@
(list (a-map :key2) (a-map :key4))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>1.23456</span>","value":"1.23456"},{"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}],"value":"(1.23456 nil)"}
;; <=

;; **
;;; ### Growing and shrinking containers and the immutability
;;; 
;;; We can add and remove values to and from containers:
;; **

;; @@
(conj a-vector 0)
(conj a-list 0)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}],"value":"(0 1 2 3 4 5)"}
;; <=

;; **
;;; We can join two containers.
;;; 
;; **

;; @@
(concat [-1 -2 -3] a-vector)
(concat '(10 20 30) a-list)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>10</span>","value":"10"},{"type":"html","content":"<span class='clj-long'>20</span>","value":"20"},{"type":"html","content":"<span class='clj-long'>30</span>","value":"30"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}],"value":"(10 20 30 1 2 3 4 5)"}
;; <=

;; **
;;; Even though the syntax is similar between different types, you see that vectors and lists behave differently due to the way they are implemented. Here is the kicker: every variable, unless it is otherwise clearly declared, is *immutable*. That is you can't change their values.
;; **

;; @@
a-vector
a-list
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}],"value":"(1 2 3 4 5)"}
;; <=

;; **
;;; Such a restriction should make you cautious. Meaning, you can't just translate what you would normally do in C++ or java or even python to clojure. Say, if I wanted an array of `n` random integers from 0 to `m` in C++, I would write a function similar to this
;;; 
;;;       int *RandomIntegers(int n, int m, int seed) {
;;;            int i;
;;;            int *a = new int[n];
;;;            
;;;            srand(seed);
;;;            
;;;            for(i=0; i < n; i++)
;;;               a[i] = rand() % m;
;;;               
;;;            return(a);
;;;       }
;;;       
;;; In clojure, I would do:
;; **

;; @@
(defn RandomIntegers [n m seed]
  (let [rng     (java.util.Random. seed)
        get-rnd (fn [] (.nextInt rng m))]
    (repeatedly n get-rnd)))

(RandomIntegers 10 5 12345)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-unkown'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-unkown'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-unkown'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-unkown'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-unkown'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-unkown'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-unkown'>4</span>","value":"4"}],"value":"(1 0 1 3 0 4 0 2 1 4)"}
;; <=

;; **
;;; ### WHAAAAAA???!!
;;; 
;;; Let us take this apart piece by piece:
;;; 
;;; 1. `defn` is the keyword for defining a function
;;; 2. `RandomIntegers` is the name of the function
;;; 3. The vector `[n m seed]` following the name of the function is the ordered sequence of the names of the parameters passed to the function
;;; 4. The `(let [...] ...)` form forms the body of the function. You don't have to have a `let` form, but if you are going to need local variables, this is how you declare and initialize them. It works in pairs `(let [var1 val1 var2 val2 ...] ...)`
;;;     * The variable `rng` is initialized as a random number generator from `java.util.Random` class. Notice the dot at the end.
;;;     * The variable `get-rnd` is initialized to the *anonymous* function `(fn [] (.netInt rng m))` which accepts no input and returns a random integer in the range 0 to `m` using the random number generator `rng`.  The keyword `fn`  like `list` or `vector` creates a new object of the appropriate type, which in this case is a function.
;;; 5. `repeatedly` calls `get-rnd` function `n` times.
;;; 
;;; The object we created using the `fn` keyword is an anonymous function. Such a function is a function that we need as a parameter to another function that we don't need after it is used, and therefore, has no need to be named.
;;; 
;;; We can actually make the code I gave above a little shorter as in
;; **

;; @@
(defn RandomIntegers[n m seed]
  (let [rng (java.util.Random. seed)]
    (repeatedly n #(.nextInt rng m))))

(RandomIntegers 10 5 12345)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-unkown'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-unkown'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-unkown'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-unkown'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-unkown'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-unkown'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-unkown'>4</span>","value":"4"}],"value":"(1 0 1 3 0 4 0 2 1 4)"}
;; <=

;; **
;;; Recall that we can create lists in two equivalent ways: `(list 1 2 3 4)` and `'(1 2 3 4)`. The same way `#(+ (* %1 %1) %2)` is a convinient way of writing anonymous functions of several or no variables `(fn [x y] (+ (* x x) y))`.
;; **

;; **
;;; Now, let us go back and look at the function I defined at the end of the previous lecture:
;; **

;; @@
(defn gcd [a b]
  (cond
    (< a b) (gcd b a)
    (< b 0) (gcd a (Math/abs b))
    (= b 0) a
    (= b 1) 1
    :else   (gcd b (mod a b))))

(gcd 129123 1293)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}
;; <=
