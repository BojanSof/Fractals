# Fractals

![Fractals](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_mandelbrot.png "Screenshot of the program")

This program contains collection of [fractals](https://en.wikipedia.org/wiki/Fractal), which can be accessed from a simple gui. Few of them have unique properties, which can be easily changed from the gui. Also, you can save the fractal as an image, with prefered width and height.

## List of fractals

- Board
![Board Fractal](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_board.png "Board Fractal")

- Cantor set
![Cantor set](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_cantor_set.png "Cantor set")

- Crystal
![Crystal Fractal](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_crystal.png "Crystal Fractal")

- HTree
![HTree Fractal](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_htree.png "HTree Fractal")

- Hilbert curve
![Hilbert curve](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_hilbert.png "Hilbert curve")

- Koch snowflake
![Koch snowflake](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_koch_snowflake.png "Koch snowflake")

- Mandelbrot set
![Mandelbrot set](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_mandelbrot.png "Mandelbrot set")

- Peano curve
![Peano curve](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_peano.png "Peano curve")

- Sierpinski carpet
![Sierpinski carpet](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_sierpinski_carpet.png "Sierpinski carpet")

- Sierpinski triangle
![Sierpinski triangle](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_sierpinski_triangle.png "Sierpinski triangle")

- Tree
![Tree Fractal](https://github.com/BojanSof/Fractals/blob/master/images/screenshot_tree.png "Tree Fractal")

# Getting Started
The program is created in Java (using JDK 11) with the JavaFX (version 11) platform. The project solution for the program was created in Eclipse IDE with maven support (m2eclipse plugin), so it can be imported in Eclipse or it can be build driectly using maven. I tested the project on Windows 10 and Ubuntu.
## Prerequisites
```
Maven
JDK 11 or greater
JavaFX 11 or greater
```
## Building
Clone the repository
```
git clone https://github.com/BojanSof/Fractals
```
Build the project using Maven
```
mvn clean
mvn package
```
Fractals-0.0.1-SNAPSHOT.jar file will be created under target folder
# Running the program
The program can be executed with double click on the Fractals-0.0.1-SNAPSHOT.jar file, or using terminal:
```
java -jar Fractals-0.0.1-SNAPSHOT.jar
```

# Future additions and improvements

Many optimisations and additions can be added spcifically to Mandelbrot set, like:

- Color Pallete editor
- Multithreding support
- Calculations with arbitrary precision, for very small bounds (below the range of double)

# Example Images generated with the Mandelbrot set

Data for the examples (like number of iterations, real and imaginary part minimum and maximum values) are from this [site](http://math.hws.edu/eck/js/mandelbrot/java/MandelbrotSettings/index.html), which I found very useful during the creation of Mandelbrot set part of the project.

![Example 1](https://github.com/BojanSof/Fractals/blob/master/images/mandelbrot/mandelbrot1.png "Example 1")
![Example 2](https://github.com/BojanSof/Fractals/blob/master/images/mandelbrot/mandelbrot2.png "Example 2")
![Example 3](https://github.com/BojanSof/Fractals/blob/master/images/mandelbrot/mandelbrot3.png "Example 3")
![Example 4](https://github.com/BojanSof/Fractals/blob/master/images/mandelbrot/mandelbrot4.png "Example 4")
![Example 5](https://github.com/BojanSof/Fractals/blob/master/images/mandelbrot/mandelbrot5.png "Example 5")
![Example 6](https://github.com/BojanSof/Fractals/blob/master/images/mandelbrot/mandelbrot6.png "Example 6")
