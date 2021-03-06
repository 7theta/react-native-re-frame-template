(ns {{ns-name}}.core
    (:require ["react-native" :refer [AppRegistry View Text] :as ReactNative]
              [reagent.core :as r]
              [re-frame.core :as rf]

              [{{ns-name}}.subs]))

(def view (r/adapt-react-class View))
(def text (r/adapt-react-class Text))

(defn root []
  [view
   {:style
    {:position "absolute"
     :left 0 :top 0 :right 0 :bottom 0
     :background-color "white"
     :align-items "center"
     :justify-content "center"}}
   [text
    {:style
     {:color "blue"
      :font-size 24}}
    @(rf/subscribe [:app/startup-message])]])

(defn init []
  (.registerComponent AppRegistry "{{js-module-name}}" #(r/reactify-component root)))
