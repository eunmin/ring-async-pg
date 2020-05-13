(defproject async-webapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [compojure "1.6.1"]
                 [ring "1.8.1"]
                 [io.vertx/vertx-pg-client "3.9.0"]]
  :main ^:skip-aot async-webapp.core
  :profiles
  {:uberjar {:aot :all}
   :dev {:dependencies [[ring/ring-mock "0.4.0"]]}})
