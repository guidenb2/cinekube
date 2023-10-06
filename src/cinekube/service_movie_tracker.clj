(ns cinekube.service-movie-tracker
  (:require [cinekube.services :as services]))

(def service-root "http://127.0.0.1:8444")

(defn currently-reading []
  (let []
    (services/request
      (str service-root "/watched")
      :get
      {:content-type "application/json"})))