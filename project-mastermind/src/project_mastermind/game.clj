(ns project-mastermind.game
  (:require [project-mastermind.functions :as fnc],
            [clojure.string :as str]))




(declare basic-game)

(defn basic-game
  "Loop du jeu de base jusqu'à victoire"
  []
  (def mastercode (fnc/code-secret 4))
  (dorun (map println mastercode))
  (println "Veuillez saisir votre soumission de 4 couleurs en format => couleur1 couleur2 couleur3 couleur4 ")
  (def soumission (fnc/submit-convert (map keyword (str/split (read-line) #" "))))
  (loop [submit soumission]
    """ commentaire """
    (def result (fnc/filtre-indications mastercode submit (fnc/indications mastercode submit)))    
    (dorun (map println result))
    
    (if (= (fnc/check-win result) false)
      (recur (fnc/submit-convert (map keyword (str/split (read-line) #" "))))
      
      ()))
  (println "Victoire!")
  (System/exit 0))
       
  
  
      
      
  


  
  
  
    
  
  


