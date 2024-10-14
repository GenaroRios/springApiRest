package com.example.springApiRest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Configurar endpoints publicos
                    http.requestMatchers(HttpMethod.GET, "/api/book/all").hasAuthority("READ");

                    //Configurar endpoints privados
                    http.requestMatchers(HttpMethod.DELETE, "/api/book/{id}").hasAuthority("DELETE");

                    // Configurar el resto de endpoints - NO ESPECIFICADOS
                    http.anyRequest().denyAll(); //Niega toda peticion a un endpoint no especificado
                    //http.anyRequest().authorize(); Solo permite peticiones autenticadas a endpoints no especificados
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    /**
     * Con esta funcion vamos a traer la informacion de los usuarios desde la base de datos para poder verificarlos con la
     * informacion ingresada.
     */
    @Bean
    public UserDetailsService userDetailsService(){

        //Simulamos que esta informacion viene de nuestra BDD para verificar la informacion que ingreso el usuario
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("Genaro")
                .password("44619325")
                .roles("ADMIN")
                .authorities("READ", "CREATE", "DELETE")
                .build());
        userDetailsList.add(User.withUsername("Rios")
                .password("44619325Gjr")
                .roles("USER")
                .authorities("READ")
                .build());
        //Guardamos el usuario en memoria
        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance(); //Esta clase esta deprecada ya que no encripta las contraseñas, pero sirve para hacer pruebas.
        //return new BcryptPasswordEncoder(); Este si encripta las contraseñas y sirve para produccion.
    }

}
