(ns project-mastermind.core
  (:require [project-mastermind.game :as game]))



(defn -main
  "Menu Principal"
  []
  (println "Bienvenue au Mastermind")
  (println "Veuillez taper 1 puis Entrée pour démarrer le jeu")
  (println "Veuillez taper 2 puis Entrée pour démarrer le solveur dit force brute")
  (println "Veuillez Taper 0 pour terminer le jeu")
  (def choix (read-line))
  ;Possibilité d'implémenter un loop içi jusqu'à ce que choix = 0 
  (cond 
    (= choix "1") ((println "Démarrage du jeu") (game/basic-game))
    (= choix "2") ((println "Démarrage du solveur dit force brute") (game/brute-force-game))
    (= choix "0") (System/exit 0)
    :else (System/exit 0)))    
  



  
  
