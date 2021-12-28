(ns aoc2021.day-24
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn serial-numbers []
  (->>
   (range (inc 99999999999999) 11111111111111 -1)
   (filter (fn [n] (not-any? #{\0} (str n))))))

(defn parse-code [line]
  (map
   (fn [v]
     (if
      (re-matches #"-*[1234567890]+" v)
       (parse-int v)
       (keyword v)))
   (str/split line #" ")))

(defn lookup-value [registers v]
  (if (keyword? v)
    (get registers v)
    v))

(defn run-1
  ([instructions input]
   (run-1
     instructions
     {:w 0 :x 0 :y 0 :z 0}
     (map (comp parse-int str) (str input))))
  ([[[op a b] & rest-instructions] registers input]
   (case op
     nil (do (prn registers) registers)
     :inp (recur
            rest-instructions
            (assoc registers a (first input))
            (rest input))
     :add (recur
            rest-instructions
            (update registers a + (lookup-value registers b))
            input)
     :mul (recur
            rest-instructions
            (update registers a * (lookup-value registers b))
            input)
     :div (recur
            rest-instructions
            (update registers a (comp int /) (lookup-value registers b))
            input)
     :mod (recur
            rest-instructions
            (update registers a mod (lookup-value registers b))
            input)
     :eql (recur
            rest-instructions
            (assoc registers a (if (= (get registers a) (lookup-value registers b)) 1 0))
            input)
     ))
  )

(defn part-1 []
  (let [instructions (->>
                      "src/aoc2021/day_24.txt"
                      file->lines
                      (map parse-code))
        inputs (serial-numbers)]
    (->>
      inputs
      (pmap (fn [i] [i (zero? (:z (run-1 instructions i)))]))
      (filter second)
      first)))

(comment

  (part-1)

  (serial-numbers)

  (int (/ (int 1) (int 2)))

  ((comp int /) 2 1)

  .)

