(ns solve-sudoku.core
  (:use [clojure.set :only [difference]])
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

(defn valid-guesses
  [puzzle pos]
  (let [valid-nums (apply hash-set (range 1 10))
        [r c] pos
        row (puzzle r)
        col (map #(% c) puzzle)
        [square-top square-left] (map #(* (quot % 3) 3) pos)
        square (mapcat (fn [row] (subvec row square-left (+ square-left 3)))
                       (subvec puzzle square-top (+ square-top 3)))]
    (difference valid-nums row col square)))

(defn next-pos
  [pos]
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
      :else (let [guesses (valid-guesses puzzle pos)
                  solutions (map #(solve-puzzle (assoc-in puzzle pos %) (next-pos pos)) guesses)]
              (some identity solutions)))))


(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
