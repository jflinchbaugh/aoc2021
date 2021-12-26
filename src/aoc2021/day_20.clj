(ns aoc2021.day-20
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn grid-coords [lines]
  (into {}
        (remove nil?
                (mapcat
                 (fn [line row]
                   (map
                    (fn [ch col]
                      (when (= \# ch) [[row col] 1]))
                    line (range)))
                 lines
                 (range)))))

(defn around [[row col]]
  (for [r (range (dec row) (inc (inc row)))
        c (range (dec col) (inc (inc col)))]
    [r c]))

(defn all-candidate-pixels [coords]
  (map #(-> [% 1]) (set (mapcat around coords))))

(defn index [default-pixel algo-lookup image-coords c]
  (->>
   c
   around
   (map #(-> (get image-coords % default-pixel)))
   str/join
   (#(parse-int % 2))
   algo-lookup
   (vector c)))

(defn part-1 []
  (let [lines (->>
               "src/aoc2021/day_20_sample.txt"
               file->lines)
        algo-lookup (mapv {\# 1 \. 0} (first lines))
        default-pixel (first algo-lookup)
        image-coords (grid-coords (rest (rest lines)))
        run-1 (->>
               image-coords
               keys
               (map (partial index 0 algo-lookup image-coords))
               (filter #(= 0 (second %)))
               (into {}))
        run-2 (->>
               run-1
               keys
               (map (partial index 1 algo-lookup run-1))
               (filter #(= 1 (second %)))
               (into {}))]
    (->> run-1)))

(comment

  (part-1)

  .)
