(ns aoc2021.day-15
  (:require [aoc2021.core :refer :all]))

(def inf Integer/MAX_VALUE)

(defn parse [map-lines]
  (->>
   map-lines
   (mapv
    (fn [line]
      (mapv (comp parse-int str) line)))))

(defn map-coords [m]
  (into {}
        (for [x (range (count (first m)))
              y (range (count m))]
          [[x y] (get-in m [y x])])))

(defn starting-costs [coords]
  (let [ks (keys coords)
        max-x (reduce max 0 (map first ks))
        max-y (reduce max 0 (map second ks))]
    (assoc
     (into {}
           (for [x (range (int max-x))
                 y (range (int max-y))]
             [[x y] {:cost inf :prev nil}]))
     [0 0] {:cost 0 :prev nil})))

(defn adjacent-coords [[x y]]
  [[x (inc y)] [x (dec y)] [(inc x) y] [(dec x) y]])

#_(defn walk [terrain visited unvisited current-coord]
    (let [current (unvisited current-coord)
          to-visit (adjacent-coords current-coord)]
      [terrain
       (merge
        unvisited
        (into {}
              (map
               (fn [coord]
                 (when-let [node (unvisited coord)]
                   (let [old-cost (:cost node)
                         new-cost (+ (:cost current) (terrain coord))]
                     (if (<= old-cost new-cost)
                       node
                       [coord (assoc node :cost new-cost :prev current-coord)]))))
               to-visit)))]))

(defn walk [f [visited unvisited]]
  (if (empty? unvisited)
    [visited unvisited]
    [(set
      (map
       (fn [[loc val]]
         [loc (f val)])
       unvisited))
     #{}]))

(defn part-1 []
  (let [terrain (->>
                 "src/aoc2021/day_15.txt"
                 file->lines
                 parse
                 map-coords)
        costs (starting-costs terrain)]
    (walk terrain {} costs [0 0])))

(comment

  (part-1)

  .)
