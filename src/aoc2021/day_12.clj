(ns aoc2021.day-12
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn build-cave-map [lines]
  (->> lines
       (map #(str/split % #"-"))
       (mapcat #(-> [%1 (reverse %1)]))
       (group-by first)
       (map (fn [[k v]] [k (map second v)]))
       (into {})))

(defn valid-path? [path]
  (->>
   path
   (filter #(re-matches #"[a-z]+" %))
   frequencies
   vals
   (every? #{1})))

(defn walk
  ([cave-map]
   (walk cave-map [["start"]]))
  ([cave-map paths]
   (let [next-paths
         (->>
          paths
          (mapcat (fn [path]
                    (if (= "end" (last path))
                      [path]
                      (->>
                        path
                        last
                        cave-map
                        (map #(conj path %))
                        (filter valid-path?))))))]

     (if (= next-paths paths)
       paths
       (recur cave-map next-paths)))))

(defn part-1 []
  (->>
   "src/aoc2021/day_12.txt"
   file->lines
   build-cave-map
   walk
   count)
  )

(comment
  (part-1);; => 4775

  .)
