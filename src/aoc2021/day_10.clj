(ns aoc2021.day-10
  (:require [aoc2021.core :refer :all]))

(def brackets {\( \) \{ \} \[ \] \< \>})
(def openers (set (keys brackets)))
(def closers (set (vals brackets)))

(def corrupt-score {\) 3 \] 57 \} 1197 \> 25137})

(defn check
  ([chars]
   (check '() (seq chars))
   )
  ([found chars]
   (let [c (first chars)]
     (cond
       (empty? chars) (if (empty? found) :good [:incomplete (map brackets found)])
       (openers c) (check (cons c found) (rest chars))
       (#{(brackets (first found))} c) (check (rest found) (rest chars))
       :else [:corrupt c])
     ))
  )

(defn part-1 []
  (->>
    "src/aoc2021/day_10.txt"
    file->lines
    (map check)
    (filter (comp #{:corrupt} first))
    (map (comp corrupt-score second))
    (reduce +)
    )
  )

(defn score-auto-complete [chars]
  (let [scores {\) 1 \] 2 \} 3 \> 4}]
    (->> chars
      (map scores)
      (reduce (fn [a s] (+ (* 5 a) s))))))

(defn part-2 []
  (->>
    "src/aoc2021/day_10.txt"
    file->lines
    (map check)
    (filter (comp #{:incomplete} first))
    (map second)
    (map score-auto-complete)
    median
    )
  )

(comment
  (part-1);; => 319329

  (part-2);; => 3515583998

  .)
