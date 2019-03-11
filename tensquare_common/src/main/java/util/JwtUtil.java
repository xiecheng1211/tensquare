package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * @author dongcheng
 * create date 2019/3/8
 **/
@ConfigurationProperties("jwt.config")
public class JwtUtil {
    //盐值
    private String key;
    //过期时间
    private long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String createJWT(String id, String subject, String roles){

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256,key)
                .claim("roles",roles);
        if (ttl > 0){
            jwtBuilder.setExpiration(new Date(nowMillis+ttl));
        }
        return jwtBuilder.compact();
    }

    public Claims parseJWT(String jwtstr){
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(jwtstr)
                .getBody();
    }

}
