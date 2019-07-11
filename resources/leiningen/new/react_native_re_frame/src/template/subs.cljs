(ns {{ns-name}}.subs
    (:require [re-frame.core :as rf]))

(rf/reg-sub
 :app/startup-message
 (fn [db [_]]
   "Hello, World!"))
