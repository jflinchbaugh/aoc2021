(ns aoc2021.day-12-test
  (:require [aoc2021.day-12 :refer :all]
            [clojure.test :refer :all]))

(deftest test-valid-path-1?
  (is (= true (valid-path-1? ["a" "A"])))
  (is (= false (valid-path-1? ["a" "a"])))
  (is (= true (valid-path-1? ["A" "A"])))
  (is (= true (valid-path-2? ["start" "end"])))
  )

(deftest test-valid-path-2?
  (is (= true (valid-path-2? ["a" "A"])))
  (is (= true (valid-path-2? ["a" "A" "a"])))
  (is (= true (valid-path-2? ["a" "A" "a" "A"])))
  (is (= false (valid-path-2? ["a" "A" "a" "A" "a"])))
  (is (= true (valid-path-2? ["a" "a"])))
  (is (= true (valid-path-2? ["A" "A"])))
  (is (= true (valid-path-2? ["start" "end"])))
  (is (= false (valid-path-2? ["start" "start" "end"])))
  (is (= false (valid-path-2? ["start" "end" "end"])))
  )

(deftest test-part-1
  (is (= 4775 (part-1))))
