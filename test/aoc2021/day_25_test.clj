(ns aoc2021.day-25-test
  (:require [aoc2021.day-25 :refer :all]
            [clojure.test :refer :all]))

(deftest test-rows->columns
  (is (= [[1 3] [2 4]] (rows->columns [[1 2] [3 4]])))
  (is (= [[1 3 5] [2 4 6]] (rows->columns [[1 2] [3 4] [5 6]])))
  (is (= [[1 4] [2 5] [3 6]] (rows->columns [[1 2 3] [4 5 6]])))
  )
