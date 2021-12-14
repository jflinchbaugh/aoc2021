(ns aoc2021.day-13
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn parse [lines]
  (let [[dot-input _ fold-input] (partition-by str/blank? lines)
        dots (map
              (fn [line]
                (map parse-int (str/split line #",")))
              dot-input)
        folds (map
               (fn [line]
                 (let [[_ _ axis val] (str/split line #"[ =]")]
                   [(keyword axis) (parse-int val)]))
               fold-input)]
    {:dots dots
     :folds folds}))

(defn fold [dots axis crease]
  (->>
    dots
    (map
      (fn [[x y]]
        (if (#{:x} axis)
          (if (< x crease)
            [x y]
            [(- (* 2 crease) x) y])
          (if (< y crease)
            [x y]
            [x (- (* 2 crease) y)]))))
    set))

(defn part-1 []
  (let [{:keys [dots folds]} (->>
          "src/aoc2021/day_13.txt"
          file->lines
          parse)
        first-fold (first folds)
        [axis crease] first-fold]
    (count (fold dots axis crease))))

(comment

  (part-1);; => 802

  .)
