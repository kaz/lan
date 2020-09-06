.PHONY: noop
noop:

.PHONY: encrypt decrypt
encrypt decrypt: secrets.pm
	perl enc.pl $@

secrets.pm:
	gpg --output $@ --decrypt $@.gpg

secrets.pm.gpg:
	gpg --default-recipient-self --encrypt secrets.pm
