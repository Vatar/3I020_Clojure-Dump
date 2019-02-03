;; # Exercice 3 : Phrase-o-matic

(ns theme01-clojure.ex03-phrases)


;; => #'theme01-clojure.ex03-phrases/ex03-phrases

(defn gen-article
  "Générateur d'article."
  []
  (rand-nth ["le" "la" "un" "une"]))

;; => #'theme01-clojure.ex03-phrases/gen-article
(repeatedly 10 gen-article)

;; => ("une" "un" "une" "un" "la" "la" "un" "la" "une" "la")
;; ## Question 1 : générateurs d'adjectifs et de noms

(defn gen-adjectif
  "Genérateur d'adjectifs"
  []
  (rand-nth ["bon" "mauvais" "fort" "faible"]))

(repeatedly 10 gen-adjectif)


(defn gen-nom 
  []
  (rand-nth ["coquin" "mécreant" "poulet" "péquenot"]))

(repeatedly 10 gen-nom)


;; ## Question 2 : générateur de proposition nominale


(defn gen-propnom
  []
  (apply str [(gen-article) " " (gen-adjectif) " " (gen-nom)]))
    
(gen-propnom)  


;; ## Question 3 : générateur de proposition verbale

(defn gen-verb
  []
  (rand-nth ["à piqué" "à ravagé" "à sauté" "à mangé"]))

(defn gen-propverbal
  []
  (apply str [(gen-verb) " " (gen-propnom)]))

(gen-propverbal)  


;; ## Question 4 : générateur de phrases


(defn gen-phrase
  []
  (apply str [(gen-propnom) " " (gen-propverbal)]))

(gen-phrase) 

;; ## Question 5 : phrase-o-matic++
;; ## Pas d'énoncé sur la feuille de TD




