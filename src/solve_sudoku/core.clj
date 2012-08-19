(ns solve-sudoku.core
  (:require [clojure.string :as string]))

(def input [[1 0 0 9 0 7 0 0 3]
            [0 8 0 0 0 0 0 7 0]
            [0 0 9 0 0 0 6 0 0]
            [0 0 7 2 0 9 4 0 0]
            [4 1 0 0 0 0 0 9 5]
            [0 0 8 5 0 4 3 0 0]
            [0 0 3 0 0 0 7 0 0]
            [0 5 0 0 0 0 0 4 0]
            [2 0 0 8 0 6 0 0 9]])

(defn valid-puzzle-seq? [coll]
  (let [filtered (remove zero? coll)]
    (= (count (distinct filtered))
       (count filtered))))

(defn valid-assoc?
  "Returns true if assoc just made at pos results in valid puzzle."
  [puzzle pos]
  (let [[r c] pos
        row (puzzle r)
        col (nth (apply map vector puzzle) c)
        square-top (* (quot r 3) 3)
        square-left (* (quot c 3) 3)
        square-rows (subvec puzzle square-top (+ square-top 3))
        square (map #(subvec % square-left (+ square-left 3)) square-rows)]
    (and (valid-puzzle-seq? row)
         (valid-puzzle-seq? col)
         (valid-puzzle-seq? (flatten square)))))

(defn next-pos [pos]
  (let [[r c] pos]
    (if (< c 8)
      [r (inc c)]
      [(inc r) 0])))

(defn puzzle->str [puzzle]
  (let [row->str (fn [row] (str (string/join "|" (map #(apply str %) (partition 3 row))) \newline))
        columnified-puzzle (map row->str puzzle)
        row-sets (map #(apply str %) (partition 3 columnified-puzzle))]
    (string/join "---+---+---\n" row-sets)))

(defn solve-puzzle
  ([puzzle] (solve-puzzle puzzle [0 0]))
  ([puzzle pos]
     (cond
      (and (>= (pos 0) 8) (>= (pos 1) 8)) puzzle
      (not (zero? (get-in puzzle pos))) (recur puzzle (next-pos pos))
      :else (let [guesses (map #(assoc-in puzzle pos %) (range 1 10))
                  valid-guesses (filter #(valid-assoc? % pos) guesses)
                  valid-solutions (map #(solve-puzzle % (next-pos pos)) valid-guesses)]
              (some identity valid-solutions)))))


(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
