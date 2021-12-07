(ns aoc2021.day-07
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn part-1 []
  (let [input (slurp "src/aoc2021/day_07.txt")
        positions (->>
                   (->
                    input
                    (str/split #","))
                   (map (comp parse-int str/trim))
                   sort)
        target-pos (nth positions (/ (count positions) 2))]
    (->>
     positions
     (map #(abs (- % target-pos)))
     (reduce +))))

(defn cost-2 [dist]
  (->>
    dist
    inc
    range
    (reduce +)))

(defn part-2 []
  (let [input (slurp "src/aoc2021/day_07.txt")
        positions (->>
                    (->
                      input
                      (str/split #","))
                    (map (comp parse-int str/trim))
                    sort)
        target-pos (int (avg positions))]
    target-pos
    (->>
      positions
      (map #(abs (- % target-pos)))
      (map cost-2)
      (reduce +))))

(comment

  (part-1);; => 344297

  (part-2);; => 97164301

  .)
