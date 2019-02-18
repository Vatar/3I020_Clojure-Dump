;; # Exercice 3 : Fonctions "standard" sur les séquences

(ns theme02-sequences.ex03-seq-cheatsheet
  (:use midje.sweet))



;; ## Question 0

;;(letfn [(<_____________> [& more] 0)]

(fact "Je valide les tests suivants et donc je suis ... map"
      
        ;; (<_____________> second { :a 1, :b 2, :c 3}) => '(1 2 3)

        (map second { :a 1, :b 2, :c 3}) => '(1 2 3)

                
        ;; (<_____________> * #(* % 4) [1 2 3]) => [4 8 12])
        
        (map #(* % 4) [1 2 3]) => [4 8 12]) ;)

(defn mymap 
  "Retourne la séquence `((f e1) (f e2) ... à partir de 
  la séquence `s=(e1 e2 ...`."
  [f s]
  (if (seq s)
    (lazy-seq (cons (f (first s)) (mymap f (rest s))))
    s))

(fact "Je valide les tests suivants et donc je suis ... mymap"
      (mymap second { :a 1, :b 2, :c 3}) => '(1 2 3)
      (mymap #(* % 4) [1 2 3]) => [4 8 12])

;; ## Question 1

(letfn [(<_____________> [& more] 0)]

  (fact "Je valide les tests suivants et donc je suis ... ????????"

        
        (take 11 (<_____________> '(1 2 3))) => '(1 2 3 1 2 3 1 2 3 1 2)

        
        
        (take 6 (<_____________> [:a :b])) => '(:a :b :a :b :a :b)))




;; ## Question 2

(letfn [(<_____________> [& more] 0)]

  (fact "Je valide les tests suivants et donc je suis ... ????????"

        
        (<_____________> [:a :b :c] '(1 2 3 4)) => '(:a 1 :b 2 :c 3)

        

        (<_____________> (range) ["zéro" "un" "deux" "trois" "quatre"]) 

        => '(0 "zéro" 1 "un" 2 "deux" 3 "trois" 4 "quatre")))




;; ## Question 3

(letfn [(<_____________> [& more] 0)]
  (fact "Je valide les tests suivants et donc je suis ... ????????"

        
        (<_____________> even? [2 4 6 8 9 10 11 12]) => '(9 10 11 12)

        
        (<_____________> (fn [[a b]] (>= a b)) 
                         '([4 2] [5 1] [3 3] [1 2] [2 3] [3 4]))

        => '([1 2] [2 3] [3 4])))




;; ## Question 4

(letfn [(<_____________> [& more] 0)]
  
  (fact "Je valide les tests suivants et donc je suis ... ????????"

        
        (<_____________> [2 3 3 4 5 5 5 6 5 5 3 2 3 1 1]) 

        => '(2 3 4 5 6 5 3 2 3 1)

        
        
        (<_____________> '(:a :b :c :d :e :e :e :e)) => '(:a :b :c :d :e)))




;; ## Question 5

(letfn [(<_____________> [& more] 0)]
  
  (fact "Je valide les tests suivants et donc je suis ... ????????"

        
        (<_____________> :et [1 2 3 4]) => '(1 :et 2 :et 3 :et 4)

        
        (<_____________> 0 '(:a :b :c :d :e)) => '(:a 0 :b 0 :c 0 :d 0 :e)))




;; ## Question 6

(letfn [(<_____________> [& more] 0)]
  
  (fact "Je valide les tests suivants et donc je suis ... ????????"

        
        
        (<_____________> odd? [1 1 1 2 2 3 3]) => '((1 1 1) (2 2) (3 3))
      
        
        
        (<_____________> odd? [1 3 5 2 4 5 3]) => '((1 3 5) (2 4) (5 3))
        
        
        
        (<_____________> #(= % :c) [:a :b :c :d :e :c :c :f :g]) 
        
        
        => '((:a :b) (:c) (:d :e) (:c :c) (:f :g))))
      







