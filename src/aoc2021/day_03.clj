(ns aoc2021.day-03
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn line->bits [cs]
  (map (comp parse-int str) (seq cs)))

(defn bits->int [bits]
  (-> bits str/join (parse-int 2)))

(defn most-common-pos [pos bits]
  (first
   (last
    (sort-by
     #(vector (count (second %)) (first %)) ;; sort by frequency then bit
     (group-by #(nth % pos) bits)))))

(def least-common-pos (comp {0 1 1 0} most-common-pos))

(defn common-bits [common-fn bits]
  (map #(common-fn % bits) (range (count (first bits)))))

(defn part-1 []
  (let [lines (file->lines "src/aoc2021/day_03.txt")
        bits (map line->bits lines)
        width (count (first bits))
        most-common-bits (common-bits most-common-pos bits)
        least-common-bits (common-bits least-common-pos bits)
        gamma-rate (bits->int most-common-bits)
        epsilon-rate (bits->int least-common-bits)]
    (* gamma-rate epsilon-rate)))

(defn search
  ([criteria-fn candidates]
   (search criteria-fn 0 candidates))
  ([criteria-fn pos candidates]
   (if (> 2 (count candidates))
     candidates
     (let [width (count (first candidates))
           criteria (mapv #(criteria-fn % candidates)
                      (range width))]
       (recur criteria-fn (inc pos)
         (filter #(= (nth % pos) (nth criteria pos)) candidates))))))

(defn part-2 []
  (let [lines (file->lines "src/aoc2021/day_03.txt")
        bits (map line->bits lines)
        o2-gen-rating (bits->int (first (search most-common-pos bits)))
        co2-scrubber-rating (bits->int (first (search least-common-pos bits)))]
    (* o2-gen-rating co2-scrubber-rating)))

(comment

  (part-1);; => 3242606

  (part-2);; => 4856080

  .)
