(ns aoc2021.day-09
  (:require [aoc2021.core :refer :all]))

(defn cell [grid row col]
  (try [(nth (nth grid row) col) row col]
       (catch IndexOutOfBoundsException _ nil)))

(defn surroundings [grid row col]
  (remove
   nil?
   [(cell grid row (dec col))
    (cell grid row (inc col))
    (cell grid (dec row) col)
    (cell grid (inc row) col)]))

(defn find-low-points [grid]
  (let [length (count grid)
        width (count (first grid))]
    (->>
      (for [row (range 100)
            col (range 100)]
        (let [point (cell grid row col)
              surroundings (surroundings grid row col)]
          (when (every? #(< (first point) (first %)) surroundings)
            point)))
      (remove nil?)
      )))

(defn part-1 []
  (let [grid (->>
              "src/aoc2021/day_09.txt"
              file->lines
              (mapv #(mapv (comp parse-int str) %)))]
    (->>
     grid
     find-low-points
     (map first)
     (map inc)
     (reduce +))))

(defn part-2 []
  (let [grid (->>
               "src/aoc2021/day_09.txt"
               file->lines
               (mapv #(mapv (comp parse-int str) %)))]
    (->>
      grid
      find-low-points)
    )

  )


(comment

  (part-1);; => 496

  (part-2)

  .)
