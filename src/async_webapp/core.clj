(ns async-webapp.core
  (:gen-class)
  (:require [async-webapp.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn -main [& args]
  (run-jetty app {:port 3000
                  :join? true
                  :async? true}))
