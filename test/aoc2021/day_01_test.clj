(ns aoc2021.day-01-test
  (:require [aoc2021.day-01 :refer :all]
            [clojure.test :refer :all]))

(deftest test-results
  (testing "results"
    (is (= 1715 (part-1)))
    (is (= 1739 (part-2)))))

