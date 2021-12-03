(ns aoc2021.day-02-test
  (:require [aoc2021.day-02 :refer :all]
            [clojure.test :refer :all]))

(deftest test-results
  (testing "results"
    (is (= 1654760 (part-1)))
    (is (= 1956047400 (part-2)))))

(deftest test-translate
  (testing "translate"
    (are [offsets dir dist]
        (= offsets (translate dir dist))
      [0 0] :forward 0
      [0 0] :up 0
      [0 0] :down 0
      [2 0] :forward 2
      [0 -2] :up 2
      [0 2] :down 2
      ))
  (testing "bad direction"
    (is (thrown? IllegalArgumentException (translate :wrong 0)))))

(deftest test-move-2
  (testing "move-2"
    (are [expected current move]
        (= expected (move-2 current move))
      [0 0 0] [0 0 0] [:forward 0]
      [1 0 0] [0 0 0] [:forward 1]
      [0 0 2] [0 0 0] [:down 2]
      [0 0 -2] [0 0 0] [:up 2]
      [2 4 2] [0 0 2] [:forward 2]
      ))
  (testing "bad direction"
    (is (thrown? IllegalArgumentException (move-2 [0 0 0] [:wrong 0])))))
