package uz.pdp.app_new_site.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.app_new_site.entity.Lavozim;

import java.util.Date;
import java.util.Set;


@Component
public class JwtProvider {

   private static final long expireTime = 1000 * 60 * 60 * 24;   // 1 KUN
   private static final String  secretSoz = "maxfiysozbunihechkimbilmasin";

   // TOKENNI GENERATION QILISH
   public String generateToken(String username, Lavozim lavozim){
      Date expireDate = new Date(System.currentTimeMillis() + expireTime);
      String token = Jwts
              .builder()
              .setSubject(username)
              .setIssuedAt(new Date())
              .setExpiration(expireDate)
              .signWith(SignatureAlgorithm.HS512,secretSoz) // XATOLIK BERGANDA: ÖRNINI ALASHTIRDIM; SECRET SÖZNI QAYTA YOZDIM....
              .claim("roles",lavozim.getName())  // claim(Name of Object, Object)- OBJECT BERISH UCHUN ISHLATILADI.
              .compact();
      return  token;
   }

   // CLIENT 2-MARTA TOKEN BILAN KIRGANDA TOKEN ICHIDAN EMAIL NI OLISH
   public String getUserNameFromToken(String token){

        try {
           String email = Jwts
                   .parser().setSigningKey(secretSoz)
                   .parseClaimsJws(token)   // kalitsuz orqali tokenni parse qilib,
                   .getBody().getSubject(); // token NING Body() QISMI(payload)DAGI Subject dan username ni olamiz.
           return email;
        // QANAQA XATOLIK BÖLSA HAM CATCH GA TUSHADI; MASALAN ExpiredDate bölsa ham.
        }catch (Exception e){
           return null;
        }
   }


}
