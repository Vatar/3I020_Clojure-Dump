;; # Exercice 6 : Mastermind

(ns theme01-clojure.ex06-mastermind
  (:use midje.sweet))



;; ## Question 1 : tirage du code secret

(declare code-secret)

(defn code-secret
  [n]
  (loop [i 0,res []]
    (cond
      (< i n) (recur (inc i) (conj res (rand-nth [:rouge :bleu :vert :jaune :noir :blanc])))
      :else res)))

(code-secret 2)

(conj [] (rand-nth [:rouge :bleu :vert :jaune :noir :blanc]))


(fact "Le `code-secret` est bien composé de couleurs."
      (every? #{:rouge :bleu :vert :jaune :noir :blanc} 
              (code-secret 4))
      => true)

(fact "Le `code-secret` a l'air aléatoire."
      (> (count (filter true? (map not= 
                                   (repeatedly 20 #(code-secret 4))
                                   (repeatedly 20 #(code-secret 4)))))
         0)
      => true)


;; ## Question 2 : indications

(declare indications)

(defn indications
  [orig,choix]
  (loop [i 0,res []]
    (cond
      (<= i (- (count orig) 1)) (recur (inc i) (conj res (cond
                                                           (= (get orig i) (get choix i)) :good
                                                           (some #(= (get choix i) %) orig) :color
                                                           :else :bad)))
   
      :else res)))
  
(get [:rouge] 0)
(some #(= :rouge %) [:rouge :vert])
(conj [] :rouge)
(conj [] (cond
           (= 1 1) :good
           (some #(= (1) %) [1]) :color
           :else :bad))

(= :rouge :rouge)

(indications [:rouge :rouge :vert :bleu] [:vert :rouge :bleu :jaune])


(fact "`indications` sont les bonnes."
      (indications [:rouge :rouge :vert :bleu] 
                   [:vert :rouge :bleu :jaune])
      => [:color :good :color :bad]
      
      (indications [:rouge :rouge :vert :bleu] 
                   [:bleu :rouge :vert :jaune])
      => [:color :good :good :bad]
      
      (indications [:rouge :rouge :vert :bleu] 
                   [:rouge :rouge :vert :bleu])
      => [:good :good :good :good]
      
      (indications [:rouge :rouge :vert :vert] 
                   [:vert :bleu :rouge :jaune])
      => [:color :bad :color :bad])

;; ## Question 3 : fréquences

(declare frequences)

(defn frequences
  [vect]
  (frequencies vect))


(frequencies  [:rouge :rouge :vert :bleu :vert :rouge])

(fact "les `frequences` suivantes sont correctes."
      (frequences [:rouge :rouge :vert :bleu :vert :rouge])
      => {:rouge 3 :vert 2 :bleu 1}
      
      (frequences [:rouge :vert :bleu])
      => {:rouge 1 :vert 1 :bleu 1}
      
      (frequences [1 2 3 2 1 4]) => {1 2, 2 2, 3 1, 4 1})

;; ## Question 4 : fréquences disponibles

(declare freqs-dispo)

(defn freqs-dispo
  [color,indic]
  (loop [i 0,res (frequences color)]
    (if (<= i (- (count color) 1))
      (recur (inc i) (cond
                       (= (get indic i) :good) (update res (get color i) dec)                       
                       :else res))
      res)))


(frequencies [:rouge :rouge :bleu :vert :rouge])

(update (frequencies [:rouge :rouge :bleu :vert :rouge]) :rouge dec)

(get [:good :color :bad :good :color] 0)

(= :good :good)
(fact "Les fréquences disponibles de `freqs-dispo` sont correctes."
      (freqs-dispo [:rouge :rouge :bleu :vert :rouge]
                   [:good :color :bad :good :color])
      => {:bleu 1, :rouge 2, :vert 0})

;; ## Question 5 : filtrer par cadinalité (+ difficile)

(declare filtre-indications)


(fact "Le `filtre-indications` fonctionne bien."
      (filtre-indications [:rouge :rouge :vert :bleu] 
                          [:vert :rouge :bleu :jaune]
                          [:color :good :color :bad])
      => [:color :good :color :bad]
      
      (filtre-indications [:rouge :vert :rouge :bleu] 
                          [:rouge :rouge :bleu :rouge]
                          [:good :color :color :color])
      => [:good :color :color :bad])
      

;; ## Questions subsidiaire


