;; # Exercice 3 : macros de filetage

(ns theme03-macros.ex03-threading-macros
  (:use midje.sweet))


;; ## Question 1

(get (conj (conj [1 2 3 4] 5) 6) 3)

;; => 4

(-> [1 2 3 4]
    (conj 5)
    (conj 6)
    (get 3))

;; => 4

(fact "Exemple de filetage thread-first"
      
      (get (assoc (assoc {:a 1 :b 2} :c 3) :d 4) :c)
      
      ;; à compléter
      => (-> nil))

;; ## Question 2

(as-> [1 2 3 4] v
   (conj v 5)
   (conj v 6)
   (get v 3))

;; => 4

(let [v [1 2 3 4]
      v (conj v 5)
      v (conj v 6)
      v (get v 3)]
  v)

;; => 4


(fact "Exemple de filetage explicite."
      
      (get (assoc (assoc {:a 1 :b 2} :c 3) :d 4) :c)
      
      ;; à compléter
      (as-> nil m))



(fact "Exemple de let fileté."
      
      (get (assoc (assoc {:a 1 :b 2} :c 3) :d 4) :c)
      
      ;; à compléter
      => (let [m nil
               m nil]
           m))

;; ## Question 3

(reduce + 0 (filter even? (range 10)))

;; => 20

(->> (range 10)
     (filter even?)
     (reduce + 0))

;; => 20

(fact "Exemple de filetage thread-last"
      
      (reduce * 1 (filter #(< 10 % 30) (map #(* % %) (range 10))))
      
      ;; à compléter
      => (->> nil))

;; ## Question 4

(as-> (range 10) s
   (filter even? s)
   (reduce + 0 s))

;; => 20

(let [s (range 10)
      s (filter even? s)
      s (reduce + 0 s)]
  s)

;; => 20

(fact "Exemple de filetage thread-as"
      
      (reduce * 1 (filter #(< 10 % 30) (map #(* % %) (range 10))))
      
      ;; à compléter
      => (as-> nil s))


(fact "Exemple de filetage thread-as"
      
      (reduce * 1 (filter #(< 10 % 30) (map #(* % %) (range 10))))
      
      ;; à compléter
      => (let [s nil
               s nil]
           s))

;; ## Question 5

;; à compléter
(defmacro my-as-> [expr v & body] nil)

(fact "`my-as->` fonctionne comme `as->`."
      
      (my-as-> [1 2 3 4] v
          (conj v 5)
          (conj v 6)
          (get v 3))
      
      => (as-> [1 2 3 4] v
            (conj v 5)
            (conj v 6)
            (get v 3))
      
      (my-as-> (range 10) s
          (filter even? s)
          (reduce + 0 s))
      
      => (as-> (range 10) s
            (filter even? s)
            (reduce + 0 s)))

;; ## Question 6 (plus difficile)

;; à modifier
(defmacro my-> [expr & body] nil)

(fact "`my->` fonctionne comme `->`."
      
      (my-> [1 2 3 4]
         (conj 5)
         (conj 6)
         (get 3))
      
      => (-> [1 2 3 4]
            (conj 5)
            (conj 6)
            (get 3)))

;; ## Question 7 (plus difficile)

;; à modifier
(defmacro my->> [expr & body] nil)

(fact "`my->>` fonctionne comme `->>`."
      
      (my->> (range 10)
             (filter even?)
         	 (reduce + 0))
      
      => (->> (range 10)
              (filter even?)
              (reduce + 0)))


