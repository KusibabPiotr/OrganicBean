package ka.piotr.organicbean.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import ka.piotr.organicbean.jwt.JwtClassParams;
import ka.piotr.organicbean.jwt.filter.JsonObjectAuthenticationFilter;
import ka.piotr.organicbean.jwt.filter.JwtAuthorizationFilter;
import ka.piotr.organicbean.jwt.handler.RestAuthenticationFailureHandler;
import ka.piotr.organicbean.jwt.handler.RestAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationSuccessHandler successHandler;
    private final RestAuthenticationFailureHandler failureHandler;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userService;
    private final JwtClassParams jwtParams;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();
        http
                .authorizeRequests()
                    .antMatchers("/swagger-ui/**").permitAll()
                    .antMatchers("/v2/api-docs").permitAll()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/swagger-resources/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/login","/v1/register").permitAll()
                    .antMatchers(HttpMethod.GET,"/v1/register/confirm").permitAll()
                    .antMatchers(HttpMethod.GET,"/v1/dishes/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/v1/weather/getNow").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilter(authenticationFilter())
                    .addFilterAfter(new JwtAuthorizationFilter(jwtParams,userService),JsonObjectAuthenticationFilter.class)
                .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
        var filter = new JsonObjectAuthenticationFilter(objectMapper);
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}
