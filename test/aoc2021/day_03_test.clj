(ns aoc2021.day-03-test
  (:require [aoc2021.day-03 :refer :all]
            [clojure.test :refer :all]))

(deftest test-line->bits
  (testing "line->bits"
    (are [bits line]
         (= bits (line->bits line))
      [0] "0"
      [1 0] "10"
      [] "")))

(deftest test-bits->int
  (testing "bits->int"
    (are [int bits]
         (= int (bits->int bits))
      0 [0]
      2 [1 0]
      1 [0 0 0 1])))

(deftest test-most-common-pos
  (testing "most-common-pos"
    (are [common-bit pos bits]
         (= common-bit (most-common-pos pos bits))
      0 0 [[0]]
      1 0 [[1]]
      1 0 [[1] [0]]
      1 0 [[0] [1]]
      0 0 [[0] [1] [0]]
      1 1 [[0 1] [1 0] [0 1]])))

(deftest test-least-common-pos
  (testing "least-common-pos"
    (are [common-bit pos bits]
         (= common-bit (least-common-pos pos bits))
      1 0 [[0]]
      0 0 [[1]]
      0 0 [[1] [0]]
      0 0 [[0] [1]]
      1 0 [[0] [1] [0]]
      0 1 [[0 1] [1 0] [0 1]])))

(deftest test-search
  (testing "search"
    (are [found criteria-fn candidates desc]
         (= found (search criteria-fn candidates))
      [[0]]   (constantly 1) [[0]] "only 1 candidate"
      [[1]]   (constantly 1) [[1]] "only 1 candidate"
      [[1]]   (constantly 1) [[0] [1]] "first match"
      [[1 1]] (constantly 1) [[0 1] [1 0] [1 1]] "progress position"
      [[0 1]] (constantly 0) [[0 1] [1 0] [1 1]]
          "only needed to search by first position"
      )))

(deftest test-common-bits
  (testing "common-bits"
    (are [expected common-fn bits-list]
        (= expected (common-bits common-fn bits-list))
      [1]   (constantly 1) [[0]]
      [1]   (constantly 1) [[1]]
      [1]   (constantly 1) [[0] [1]]
      [1 1] (constantly 1) [[0 1] [1 0] [1 1]]
      [0 0] (constantly 0) [[0 1] [1 0] [1 1]]
      [1 0] most-common-pos [[1 0] [1 0] [0 1]]
      )))
