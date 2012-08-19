(ns solve-sudoku.core
  (:use [solve-sudoku.solver :only [solve-puzzle str-to-puzzle puzzle-to-str]])
  (:gen-class))

(defn -main
  "Reads puzzle in file at path and prints puzzle and its solution to *out*."
  [path & args]
  (let [input (str-to-puzzle (slurp path))]
    (println "Input:")
    (println (puzzle-to-str input))
    (println "Solution:")
    (println (puzzle-to-str (solve-puzzle input)))))
