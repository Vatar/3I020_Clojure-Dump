(ns project-mastermind.functions
  (:use midje.sweet))


(defn code-secret  
  [n]
  (loop [i 0,res []] 
    (cond     
      (< i n) (recur (inc i) (conj res (rand-nth [:rouge :bleu :vert :jaune :noir :blanc])))     
      :else res)))


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



(declare indications)

(defn indications  
  [orig,choix]  
  (loop [i 0,res []]  
    (cond   
      (<= i (- (count orig) 1)) (recur (inc i) (conj res (cond    
                                                           (= (get orig i) (get choix i)) :good     
                                                           (some #(= (get choix i) %) orig) :color 
                                                           :else :bad))))))

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

(declare freqs-dispo)

(defn freqs-dispo 
  [color,indic] 
  (loop [i 0,res (frequences color)]   
    (if (<= i (- (count color) 1))  
      (recur (inc i) (cond                
                       (= (get indic i) :good)
                       (update res (get color i) dec)    
                       :else res))   
      res)))

(frequencies [:rouge :rouge :bleu :vert :rouge])

(update (frequencies [:rouge :rouge :bleu :vert :rouge]) :rouge dec)

(get [:good :color :bad :good :color] 0)(= :good :good)

(fact "Les fréquences disponibles de `freqs-dispo` sont correctes."  
                                                         (freqs-dispo [:rouge :rouge :bleu :vert :rouge]      
                                                           [:good :color :bad :good :color])      => {:bleu 1, :rouge 2, :vert 0})



(declare filtre-indications)


"combiner indications + freqs-dispo pour avoir les indications filtré par cardinalité "

(fact "Le `filtre-indications` fonctionne bien."    
  (filtre-indications [:rouge :rouge :vert :bleu]         
    [:vert :rouge :bleu :jaune]                     
    [:color :good :color :bad])  
  => [:color :good :color :bad]       
  (filtre-indications [:rouge :vert :rouge :bleu]               
    [:rouge :rouge :bleu :rouge]                     
    [:good :color :color :color])  
  => [:good :color :color :bad])

















