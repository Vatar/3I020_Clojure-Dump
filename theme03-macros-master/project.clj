(defproject theme02-sequences "0.0.3-SNAPSHOT"
  :description "Exercices sur le thème 3 : macros Clojure"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :profiles {:dev {:dependencies [[midje "1.9.1" :exclusions [org.clojure/clojure]]]
                   :plugins [[lein-midje "3.2.1"]]}
             ;; You can add dependencies that apply to `lein midje` below.
             ;; An example would be changing the logging destination for test runs.
             :midje {}})
             ;; Note that Midje itself is in the `dev` profile to support
             ;; running autotest in the repl.

