FROM ubuntu:latest
LABEL authors="ib"

ENTRYPOINT ["top", "-b"]