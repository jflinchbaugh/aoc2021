(ns aoc2021.day-10
  (:require [aoc2021.core :refer :all]))

(def brackets {\( \) \{ \} \[ \] \< \>})
(def openers (set (keys brackets)))
(def closers (set (vals brackets)))

(def score {\) 3 \] 57 \} 1197 \> 25137})

(defn check
  ([chars]
   (check '() (seq chars))
   )
  ([found chars]
   (let [c (first chars)]
     (cond
       (empty? chars) (if (empty? found) :good :incomplete)
       (openers c) (check (cons c found) (rest chars))
       (#{(brackets (first found))} c) (check (rest found) (rest chars))
       :else c)
     ))
  )

(defn part-1 []
  (->>
    "src/aoc2021/day_10.txt"
    file->lines
    (map check)
    (remove #{:incomplete})
    (map score)
    (reduce +)
    )
  )

(comment
  (part-1);; => 319329

  .)
