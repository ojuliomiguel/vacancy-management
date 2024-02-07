package br.com.bluelobster.vacancy_management.modules.company.helpers;

import java.time.Instant;
import java.util.Arrays;
import java.time.Duration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class CompanyJTWToken {
    
    public static String generate(String companyID) {
        String jwtscret = "@!blue_lobster737!@";
        Algorithm jwAlgorithm = Algorithm.HMAC256(jwtscret);
        var tokenExpirationTime = Instant.now().plus(Duration.ofMinutes(30));

        var token = JWT
            .create()
            .withIssuer("bluelobster")
            .withClaim("roles", Arrays.asList("COMPANY"))
            .withExpiresAt(tokenExpirationTime)
            .withSubject(companyID)
            .sign(jwAlgorithm);

        return token;
    }

}
