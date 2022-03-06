package com.devops.developers.client.resource;

import com.devops.developers.client.dto.*;
import com.devops.developers.client.model.request.*;
import com.devops.developers.client.model.response.ClientDetailRest;
import com.devops.developers.client.service.ClientDetailsRegisteration;
import com.devops.developers.client.service.RedirectUriService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientResource {

    private final ModelMapper mapper;
    private final ClientDetailsRegisteration clientDetailsRegisteration;

    public ClientResource(ModelMapper mapper, ClientDetailsRegisteration clientDetailsRegisteration) {
        this.mapper = mapper;
        this.clientDetailsRegisteration = clientDetailsRegisteration;
    }

    @GetMapping("{clientId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getClientByClientId(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(this.clientDetailsRegisteration.getClientByClientId(clientId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addClient(@RequestBody ClientDetailsRM clientDetailsRM) {
        ClientDto clientDto = this.clientDetailsRegisteration.addClient(clientDetailsRM);
        ClientDetailRest clientDetailRest = mapper.map(clientDto, ClientDetailRest.class);
        return ResponseEntity.ok(clientDetailRest);
    }

    @PutMapping("authority")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> attachAuthorities(@RequestBody List<CAuthoritiesRM> authoritiesRMS, @RequestParam("clientId") String clientId) {
        ClientDto clientDto = this.clientDetailsRegisteration.attachAuthoritiesToClient(mapper.map(authoritiesRMS, new TypeToken<List<CAuthoritiesDto>>() {
        }.getType()), clientId);
        return ResponseEntity.ok(mapper.map(clientDto, ClientDetailRest.class));
    }

    @PutMapping("grant-type")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> attachAuthorizedGrantTypes(@RequestBody List<GrantTypeRM> grantTypeRMS, @RequestParam("clientId") String clientId) {
        ClientDto clientDto = this.clientDetailsRegisteration.attachGrantTypeToClient(mapper.map(grantTypeRMS, new TypeToken<List<GrantTypeDto>>() {
        }.getType()), clientId);
        return ResponseEntity.ok(mapper.map(clientDto, ClientDetailRest.class));
    }

    @PutMapping("scope")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> attachScopes(@RequestBody List<ScopeRM> scopeRMS, @RequestParam("clientId") String clientId) {
        ClientDto clientDto = this.clientDetailsRegisteration.attachScopeToClient(mapper.map(scopeRMS, new TypeToken<List<ScopeDto>>() {
        }.getType()), clientId);
        return ResponseEntity.ok(mapper.map(clientDto, ClientDetailRest.class));
    }

    @PutMapping("redirectUri")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> attachRedirectUri(@RequestBody List<RedirectUriRM> redirectUriRMS, @RequestParam("clientId") String clientId) {
        ClientDto clientDto = this.clientDetailsRegisteration.attachRegisteredRedirectUris(mapper.map(redirectUriRMS, new TypeToken<List<RedirectUriDto>>() {
        }.getType()), clientId);
        return ResponseEntity.ok(mapper.map(clientDto, ClientDetailRest.class));
    }


    @DeleteMapping("/authority/{clientId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deattachAuthoritiesFromClient(@PathVariable("clientId") String clientId, @RequestParam("authority") String authority) {
        ClientDto clientDto = this.clientDetailsRegisteration.deattachAuthoritiesFromClient(clientId, authority);
        return ResponseEntity.ok(mapper.map(clientDto, ClientDetailRest.class));
    }

    @DeleteMapping("/grant-type/{clientId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deattachGrantTypeFromClient(@PathVariable("clientId") String clientId, @RequestParam("grantType") String grantType) {
        ClientDto clientDto = this.clientDetailsRegisteration.deattachGrantTypeFromClient(clientId, grantType);
        return ResponseEntity.ok(mapper.map(clientDto, ClientDetailRest.class));
    }

    @DeleteMapping("/scope/{clientId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deattachScopeFromClient(@PathVariable("clientId") String clientId, @RequestParam("scope") String scope) {
        ClientDto clientDto = this.clientDetailsRegisteration.deattachScopeFromClient(clientId, scope);
        return ResponseEntity.ok(mapper.map(clientDto, ClientDetailRest.class));
    }

    @DeleteMapping("/redirectUri/{clientId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> dettachRegisteredRedirectUriFromClient(@PathVariable("clientId") String clientId, @RequestParam("redirectUri") String redireUri) {
        ClientDto clientDto = this.clientDetailsRegisteration.deattachRegisteredRedirectUriFromClient(clientId, redireUri);
        return ResponseEntity.ok(mapper.map(clientDto, ClientDetailRest.class));
    }
    /**
     * remove redirect uris
     * remove resource*/
}
