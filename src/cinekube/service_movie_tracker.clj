(ns cinekube.service-movie-tracker
  (:require [cinekube.services :as services]))

(defn currently-watching [config]
  (let []
    (services/request
      (str (services/get-url config :movie-tracker) "/watching")
      :get
      {:content-type "application/json"})))