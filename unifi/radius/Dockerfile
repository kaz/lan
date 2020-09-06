FROM alpine

RUN apk --no-cache add openssl && \
    openssl dhparam -out dh 2048 && \
    openssl ecparam -genkey -name prime256v1 -out key.pem && \
    openssl req -new -x509 -key key.pem -days 2048 -extensions v3_ca -subj "/C=JP/CN=lan.sysad.net/" -out cert.pem

FROM alpine

RUN apk --no-cache add freeradius freeradius-eap

COPY --from=0 dh /etc/raddb/certs/dh
COPY --from=0 key.pem /etc/raddb/certs/key.pem
COPY --from=0 cert.pem /etc/raddb/certs/cert.pem

RUN chown -R radius:radius /etc/raddb/certs && \
    sed -i "s/-eap/eap/g" /etc/raddb/sites-available/default && \
    sed -i "s/-eap/eap/g" /etc/raddb/sites-available/inner-tunnel && \
    sed -i "s/if (0) {/if (1) {/g" /etc/raddb/sites-available/inner-tunnel

EXPOSE 1812/udp
EXPOSE 1813/udp

ENTRYPOINT ["radiusd"]
CMD ["-f"]
