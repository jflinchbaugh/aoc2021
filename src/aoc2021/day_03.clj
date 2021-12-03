(ns aoc2021.day-03
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn most-common-bit [bit ints]
  (if
   (first
    (last
     (sort-by
      #(vector (count (second %)) (not (first %)))
      (group-by #(bit-test % bit) ints))))
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
        epsilon-rate (- sum gamma-rate)]
    (* gamma-rate epsilon-rate)))

(defn to-bits [w i]
  (->> w range (mapv #(if (bit-test i (- w % 1)) 1 0))))

(defn bits->int [bits]
  (-> bits str/join (parse-int 2)))

(defn most-common-pos [pos bits]
  (first
   (last
    (sort-by
     #(vector (count (second %)) (first %)) ;; sort by frequency then bit
     (group-by #(nth % pos) bits)))))

(def least-common-pos (comp {0 1 1 0} most-common-pos))

(defn search [criteria-fn pos candidates]
  (if (> 2 (count candidates))
    candidates
    (let [criteria (mapv #(criteria-fn % candidates)
                         (range (count (first candidates))))]
      (search criteria-fn (inc pos)
              (filter #(= (nth % pos) (nth criteria pos)) candidates)))))

(defn part-2 []
  (let [lines (->>
               "src/aoc2021/day_03.txt"
               file->lines)
        ints (map #(parse-int % 2) lines)
        width (count (seq  (first lines)))
        as-bits (map #(to-bits width %) ints)
        o2-gen-rating (bits->int (first (search most-common-pos 0 as-bits)))
        co2-scrubber-rating (bits->int (first (search least-common-pos 0 as-bits)))]
    (* o2-gen-rating co2-scrubber-rating)))

(comment

  (part-1);; => 3242606

  (part-2);; => 4856080

  .)
