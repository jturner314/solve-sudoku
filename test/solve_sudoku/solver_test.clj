(ns solve-sudoku.solver-test
  (:use clojure.test
        solve-sudoku.solver))

(defn valid-seq?
  [s]
  (and (not-any? zero? s)
       (= (count (distinct s)) (count s))))

(defn valid-solution?
  [solution]
  (if (not (vector? solution))
    false
    (let [rows solution
          cols (apply map vector solution)
          squares (for [square-top (range 0 9 3)
                        square-left (range 0 9 3)]
                    (mapcat (fn [row] (subvec row square-left (+ square-left 3)))
                            (subvec solution square-top (+ square-top 3))))]
      (and (every? valid-seq? rows)
           (every? valid-seq? cols)
           (every? valid-seq? squares)))))

(deftest test-solve-puzzle
  (testing "Puzzles with a solution"
    (is (valid-solution? (solve-puzzle [[1 0 0 9 0 7 0 0 3]
                                        [0 8 0 0 0 0 0 7 0]
                                        [0 0 9 0 0 0 6 0 0]
                                        [0 0 7 2 0 9 4 0 0]
                                        [4 1 0 0 0 0 0 9 5]
                                        [0 0 8 5 0 4 3 0 0]
                                        [0 0 3 0 0 0 7 0 0]
                                        [0 5 0 0 0 0 0 4 0]
                                        [2 0 0 8 0 6 0 0 9]])))
    (is (valid-solution? (solve-puzzle [[0 0 0 3 0 2 0 0 0]
                                        [0 5 0 7 9 8 0 3 0]
                                        [0 0 7 0 0 0 8 0 0]
                                        [0 0 8 6 0 7 3 0 0]
                                        [0 7 0 0 0 0 0 6 0]
                                        [0 0 3 5 0 4 1 0 0]
                                        [0 0 5 0 0 0 6 0 0]
                                        [0 2 0 4 1 9 0 5 0]
                                        [0 0 0 8 0 6 0 0 0]])))
    (is (valid-solution? (solve-puzzle [[0 0 0 8 0 0 0 0 6]
                                        [0 0 1 6 2 0 4 3 0]
                                        [4 0 0 0 7 1 0 0 2]
                                        [0 0 7 2 0 0 0 8 0]
                                        [0 0 0 0 1 0 0 0 0]
                                        [0 1 0 0 0 6 2 0 0]
                                        [1 0 0 7 3 0 0 0 4]
                                        [0 2 6 0 4 8 1 0 0]
                                        [3 0 0 0 0 5 0 0 0]])))
    (is (valid-solution? (solve-puzzle [[3 0 5 0 0 4 0 7 0]
                                        [0 7 0 0 0 0 0 0 1]
                                        [0 4 0 9 0 0 0 3 0]
                                        [4 0 0 0 5 1 0 0 6]
                                        [0 9 0 0 0 0 0 4 0]
                                        [2 0 0 8 4 0 0 0 7]
                                        [0 2 0 0 0 7 0 6 0]
                                        [8 0 0 0 0 0 0 9 0]
                                        [0 6 0 4 0 0 2 0 8]])))
    (is (valid-solution? (solve-puzzle [[0 0 0 7 0 0 3 0 0]
                                        [0 6 0 0 0 0 5 7 0]
                                        [0 7 3 8 0 0 4 1 0]
                                        [0 0 9 2 8 0 0 0 0]
                                        [5 0 0 0 0 0 0 0 9]
                                        [0 0 0 0 9 3 6 0 0]
                                        [0 9 8 0 0 7 1 5 0]
                                        [0 5 4 0 0 0 0 6 0]
                                        [0 0 1 0 0 9 0 0 0]])))
    (is (valid-solution? (solve-puzzle [[0 0 0 6 0 0 0 0 4]
                                        [0 3 0 0 9 0 0 2 0]
                                        [0 6 0 8 0 0 7 0 0]
                                        [0 0 5 0 6 0 0 0 1]
                                        [6 7 0 3 0 1 0 5 8]
                                        [9 0 0 0 5 0 4 0 0]
                                        [0 0 6 0 0 3 0 9 0]
                                        [0 1 0 0 8 0 0 6 0]
                                        [2 0 0 0 0 6 0 0 0]])))
    (is (valid-solution? (solve-puzzle [[8 0 0 0 0 1 0 4 0]
                                        [2 0 6 0 9 0 0 1 0]
                                        [0 0 9 0 0 6 0 8 0]
                                        [1 2 4 0 0 0 0 0 9]
                                        [0 0 0 0 0 0 0 0 0]
                                        [9 0 0 0 0 0 8 2 4]
                                        [0 5 0 4 0 0 1 0 0]
                                        [0 8 0 0 7 0 2 0 5]
                                        [0 9 0 5 0 0 0 0 7]]))))
  (testing "Puzzles with no solution"
    (is (nil? (solve-puzzle [[1 0 0 9 0 7 0 0 3]
                             [0 8 0 0 0 0 0 7 0]
                             [0 0 9 0 0 0 6 0 0]
                             [0 0 7 2 0 9 4 0 0]
                             [4 1 0 0 0 0 0 9 5]
                             [0 0 8 5 0 4 3 0 0]
                             [0 0 3 0 0 3 7 0 0]
                             [0 5 0 0 0 0 0 4 0]
                             [2 0 0 8 0 6 0 0 9]])))
    (is (nil? (solve-puzzle [[8 0 0 0 0 1 0 4 0]
                             [2 0 6 0 9 0 0 1 0]
                             [0 0 9 0 0 6 0 8 0]
                             [1 2 4 0 0 0 0 0 9]
                             [0 0 0 0 0 0 0 9 0]
                             [9 0 0 0 0 0 8 2 4]
                             [0 5 0 4 0 0 1 0 0]
                             [0 8 0 0 7 0 2 0 5]
                             [0 9 0 5 0 0 0 0 7]])))
    (is (nil? (solve-puzzle [[0 0 0 6 0 0 0 0 4]
                             [0 3 0 0 9 0 0 2 0]
                             [0 6 0 8 0 0 7 0 0]
                             [0 0 5 0 6 0 0 0 1]
                             [6 7 0 3 0 1 0 5 8]
                             [9 0 0 0 5 0 4 0 0]
                             [0 0 6 0 0 3 0 9 8]
                             [0 1 0 0 8 0 0 6 0]
                             [2 0 0 0 0 6 0 0 0]])))))
