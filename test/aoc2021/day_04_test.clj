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

(deftest test-boards->coords
  (is (= {1 #{ [0 0 0] }
          2 #{ [0 0 1] [2 0 0] }
          3 #{ [0 1 0] [2 1 0] }
          4 #{ [0 1 1] }
          5 #{ [1 0 0] [2 1 1] }
          6 #{ [1 0 1] }
          7 #{ [1 1 0] [2 0 1] }
          8 #{ [1 1 1] }}
        (boards->coords [[[1 2]
                          [3 4]]
                         [[5 6]
                          [7 8]]
                         [[2 7]
                          [3 5]]]))))

(deftest test-complete-row?
  (are [expected coords]
      (= expected (complete-row? coords))
    true [[0 0 0] [0 0 1] [0 0 2] [0 0 3] [0 0 4]]
    true [[1 0 0] [1 0 1] [1 0 2] [1 0 3] [1 0 4]]
    true [[1 2 0] [1 2 1] [1 2 2] [1 2 3] [1 2 4]]
    false [[1 2 1] [1 2 1] [1 2 1] [1 2 1] [1 2 1]]
    false [[2 2 0] [1 2 1] [1 2 2] [1 2 3] [1 2 4]]
    false [[1 1 0] [1 2 1] [1 2 2] [1 2 3] [1 2 4]]
    false []
    ))

(deftest test-complete-col?
  (are [expected coords]
      (= expected (complete-col? coords))
    true [[0 0 0] [0 1 0] [0 2 0] [0 3 0] [0 4 0]]
    true [[1 0 0] [1 1 0] [1 2 0] [1 3 0] [1 4 0]]
    true [[1 0 1] [1 1 1] [1 2 1] [1 3 1] [1 4 1]]
    false [[1 1 1] [1 1 1] [1 1 1] [1 1 1] [1 1 1]]
    false [[2 0 0] [1 1 0] [1 2 0] [1 3 0] [1 4 0]]
    false [[1 0 2] [1 1 1] [1 2 1] [1 3 1] [1 4 1]]
    false []
    ))

(deftest test-complete-diag-down?
  (are [expected coords]
      (= expected (complete-diag-down? coords))
    true [[0 0 0] [0 1 1] [0 2 2] [0 3 3] [0 4 4]]
    false [[0 4 0] [0 3 1] [0 2 2] [0 1 3] [0 0 4]]
    false [[0 0 0] [0 0 0] [0 0 0] [0 0 0] [0 0 0]]
    false [[1 0 0] [0 1 1] [0 2 2] [0 3 3] [0 4 4]]
    false []
    ))

(deftest test-complete-diag-up?
  (are [expected coords]
      (= expected (complete-diag-up? coords))
    true [[0 4 0] [0 3 1] [0 2 2] [0 1 3] [0 0 4]]
    false [[0 0 0] [0 1 1] [0 2 2] [0 3 3] [0 4 4]]
    false [[0 0 0] [0 0 0] [0 0 0] [0 0 0] [0 0 0]]
    false [[1 0 0] [0 1 1] [0 2 2] [0 3 3] [0 4 4]]
    false []
    ))

(deftest test-any-complete?
  (are [expected coords]
      (= expected (any-complete? coords))
    true [[0 4 0] [0 3 1] [0 2 2] [0 1 3] [0 0 4]]
    true [[0 0 0] [0 1 1] [0 2 2] [0 3 3] [0 4 4]]
    true [[0 0 0] [0 1 0] [0 2 0] [0 3 0] [0 4 0]]
    true [[0 0 0] [0 0 1] [0 0 2] [0 0 3] [0 0 4]
          [1 0 0] [1 0 1] [1 0 2] [1 0 3] [1 0 4]]
    false [[1 0 0] [0 1 1] [0 2 2] [0 3 3] [0 4 4]]
    ))

(deftest test-part-1
  (= 82440 (part-1)))
