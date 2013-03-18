#!/bin/sh

PIDFILE=/var/run/bloottorrent.pid

if [ -f $PIDFILE ] ; then
	PID=`cat $PIDFILE`
	if [ ! -z "$PID" ] ; then
	    kill -9 $PID
	fi
	rm $PIDFILE
fi

