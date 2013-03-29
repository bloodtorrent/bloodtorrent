#!/bin/sh

PID=`jps -v | grep bloodtorrent-.*-SNAPSHOT.jar | awk '{print $1}'`

if [ ! -z "$PID" ] ; then
    kill -9 $PID
fi
