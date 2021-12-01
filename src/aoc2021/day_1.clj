(ns aoc2021.day-1
  (:require [aoc2021.core :refer :all]))

(defn part-1 []
  (->>
    "src/aoc2021/day_1.txt"
    file->lines
    (map parse-int)
    (partition 2 1)
    (map #(reduce - %))
    (filter neg?)
    count
    )

  )

(comment
  (part-1);; => 1715

  .)
