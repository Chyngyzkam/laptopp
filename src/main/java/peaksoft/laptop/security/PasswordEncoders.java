package peaksoft.laptop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class PasswordEncoders {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    public static void main(String[] args) {
        String encoder = new BCryptPasswordEncoder(10).encode("ch");
        System.out.println(encoder);
    }
}