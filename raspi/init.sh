su

useradd -m kaz
echo "kaz ALL=(ALL) NOPASSWD: ALL" > /etc/sudoers.d/kaz

pacman-key --init
pacman-key --populate archlinuxarm
pacman -Sy sudo python

su kaz

cd ~
mkdir .ssh
curl https://github.com/kaz.keys > .ssh/authorized_keys
chmod 700 .ssh
chmod 600 .ssh/authorized_keys
