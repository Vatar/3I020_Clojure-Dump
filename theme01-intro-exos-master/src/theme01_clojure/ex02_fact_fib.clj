;; # Exercice 2 : Factorielle, Fib et récursion terminale

(ns theme01-clojure.ex02-fact-fib
  (:use midje.sweet))



;; ## Question 1

(declare factorialrec)


(factorialrec 0)


(fact "`factorialrec` calcule bien la factorielle."
      (factorialrec 0) => 1
      (factorialrec 1) => 1
      (factorialrec 5) => 120
      (factorialrec 6) => 720)

(fact "`factorialrec` consomme beaucoup (trop) de pile."
      (try
        (factorialrec 9000N)
        :ok-pas-de-probleme  ;; on arrive ici sans problème ?
        (catch java.lang.StackOverflowError e :plus-de-pile))
      => :plus-de-pile)


(defn factorialrec 
  "calcule n!"
  [n]
  (if (zero? n)
    1
    (* n(factorialrec(dec n)))))


;; ## Question 2

(declare factorial-term)


(defn factorial-term
  "calcule n! de manière terminale"
  [n]
  (loop
    [k n,res 1]
    (if (zero? k)
      res
      
      (recur (dec k)(* res k)))))
    

(fact "`factorial` calcule bien la factorielle."
      (factorial-term 0) => 1
      (factorial-term 1) => 1
      (factorial-term 5) => 120
      (factorial-term 6) => 720)

(fact "`factorial` ne consomme pas (trop) de pile."
      (try
        (factorial-term 9000N)
        :ok-pas-de-probleme  ;; on arrive ici sans problème ?
        (catch java.lang.StackOverflowError _ :plus-de-pile))
      => :ok-pas-de-probleme)


;; ## Question 3

(declare fiborec)

(defn fiborec
  "calcule fibonacci récursivement"
  [n]
 (if (= n 0) 0
  (if(= n 1) 1
   (+ (fiborec(dec n)) (fiborec (- n 2))))))
    
   



(fiborec 3)
  



(fact "Les premiers termes de `fibo-rec` sont justes,
      (d'après Wikipedia."
      (for [k (range 11)]
        (fiborec k)) => '(0 1 1 2 3 5 8 13 21 34 55))

(fact "`fibo-rec` consomme énormément de pile."
      (try (fiborec 100000N)
        (catch java.lang.StackOverflowError _ :plus-de-pile))
      => :plus-de-pile)

(fact "`fiborec` est super lente."
      (>= (Integer/parseInt
           (second
            (re-find #"(\d+)\." (with-out-str (time (fiborec 35))))))
          10)
      => true)

;; ## Question 4

(declare fibo)

(defn fibo
  "calcule fibonacci résursivement terminale"
  [n]
  (loop[fib1 0,fib2 1,i n])
  (if (= res 0) 
    fib1 
    (recur fib2 (+ fib1 fib2) (dec i))))

    
  

(fibo 3)
  


(fact "Les premiers termes de `fibo` sont justes,
      (d'après Wikipedia."
      (for [k (range 11)]
        (fibo k)) => '(0 1 1 2 3 5 8 13 21 34 55))

(fact "`fibo` ne consomme pas de pile."
      (try (count (str (fibo 100000N)))
        (catch java.lang.StackOverflowError _ :plus-de-pile))
      => 20899)

(fact "`fibo` est super rapide."
      (<= (Integer/parseInt
           (second (re-find #"(\d+)\."
                            (with-out-str (time (fibo 200000))))))
          2000)
      => true)



