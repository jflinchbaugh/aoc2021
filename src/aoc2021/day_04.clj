(ns aoc2021.day-04
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn lines->drawings [lines]
  (map parse-int (str/split (first lines) #",")))

(defn build-board [in]
  (map #(map parse-int (str/split % #" +")) in))

(defn lines->boards [lines]
  (->>
    lines
    rest
    (partition-by str/blank?)
    (filter #(< 1 (count %)))
    (map build-board)
    ))

(defn part-1 []
  (let [lines (file->lines  "src/aoc2021/day_04.txt")
        drawings (lines->drawings lines)
        boards (lines->boards lines)]
    boards
    )

  )

(comment


  .)
