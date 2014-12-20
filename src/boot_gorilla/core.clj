(ns boot-gorilla.core
  {:boot/export-tasks true}
   (:require [clojure.java.io    :as io]
             [boot.core          :as boot :refer [deftask]]
             [boot.pod           :as pod]
             [boot.task.built-in :as task]
             [boot.file          :as file]
             [boot.util          :as util]))

(deftask gorilla
  "Run a Gorilla REPL."
  [p port       PORT int "The port to run the web REPL on. Defaults to 4000."
   i ip         IP   str "The IP address the server listens on. Defaults to 127.0.0.1"
   n nrepl-port PORT int "The port that the nREPL server will be started up on. Defaults to a free port."
   b block           bool "Blocking (for standalone use)"]
  (let [worker     (pod/make-pod (get-env))
        ip         (or ip "127.0.01")
        port       (or port 4000)
        nrepl-port (or nrepl-port 0)
        block      (or block false)]
    (comp
     (with-pre-wrap fileset
       (pod/with-eval-in worker
         (require '[gorilla-repl.core :as g])
         (g/run-gorilla-server {:port ~port
                                :ip ~ip
                                :nrepl-port ~nrepl-port
                                :project "Coriander"})
         (util/info "<< started Gorilla REPL on http://localhost:%d >>\n" ~port))
       fileset)
     (if block
       (task/wait)
       identity))))
