(ns aoc2021.day-24-test
  (:require [aoc2021.day-24 :refer :all]
            [clojure.test :refer :all]))

(deftest run-samples
  (is (= -1
         (:x (run-1
              (map parse-code ["inp x"
                               "mul x -1"])
              "1"))))
  (is (= 1
         (:z (run-1
              (map parse-code ["inp z"
                               "inp x"
                               "mul z 3"
                               "eql z x"])
              "13"))))
  (is (= 0
         (:z (run-1
              (map parse-code [])
              "12"))))
  (is (= {:w 1
          :x 0
          :y 0
          :z 1}
         (run-1
           (map parse-code ["inp w"
                            "add z w"
                            "mod z 2"
                            "div w 2"
                            "add y w"
                            "mod y 2"
                            "div w 2"
                            "add x w"
                            "mod x 2"
                            "div w 2"
                            "mod w 2"])
              "9"))))

