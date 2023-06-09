Generators of input files for the global graph.

To use it "csvrelgen.sh" should be launched.

Some configuration of "csvrelgen.sh" is required.

# Docker image

You need to have Docker installed on your machine to be able to build and run the Docker image.

## Build the Docker image

To build the Docker image, run the following command:

```shell
docker build -t csvrelgen .
```

## Run the Docker image

To run the Docker image, run the following command:

```shell
docker run --rm -v <input_path>:/app/input -v <output_path>:/app/output csvrelgen <java_Xmx> <MMLLAR> <ESXFILES> <OUTDIR>
```

Replace `<input_path>` with the path to your input directory, and `<output_path>` with the path to your output directory.

### Optional Arguments

* `<java_Xmx>` - the amount of memory for JVM (default: `64G`)
* `<MMLLAR>` - the path to the MMLLAR file (default: `/app/input/mml.lar`)
* `<ESXFILES>` - the path to the ESXFILES files (default: `/app/input/esx_mml/`)
* `<OUTDIR>` - the path to the output directory (default: `/app/output`)