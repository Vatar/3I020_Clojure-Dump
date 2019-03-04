(ns project-mastermind.functions
  (:use midje.sweet))



;;; J'ai laissé mon "brouillon" avec les appels de fonctions entre la défintion de la fonction
;;; et les tests pour trouver la bonne voie avec l'aide du InstaREPL

(declare code-secret)

(defn code-secret
  "Genère au hasard un code mastermind"
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
  "Correspond le choix du joueur au code mastermind"
  [orig,choix]  
  (loop [i 0,res []]  
    (cond   
      (<= i (- (count orig) 1)) (recur (inc i) (conj res (cond    
                                                           (= (get orig i) (get choix i)) :good     
                                                           (some #(= (get choix i) %) orig) :color 
                                                           :else :bad)))
      :else res)))


 

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


(declare frequences)

(defn frequences
  "Retourne la table des fréquences des couleurs"
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
  "Retourne la table des fréquences disponibles des couleurs"
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




(declare filtre-indications)

(defn filtre-indications
  "Retourne l'indication de la correspondance choix joueur-code mastermind
  respectant la cardinalité"
  
  [orig,choix,indic]
  (loop [i 0, freq (frequences orig),res []]
    (if (<= i (- (count orig) 1))
      (cond
        (= (get indic i) :bad) (recur (inc i) freq (conj res (get indic i)))
        (> (get freq (get choix i)) 0) (recur (inc i) (update freq (get choix i) dec) (conj res (get indic i)))
        :else (recur (inc i) freq (conj res :bad)))
       
      res)))
    
  
 

(filtre-indications [:rouge :rouge :vert :bleu]         
    [:vert :rouge :bleu :jaune]                     
    [:color :good :color :bad])  
  
(filtre-indications [:rouge :vert :rouge :bleu]               
    [:rouge :rouge :bleu :rouge]                     
    [:good :color :color :color]) 
  

(fact "Le `filtre-indications` fonctionne bien."    
  (filtre-indications [:rouge :rouge :vert :bleu]         
    [:vert :rouge :bleu :jaune]                     
    [:color :good :color :bad])  
  => [:color :good :color :bad]       
  (filtre-indications [:rouge :vert :rouge :bleu]               
    [:rouge :rouge :bleu :rouge]                     
    [:good :color :color :color])  
  => [:good :color :color :bad])


;;Fonctions pour le loop du jeu

(declare check-win)

(defn check-win
  "Vérifie si le joueur à gagné avec une indication entièrement composée de :good"
  [findic]
  (every? (fn [a] (= a :good)) findic))
  
(every? (fn [a] (= a :good)) [:good :good])

(check-win [:good :color])

(fact "Le `check-win` fonctionne bien."    
  (check-win                    
    [:color :good :color :bad])  
  => false       
  (check-win                    
    [:good :good :good :good])  
  => true)


(declare submit-convert)

(defn submit-convert
  "Convertit la soumission en vecteur compatible avec les fonctions précédentes "
  [list]
  (into [] list))
  


(keyword "a")
(keyword ["a"])
(keyword (get ["a"] 0))

(apply keyword ["a", "b"])

(map keyword ["a","b"])


(indications [:rouge :rouge] (:rouge :rouge))

(into [] '(:rouge :rouge))

(into [] '(map keyword ["a","b"]))

(submit-convert (map keyword ["a","b"]))

(submit-convert (:a :b)) 


;;Fonctions pour le solveur


(declare rand-color)

(defn rand-color
  []
  (rand-nth [:rouge :bleu :vert :jaune :noir :blanc]))
  

(rand-color)



(declare dumb-bot-brute-force)

;;Si on voulait un bot plus intelligent, il éviterait de tirer la même couleur au hasard que celle d'origine
;; + tenir en compte des indications :color pour tenter cette couleur à un endroit :bad
(defn dumb-bot-brute-force
  "L'ordinateur garde les couleurs :bonnes et retente un nouveau couleur au hasard pour le reste"
  [orig,result]
  (loop [i 0, res []]
    (if (< i (count orig))
      (recur (inc i) (conj res (cond
                                 (= (get result i) :good) (get orig i)
                                 :else (rand-color))))
      res)))
  
  
(dumb-bot-brute-force [:rouge :rouge :bleu :vert] [:good :color :color :bad])  
  



(fact "Le `dumb-bot-brute-force` est bien composé de couleurs."  
  (every? #{:rouge :bleu :vert :jaune :noir :blanc}      
    (dumb-bot-brute-force [:rouge :bleu :jaune :vert] [:good :color :bad :bad]))  
  => true)

(fact "Le `dumb-bot-brute-force` a l'air aléatoire."    
            (> (count (filter true? (map not=   
                                      (repeatedly 20 #(dumb-bot-brute-force [:rouge :bleu :jaune :vert] [:good :color :bad :bad]))       
                                      (repeatedly 20 #(dumb-bot-brute-force [:rouge :bleu :jaune :vert] [:good :color :bad :bad])))))
               0)
  
  => true)





