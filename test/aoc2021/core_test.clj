(ns aoc2021.core-test
  (:require [clojure.test :refer :all]
            [aoc2021.core :refer :all]))

(deftest test-parse-int
  (testing "parse-int"
    (is (= 1 (parse-int "1")))
    (is (= 2 (parse-int "10" 2)))))

(deftest test-parse-long
  (testing "parse-long"
    (is (= 1 (parse-long "1")))
    (is (= 2 (parse-long "10" 2)))))
