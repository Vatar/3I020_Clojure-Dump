;; # Exercice 5 : Récursion et collections

(ns theme01-clojure.ex05-recur
  (:use midje.sweet))



;; ## Question 1 : Construction d'un vecteur

(declare inter)



(defn inter
   [n,m,k]
   (cond
     (< m n) []
     :else (loop [i n,res [n]]
             (if (<= (+ i k) m)
               (recur (+ i k) (conj res (+ i k)))
               res))))
             


(inter 2 5 1)


(fact "`inter` construit les bons intervalles."
      (inter 2 5 1) => [2 3 4 5]
      (inter 2 5 2) => [2 4]
      (inter 2 2 10) => [2]
      (inter 3 2 1) => []
      (inter 10 50 10) => [10 20 30 40 50])

;; ## Question 2 : Transformation d'un vecteur

(declare vmap)

(defn vmap
  [fn,vect]
  (loop [i(- (count vect) 1),v vect]
    (if (>= i 0)
      (recur (dec i) (assoc v i (fn (get v i))))
      v)))

(assoc [1 2 3 4 5] 1 (- (get [1 2 3 4 5] 1)))

(vmap - [1 2 3 4 5])

(fact "`vmap` applique bien la fonction au vecteur."
      (vmap - [1 2 3 4 5]) => [-1 -2 -3 -4 -5]
      (vmap (fn [x] (* x x)) [1 2 3 4 5]) => [1 4 9 16 25]
      (vmap #(count %) [[1 2] ["a" "b" "c"] []]) => [2 3 0])

;; ## Question 3 : Réductions d'un vecteur


(declare vmax)
(declare vmax-nth)

(defn vmax
  [vect]
  (loop [i (- (count vect) 1),max nil]
    (if (>= i 0)
      (recur (dec i) (if ( > (compare (get vect i) max) 0) (get vect i) max))
      max)))
    
(vmax [2 3 5 1 2 0])

(defn vmax-nth 
  [vect]
  (loop [i 0,max nil, indice nil]
    (if (<= i (count vect))
      (recur (inc i) (if (> (compare (get vect i) max) 0) (get vect i) max) (if (> (compare (get vect i) max) 0) i indice))
      indice)))


(vmax-nth [2 9 2 9])

(fact "`vmax` retourne bien l'élément maximal, et `vmax-nth` son indice."
      (vmax [2 3 5 1 2 0]) => 5
      (vmax-nth [2 3 5 1 2 0]) => 2
      (vmax []) => nil
      (vmax-nth []) => nil
      (vmax ["un" "deux" "trois" "quatre" "cinq" "six"]) => "un"
      (vmax-nth ["un" "deux" "trois" "quatre" "cinq" "six"]) => 0
      (vmax-nth [2 9 2 9]) => 1)

;; ## Question 4 : Construction d'une map

(declare mkmap)

(defn mkmap 
  [vect]
  (into {} vect))

(mkmap [[:un, 1] [:deux, 2] [:trois, 3]])



(fact "`mkmap` construit la bonne map."
      (mkmap [[:un, 1] [:deux, 2] [:trois, 3]])
      => {:deux 2, :un 1, :trois 3}
      (mkmap []) => {})

;; ## Question 5 : Réduction d'une map

(declare mmaxv)


(defn mmaxv
  [map]
  (let [cles (keys map)]
   (loop [i 0, max nil, clemax nil]
     (if (< i (- (count cles) 1))
       
       (recur (inc i) (if (> (compare (get map (nth cles i)) max) 0) (get map (nth cles i)) max) (if (> (compare (get map (nth cles i)) max) 0) (nth cles i) clemax))
       clemax))))
       
(let [cles (keys {:a 1, :b 3, :c 2, :d -1})]
  print cles)
(let [cles (keys {:a 1, :b 3, :c 2, :d -1})]
  (nth cles 1)
  (get {:a 1, :b 3, :c 2, :d -1} (nth cles 1)))

(get {:a 1, :b 3} :a)
(nth (keys {:a 1, :b 3}) 1)
(get {:a 1, :b 3} (nth (keys {:a 1, :b 3}) 1))
  
(mmaxv {:a 1, :b 3, :c 2, :d -1})

(fact "`mmaxv` retourne la bonne clé."
      (mmaxv {:a 1, :b 3, :c 2, :d -1}) => :b
      (mmaxv {}) => nil)

;; ## Question 6 : construction d'un ensemble

(declare premiers)

(defn prime? [n]
  (loop [i 2]
    (cond
     (< i n) (if (not= 0 (mod n i))
               (recur (inc i))
               false)
     :else true)))


(defn premiers
  [n]
  (loop [i 2, res []]
    (cond
      (< (count res) n) (recur (inc i) (if (prime? i) (conj res i) res))
      :else (into #{} res))))
      

(into #{} [2 1 3 4 2]) 

(premiers 10)

(fact "`premiers` retourne bien les `n` premiers premiers"
      (premiers 1) => #{2}
      (premiers 10) => #{2 3 5 29 7 11 13 17 19 23}
      (count (premiers 10)) => 10
      (last (premiers 100)) => 379)

;; ## Question 7 : Opérations ensemblistes.

(declare union)
(declare intersection)
(declare difference)

(defn union
  [vect1,vect2]
  (loop[s vect1,res vect2]
    (if(seq s)
      (recur (rest s) (if (contains? vect1 (first s))
                        (conj res (first s))
                        res))
      (into #{} res))))
    

(defn intersection 
  [vect1,vect2]
  (loop[s vect1,res []]
    (if(seq s)
      (recur (rest s) (if (contains? vect2 (first s)) 
                        (conj res (first s))
                        res))
      (into #{} res))))

(defn difference
  [vect1,vect2]
  (loop[s vect1,res []]
    (if(seq s)
      (recur (rest s) (if (not(contains? vect2 (first s))) 
                        (conj res (first s))
                        res))
      (into #{} res))))
  

(apply merge #{1 2} #{1 2 3 4})

(apply disj #{1 2} #{1 3 4})

(apply conj #{1 2} #{1 3 4}) 

(intersection #{1 2 4} #{2 3 4 5}) 

(get #{1 2} 0) ;;Comment récupérer les valeurs du set aaaaaaa

(into () #{1 2})

(disj #{1 2} #{1})


(facts "sur les opérations ensemblistes."
       (union #{1 2 4} #{2 3 4 5}) => #{1 2 3 4 5}
       (intersection #{1 2 4} #{2 3 4 5}) => #{2 4}
       (difference #{2 3 4 5} #{1 2 4}) => #{3 5}
       (union #{1 2 3} #{}) => #{1 2 3}
       (union #{} #{1 2 3}) => #{1 2 3}
       (intersection #{1 2 3} #{}) => #{}
       (intersection #{} #{1 2 3}) => #{}
       (difference #{1 2 3} #{}) => #{1 2 3}
       (difference #{} #{1 2 3}) => #{})




