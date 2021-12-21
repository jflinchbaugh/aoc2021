(ns aoc2021.day-15-test
  (:require [aoc2021.day-15 :refer :all]
            [clojure.test :refer :all]))

(def small-map [
"1163751742"
"1381373672"
"2136511328"
"3694931569"
"7463417111"
"1319128137"
"1359912421"
"3125421639"
"1293138521"
"2311944581"
])

(deftest test-parse-map
  (is (= [[1 1 6 3 7 5 1 7 4 2]
          [1 3 8 1 3 7 3 6 7 2]
          [2 1 3 6 5 1 1 3 2 8]
          [3 6 9 4 9 3 1 5 6 9]
          [7 4 6 3 4 1 7 1 1 1]
          [1 3 1 9 1 2 8 1 3 7]
          [1 3 5 9 9 1 2 4 2 1]
          [3 1 2 5 4 2 1 6 3 9]
          [1 2 9 3 1 3 8 5 2 1]
          [2 3 1 1 9 4 4 5 8 1]] (parse small-map))))

(deftest test-map-coords
  (is (= {[0 0] 1 [1 0] 2 [2 0] 3
          [0 1] 4 [1 1] 5 [2 1] 6
          [0 2] 7 [1 2] 8 [2 2] 9}
        (map-coords [[1 2 3]
                     [4 5 6]
                     [7 8 9]]))))

(deftest test-adjacent-coords
  (is (= [[0 1] [0 -1] [1 0] [-1 0]] (adjacent-coords [0 0]))))


(deftest test-walk
  (is (= [#{} #{}] (walk inc [#{} #{}])))
  (is (= [#{[[0 0] 1]} #{}] (walk inc [#{} #{[[0 0] 0]}]))))
