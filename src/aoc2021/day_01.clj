(ns aoc2021.day-01
  (:require [aoc2021.core :refer :all]))

(defn part-1 []
  (->>
    "src/aoc2021/day_01.txt"
    file->lines
    (map parse-int)
    (partition 2 1)
    (map #(reduce - %))
    (filter neg?)
    count
    )
  )

(defn part-2 []
  (->>
    "src/aoc2021/day_01.txt"
    file->lines
    (map parse-int)
    (partition 3 1)
    (map #(reduce + %))
    (partition 2 1)
    (map #(reduce - %))
    (filter neg?)
    count
    )
  )

(comment
  (part-1);; => 1715

  (part-2);; => 1739

  .)
