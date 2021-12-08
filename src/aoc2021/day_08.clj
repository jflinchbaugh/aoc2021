(ns aoc2021.day-08
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn parse [lines]
  (for [line lines]
    (for [part (str/split line #" \| ")]
      (str/split part #" ")))
  )

(defn part-1 []
  (->> "src/aoc2021/day_08.txt"
    file->lines
    parse
    (map second)
    flatten
    (map count)
    (filter #{2 4 3 7})
    count)
  )

(comment
  (part-1);; => 525

  )


