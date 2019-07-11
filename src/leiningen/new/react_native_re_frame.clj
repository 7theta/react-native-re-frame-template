(ns leiningen.new.react-native-re-frame
  (:require [clojure.tools.cli :refer [parse-opts]]
            [leiningen.new.templates :refer [renderer raw-resourcer sanitize-ns name-to-path ->files]]
            [leiningen.core.main :as main]

            [leiningen.new.util.str :as str-util]
            [leiningen.new.profiles.base :as base]
            [leiningen.new.profiles.android :as android]
            [leiningen.new.profiles.ios :as ios]))

(def template-name "react-native-re-frame")
(def render-text (renderer template-name))
(def render-raw (raw-resourcer template-name))

(defn render
  ([template data]
   (render-text template data))
  ([file]
   (render-raw file)))

(defn app-files
  [data options]
  (concat
   (base/files data options render)
   (ios/files data options render)
   (android/files data options render)))

(defn extract-name-and-group-id
  "Given an input string following the form \"<group-id>/<name-id>\" or \"<name-id>\", return a tuple representing the
  name and group ID.
  Examples:
    (extract-name-and-group-id \"com.mydomain/my-project\")
    ;=> [\"my-project\" \"com.mydomain\"]
    (extract-name-and-group-id \"my-project\" :lower)
    ;=> [\"my-project\"]"
  [s]
  (-> s
      (clojure.string/split #"/")
      (reverse)))

(defn extract-ios-organization-name
  ""
  [group-id]
  (-> group-id
      (clojure.string/split #"\.")
      (last)
      (str-util/camel-case)))

(def cli-options
  [[nil "--android-package" "Android package"
    nil "--ios-organization-name" "iOS organization name"
    :default nil]])

(defn react-native-re-frame
  ""
  [name & options]
  (let [[name group-id] (extract-name-and-group-id name)
        group-id (or group-id "com.example")
        {{:keys [android-package ios-organization-name]} :options} (parse-opts options cli-options)
        data {:name name
              :ns-name (sanitize-ns name)
              :sanitized (name-to-path name)
              :group-id group-id
              :js-module-name (str-util/camel-case name)
              :android-package (str-util/android-sanitize (or android-package (str group-id "." name)))
              :ios-bundle-prefix group-id
              :ios-organization-name (or ios-organization-name (extract-ios-organization-name group-id))
              :ios-project-name (str-util/camel-case name)}]
    (main/info (str "Generating fresh 'lein new' react-native-re-frame project"))
    (apply ->files data (app-files data options))))
