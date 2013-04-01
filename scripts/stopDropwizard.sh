#!/bin/sh

PID=`jps -v | grep -E bloodtorrent-.*-SNAPSHOT.jar | awk '{print $1}'`

if [ ! -z "$PID" ] ; then
  if [ `uname` = 'Linux' ] ; then
    echo "Linux detected, using kill -9 to kill jps process"
    kill -9 $PID
  else
    echo "Assuming windows, using taskkill to kill jps process"
    taskkill -f -pid $PID
  fi
fi
