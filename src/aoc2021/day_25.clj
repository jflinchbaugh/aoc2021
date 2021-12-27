(ns aoc2021.day-25
  (:require [aoc2021.core :refer :all]))

(defn next-state-east [[prev current next]]
  (case [prev current next]
    [\. \> \.] \.
    [\v \> \.] \.
    [\> \> \.] \.
    [\> \. \.] \>
    [\> \. \>] \>
    [\> \. \v] \>
    current))

(defn next-state-south [[prev current next]]
  (case [prev current next]
    [\. \v \.] \.
    [\> \v \.] \.
    [\v \v \.] \.
    [\v \. \.] \v
    [\v \. \v] \v
    [\v \. \>] \v
    current))

(defn move-row-east [row]
  (let [width (count row)
        expanded-row (cycle row)
        neighbors (->> expanded-row (drop (dec width)) (partition 3 1) (take width))]
    (map next-state-east neighbors)))

(defn rows->columns [rows]
  (for [column (range (count (first rows)))]
    (map
      (fn [v] (nth v column))
      rows)))

(defn move-row-south [row]
  (let [width (count row)
        expanded-row (cycle row)
        neighbors (->> expanded-row (drop (dec width)) (partition 3 1) (take width))]
    (map next-state-south neighbors)))

(defn move-east [rows]
  (map move-row-east rows))

(defn move-south [rows]
  (->>
    rows
    rows->columns
    (map move-row-south)
    rows->columns))

(defn move-both [rows]
  (->> rows move-east move-south))

(defn part-1 []
  (->> "src/aoc2021/day_25.txt"
       file->lines
       (iterate move-both)
       (partition 2 1)
       (take-while (fn [[p n]] (not= p n)))
       count
       inc))

(comment

  (part-1);; => 530


  .)

