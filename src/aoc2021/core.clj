(ns aoc2021.core
  (:require [clojure.string :as str]))

(defn parse-int
  ([i]
   (Integer/parseInt i))
  ([i radix]
   (Integer/parseInt i radix)))

(defn parse-long
  ([i]
   (Long/parseLong i))
  ([i radix]
   (Long/parseLong i radix)))

(defn avg [coll]
  (when (not (empty? coll))
    (/ (reduce + coll) (count coll))))

(defn abs [n] (Math/abs n))

(defn file->lines [file-name]
  (->> file-name
    slurp
    str/trim
    str/split-lines
    (map str/trim)))
