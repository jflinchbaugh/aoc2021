(ns aoc2021.day-13-test
  (:require  [aoc2021.day-13 :refer :all]
             [clojure.test :refer :all]))

(deftest test-part-1
  (is (= 802 (part-1))))

(deftest test-part-2
  (is (=
       ["###  #  # #  # #### ####  ##  #  # ### "
        "#  # # #  #  # #       # #  # #  # #  #"
        "#  # ##   #### ###    #  #    #  # ### "
        "###  # #  #  # #     #   # ## #  # #  #"
        "# #  # #  #  # #    #    #  # #  # #  #"
        "#  # #  # #  # #    ####  ###  ##  ### "]
       (part-2))))
