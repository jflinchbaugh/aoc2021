(ns aoc2021.day-03
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn most-common-bit [bit lines]
  (if
   (first
    (last
     (sort-by
      #(vector (count (second %)) (not (first %)))
      (group-by #(bit-test % bit) lines))))
    1
    0))

(defn part-1 []
  (let [lines (->>
               "src/aoc2021/day_03.txt"
               file->lines)
        ints (map #(parse-int % 2) lines)
        width (count (seq  (first lines)))
        sum (parse-int (str/join (repeat width 1)) 2)
        most-common-bits (map #(most-common-bit (- width 1 %) ints) (range width))
        gamma-rate (parse-int (str/join most-common-bits) 2)
        epsilon-rate (- sum gamma-rate)
        ;; epsilon-rate (parse-int (str/join least-common-bits) 2)
        ]
    (* gamma-rate epsilon-rate)))

(comment

  (part-1);; => 3242606

  .)
