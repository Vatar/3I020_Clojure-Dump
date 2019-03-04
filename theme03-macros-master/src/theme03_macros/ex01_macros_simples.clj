;; # Exercice 1 : Macros simples

(ns theme03-macros.ex01-macros-simples
  (:use midje.sweet))



;; ## Question 1

;; à modifier
(defmacro unless [& _] nil)


(fact "`unless` s'évalue selon le principe annoncé."

      (unless (zero? (* 2 21))
          :ok) => :ok

      (unless (zero? 0)
          :ok) => nil

      (unless (zero? 42)
              (* 2 3) ;; expression "perdue"
              (+ 4 2)) => 6)

;; ## Question 2

;; à modifier
(defmacro show [_] nil)


(fact "`show` évalue l'expression."
      (show 42) => 42
      (show (* 21 2) => 42)
      (show (reduce + 0 (range 5))) => 10)

(fact "`show` affiche les bonnes informations."

      (with-out-str 
        (show 42)) => "42 => 42\n"

      (with-out-str 
        (show (* 21 2))) => "(* 21 2) => 42\n"
      
      (with-out-str
        (show (reduce + 0 (range 5))))
        
      => "(reduce + 0 (range 5)) => 10\n")

;; ## Question 3

(when-let [n (get {:a 1 :b 2 :c 3} :a)]
  (println "J'ai trouvé: n=" n)
  (* n 2))

;; J'ai trouvé: n= 1
;;
;; => 2

(when-let [n (get {:a 1 :b 2 :c 3} :d)]
  (println "J'ai trouvé: n=" n)
  (* n 2))

;; => nil

;; à compléter
(defmacro my-when-let [& _] nil)


(fact "`my-when-let` fonctionne comme `when-let`."
      
      (my-when-let [n (get {:a 1 :b 2 :c 3} :a)]
                   (println "J'ai trouvé: n=" n)
                   (* n 2)) 
      => (when-let [n (get {:a 1 :b 2 :c 3} :a)]
           (println "J'ai trouvé: n=" n)
           (* n 2))
      
      (my-when-let [n (get {:a 1 :b 2 :c 3} :d)]
                   (println "J'ai trouvé: n=" n)
                   (* n 2)) 
      => (when-let [n (get {:a 1 :b 2 :c 3} :d)]
           (println "J'ai trouvé: n=" n)
           (* n 2)))



