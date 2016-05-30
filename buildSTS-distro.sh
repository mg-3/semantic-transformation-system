#!/usr/bin/env bash

builton=$(date -u "+builton-%Y-%m-%d_%H:%M_UTC")

tar -czvf "lilly-sts-$builton.tar.gz" target/refdb-sts-1.0-SNAPSHOT-bundle.tar.gz \
Dockerfile README.md config sparql
