interfaces {
    ethernet eth0 {
        duplex auto
        speed auto
    }
    ethernet eth1 {
        disable
        duplex auto
        speed auto
    }
    ethernet eth2 {
        disable
        duplex auto
        speed auto
    }
    ethernet eth3 {
        duplex auto
        speed auto
    }
    ethernet eth4 {
        duplex auto
        poe {
            output off
        }
        speed auto
    }
    loopback lo {
    }
    switch switch0 {
        address dhcp
        mtu 1500
        switch-port {
            interface eth0 {
                vlan {
                    vid 1001
                    vid 1002
                    vid 1003
                }
            }
            interface eth3 {
                vlan {
                    vid 1001
                    vid 1002
                    vid 1003
                }
            }
            interface eth4 {
                vlan {
                    vid 1001
                    vid 1002
                    vid 1003
                }
            }
            vlan-aware enable
        }
        vif 1001 {
            address dhcp
        }
        vif 1002 {
            address dhcp
        }
    }
}
service {
    gui {
        http-port 80
        https-port 443
        older-ciphers enable
    }
    mdns {
        repeater {
            interface switch0.1002
            interface switch0.1001
        }
    }
    ssh {
        port 22
        protocol-version v2
    }
    unms {
        disable
    }
}
system {
    analytics-handler {
        send-analytics-report true
    }
    crash-handler {
        send-crash-report true
    }
    host-name EdgeRouter-X-5-Port
    login {
        user kaz {
            authentication {
                encrypted-password $5$O.ZZbDwGKlzFmC/z$bi.thBF6CphnCcI61UZbH76Uc6DdiqzOR6npxIH5Kr3
                plaintext-password ""
                public-keys main-key {
                    key AAAAC3NzaC1lZDI1NTE5AAAAIPKlZKEWlSBuZcT407R2XQNNwwQ2LXEHFV54NMpMlBV8
                    type ssh-ed25519
                }
            }
            level admin
        }
    }
    ntp {
        server ntp.jst.mfeed.ad.jp {
        }
    }
    syslog {
        global {
            facility all {
                level notice
            }
            facility protocols {
                level debug
            }
        }
    }
    time-zone Asia/Tokyo
}


/* Warning: Do not remove the following line. */
/* === vyatta-config-version: "config-management@1:conntrack@1:cron@1:dhcp-relay@1:dhcp-server@4:firewall@5:ipsec@5:nat@3:qos@1:quagga@2:suspend@1:system@5:ubnt-l2tp@1:ubnt-pptp@1:ubnt-udapi-server@1:ubnt-unms@2:ubnt-util@1:vrrp@1:vyatta-netflow@1:webgui@1:webproxy@1:zone-policy@1" === */
/* Release version: v2.0.9.5346345.201028.1647 */
