(ns project-mastermind.core
  (:require [project-mastermind.game :as game]))



(defn -main
  "Menu Principal"
  []
  (println "Bienvenue au menu Mastermind (WIP)")
  (println "Veuillez taper 1 puis Entrée pour démarrer le jeu")
  (println "Veuillez Taper 0 pour terminer le jeu")
  (def choix (read-line))
  """Possibilité d'un loop içi jusqu'à ce que choix = 0 """
  (cond 
    (= choix "1") ((println "Démarrage") (game/basic-game))
    (= choix "0") (System/exit 0)
    :else (System/exit 0)))    
  



  
  
