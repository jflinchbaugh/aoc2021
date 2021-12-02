(ns aoc2021.day-02
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn translate
  "turn [dir dist] into an [x y] offset"
  [dir dist]
  (case dir
    :forward [dist 0]
    :up [0, (* -1 dist)]
    :down [0, dist]))

(defn split [line]
  (let [[dir dist] (str/split line #" ")]
    [(keyword dir) (parse-int dist)]))

(defn move-1
  "move the position by the change"
  [position change]
  (let [[px pd] position
        [mx md] change]
    [(+ px mx) (+ pd md)]))

(defn part-1 []
  (->>
   "src/aoc2021/day_02.txt"
   file->lines
   (map split)
   (map #(apply translate %))
   (reduce move-1 [0 0])
   (reduce *)))

(defn move-2
  [current [dir dist]]
  (let [[x depth aim] current]
    (case dir
      :forward [(+ x dist) (+ depth (* aim dist)) aim]
      :down [x depth (+ aim dist)]
      :up [x depth (- aim dist)])))

(defn part-2 []
  (->>
   "src/aoc2021/day_02.txt"
   file->lines
   (map split)
   (reduce move-2 [0 0 0])
   (take 2)
   (reduce *)))

(comment
  (part-1);; => 1654760

  (part-2);; => 1956047400

  .)
