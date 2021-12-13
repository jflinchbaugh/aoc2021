(ns aoc2021.day-04
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]
            [clojure.set :as set]))

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
   (map build-board)))

(defn boards->coords [boards]
  (apply merge-with (comp #(apply hash-set %) concat)
         (mapcat
          (fn [board bi]
            (mapcat
             (fn [row ri]
               (map
                (fn [col ci]
                  {col #{[bi ri ci]}})
                row (range)))
             board (range)))
          boards (range))))

(defn complete-row? [coords]
  ((complement not-any?)
   #(<= 5 (count %))
   (vals (group-by (juxt first second) (set coords)))))

(defn complete-col? [coords]
  ((complement not-any?)
   #(<= 5 (count %))
   (vals (group-by (juxt first last) (set coords)))))

(defn diag-down? [coord]
  (= (second coord) (last coord)))

(defn complete-diag-down? [coords]
  (->>
   coords
   set
   (filter diag-down?)
   (group-by first)
   vals
   ((complement not-any?) #(<= 5 (count %)))))

(defn diag-up? [coord]
  (= 4 (+ (second coord) (last coord))))

(defn complete-diag-up? [coords]
  (->>
   coords
   set
   (filter diag-up?)
   (group-by first)
   vals
   ((complement not-any?) #(<= 5 (count %)))))

(defn any-complete? [coords]
  (or
   (complete-diag-down? coords)
   (complete-diag-up? coords)
   (complete-row? coords)
   (complete-col? coords)))

(defn coord-reducer
  [coords c d]
  [(conj (first c) d) (sort (concat (second c) (coords d)))])

(defn get-winner-card-numbers [winner-state]
  (->>
   winner-state
   second
   (partition-by first)
   ((juxt
     #(filter complete-col? %)
     #(filter complete-row? %)
     #(filter complete-diag-down? %)
     #(filter complete-diag-up? %)))
   (remove empty?)
   (mapcat concat)
   (mapcat concat)
   (map first)
   set))

(defn part-1 []
  (let [lines (file->lines "src/aoc2021/day_04.txt")
        drawings (lines->drawings lines)
        boards (lines->boards lines)
        coords (boards->coords boards)
        winner-state (->>
                      drawings
                      (reductions (partial coord-reducer coords) [[] []])
                      rest
                      (filter (comp any-complete? second))
                      first)
        drawn (->> winner-state first)
        last-drawn (->> drawn last)
        winner-card-num (first (get-winner-card-numbers winner-state))
        winner-card-flat (flatten (nth boards winner-card-num))
        unmarked (remove (set drawn) winner-card-flat)]
    (* last-drawn (reduce + unmarked))))

(defn part-2 []
  (let [lines (file->lines "src/aoc2021/day_04.txt")
        drawings (lines->drawings lines)
        boards (lines->boards lines)
        coords (boards->coords boards)
        winner-states (->>
                       drawings
                       (reductions (partial coord-reducer coords) [[] []])
                       (filter (comp any-complete? second)))
        last-winner-state (->>
                           winner-states
                           (map get-winner-card-numbers))]
    (->>
     winner-states
     (map
      (fn [[k v]]
        [k
         (map identity
           (filter
             (fn [[b c]]
               (any-complete? c))
             (group-by first v)))]))
     ))

  )

(comment

  (part-1);; => 82440

  (part-2)

  .)
