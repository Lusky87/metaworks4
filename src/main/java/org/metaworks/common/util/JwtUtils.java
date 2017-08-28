/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.metaworks.common.util;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import net.minidev.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

/**
 * Jwt Utility.
 *
 * @author Seungpil, Park
 * @since 0.1
 */
public class JwtUtils {

    public static JWTClaimsSet parseToken(String jwtToken) throws Exception {
        JWSObject jwsObject = JWSObject.parse(jwtToken);

        JSONObject jsonPayload = jwsObject.getPayload().toJSONObject();
        return JWTClaimsSet.parse(jsonPayload);
    }

    public static boolean verify(JWSObject jwsObject) throws Exception {
        JWSHeader header = jwsObject.getHeader();
        JWSAlgorithm algorithm = header.getAlgorithm();

        if (algorithm.getName().equals(JWSAlgorithm.HS256.getName())) {
            JWSVerifier verifier = new MACVerifier(getHS256SecretKey());
            return jwsObject.verify(verifier);

        } else if (algorithm.getName().equals(JWSAlgorithm.RS256.getName())) {
            RSAPublicKey publicKey = getRSAPublicKey();
            JWSVerifier verifier = new RSASSAVerifier(publicKey);
            return jwsObject.verify(verifier);
        }
        return false;
    }

    public static boolean validateToken(String jwtToken) throws Exception {
        JWSObject jwsObject = JWSObject.parse(jwtToken);
        if (!verify(jwsObject)) {
            return false;
        }
        return true;
    }

    public static boolean validateToken(String jwtToken, Date expirationTime) throws Exception {
        JWSObject jwsObject = JWSObject.parse(jwtToken);
        if (!verify(jwsObject)) {
            return false;
        }

        JSONObject jsonPayload = jwsObject.getPayload().toJSONObject();
        JWTClaimsSet jwtClaimsSet = JWTClaimsSet.parse(jsonPayload);

        if (expirationTime == null) {
            expirationTime = jwtClaimsSet.getExpirationTime();
        }

        int compareTo = new Date().compareTo(expirationTime);
        if (compareTo > 0) {
            return false;
        }
        return true;
    }

    public static RSAPublicKey getRSAPublicKey() throws Exception {
        return getRSAPublicKey(null);
    }

    public static RSAPublicKey getRSAPublicKey(File f)
            throws Exception {

        InputStream fis;
        if (f == null) {
            fis = ResourceUtils.getSpringResourceInputStream("/jwt-keys/RS256.pub");
        } else {
            fis = new FileInputStream(f);
        }

        byte[] bytes = IOUtils.toByteArray(fis);
        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey rsaPublicKey = (RSAPublicKey) kf.generatePublic(spec);
        fis.close();
        return rsaPublicKey;
    }


    public static RSAPrivateKey getRSAPrivateKey() throws Exception {
        return getRSAPrivateKey(null);
    }

    public static RSAPrivateKey getRSAPrivateKey(File f) throws Exception {

        InputStream fis;
        if (f == null) {
            fis = ResourceUtils.getSpringResourceInputStream("/jwt-keys/RS256.private");
        } else {
            fis = new FileInputStream(f);
        }
        byte[] bytes = IOUtils.toByteArray(fis);
        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kf.generatePrivate(spec);
        fis.close();
        return rsaPrivateKey;
    }

    public static String getHS256SecretKey() throws Exception {
        InputStream inputStream = ResourceUtils.getSpringResourceInputStream("/jwt-keys/HS256.key");
        String sharedSecret = IOUtils.toString(inputStream, "UTF-8");
        return sharedSecret;
    }
}
