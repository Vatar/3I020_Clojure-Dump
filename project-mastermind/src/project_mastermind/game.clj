(ns project-mastermind.game
  (:require [project-mastermind.functions :as fnc],
            [clojure.string :as str]))




(declare basic-game)

;;;Il serait facilement possible de coder la possibilité à l'utilisateur de taper la taille du code mastermind 
;;;et de le faire passer en argument pour ces fonctions

(defn basic-game
  "Loop du jeu de base jusqu'à victoire"
  []
  (def mastercode (fnc/code-secret 4))
  (println "Le code mastercode est la suivante: ")
  (dorun (map println mastercode))
  (println "Veuillez saisir votre soumission de 4 couleurs en format => couleur1 couleur2 couleur3 couleur4 ")
  (def soumission (fnc/submit-convert (map keyword (str/split (read-line) #" "))))
  (loop [submit soumission]
    
    (println "Le résultat de votre soumission est: ")
    (def result (fnc/filtre-indications mastercode submit (fnc/indications mastercode submit)))    
    (dorun (map println result))
    
    (if (= (fnc/check-win result) false)
      (recur (fnc/submit-convert (map keyword (str/split (read-line) #" "))))
      
      ()))
  (println "Victoire!")
  (System/exit 0))
       
  
  
(declare brute-force-game)

(defn brute-force-game
  "Solveur brute-force par l'ordinateur"
  []
  ;;Le flux est très similaire au basic-game 
  (def mastercode2 (fnc/code-secret 4))
  (println "Le code mastercode est la suivante: ")
  (dorun (map println mastercode2))
  ;;fnc/code-secret convient parfaitement pour une soumission initiale aléatoire
  (loop [submit (fnc/code-secret 4)]
    (println "La soumission du bot est:")
    (dorun (map println submit))
    (Thread/sleep 1000)
    (println "Le Résultat de la soumission du bot est:")
    (def result2 (fnc/filtre-indications mastercode2 submit (fnc/indications mastercode2 submit)))
    (dorun (map println result2))
    
    (if (= (fnc/check-win result2) false)
     
      (recur (fnc/dumb-bot-brute-force submit result2))
      
      ()))
  
  (println "Victoire!")
  (System/exit 0))
    
    

  
  
  

  
  
      
      
  


  
  
  
    
  
  


