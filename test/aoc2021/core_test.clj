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

(deftest test-abs
  (testing "abs"
    (is (= 2 (abs -2)))
    (is (= 2 (abs 2)))))

(deftest test-avg
  (testing "avg"
    (is (= 2 (avg [1 2 3])))
    (is (= 2 (avg [2])))
    (is (= nil (avg [])))))

(deftest test-file->lines
  (testing "file->lines"
    (is (= ["1" "2"] (file->lines "test/aoc2021/file.txt")))))
