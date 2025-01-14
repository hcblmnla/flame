# Flame

The Chaos game implementation. Pure Java with picocli

## Example

Was created with default parameters:

![example](generated/example.png)

## Usage

Clone repo, build project:

```shell
git clone https://github.com/hcblmnla/flame.git flame
cd flame
mvn clean compile assembly:single
mv target/flame-1.0.0-jar-with-dependencies.jar flame.jar
```

And print usage:

```shell
java -jar flame.jar --help
```
