import sys
import yaml
import hashlib

if sys.argv[1] == "encrypt":
	translator = lambda secret, key, content: content.replace(secret, f"{{{{{key}}}}}")
elif sys.argv[1] == "decrypt":
	translator = lambda secret, key, content: content.replace(f"{{{{{key}}}}}", secret)
else:
	print("specify valid operation: encrypt, decrypt")
	exit(-1)

with open("secrets.yml", mode="r") as yml:
	secrets = yaml.safe_load(yml)

for path, secret in secrets.items():
	secKey = dict((sec, hashlib.md5(key.encode("utf8")).hexdigest()) for sec, key in secret.items())
	with open(path, mode="r+") as file:
		content = file.read()
		for sec, key in secKey.items():
			content = translator(sec, key, content)
		file.seek(0)
		file.truncate(0)
		file.write(content)
