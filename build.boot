(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure       "1.6.0"       :scope "provided"]
                  [gorilla-repl              "0.3.4"       :scope "provided"]
                  [ns-tracker                "0.2.2"]
                  [adzerk/bootlaces          "0.1.5"       :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.0.1")

(bootlaces! +version+)

(task-options!
 pom  {:project     'boot-gorilla
       :version     +version+
       :description "Use the Gorilla REPL as a task in your Boot project"
       :url         "https://github.com/mathias/boot-gorilla"
       :scm         {:url "https://github.com/mathias/boot-gorilla"}
       :license     {:name "Eclipse Public License"
                     :url  "http://www.eclipse.org/legal/epl-v10.html"}})
