;; # Exercice 4 : macros de comprehension

(ns theme03-macros.ex04-comprehensions
  (:use midje.sweet))



(for [x (range 13)
      :when (even? x)]
  (* x x))

;; => (0 4 16 36 64 100 144)


(for [i (range 1 5)
      j (range 1 5)
      :when (< i j)]
  [i, j])

;; => ([1 2] [1 3] [1 4] [2 3] [2 4] [3 4])

;; ## Question 1

(defn seq-bind [s f]
 (if (seq s)
    (lazy-cat (f (first s)) (seq-bind (rest s) f))
    s))

(defn seq-ret [x]
  (list x))


(seq-bind (range 13) (fn [n] (seq-ret (* n n))))

;; => (0 1 4 9 16 25 36 49 64 81 100 121 144)

;; à compléter
(defmacro myfor1 [[x expr1] expr2] nil)


(fact "La macro `myfor1` permet de faire des compréhensions simples."

      (myfor1 [n (range 6)] n) => '(0 1 2 3 4 5)
      
      (myfor1 [n (range 6)] (* n n)) => '(0 1 4 9 16 25))

;; ## Question 2

(defn seq-fail []
  ())


(seq-bind (range 13) (fn [x] (if (even? x)
                               (seq-ret (* x x))
                               (seq-fail))))

;; => (0 4 16 36 64 100 144)

;; à compléter
(defmacro myfor2 [[x expr1 wh cnd] expr2] nil)


(fact "La macro `myfor2` permet de faire des compréhensions conditionnelles simples."

      (myfor2 [n (range 6) 
               :when (odd? n)] 
         n) => '(1 3 5)
      
      (myfor2 [n (range 7)
               :when (even? n)] 
         (* n n)) => '(0 4 16 36))

;; ## Question 3

(seq-bind 
  (range 1 4)
  (fn [i] (seq-bind 
            [:a :b]
            (fn [j] (seq-ret [i, j])))))

;; => ([1 :a] [1 :b] [2 :a] [2 :b] [3 :a] [3 :b])

;; à compléter
(defn expand-myfor3 [& _] nil)


(defmacro myfor3 [xs expr] 
  (expand-myfor3 xs expr))

(fact "La macro `myfor3` permet les compréhensions multiples."
      
      (myfor3 [i (range 1 4)
               j [:a :b]]
          [i, j])
      
      
      => '([1 :a] [1 :b] [2 :a] [2 :b] [3 :a] [3 :b]))

;; ## Question 4 (plus difficile)

;; à compléter
(defn expand-myfor [& _] nil)


(defmacro myfor [xs expr] (expand-myfor xs expr))

(fact "La macro `myfor` fonctionne comme `for`."
      
      (myfor [i (range 1 5)
              j (range 1 5)
              :when (< i j)]
             [i, j])
      
      => (for [i (range 1 5)
               j (range 1 5)
               :when (< i j)]
           [i, j])
      

      (myfor [i (range 0 10)
              :when (even? i)
              j (range 0 10)
              :when (odd? j)
              :when (< i j)]
             [i, j])
      
      => (for [i (range 0 10)
               :when (even? i)
               j (range 0 10)
               :when (odd? j)
               :when (< i j)]
           [i, j]))



