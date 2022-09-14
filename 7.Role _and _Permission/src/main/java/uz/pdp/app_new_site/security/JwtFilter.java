package uz.pdp.app_new_site.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.app_new_site.service.AuthService;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

 // REQUEST HAR DOIM, CONTROLLERDAN OLDIN BU JwtFilterGA KELADI, BU CLASS REQUEST TOKEN B-N KELGANDA TOKENNI <<doFilterInternal>>
 // METHODI ORQALI VALIDATSIYADAN ÖTKAZIB,CONTROLLER GA JÖNATADI.
@Component
public class JwtFilter extends OncePerRequestFilter {

     @Autowired
     JwtProvider jwtProvider;

     @Autowired
     AuthService authService;

     // Token b-n Login qilganda, USERNI FILTERDAN ÖTKAZIB KEYIN SYSTEMAGA KIRITISH,
     // request ni  TOKEN B-N JO'NATSA, TIZIM HAR SAFAR LOGIN PAROL TALAB QILMAYDI VA SecurityConfig DAGI YOPIQ YO'L/GA HAM KIRISH MUMKIN
     @Override
     protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
         // request NING Header QISMIDA "Authorization" KEY SI BO'LADI VA UNIG VALUE SI Bearer B-N BOSHLANSA
        String authorization = request.getHeader("Authorization");
        if (authorization!=null&&authorization.startsWith("Bearer")){

            // authorization NING VALUE SIDAN TOKEN NI QIRQIB OLISH
            authorization=authorization.substring(7);
            // TOKENDAN EMAIL NI AJRATIB OLAMIZ
            String  emailFromToken = jwtProvider.getUserNameFromToken(authorization);
            if (emailFromToken!=null){
                // DB DAN USERNAME(EMAIL) NI TOPISH
                UserDetails userDetails = authService.loadUserByUsername(emailFromToken);

                // userDetails DAN OLGAN OBJECT NI SYSTEMAGA AUTHENTICATE (SET- MUXRLAB QO'YISH)
                // QILISH KERAK TOKI CLIENT HAR GAL request JO'NATSA TIZIM YANA LOGIN-PAROL SO'RAMASIN:
                // userDetails-username, null- password; userDetails.getAuthorities() yani- Roles
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                 new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());

                //SYSTEMAGA USERNI KIRITISH
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
         }
        }
         filterChain.doFilter(request,response);
    }

}
