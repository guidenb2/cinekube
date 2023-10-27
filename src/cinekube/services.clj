(ns cinekube.services
  (:require [clj-http.client :as client]))


(defn request
  ([url method headers] (request url method headers nil))
  ([url
    method
    headers
    body]
   (if (nil? body)
     (client/request
       {:url url
        :method method
        :headers headers}))))

(defn get-url
  [config
   service]
  (let [service-config (-> config service)]
    (str (:scheme service-config) "://" (:address service-config) ":" (:port service-config))))