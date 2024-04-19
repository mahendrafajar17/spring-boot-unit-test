	#!/bin/bash

app="apiedittemplate-1.0.0.jar"
maxInstance=1
acummulator=1
exprdefault=0

pidfile="apiedittemplate.pid"
logfile="logback.xml"
configfile="application.properties"
trxname="apiedittemplate"
trxlog="out-apiedittemplate.log"

case $1 in
start)
	#check number of currently running instances
	if [ -f $pidfile ];
	then
		pids=$(<$pidfile)
		pidArr=($pids)
		exprdefault=${#pidArr[@]}
		if [ "$maxInstance" -le "${#pidArr[@]}" ];
		then
			echo "Too many instance running, please stop one of them"
			exit 2
		fi
	else
		#backup the previous log
		if [ -f $trxlog ];			
		then
			curdate=`date +%Y%m%d_%H%M%S`
			if [ ! -d "log" ];
			then
				mkdir "log"
			fi
			mv $trxlog log/$trxlog.$curdate.log
		fi
	fi

	#start the m2mcontroller

	finalexpr=$(expr $acummulator + $exprdefault)
	echo "Starting $trxname"
	#JAVA_OPTS="-server -d64 -Xms1G -Xmx1G -Xss2M -XX:MaxPermSize=256M -XX:+UseParallelGC"
	java -Xmx512M -Dlogback.configurationFile=$logfile -jar $app -f $configfile > $trxlog 2>&1 &
	pid=$!
	echo "$trxname started ($pid)"

	#save the pid into file	
	if [ -f $pidfile ];
	then
		echo -n " $pid" >> $pidfile
	else
		#backup the previous log
		if [ -f $trxlog ];			
		then
			curdate=`date +%Y%m%d_%H%M%S`
			mv $trxlog log/$trxlog.$curdate.log
		fi
		echo -n $pid > $pidfile
	fi
	;;
stop)
	if [ -f $pidfile ];
	then
		#stop m2mcontroller
		pids=$(<$pidfile)
		pidArr=($pids)
		
		lastPid=${pidArr[@]: -1:1}

		kill -15 $lastPid
		echo "Please wait for 2 Seconds to kill application"
		sleep 2
		kill -9 $lastPid
		echo "$trxname with pid=$lastPid has been stopped"

		#rewrite pidfile
		if [ 1 = ${#pidArr[@]} ];
		then
			rm -f $pidfile
		else
			afterPid=("${pidArr[@]/$lastPid}")
			echo -n "${afterPid[@]}" > $pidfile
		fi
	else
		echo "Previous instance not found, please start it first"
	fi
	;;
stop-all)
	#stop all m2mcontroller
	pids=$(<$pidfile)
        
	kill -15 $pids		
	echo "Please wait for 2 seconds to kill application"
	sleep 2
	echo "Stoping all $trxname with $pids"	
	kill -9 $pids
	rm -f $pidfile	
	;;
*)
	echo "Command not recognize, please check parameters"
	exit 1
	;;
esac

exit 0
