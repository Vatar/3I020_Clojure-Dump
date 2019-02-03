;; # Exercice 4 : Collections

(ns theme01-clojure.ex04-collections
  (:use midje.sweet))


;; ## Question 1 : les vecteurs

(facts "pour l'accès aux informations des vecteurs"
  (and 
    
    (= (type [42 12 8]) clojure.lang.PersistentVector)
    
    (= [1 2 (+ 3 4)]  [1 2 7])

    (= (count [42 12 8]) 3)
    
    (= (get [42 12 8] 0) 42)
    
    (= (get [42 12 8] 2) 8)
    
    (= (get [42 12 8] 3) nil)
    
    (= (get [:a :b nil] 2) nil)
    
    (= (get [42 12 8] 3 :bad) :bad)
    
    (= (let [vs [42 12 8]]
         [(vs 0) (vs 2)]) [42 8])
    
   (= (try ([42 12 8] 3)
          (catch  java.lang.IndexOutOfBoundsException e :boum))
       
      :boum)
    
    
   (= (let [[a b] [1 2]]
        b) 
       
      2)
    
    
   (= (let [[x y z] [2 3 4]]
        (+ x (- y z)))
       
      1))

  => true)

(facts "pour les mises-à-jour des vecteurs"
       (and 
         
         (= (conj [3 2 1] 4) [3 2 1 4])
         
         (= (conj [3 2 1] 4 5 6) [3 2 1 4 5 6])
        
        
         (= (let [v [:a :b :c :d]
                  w (conj v :e)]
                [v w]) [[:a :b :c :d] [:a :b :c :d :e]])
       
       
         (= (assoc [3 2 1] 1 42) [3 42 1])

         (= (let [v [:a :b :c :d]
                  w (assoc v 2 :z)]
              [v w]) [[:a :b :c :d] [:a :b :z :d]]))

       =>  true)

;; ## Question 2 : les listes

(facts 
  (and
    
    (= (type '(1 2 3)) clojure.lang.PersistentList)
    
    (= '(1 2 (+ 3 4))  '(1 2 (+ 3 4)))
    
    (= (list 1 2 (+ 3 4)) '(1 2 7))
  
    (= (conj '(1 2 3) 4) '(4 1 2 3))
 
    (= (conj '(1 2 3) 4 5 6) '(6 5 4 1 2 3))
    
    (= (count '(:a :b :c :d)) 4)

    (= (first '(:a :b :c :d))  :a)
    
    (= (rest '(:a :b :c :d)) '(:b :c :d))
    
    (= (rest (rest '(:a :b :c :d))) '(:c :d))

    (= (second '(:a  :b :c :d))  :b)

    (= (first (rest '(:a  :b :c :d)))  :b)
    
    (= (peek '(:a :b :c :d)) :a)
    
    (= (pop '(:a :b :c :d)) '(:b :c :d))
    
    (= (let [stk1 '(1 2 3 4)
             stk2 (pop (pop stk1))]
         [stk1 stk2])
       
       ['(1 2 3 4) '(3 4)]))
    
  => true)

;; ## Question 3 : les maps (tables associatives)

(facts "pour l'accès aux informations des vecteurs"
  (and 
    
    (= (type {:a 42 :b 12 :c 8}) clojure.lang.PersistentArrayMap

       
       (= (count {:a 42 :b 12 :c 8}) 3)
       
       (= (get {:a 42 :b 12 :c 8} :b) 12)
       
       (= (get {:a 42 :b 12 :c 8} :e) nil)
       
       (= (get {:a 42 :b 12 :c 8} :e :not-found) :not-found)
       
       
       (= (let [m {:a 42 :b (+ (* 3 10) 12) :c 8}]
            
            (m :b)) 42)
       
       
       (= (let [m {:a 42 :b 12 :c 8}]
            
            (m :e)) nil)
       
       
       (= (let [m {"un" 1 "two" 2 "three" 3}]
            
            (m "two")) 2)
       
       
       (= (let [m {:a 42 :b (+ (* 3 10) 12) :c 8}]
            
            (:b m)) 42)
       
       
       (= (let [m {:a 42 :b (+ (* 3 10) 12) :c 8}]
            
            (:e m)) nil)
       
       
       (= (contains? {:a 42 :b 12 :c 9} :b) true)
       
       (= (contains? {:a 42 :b 12 :c 9} :e) false)
       
       
       (= (= {:a 42 :b 12 :c 3} 
             {:c (+ 2 1) :a (* 21 2) :b (quot 144 12)}) true)))
  
  
  => true)

(facts "pour les mises-à-jour des maps"
       (and 
         
         (= (assoc {:a 42 :b 12 :c 8} :e 19)
            
            {:a 42, :b 12, :c 8, :e 19})
         
         (= (assoc {:a 42 :b 12 :c 8} :b 42)
            
           {:a 42 :b 42 :c 8})
         
         (= (dissoc {:a 42 :b 12 :c 8} :b)
            
            {:a 42 :c 8})

         (= (dissoc {:a 42 :b 12 :c 8} :e)
                     
            {:a 42 :b 12 :c 8}))

       => true)

;; ## Question 4 : Les ensembles

(facts "pour les opérations de base des ensembles."
  (and
    
    (= (type #{13 24 3}) clojure.lang.PersistentHashSet)
    
    (= (count #{13 24 3}) 3)
    
   (= (conj #{13 24 3} 12) #{13 24 3 12})
    
   (= (conj #{13 24 3} 24) #{24 13 3})
    
   (= (contains? #{13 24 3} 24) true)
    
   (= (contains? #{13 24 3} 12) false)

   (= (= (conj #{13 24 3} 12) #{3 12 13 24}) true))
    
  => true)

(facts "pour les opérations ensemblistes."
  (and

    (= (clojure.set/subset? 
         #{:a :b :c} 
         #{:z :d :b :e :c :g :a}) 
       
       true)
    
    (= (clojure.set/subset? 
         #{:z :d :b :e :c :g :a}
         #{:a :b :c}) 
       
       false)


    (= (clojure.set/union
         #{:a :b :c} 
         #{:z :d :b :e :c :g :a})
       
       #{:e :z :g :c :b :d :a})
    
    (= (clojure.set/intersection
         #{:a :b :c} 
         #{:z :d :b :e :c :g :a})
       
      #{:c :b :a})
    

    (= (clojure.set/difference
         #{:z :d :b :e :c :g :a}
         #{:a :b :c})
        #{:e :z :g :d}))
  
  
  => true)


