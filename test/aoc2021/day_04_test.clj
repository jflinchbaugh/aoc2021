(ns aoc2021.day-04-test
  (:require [aoc2021.day-04 :refer :all]
            [clojure.test :refer :all]))

(deftest test-lines->drawings
  (is (= [1 2 3] (lines->drawings ["1,2,3" "" "a,b,c"]))))

(deftest test-lines->boards
  (is (= [[[1 2 3]
           [4 5 6]
           [7 8 9]]
          [[9 8 7]
           [6 5 4]
           [3 2 1]]]
        (lines->boards ["a,b,c"
                        ""
                        "1  2  3"
                        "4 5 6"
                        "7 8 9"
                        ""
                        "9 8 7"
                        "6 5 4"
                        "3 2 1"
                        ""]))))
