#!/bin/bash
STOREPASS="forgotten"
for ENT in serverMotorFluxos client1Motor client2Motor client3Motor client4Motor client5Motor client6Motor client7Motor client8Motor client9Motor client10Motor client11Motor client12Motor client13Motor client14Motor client15Motor client16Motor; do
 rm -f ${ENT}.jks ${ENT}.pem
 echo -e "${ENT}\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias ${ENT} -keyalg RSA -keysize 2048 \
	-validity 365 -keystore ${ENT}.jks -storepass ${STOREPASS}
 keytool -exportcert -alias ${ENT} -keystore ${ENT}.jks -storepass ${STOREPASS} -rfc -file ${ENT}.pem
done
####
echo "Creating trust relations"
### IMPORTING TRUSTED CERTIFICATES
### (Every client trusts server_J)
for ENT in client1Motor client2Motor client3Motor client4Motor client5Motor client6Motor client7Motor client8Motor client9Motor client10Motor client11Motor client12Motor client13Motor client14Motor client15Motor client16Motor; do
 echo "yes"|keytool -import -alias ${ENT} -keystore serverMotorFluxos.jks -file ${ENT}.pem -storepass ${STOREPASS}
 echo "yes"|keytool -import -alias serverMotorFluxos -keystore ${ENT}.jks -file serverMotorFluxos.pem -storepass ${STOREPASS}
done

keytool -list -keystore serverMotorFluxos.jks -storepass ${STOREPASS}
#######