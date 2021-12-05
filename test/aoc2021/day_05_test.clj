(ns aoc2021.day-05-test
  (:require [aoc2021.day-05 :refer :all]
            [clojure.test :refer :all]))

(deftest test-all-range
  (are [expected actual] (= expected actual)
    [0] (all-range 0 0)
    [0 1] (all-range 0 1)
    [1 0] (all-range 1 0)))

(deftest test-horizontal?
  (are [expected segment] (= expected (horizontal? segment))
    true [[0 1] [2 1]]
    true [[0 1] [0 1]]
    false [[0 1] [0 2]]
    false [[0 0] [1 1]]
    ))

(deftest test-vertical?
  (are [expected segment] (= expected (vertical? segment))
    true [[0 1] [0 1]]
    true [[2 2] [2 1]]
    true [[0 1] [0 2]]
    false [[0 0] [1 1]]
    ))

(deftest test-diagonal?
  (are [expected segment] (= expected (diagonal? segment))
    true [[0 1] [0 1]]
    true [[0 0] [1 1]]
    true [[1 0] [2 1]]
    true [[1 1] [0 0]]
    true [[2 1] [1 0]]
    false [[0 2] [2 1]]
    false [[0 1] [0 2]]
    ))

(deftest test-expand-segment
  (testing "good segments"
    (are [expected _ segment] (= expected (expand-segment segment))
      [[0 0]] <= [[0 0] [0 0]] ;; point
      [[0 0] [0 1] [0 2]] <= [[0 0] [0 2]] ;; vertical
      [[0 2] [0 1] [0 0]] <= [[0 2] [0 0]] ;; vertical backward
      [[0 0] [1 0] [2 0]] <= [[0 0] [2 0]] ;; horizontal
      [[2 0] [1 0] [0 0]] <= [[2 0] [0 0]] ;; horizontal backward
      [[0 0] [1 1] [2 2]] <= [[0 0] [2 2]] ;; diagonal
      [[2 2] [1 1] [0 0]] <= [[2 2] [0 0]] ;; diagonal backward
      ))
  (testing "askew segment fails"
    (is (thrown? IllegalArgumentException (expand-segment [[0 0] [1 2]])))))

(deftest test-part-1
  (is (= 5084 (part-1))))

(deftest test-part-2
  (is (= 17882 (part-2))))
