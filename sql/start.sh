#!/bin/bash
#for I in 1 2 3 4 5 6 7 8
#do

  #telnet 127.0.0.1 21081
  #ip 115的java路径不同是通过apt-get install java自动安装的路径 
  #/usr/bin/java -Xms400m -Xmx400m -Xmn80m -XX:MaxPermSize=64M -XX:+ForceTimeHighResolution -jar SeaWar2.jar &
  /opt/jdk1.7.0_45/bin/java -Xms1024m -Xmx1024m -Xmn256m -XX:MaxPermSize=64M -XX:+ForceTimeHighResolution -jar SeaWar2.jar &

#done
