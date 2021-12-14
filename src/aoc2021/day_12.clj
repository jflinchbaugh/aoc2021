(ns aoc2021.day-12
  (:require [aoc2021.core :refer :all]
            [clojure.string :as str]))

(defn build-cave-map [lines]
  (->> lines
       (map #(str/split % #"-"))
       (mapcat #(-> [%1 (reverse %1)]))
       (group-by first)
       (map (fn [[k v]] [k (map second v)]))
       (remove (fn [[k _]] (#{"end"} k)))
       (into {})))

(defn valid-path-1? [path]
  (->>
   path
   (filter #(re-matches #"[a-z]+" %))
   frequencies
   vals
   (every? #{1})))

(defn valid-path-2? [path]
  (let [freqs (->>
               path
               (filter #(re-matches #"[a-z]+" %))
               frequencies)]
    (every?
     (fn [[k v]]
       (if (#{"start" "end"} k)
         (#{0 1} v)
         (#{1 2} v)))
     freqs)))

(defn walk
  ([valid-path? cave-map]
   (walk valid-path? cave-map [["start"]]))
  ([valid-path? cave-map paths]
   (let [next-paths (->>
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
       (walk valid-path? cave-map next-paths)))))

(defn part-1 []
  (->>
   "src/aoc2021/day_12.txt"
   file->lines
   build-cave-map
   (walk valid-path-1?)
   count))

(defn part-2 []
  (->>
   "src/aoc2021/day_12.txt"
   file->lines
   build-cave-map
   (walk valid-path-2?)
   count))

(comment
  (part-1);; => 4775

  (part-2)

  (->>
    ["start-end"
     "start-x"
     "y-x"
     "A-y"
     "A-z"
     "y-z"
     "x-end"]
    build-cave-map
    (walk valid-path-2?))

  .)
