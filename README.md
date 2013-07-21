# solve-sudoku

This program solves sudoku puzzles.  It is my first real experiment with Clojure programming.

## Usage

Build using [Leiningen](https://github.com/technomancy/leiningen):

    $ lein compile
    $ lein uberjar

Run with an input file:

    $ java -jar target/solve-sudoku-0.1.0-SNAPSHOT-standalone.jar input-file-path

## Input/output:

The input file should be formatted like this (zeros represent blank squares):

    1 0 0 9 0 7 0 0 3
    0 8 0 0 0 0 0 7 0
    0 0 9 0 0 0 6 0 0
    0 0 7 2 0 9 4 0 0
    4 1 0 0 0 0 0 9 5
    0 0 8 5 0 4 3 0 0
    0 0 3 0 0 0 7 0 0
    0 5 0 0 0 0 0 4 0
    2 0 0 8 0 6 0 0 9

which will give output like this:

    Input:
    +---+---+---+
    |1  |9 7|  3|
    | 8 |   | 7 |
    |  9|   |6  |
    +---+---+---+
    |  7|2 9|4  |
    |41 |   | 95|
    |  8|5 4|3  |
    +---+---+---+
    |  3|   |7  |
    | 5 |   | 4 |
    |2  |8 6|  9|
    +---+---+---+

    Solution:
    +---+---+---+
    |164|957|283|
    |385|621|974|
    |729|438|651|
    +---+---+---+
    |537|289|416|
    |412|763|895|
    |698|514|327|
    +---+---+---+
    |843|195|762|
    |956|372|148|
    |271|846|539|
    +---+---+---+
