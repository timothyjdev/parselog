#!/bin/bash

# This script runs a Jar file that searches a log file for a specific term.
# If the term is found the Jar file writes the line from the log file to
# a file that will be emailed.

$JAVA_HOME -jar ParseLog-0.0.1.jar $1 $2

script=$(readlink -f "$0")
scriptpath=$(dirname "$script")

if [ -e "$scriptpath"/"$2".txt ]; then

   to="user@email.com"
   subject="$2 messages found in $1"
   message=$(cat "$scriptpath"/"$2".txt)

   (
   echo "To: ${to}";
   echo "Subject: ${subject}";
   echo "Content-Type: text/html";
   echo "MIME-Version: 1.0";
   echo "";
   echo "${message}";
   ) |  /usr/sbin/sendmail -t

   rm -f "$2.txt"
fi
