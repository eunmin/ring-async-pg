(ns async-webapp.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route])
  (:import [io.vertx.pgclient PgConnectOptions PgPool]
           [io.vertx.sqlclient PoolOptions]
           [io.vertx.core Handler]))

(def options
  (doto (PgConnectOptions.)
    (.setPort 5432)
    (.setHost "localhost")
    (.setDatabase "test")
    (.setUser "guest")))

(def pool-options
  (doto (PoolOptions.)
    (.setMaxSize 5)))

(def client
  (PgPool/pool options pool-options))

(defn hello [req resp raise]
  (let [q (.query client "SELECT * FROM posts")]
    (.execute q (proxy [Handler] []
                  (handle [ar]
                    (let [rs (.result ar)
                          data (apply str (map (fn [r]
                                                 (.getString r "title"))
                                               (iterator-seq (.iterator rs))))]
                      (resp {:status 200 :body data})))))))

(defroutes app
  (GET "/" [] hello)
  (route/not-found "Not Found"))
