#!/bin/sh

DROPWIZARD_HTTP_PORT=8080
PID=`netstat -nap | grep :$DROPWIZARD_HTTP_PORT | tr '/' ' ' | awk '{print $7}' | sort | uniq`

if [ ! -z "$PID" ] ; then
    kill -9 $PID
fi

