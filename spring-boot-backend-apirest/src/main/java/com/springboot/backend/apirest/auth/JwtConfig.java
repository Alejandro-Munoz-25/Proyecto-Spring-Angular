package com.springboot.backend.apirest.auth;

public class JwtConfig {

//	Para generar las llaves se usa el comando openssl genrsa -out nombrearchivo.pem
//	Para ver la llave primaria se usa el comando openssl rsa -in nombrearchivo.pem
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----"
			+ "MIIEpAIBAAKCAQEAvuG/bQqozNDQfZzhLRZEcurwlSr4tC3wfOuM7GJ7Lk+YjHe+"
			+ "jOG1YF1JEHMiWW/yXHzgXn9uP2NDCMM7KwDckMG8wncQYUdSlwiDauD35G0qAYFL"
			+ "ZCjs3yo9q9m3nD2pZ3/i88anAnJ8vs8kq6Pn16R2yNrsOaRb+G7s4W5BnRc4zKHl"
			+ "zBfOHYkDzsBGh2XFpo82hdvZvMQcQn90qeS9378fdlIFVkvOEwJqKy/h47FYQZFS"
			+ "z4aItTYQ1jYkqbZreYUYc78ty9sUni5fzizik3NiRHkr6kj1R6tuwyZ1Sb4YaGlu"
			+ "a8gvYWOWsRbqn4FFkv+wpB9zFUitRgSfi5fqVQIDAQABAoIBAHXPnTduycpH4zZ0"
			+ "GiKayLpouP5IHGIQrIpdU4geLErkA5JG4cvQDwMf8FV/yiaLSnm89To9ykzDNf91"
			+ "h9I94ORTPu6DUA/KVnYzYfEYUsegfLmbFG4IOFJwnsmvQXzkLedcbjXpWPDQup+F"
			+ "9+sb6Z8Vud60B/J3t+/4fqmQRFzwhEV2iWbvppsTyQW+x8JJVN62JZJXwBPeE2yb"
			+ "u4ya4FofPdzPmhZvg+RJY+EzHeMhR4v3u+hdxUIbm22hFiznyMFpCF1Jv6Lz6xjM"
			+ "yeZjfdJvEvYbwvFAHmrJv12+unF2KeTm+Tk8vFhOeb3OpkV4yv+zaCWllA0gsiBO"
			+ "/2g6+C0CgYEA4ds8fk7bAF/M3uNrIdotvwkQTgl2hJu5InCMvcxj1ZODH6M4eWOi"
			+ "s2V6CgiJ/8wB3aYKlQthhgHsmtiDl1IM2FuZf3O5lZEWI3ygMAR5fYCjc8kAt+L6"
			+ "rLwaFCtRb/jQR2UNch7JyrN3EB8PmDTCosK8onNBzCFZK7X3E4KR4l8CgYEA2FuL"
			+ "7fHmGy8aY1ngGuEFUtWnCC2hQj7yO+80tJJwfYjcrdHp0HcG4nce8lsYkdVc/uTR"
			+ "+XD4sPTPvs3dxHlaCNPy49nDOS1Fv6C0dBUiFKOlDoSxjYJDrzoOXMreRCR6E5mD"
			+ "wYQc5SnNP2ZGJ8PukypLjMNrwHd9qmDM1dEZN8sCgYBao75SPlk7fDbe2Eu9SS8E"
			+ "tKF7f+a1bz1MKfkBjo4eP1XnE3ztkmPQNRBWCHlBU+EHz8P9bpTTz9/hgLTGvKH1"
			+ "DkW61XmMIa7NzQ0IWDNdf4uZQvhr08agHdklcQD8u9DyZgaQXvTCyWUl7iE3By15"
			+ "XkePtkuYnWWI2jzu8n6TZwKBgQDQSFlOKQifdNoPeO9AkQ2h7+KMAflzH8vsnNx9"
			+ "VViL0Vy4zRMtR9jN3WViZQYjJmcPT1NAslwHZrYz9lWfFj/xrbDvXq0M9yNlVh1C"
			+ "fzG+8scsUdvn4das8tPuyrHsob0P70Ki4j868eIQPbIeCAY9kIMIsPyjXaURh3zJ"
			+ "/jErvwKBgQCUL2tYdVl6LgYs8Atw3w6e2sS2wGIk/DMUg/iSLMrGRHtW8DXKPleg"
			+ "Uocy3vV9ynVNtr64fJKtplqcqQ3B/dF9KeJtUXz8qqdmPbSelhj/2N09rySxEmo2"
			+ "kVLW51WndU3tnjV7I1364WkupL28gyWpuRwSfncX5yBDiXuRnuUJew==-----END RSA PRIVATE KEY-----";
//	Para ver la llave primaria se usa el comando openssl rsa -in nombrearchivo.pem -pubout
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvuG/bQqozNDQfZzhLRZE"
			+ "curwlSr4tC3wfOuM7GJ7Lk+YjHe+jOG1YF1JEHMiWW/yXHzgXn9uP2NDCMM7KwDc"
			+ "kMG8wncQYUdSlwiDauD35G0qAYFLZCjs3yo9q9m3nD2pZ3/i88anAnJ8vs8kq6Pn"
			+ "16R2yNrsOaRb+G7s4W5BnRc4zKHlzBfOHYkDzsBGh2XFpo82hdvZvMQcQn90qeS9"
			+ "378fdlIFVkvOEwJqKy/h47FYQZFSz4aItTYQ1jYkqbZreYUYc78ty9sUni5fzizi"
			+ "k3NiRHkr6kj1R6tuwyZ1Sb4YaGlua8gvYWOWsRbqn4FFkv+wpB9zFUitRgSfi5fq" + "VQIDAQAB"
			+ "-----END PUBLIC KEY-----";

}
