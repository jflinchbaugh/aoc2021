(ns aoc2021.day-05
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn parse-point [point]
  (map parse-int (str/split point #",")))

(defn parse-line [line]
  (map parse-point (str/split line #" -> ")))

(defn horizontal? [[[x1 y1] [x2 y2]]]
  (= y1 y2))

(defn vertical? [[[x1 y1] [x2 y2]]]
  (= x1 x2))

(defn diagonal? [[[x1 y1] [x2 y2]]]
  (= (abs (- x1 x2)) (abs (- y1 y2))))

(def get-x first)
(def get-y second)

(defn all-range
  "produce an inclusive range in either direction"
  [s e]
  (if (<= s e)
    (range s (inc e))
    (reverse (range e (inc s)))))

(defn expand-segment [segment]
  (let [[[x1 y1] [x2 y2]] segment]
    (or 
      (cond
        (vertical? segment) (map #(-> [x1 %]) (all-range y1 y2))
        (horizontal? segment) (map #(-> [% y1]) (all-range x1 x2))
        (diagonal? segment) (map
                              (fn [x y] [x y])
                              (all-range x1 x2)
                              (all-range y1 y2)))
      (throw (IllegalArgumentException. "askew segment")))))

(defn part-1 []
  (->>
   "src/aoc2021/day_05.txt"
   file->lines
   (map parse-line)
   (filter #(or (horizontal? %) (vertical? %)))
   (map expand-segment)
   (mapcat concat)
   (group-by identity)
   vals
   (filter #(< 1 (count %)))
   count))

(defn part-2 []
  (->>
    "src/aoc2021/day_05.txt"
    file->lines
    (map parse-line)
    (filter #(or (diagonal? %)(horizontal? %) (vertical? %)))
    (map expand-segment)
    (mapcat concat)
    (group-by identity)
    vals
    (filter #(< 1 (count %)))
    count))

(comment
  (part-1);; => 5084

  (part-2);; => 17882

  (range 5 (inc 3))

  .)
