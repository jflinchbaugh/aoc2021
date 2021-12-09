(ns aoc2021.day-08
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn parse [lines]
  (for [line lines]
    (for [part (str/split line #" \| ")]
      (for [group (str/split part #" ")]
        (for [segment group]
          (keyword (str segment)))))))

(defn part-1 []
  (->> "src/aoc2021/day_08.txt"
       file->lines
       parse
       (map second)
       (mapcat concat)
       (map count)
       (filter #{2 4 3 7})
       count))

(def segments
  {#{:a :b :c :e :f :g} 0
   #{:c :f} 1
   #{:a :c :d :e :g} 2
   #{:a :c :d :f :g} 3
   #{:b :c :d :f} 4
   #{:a :b :d :f :g} 5
   #{:a :b :d :e :f :g} 6
   #{:a :c :f} 7
   #{:a :b :c :d :e :f :g} 8
   #{:a :b :c :d :f :g} 9})

(def segment-frequencies
  {:a 8
   :b 6
   :c 8
   :d 7
   :e 4
   :f 9
   :g 7})

(defn map-segments [numbers]
  (let [seg-freqs (->> numbers flatten frequencies)
        e-seg (->> seg-freqs (filter #(= 4 (second %))) first first)
        f-seg (->> seg-freqs (filter #(= 9 (second %))) first first)
        b-seg (->> seg-freqs (filter #(= 6 (second %))) first first)
        segs-1 (->> numbers (filter #(= 2 (count %))) first)
        c-seg (->> segs-1 (remove #{f-seg}) first)
        segs-7 (->> numbers (filter #(= 3 (count %))) first)
        a-seg (->> segs-7 (remove #{c-seg f-seg}) first)
        segs-4 (->> numbers (filter #(= 4 (count %))) first)
        d-seg (->> segs-4 (remove #{b-seg c-seg f-seg}) first)
        g-seg (->>
               seg-freqs
               (filter
                #(and (= 7 (second %)) (not= d-seg (first %))))
               first
               first)]
    {e-seg :e
     f-seg :f
     b-seg :b
     c-seg :c
     a-seg :a
     d-seg :d
     g-seg :g}))

(defn solve [[example-segments segment-values]]
  (let [segment-mapping (map-segments example-segments)
        mapped-values (parse-int
                       (str/join
                        (for [digit segment-values]
                          (str
                           (segments
                            (set (for [segment digit]
                                   (segment-mapping segment))))))))]
    mapped-values))

(defn part-2 []
  (->>
   "src/aoc2021/day_08.txt"
   file->lines
   parse
   (map solve)
   (reduce +)))

(comment
  (part-1);; => 525

  (part-2);; => 1083859

  .)

