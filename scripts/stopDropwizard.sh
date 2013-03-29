#!/bin/sh

PID=`jps -v | grep -E bloodtorrent-.*-SNAPSHOT.jar | awk '{print $1}'`

if [ ! -z "$PID" ] ; then
    kill -9 $PID
fi
