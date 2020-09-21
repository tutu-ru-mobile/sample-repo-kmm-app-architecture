#!/usr/bin/env bash
./gradlew --stop
./gradlew app-browser:myBuildProduction && \
  du -sh app-browser/build/distributions/* && \
  ./gradlew app-browser:myRun
