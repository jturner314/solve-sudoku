(ns solve-sudoku.solver
  (:use [clojure.set :only [difference]])
  (:require [clojure.string :as string]))

(defn- valid-guesses
  [puzzle pos]
  (let [valid-nums (apply hash-set (range 1 10))
        [r c] pos
        row (puzzle r)
        col (map #(% c) puzzle)
        [square-top square-left] (map #(* (quot % 3) 3) pos)
        square (mapcat (fn [row] (subvec row square-left (+ square-left 3)))
                       (subvec puzzle square-top (+ square-top 3)))]
    (difference valid-nums row col square)))

(defn- next-pos
  [pos]
  (let [[r c] pos]
    (if (< c 8)
      [r (inc c)]
      [(inc r) 0])))

(defn solve-puzzle
  ([puzzle] (solve-puzzle puzzle [0 0]))
  ([puzzle pos]
     (cond
      (and (>= (pos 0) 8) (>= (pos 1) 8)) puzzle
      (not (zero? (get-in puzzle pos))) (recur puzzle (next-pos pos))
      :else (let [guesses (valid-guesses puzzle pos)
                  solutions (map #(solve-puzzle (assoc-in puzzle pos %) (next-pos pos)) guesses)]
              (some identity solutions)))))

(defn puzzle->str
  [puzzle]
  (let [col-sep \|
        row-sep "+---+---+---+\n"
        grouped (map (fn [row] (map #(apply str %) (partition 3 row))) puzzle)
        rows (map (fn [row] (str col-sep (string/join col-sep row) col-sep \newline)) grouped)
        rows-grouped (map #(string/join %) (partition 3 rows))
        stringified (str row-sep (string/join row-sep rows-grouped) row-sep)]
    (apply str (replace {\0 \space} stringified))))
