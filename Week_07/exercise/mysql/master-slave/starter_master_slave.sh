# 在slave中分别执行如下命令即可开启主从复制(异步)
CHANGE MASTER TO MASTER_HOST='192.168.49.100', MASTER_USER='root', MASTER_PASSWORD='root', MASTER_LOG_FILE='replicas-mysql-bin.000001', MASTER_LOG_POS=0;
START slave;