# csvrelgen Dockerfile
# 
# Maintainer: Łukasz Szeremeta
# Email: l.szeremeta.dev+mmlkg@gmail.com
# https://github.com/lszeremeta
#
# Usage:
# docker build -t csvrelgen .
# docker run --rm -v <input_path>:/app/input -v <output_path>:/app/output csvrelgen <java_Xmx> <MMLLAR> <ESXFILES> <OUTDIR>
#
# Replace <input_path> with the path to your input directory, <output_path> with the path to your output directory.
#
# Optional Arguments
# * `<java_Xmx>` - the amount of memory for JVM (default: `64G`)
# * `<MMLLAR>` - the path to the MMLLAR file (default: `/app/input/mml.lar`)
# * `<ESXFILES>` - the path to the ESXFILES files (default: `/app/input/esx_mml/`)
# * `<OUTDIR>` - the path to the output directory (default: `/app/output`)

# Multi-stage build: 1) build 2) busybox 3) package
# Build stage
FROM maven:3.9.2-eclipse-temurin-17 as build
WORKDIR /app

# Copy the project files into the docker image (see .dockerignore)
COPY . .

# Build and rename jar, all in one layer
RUN mvn -B package --file=pom.xml \
    && mv target/csvrelgen-*-jar-with-dependencies.jar csvrelgen.jar

# Busybox stage
FROM busybox:1.36.0-uclibc as busybox

# Package stage
FROM gcr.io/distroless/java17-debian11
LABEL maintainer="Łukasz Szeremeta <l.szeremeta.dev+mmlkg@gmail.com>"

# Copy the static shell and executables into distroless image
COPY --from=busybox /bin /bin
COPY --from=build /app/csvrelgen.jar /app/csvrelgen.jar

WORKDIR /app

# Copy shell script for generating files
COPY csvrelgen.sh /app/csvrelgen.sh

# Use shell script as entry point
ENTRYPOINT ["/bin/sh", "/app/csvrelgen.sh"]