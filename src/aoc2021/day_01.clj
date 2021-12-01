(ns aoc2021.day-01
  (:require [aoc2021.core :refer :all]))

(defn changes [c]
  (->>
    c
    (partition 2 1)
    (map (comp #(reduce - %) reverse)))
  )

(defn part-1 []
  (->>
    "src/aoc2021/day_01.txt"
    file->lines
    (map parse-int)
    changes
    (filter pos?)
    count
    )
  )

(defn sliding-window-sum [size c]
  (->>
    c
    (partition size 1)
    (map #(reduce + %))
    ))

(defn part-2 []
  (->>
    "src/aoc2021/day_01.txt"
    file->lines
    (map parse-int)
    (sliding-window-sum 3)
    changes
    (filter pos?)
    count
    )
  )

(comment
  (part-1);; => 1715

  (part-2);; => 1739

  .)
