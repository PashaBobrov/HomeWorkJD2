package iClinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
    private static List<CommonOAuth2Provider> clients = Arrays.asList(
            CommonOAuth2Provider.GITHUB
    );

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(Environment env) {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c, env))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(CommonOAuth2Provider client, Environment env) {

        String clientName = client.name().toLowerCase();

        String clientId = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".client-id");

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".client-secret");

        if (clientSecret == null) {
            return null;
        }
        String callbackUrl = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".callback-url");

        if (callbackUrl == null) {
            return null;
        }

        return client.getBuilder(clientSecret).redirectUri(callbackUrl)
                .clientId(clientId).clientSecret(clientSecret).build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and().formLogin()
                //.loginPage("/loginUser")
                .loginProcessingUrl("/registration").permitAll()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/admin/dashboard")
                .usernameParameter("login")
                .passwordParameter("password")
                .and().oauth2Login()
//                .loginPage("/oauth_login")
                .failureUrl("/oauth_login?error=true")
                .defaultSuccessUrl("/admin/dashboard")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout=true")
                .and().exceptionHandling()
                .accessDeniedPage("/access_denied")
                .and().authorizeRequests()
                .antMatchers("/loginUser/**").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/oauth_login/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/loginUser/**").permitAll()
                //.antMatchers("/**").permitAll()
                .anyRequest().authenticated()
        ;

    }
}