use utf8;
use strict;
use warnings;

use secrets;

my $subcmds = {
	"encrypt" => sub {
		my ($data, $ents) = @_;
		foreach my $raw (@$ents) {
			my $encrypted = `echo '$raw' | gpg --encrypt --default-recipient-self | base64`;
			chomp($encrypted);
			$data =~ s/$raw/<E>$encrypted<\/E>/g;
		}
		$data;
	},
	"decrypt" => sub {
		my ($data) = @_;
		while ($data =~ /<E>(.+?)<\/E>/) {
			my $decrypted = `echo '$1' | base64 -d | gpg --decrypt`;
			chomp($decrypted);
			$data =~ s/<E>(.+?)<\/E>/$decrypted/;
		}
		$data;
	},
};

my $cmd = $subcmds->{$ARGV[0]};
die("no such command! died") unless $cmd;

foreach my $path (keys($secrets::entries)) {
	open(FILE, "<", $path);
	$_ = $cmd->(do { local $/; <FILE> }, $secrets::entries->{$path});
	close(FILE);

	open(FILE, ">", $path);
	print(FILE);
	close(FILE);
}
