#!/bin/sh

HOST=192.168.1.1

ssh $HOST sudo mkdir -p /ndppd/local/sbin
ssh $HOST sudo chown -R kaz /ndppd/local/sbin
scp ndppd $HOST:/ndppd/local/sbin
scp scripts/ndppd.initscript $HOST:/ndppd/local/sbin
scp scripts/ndppd $HOST:/config/scripts/post-config.d
scp ndppd.conf $HOST:/tmp
ssh $HOST sudo mv /tmp/ndppd.conf /etc
ssh $HOST sudo chown -R root:vyattacfg /ndppd/local/sbin
ssh $HOST sudo chown root:vyattacfg /config/scripts/post-config.d/ndppd
ssh $HOST sudo chown root:vyattacfg /etc/ndppd.conf
ssh $HOST sudo /config/scripts/post-config.d/ndppd
