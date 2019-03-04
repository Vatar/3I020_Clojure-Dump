;; # Exercice 2 : dotimes

(ns theme03-macros.ex02-dotimes
  (:use midje.sweet))



;; ## Question 1

;; ## Question 2

;; à modifier
(defmacro my-dotimes [& _] nil)



(fact "`my-dotimes` reproduit le comportement attendu."
      (with-out-str
        (my-dotimes [k 5]
          (println "k =" k)
          (when (= k 4)
            (println "fin de la boucle"))))
      
      => (str "k = 0\nk = 1\nk = 2\nk = 3\n"
      	      "k = 4\nfin de la boucle\n"))
      
      

(fact "`my-dotimes` fonctionne comme la fonction standard `dotimes`."
      (with-out-str
        (my-dotimes [k 5]
          (println "k =" k)
          (when (= k 4)
            (println "fin de la boucle"))))
      
      => (with-out-str
            (dotimes [k 5]
              (println "k =" k)
                (when (= k 4)
                  (println "fin de la boucle")))))




