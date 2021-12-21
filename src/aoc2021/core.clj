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

(defn str->lines
  "parse a string into trimmed lines"
  [str-data]
  (->> str-data
    str/trim
    str/split-lines
    (map str/trim)))

(defn file->lines
  "read a local named file and parse it into trimmed lines"
  [file-name]
  (->> file-name
    slurp
    str->lines))

(defn all-range
  "produce an inclusive range in either direction"
  [s e]
  (if (<= s e)
    (range s (inc e))
    (reverse (range e (inc s)))))

(defn median [col]
  (let [sorted (sort col)
        size (count sorted)]
    (cond
      (zero? size) nil
      (odd? size) (nth sorted (dec (/ (inc size) 2)))
      :else (avg [(nth sorted (dec (int (/ size 2)))) (nth sorted (int (/ size 2)))]))))
