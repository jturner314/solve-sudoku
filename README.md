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

## License

Copyright (c) 2012 Jim Turner

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
