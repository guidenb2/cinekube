(ns cinekube.core
  (:require [com.appsflyer.donkey.core :refer [create-donkey create-server]]
            [com.appsflyer.donkey.server :refer [start]]
            [com.appsflyer.donkey.result :refer [on-success]]
            [cinekube.configuration :as config])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [config (config/read-config)]
    (->
      (create-donkey)
      (create-server {:port (:port config)
                      :routes [{:handler (fn [_request respond _raise]
                                           (respond {:body "Hello, world!"}))}]})
      start
      (on-success (fn [_] (println "Server started listening on port" (:port config)))))))
