ExpressionEvaluator
===================

An expression evaluator project using Shunting Yard Algorithm

## How to use

Clone the repository with: 

```
git clone https://github.com/unnikked/ExpressionEvaluator.git
```

Compile:

```
javac src/ga/unnikked/expressionevaluator/*.java src/ga/unnikked/expressionevaluator/*/*.java
```

And execute it with (remember to `cd src`): 

```
java ga/unnikked/expressionevaluator/Main
```

You can also pipe in a file using `-f` directive

```
cat yourfile | java ga/unnikked/expressionevaluator/Main -f
```

Here an execution example:

```
$ cat test | java ga.unnikked.expressionevaluator.Main -f
4*atan(1)
4 1 atan * 
3.141592653589793
```

##Notes 

For testing purpose I've added 6 builtin function: `sin`, `cos`, `atan`, `ln`, `exp`, `ack`. 

For example if you want to compute the pi number you could write as input:

```
4*atan(1)
```

Or if you want to compute the [Ackermann](http://en.wikipedia.org/wiki/Ackermann_function) function of 4 and 3 (don't do that :P):

```
ack(4,3)
```

If you want to combine more than two builin function I recommend to parentheses them:

```
(sin(0.71))^2+(cos(0.71))^2
```

##License

Check `LICENSE`