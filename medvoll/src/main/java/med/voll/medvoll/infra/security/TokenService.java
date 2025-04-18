package med.voll.medvoll.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.medvoll.model.Usuario;

@Service
public class TokenService {

    /**
     * Criado uma propriedade da aplicação no arquivo application.properties
     * para armazenar a chave secreta utilizada para gerar o token
     */
    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.issuer}")
    private String issuer;

    /**
     * Método para gerar um token
     * Para gerar um token, é necessário utilizar a biblioteca jjwt
     * baixando a dependência no site jwt.io utilizando o repositório
     * auth0 e adicionando ao pom.xml
     */
    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId()) // withClaim utilizado para adicionar informações ao token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(issuer)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
