(ns cinekube.server
  (:require [cinekube.configuration :as config]
            [cinekube.service-movie-tracker :as movie-tracker]
            [com.appsflyer.donkey.core :refer [create-donkey create-server]]
            [com.appsflyer.donkey.server :refer [start]]
            [com.appsflyer.donkey.result :refer [on-success]]
            [reitit.ring :as ring]))


(defn handler [{:keys [config]}]
  {:status 200, :body (:body (movie-tracker/currently-watching))})

(defn config-middleware [handler config]
  (fn [request]
    (handler (assoc request :config config))))

(def app
  (ring/ring-handler
    (ring/router
      [["/tracker/watching" {:get handler}]])))

(defn init-server []
  (let [config (config/read-config)]
    (->
      (create-donkey)
      (create-server {:port (:port config)
                      :routes [{:handler app
                                :handler-mode :blocking}]})
      start
      (on-success (fn [_] (println "Server started listening on port" (:port config)))))))
