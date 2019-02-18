;; # Exercice 2 : Combinateurs map, filter et reduce

(ns theme02-sequences.ex02-map-filt-red
  (:use midje.sweet))



;; ## Question 1

(declare mymap)


(fact "`mymap` fonctionne comme `map`."
      (map inc (range 0 10)) => '(1 2 3 4 5 6 7 8 9 10)
      (mymap inc (range 0 10)) => '(1 2 3 4 5 6 7 8 9 10))

(fact "`mymap` préserve la caractère paresseux"
      (first (drop 1000000 (mymap inc (range)))) => 1000001)

;; ## Question 2

(declare myfilter)


(fact "`myfilter` fonctionne comme `filter`."
      (filter #(= (rem % 2) 0) (range 1 10)) => '(2 4 6 8)
      (myfilter #(= (rem % 2) 0) (range 1 10)) => '(2 4 6 8))

(fact "`myfilter` préserve la caractère paresseux."
      (first (drop 1000000 (myfilter #(= (rem % 2) 0) (range)))) => 2000000)

;; ## Question 3

(declare myreduce)


(fact "`myreduce` fonctionne comme `reduce`."
      (reduce + 0 (range 11)) => 55
      (myreduce + 0 (range 11)) => 55
      (reduce (fn [acc e]
                (if (> e acc)
                  e
                  acc)) 0 '(1 3 7 3 2 9 1 2 5)) => 9
      (myreduce (fn [acc e]
                  (if (> e acc)
                    e
                    acc)) 0 '(1 3 7 3 2 9 1 2 5)) => 9)

;; ## Question 4

(letfn [(>_________________< [& more] 0)]
  (facts "A propos de map, reduce et filter."
         (>_________________< (range 1 11)) => '(-2 -4 -6 -8 -10)
         (>_________________< '(10 20 30 40 50 60)) => 720
         (>_________________< { :a 1 :b 22 :c 31 :d 14 :e 1}) => 31))
         




