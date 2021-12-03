(ns aoc2021.day-03
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
    (are [found criteria-fn candidates]
         (= found (search criteria-fn candidates))
      [[0]]   (constantly 1) [[0]]
      [[1]]   (constantly 1) [[1]]
      [[1]]   (constantly 1) [[0] [1]]
      [[1 1]] (constantly 1) [[0 1] [1 0] [1 1]]
      [[0 1]] (constantly 0) [[0 1] [1 0] [1 1]])))
