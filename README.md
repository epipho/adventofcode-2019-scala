# Advent of Code 2019 solutions in Scala

Solutions for [Advent of Code 2019](https://adventofcode.com/2019).

Solutions are partitioned by day, 1/ contains day 1 puzzles, 2/ day 2 etc.

Each folder contains the following files:
* `DayX_1.scala` and `DayX_2.scala` main object files for part 1 and 2 of the day's puzzles
* `input` Puzzle input
* `output.1` Output from `DayX_1` and solution for the first star
* `output.2` Output from `DayX_2` and solution for the second star
* Other Scala classes

## build and run
Change to the day's directory, compile scala files, run appropriate `DayX_N` class.

```
cd 2
scalac *.scala
scala Day2_1
````
