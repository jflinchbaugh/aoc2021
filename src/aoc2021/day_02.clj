(ns aoc2021.day-02
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn translate
  "turn [dir dist] into an [x y] offset"
  [dir dist]
  (let [d (parse-int dist)]
    (case dir
      "forward" [d 0]
      "up" [0, (* -1 d)]
      "down" [0, d])))

(defn move
  "move the position by the change"
  [position change]
  (let [[px pd] position
        [mx md] change]
    [(+ px mx) (+ pd md)]))

(defn part-1 []
  (->>
   "src/aoc2021/day_02.txt"
   file->lines
   (map #(str/split % #" "))
   (map #(apply translate %))
   (reduce move [0 0])))

(comment
  (->> (part-1) (reduce *));; => 1654760

  .)
