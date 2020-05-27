package Api.Service;

import Api.Entity.User;
import Api.Exceptions.RegisterException;
import Api.Helpers.Data.TokenData;
import Api.Helpers.Endpoints.Endpoints;
import Api.Helpers.Requests.LoginRequest;
import Api.Helpers.Requests.RegisterRequest;
import Api.Helpers.Responses.LoginResponse;
import Api.Helpers.Responses.PublicKeyResponse;
import Api.Helpers.Responses.RegisterResponse;
import Api.Helpers.HttpMethod;
import Api.JpaRepository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.JacksonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AuthService {
    private HttpService httpService;
    private UserRepository userRepo;
    private final PublicKey pubKey;

    @Autowired
    public AuthService(HttpService httpService, UserRepository userRepo) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, URISyntaxException {
        this.httpService = httpService;
        this.userRepo = userRepo;
        this.pubKey = getPubKey();

    }

    private PublicKey getPubKey() throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeySpecException {
        CompletableFuture<HttpResponse<PublicKeyResponse>> future = httpService.requestAsync(Endpoints.AUTHSERVER + "/auth/key", HttpMethod.GET, PublicKeyResponse.class);
        HttpResponse<PublicKeyResponse> response = future.join();
        PublicKeyResponse keyResponse = response.body();
        String base64Key = keyResponse.getPublicKey();
        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes, "RSA"));
    }

    public LoginResponse login(LoginRequest request) throws ExecutionException, InterruptedException, AuthenticationException, IOException, URISyntaxException {
        CompletableFuture<HttpResponse<LoginResponse>> completableFutureLoginResponse = this.httpService.requestAsync(Endpoints.LOGIN, HttpMethod.POST, LoginResponse.class, request);
        HttpResponse<LoginResponse> loginResponse = completableFutureLoginResponse.get();
        if (loginResponse.statusCode() == 200) {
            return loginResponse.body();
        }
        throw new AuthenticationException("Failed to authenticate");
    }

    public void register(RegisterRequest request) throws IOException, URISyntaxException, ExecutionException, InterruptedException, RegisterException {
        CompletableFuture<HttpResponse<RegisterResponse>> completableFutureRegisterResponse = this.httpService.requestAsync(Endpoints.REGISTER, HttpMethod.POST, RegisterResponse.class, request);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());

        HttpResponse<RegisterResponse> registerResponse = completableFutureRegisterResponse.get();
        if (registerResponse.statusCode() == 200) {
            user.setId(registerResponse.body().getUserId());
            this.userRepo.save(user);
            return;
        }
        throw new RegisterException("Email or name already in use");
    }


    public TokenData verify(String token) throws AuthenticationException{
        Claims claims;
        try {
            claims = Jwts.parser()
                    .deserializeJsonWith(new JacksonDeserializer<>(new ObjectMapper()))
                    .setSigningKey(this.pubKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new AuthenticationException("kismet");
        }

        TokenData data = new TokenData();
        data.setEmail(claims.get("email", String.class));
        data.setId(claims.get("id", String.class));
        data.setName(claims.get("name", String.class));
        return data;
    }

}
