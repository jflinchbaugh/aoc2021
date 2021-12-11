(ns aoc2021.day-10-test
  (:require [aoc2021.day-10 :refer :all]
            [clojure.test :refer :all]))

(deftest test-part-1
  (is (= 319329 (part-1))))

(deftest test-part-2
  (is (= 3515583998 (part-2))))

(deftest test-check
  (is (= :good (check "")))
  (is (= :good (check "()")))
  (is (= [:incomplete [\)]] (check "(")))
  (is (= [:corrupt \]] (check "(]"))))

(deftest test-score-auto-complete
  (is (= 1 (score-auto-complete [\)])))
  (is (= 2 (score-auto-complete [\]])))
  (is (= 3 (score-auto-complete [\}])))
  (is (= 4 (score-auto-complete [\>])))
  (is (= 6 (score-auto-complete [\) \)])))
  (is (= 12 (score-auto-complete [\] \]])))
  (is (= 18 (score-auto-complete [\} \}])))
  (is (= 24 (score-auto-complete [\> \>])))
  )
