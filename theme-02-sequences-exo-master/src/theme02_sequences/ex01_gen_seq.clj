;; # Exercice 1 : Génération de séquences

(ns theme02-sequences.ex01-gen-seq
  (:use midje.sweet))



;; ## Question 1

(declare const-seq)


(fact "`const-seq` est la bonne séquence."
      (take 4 (const-seq 1)) => '(1 1 1 1)
      (take 3 (drop 1000 (const-seq :bip)))
      => '(:bip :bip :bip)
      (reduce + (take 1000 (const-seq 1)))
      => 1000)

;; ## Question 2

(declare ints)


(fact "`ints` génère la bonne séquence."
      (take 5 (ints 1)) => '(1 2 3 4 5)
      (first (drop 99 (ints 1))) => 100)

(fact "la factorielle peut être calculée de façon assez originale."
      (reduce * (take 6 (ints 1))) => 720)

;; ## Question 3

(declare fibo-seq)


(fact "Les premiers termes de `fibo-seq` sont justes,
      (d'après Wikipedia."
      (take 11 (fibo-seq)) => '(0 1 1 2 3 5 8 13 21 34 55))

(fact "le 100-ème fibonacci est..."
      (first (drop 100 (fibo-seq))) => 354224848179261915075N)

;; ## Question 4

(declare primes)


(fact "Les premiers termes de `primes` sont justes."
      (take 10 (primes)) => '(2 3 5 7 11 13 17 19 23 29))

(fact "Le 1000-ème nombre premier est ?"
      (first (drop 1000 (primes))) => 7927)

;; ## Question 5

(declare const-seq')


(fact "`const-seq'` retourne la bonne séquence."
      (take 4 (const-seq' 1)) => '(1 1 1 1)
      (take 3 (drop 1000 (const-seq' :bip)))
      => '(:bip :bip :bip)
      (reduce + (take 1000 (const-seq' 1)))
      => 1000)

(declare ints')


(fact "`ints'` génère la bonne séquence."
      (take 5 (ints' 1)) => '(1 2 3 4 5)
      (first (drop 99 (ints' 1))) => 100)

;; ## Question 6


(fact "`rand-seq` retourne bien des entiers dans les bornes fixées."
      (every? #(and (<= 3 %)
                    (< % 9)) (take 10000 (rand-seq 3 9))) => true)


