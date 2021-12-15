(ns aoc2021.day-14
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn expand [rules start]
  (let [triples (->>
                 start
                 (map str)
                 (partition 2 1)
                 (map rules))]
    (str/join (concat (first triples) (mapcat rest (rest triples))))))

(defn part-1 []
  (let [[start _ & rules] (->>
                           "src/aoc2021/day_14.txt"
                           file->lines)
        rules (->> rules
                   (map
                    (fn [i]
                      (let [[in new] (str/split i #" -> ")
                            in (map str in)]
                        [in [(first in) new (second in)]])))
                   (into {}))
        freqs (->>
               start
               (iterate #(expand rules %))
               (take (inc 10))
               last
               frequencies
               (sort-by second))]
    (- (second (last freqs)) (second (first freqs)))))

(comment
  (part-1);; => 4517

  .)

