.PHONY: noop
noop:

.PHONY: encrypt decrypt
encrypt decrypt: secrets.yml
	python secrets.py $@

secrets.yml:
	gpg --output $@ --decrypt $@.gpg

.PHONY: secrets.yml.gpg
secrets.yml.gpg:
	gpg --default-recipient-self --encrypt secrets.yml
