#!/bin/bash
apphome="/home/app/houzi/helloworld"
jarname="helloworld.jar"

cd ${{appname}}/bin/
java -Xms256M -Xmx512M -Droot.path=${apphome} -Dfile.encoding=UTF-8 -Duser.timezone=GMT+08 -jar ${apphome}/lib/${jarname}