package com.example.BookMyShowProject.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



//Learn code with durgesh

@Configuration
public class SecurityConfig{


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public UserDetailsService getDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public UserDetailsService userDetailsService(){
        //Demo Admin and User
            UserDetails normal= User.withUsername("Nayan")
                            .password(passwordEncoder().encode("Nayan123"))
                            .roles("NORMAL")
                            .build();

        UserDetails admin= User.withUsername("Rajawat")
                .password(passwordEncoder().encode("Rajawat123)"))
                .roles("ADMIN")
                .build();


        InMemoryUserDetailsManager memoryUserDetailsManager=new InMemoryUserDetailsManager(normal,admin);

        return memoryUserDetailsManager;
    }

    //We have to make a bean of Security filter chain for authorization
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
//                .requestMatchers("/User/Admin/**").hasRole("ADMIN")
//                .requestMatchers("/User/normal/**").hasRole("NORMAL")
                .requestMatchers( "/User/public") //I want public user to be accessed by all
                .permitAll()
                .anyRequest()//If any other request thann User/Public it needs to be get authenticated
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }
}
//public class SecurityConfig {
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public UserDetailsService getDeatilsService(){
//        return new MyUserDetailService();
//    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(getDeatilsService());
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.csrf().disable().
//                authorizeHttpRequests().requestMatchers("/*").permitAll().
//                anyRequest().authenticated().
//                and().formLogin();
//        return httpSecurity.build();
//    }
//}
//@Configuration
//@EnableWebSecurity
//public class AppSecurityConfig  {
//
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/api/**", "/h2-console/**").permitAll()
//                .anyRequest().authenticated();
//        http.headers().frameOptions().disable();
//        return http.build();
//    }
//    @Autowired
//    MyUserDetailService myUserDetailService;
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);
//        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        return daoAuthenticationProvider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception{
//
//    }
//}
