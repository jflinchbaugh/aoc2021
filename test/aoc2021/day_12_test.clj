(ns aoc2021.day-12-test
  (:require [aoc2021.day-12 :refer :all]
            [clojure.test :refer :all]))

(deftest test-valid-path?
  (is (= true (valid-path? ["a" "A"])))
  (is (= false (valid-path? ["a" "a"])))
  (is (= true (valid-path? ["A" "A"])))
  )

(deftest test-part-1
  (is (= 4775 (part-1))))
