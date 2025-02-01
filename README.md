# Flame

The Chaos game implementation. Pure Java with picocli. The project is old, the code is not perfect, but..

## Example

Was created with default parameters:

![example](generated/example.png)

## Usage

Clone repo, build project:

```
git clone https://github.com/hcblmnla/flame.git flame
cd flame
mvn clean compile assembly:single
mv target/flame-1.0.0-jar-with-dependencies.jar flame.jar
```

And print usage:

```
java -jar flame.jar --help
```

## But

[Here](src/test/java/logic2024) are the solutions to the problems of Zermelo–Fraenkel
set [theory](https://en.wikipedia.org/wiki/Zermelo%E2%80%93Fraenkel_set_theory).
