(ns solve-sudoku.solver
  (:use [clojure.set :only [difference]])
  (:require [clojure.string :as string]))

(defn- valid-guesses
  "Returns all the numbers from 1 through 9 that do not conflict with the row,
  column, and 3x3 square corresponding to pos."
  [puzzle pos]
  (let [valid-nums (apply hash-set (range 1 10))
        [r c] pos
        row (puzzle r)
        col (map #(% c) puzzle)
        [square-top square-left] (map #(* (quot % 3) 3) pos)
        square (for [rsq (range square-top (+ square-top 3))
                     csq (range square-left (+ square-left 3))]
                 (get-in puzzle [rsq csq]))]
    (difference valid-nums
                (apply sorted-set row)
                (apply sorted-set col)
                (apply sorted-set square))))

(defn- next-pos
  "Returns next position in a sudoku puzzle, wrapping at the end of each row."
  [pos]
  (let [[r c] pos]
    (if (< c 8)
      [r (inc c)]
      [(inc r) 0])))

(defn solve-puzzle
  "Returns the solution to puzzle.  The puzzle should be a vector of vectors
  with zeros representing the unknown cells. Optionally takes a starting
  position; progresses rightward and downward, solving unknown cells."
  ([puzzle] (solve-puzzle puzzle [0 0]))
  ([puzzle pos]
     (cond
      (or (>= (pos 0) 9) (>= (pos 1) 9)) puzzle
      (not (zero? (get-in puzzle pos))) (recur puzzle (next-pos pos))
      :else (let [guesses (valid-guesses puzzle pos)
                  solutions (map #(solve-puzzle (assoc-in puzzle pos %)
                                                (next-pos pos))
                                 guesses)]
              (some identity solutions)))))

(defn str-to-puzzle
  "Returns a puzzle from interpreting string. string should be space-delimited
  with zeros indicating the unknown cells."
  [string]
  (letfn [(line-to-vec [string]
            (vec (map #(Integer/parseInt (str %))
                      (string/split string #"\s+"))))]
    (vec (map line-to-vec (string/split string #"\r?\n")))))

(defn puzzle-to-str
  "Returns a string with a pretty representation of puzzle. Adds borders around
  3x3 squares and replaces zeros with spaces."
  [puzzle]
  (let [outerpose (fn [sep coll] (concat [sep] (interpose sep coll) [sep]))
        with-vert (map #(->> % (partition 3) (outerpose \|) vec) puzzle)
        with-newline (map #(conj % \newline) with-vert)
        with-horiz (outerpose "+---+---+---+\n" (partition 3 with-newline))]
    (apply str (flatten with-horiz))))
