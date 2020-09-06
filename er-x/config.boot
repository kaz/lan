interfaces {
    ethernet eth0 {
        duplex auto
        speed auto
    }
    ethernet eth1 {
        duplex auto
        speed auto
    }
    ethernet eth2 {
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
                    pvid 1001
                }
            }
            interface eth1 {
                vlan {
                    pvid 1001
                }
            }
            interface eth2 {
                vlan {
                    vid 1001
                    vid 1002
                    vid 1003
                }
            }
            interface eth3 {
                vlan {
                    pvid 1001
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
}
system {
    host-name EdgeRouter-X-5-Port
    login {
        user kaz {
            authentication {
                encrypted-password $5$mK3YfCV6JiWIqWFy$r3yBi2JzUpIFremfRdETfyHP7IXNCOYVAVp8nYF6EuD
                plaintext-password ""
                public-keys key1 {
                    key AAAAC3NzaC1lZDI1NTE5AAAAIPKlZKEWlSBuZcT407R2XQNNwwQ2LXEHFV54NMpMlBV8
                    type ssh-ed25519
                }
            }
            full-name "Kazuki Sawada"
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
/* === vyatta-config-version: "config-management@1:conntrack@1:cron@1:dhcp-relay@1:dhcp-server@4:firewall@5:ipsec@5:nat@3:qos@1:quagga@2:suspend@1:system@4:ubnt-pptp@1:ubnt-udapi-server@1:ubnt-unms@1:ubnt-util@1:vrrp@1:vyatta-netflow@1:webgui@1:webproxy@1:zone-policy@1" === */
/* Release version: v2.0.8-hotfix.1.5278088.200305.1641 */
