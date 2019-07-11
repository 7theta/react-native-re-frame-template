(ns leiningen.new.util.str
  (:require [clojure.string :refer [blank? lower-case upper-case replace split join escape]])
  (:refer-clojure :exclude [replace]))

(defn camel-case
  "Convert `word` to camel case. By default, camel-case converts to
  UpperCamelCase. If the argument to camel-case is set to :lower then
  camel-case produces lowerCamelCase.
  Examples:
    (camel-case \"active_record\")
    ;=> \"ActiveRecord\"
    (camel-case \"active_record\" :lower)
    ;=> \"activeRecord\"
    (camel-case \"active_record/errors\")
    ;=> \"ActiveRecord/Errors\"
    (camel-case \"active_record/errors\" :lower)
    ;=> \"activeRecord/Errors\""
  [word & [mode]]
  (when word
    (cond
      (= mode :lower) (camel-case word lower-case)
      (= mode :upper) (camel-case word upper-case)
      (fn? mode) (str (mode (str (first word)))
                      (apply str (rest (camel-case word nil))))
      :else (-> (replace word #"/(.?)" #(str "/" (upper-case (nth % 1))))
                (replace #"(^|_|-)(.)"
                         #(str (if (#{\_ \-} (nth % 1))
                                 (nth % 1))
                               (upper-case (nth % 2))))))))

(defn underscore
  "The reverse of camel-case. Makes an underscored, lowercase form from
           the expression in the string.
           Examples:
             (underscore \"ActiveRecord\")
             ;=> \"active_record\"
             (underscore \"ActiveRecord::Errors\")
             ;=> \"active_record::errors\""
  [x]
  (when x
    (-> x
        (replace #"([A-Z\d]+)([A-Z][a-z])" "$1_$2")
        (replace #"([a-z\d])([A-Z])" "$1_$2")
        (replace #"-" "_")
        lower-case)))

(defn hyphenate
  "Hyphenate x, a String, which is the same as threading `x` through the
  underscore and dasherize fns.
  Examples:
    (hyphenate \"Continent\")
    ; => \"continent\"
    (hyphenate \"CountryFlag\")
    ; => \"country-flag\""
  [x]
  (when x
    (-> (replace #"([A-Z]+)([A-Z][a-z])" "$1-$2")
        (replace #"([a-z\d])([A-Z])" "$1-$2")
        (replace #"\s+" "-")
        (replace #"_" "-")
        (lower-case))))

(defn android-sanitize
  [s]
  (when s
    (-> s
        (escape {\0 "zero"
                 \1 "one"
                 \2 "two"
                 \3 "three"
                 \4 "four"
                 \5 "five"
                 \6 "six"
                 \7 "seven"
                 \8 "eight"
                 \9 "nine"})
        (underscore)
        (lower-case))))
