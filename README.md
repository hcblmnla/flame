# Flame

The Chaos game implementation. Both the code and the artifact are here.

## Example

Was created with default parameters:

```
java -jar flame.jar
```

![example](generated/example.png)

## Usage

Clone repo (SSH):

```
git clone git@github.com:hcblmnla/flame.git
```

Change directory:

```
cd flame
```

Print usage:

```
java -jar flame.jar --help
```

Possible output:

```
Usage: flame [-hV] [-f=<extension>] [-i=<iterations>] [-r=<resolution>]
             [-s=<samples>] [-sym=<symmetries>] [-t=<nThreads>]
             [-v=<variations>...]...
  -f, --format=<extension>
  -h, --help                 Show this help message and exit.
  -i, --iterations=<iterations>

  -r, --resolution=<resolution>

  -s, --samples=<samples>
      -sym, --symmetries=<symmetries>

  -t, --threads=<nThreads>
  -v, --variations=<variations>...

  -V, --version              Print version information and exit.
```

## Notes

* 1 TODO: in flame.function.affine.Affine
