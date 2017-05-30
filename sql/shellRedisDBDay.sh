#!/bin/bash

#取得时间yyyyMMddHHmmss
ymdhms=`date +%Y%m%d%H%M%S`

orign_file=/root/seawar/redis/redis-2.8.6/

back_file=/root/seawar/dbDay/

back_data_beg=${back_file}dump.rdb.${ymdhms}

back_data_end=${back_data_beg}.tar.gz

back_data_relative=dbDay/dump.rdb.${ymdhms}.tar.gz

origin_data_relative=dbDay/dump.rdb

echo $ymdhms

#备份redis数据
#cp -f ${orign_file}dump.rdb ${back_file}dump.rdb.${ymdhms}

cp -f ${orign_file}dump.rdb ${back_data_beg}

#压缩为.tar.gz格式
#(加上P解决警告tar: Removing leading `/' from member names ,绝对路径进行打包出现的，一般用相对路径)
#tar -czvPf ${back_data_end} ${back_data_beg} 

tar -czvf ${back_data_relative} ${origin_data_relative}

rm -rf ${back_data_beg} #移除备份文件

#删除数据
#ls -h 文件目录  表示取得该目录下面的所有文件名
#ls -ht 文件目录  表示取得该目录下面的所有文件名，并且按照：从新到旧排序
#ls -hrt 文件目录  表示取得该目录下面的所有文件名，并且按照：从旧到新排序
#num=${#array[@]} 获取数组元素的个数
#`echo ${fname}|cut -c10-24` 取得截取的字符串（单引号必须要）

files=$(ls -ht ${back_file})
i=0
max=3
for fname in ${files}
do
	tmnam=`echo ${fname}|cut -c10-24`
	echo ${tmnam}
	
	i=$((${i}+1))
	
	if [ "${i}" -gt "${max}" ];
	then
		rm -rf "${back_file}${fname}"
	fi
done

#定时执行的东西 在crontab -e回车后里面添加定时任务
#任务格式:分 时 天 月 周 执行的文件路径
#如:0 2 */1 * * sh /root/seawar/RDBDay112.sh
#如:0 2 */1 * * sh /root/seawar/RDBDay.sh
#然后ctrl+x 退出编写
#crontab -l 查看定时任务列表