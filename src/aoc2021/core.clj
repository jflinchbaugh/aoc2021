(ns aoc2021.core
  (:require [clojure.string :as str]))

(defn parse-int
  "parse an string into an integer"
  ([i]
   (Integer/parseInt i))
  ([i radix]
   (Integer/parseInt i radix)))

(defn parse-long
  "parse a string into a long"
  ([i]
   (Long/parseLong i))
  ([i radix]
   (Long/parseLong i radix)))

(defn avg
  "average of values in a list"
  [coll]
  (when (seq coll)
    (/ (reduce + coll) (count coll))))

(defn abs
  "absolute value"
  [n]
  (Math/abs n))

(defn file->lines
  "read a local named file and parse it into trimmed lines"
  [file-name]
  (->> file-name
    slurp
    str/trim
    str/split-lines
    (map str/trim)))
