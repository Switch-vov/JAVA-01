# 获取节点IP
# "IPAddress": "192.168.48.4",
# "IPAddress": "192.168.48.7",
# "IPAddress": "192.168.48.5",
# "IPAddress": "192.168.48.2",
# "IPAddress": "192.168.48.3",
# "IPAddress": "192.168.48.6",
docker inspect cluster_redis_16380_1 cluster_redis_26381_1 cluster_redis_26382_1 cluster_redis_26380_1 cluster_redis_16381_1 cluster_redis_16382_1 | grep \"IPAddress\"
# 登录其中一台机器
docker-compose exec redis_16380 /bin/bash
# 执行集群创建操作
redis-cli --cluster create 192.168.48.2:6379 192.168.48.3:6379 192.168.48.4:6379 192.168.48.5:6379 192.168.48.6:6379 192.168.48.7:6379 --cluster-replicas 1