#!/usr/bin/env bash
bin=`dirname "$0"`
bin=`cd "$bin";pwd`
echo $bin
lib=$bin/../lib
classes=$bin/../conf
logs=$bin/../logs
if [ ! -d "$logs" ];then
   mkdir $logs
fi
MAIN_CLASS="com.generator.example.GeneratorRun"
CLASSPATH=$classes/:$lib/*
echo generator start
echo $CLASSPATH
java -classpath $CLASSPATH -Dlog.home=$logs $MAIN_CLASS
exit 0
