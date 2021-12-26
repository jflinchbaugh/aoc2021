(ns aoc2021.day-20-test
  (:require [aoc2021.day-20 :refer :all]
            [clojure.test :refer :all]))

(deftest test-grid-coords
  (is (= {[0 0] 1
          [1 0] 1
          [2 1] 1}
        (grid-coords
          ["#."
           "#."
           ".#"]))))

(deftest test-around
  (is (= [[-1 -1] [-1 0] [-1 1]
          [0 -1] [0 0] [0 1]
          [1 -1] [1 0] [1 1]]
        (around [0 0]))))
