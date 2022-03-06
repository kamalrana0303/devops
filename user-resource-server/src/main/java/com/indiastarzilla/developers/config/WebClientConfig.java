//package com.indiastarzilla.developers.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
//import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class WebClientConfig {
//
//
//    @Bean
//    public WebClient webClient(OAuth2AuthorizedClientManager auth2AuthorizedClientManager) {
//        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
//                new ServletOAuth2AuthorizedClientExchangeFilterFunction(auth2AuthorizedClientManager);
//        /** it will add access token in authorization header
//         * if authorized client does not exist it will retrigger
//         * authorization for the authorization code grant for specific
//         * client it will also refresh token if it expired
//         **/
//        return WebClient.builder()
//                .apply(oauth2.oauth2Configuration())
//                .build();
//    }
//
//
//    @Bean
//    OAuth2AuthorizedClientManager auth2AuthorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
//        OAuth2AuthorizedClientProvider auth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
//                .authorizationCode()
//                .refreshToken()
//                .clientCredentials()
//                .build();
//        DefaultOAuth2AuthorizedClientManager auth2AuthorizedClientManager =
//                new DefaultOAuth2AuthorizedClientManager(
//                        clientRegistrationRepository, authorizedClientRepository);
//        auth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
//        return auth2AuthorizedClientManager;
//    }
//}
