package kobeU.cs.samplesNet.fbRealtimeDB.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.*;
import java.time.Instant;

public class TokenManager {
    static String tokenPath = "account/token.jwt"; // project 直下の先パスにデータが置かれます
    static String requestPath = "account/request.json"; // 先 request.json 内に account 情報を記入してください。

    static String googleapiID = "https://identitytoolkit.googleapis.com/"; // google API の URL

    static String webAPIKey = "WEB_API_KEY_OF_YOUR_FIREBASE_PROJECT"; // firebase project の web API key

    static private final TokenManager singleton = new TokenManager();
    public static TokenManager getManager() { return singleton; }

    private String readResourceFile(String path)  {
        try(InputStream in0 = new FileInputStream(path)) {
            StringWriter buf = new StringWriter();
            BufferedReader in = new BufferedReader(new InputStreamReader(in0));
            while(true) {
                String line = in.readLine();
                if(line==null) {
                    String result = buf.toString().trim();
                    System.out.println("Result["+ path + "]" + result);
                    return result;
                }
                buf.write(line);
            }
        } catch (IOException e) {
            return null;
        }
    }
    private void writeToken(String token) {
        try (PrintWriter out = new PrintWriter(new FileWriter(tokenPath))) {
            out.println(token);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String token;
    DecodedJWT decoded;

    public void sendAuthRequest()  {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(googleapiID)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AuthService service = retrofit.create(AuthService.class);
            String req0 = readResourceFile(requestPath);
            AuthRequest req = new Gson().fromJson(req0, AuthRequest.class);
            Call<TokenRecord> call = service.get(webAPIKey, req);

            Response<TokenRecord> res = call.execute();
            if (!res.isSuccessful()) {
                System.err.println("auth request failed:" + res.message() + ":" + res.body());
                return;
            }
            this.token = res.body().idToken;
            System.out.println("Received Token: " + this.token);
            writeToken(this.token);
            decoded = JWT.decode(this.token);
            System.out.println("Expire: " + decoded.getExpiresAt());

        } catch(IOException | NullPointerException e) {
            e.printStackTrace(System.err);
        }
    }

    public String getToken() {
        if(token==null) {
            String token0 = readResourceFile(tokenPath);
            if(token0 != null) {
                try {
                    decoded = JWT.decode(token0);
                    this.token = token0;
                } catch(Exception e) {
                    /* maybe broken token, do nothing */
                }
            }
        }
        if(token == null || decoded.getExpiresAtAsInstant().isBefore(Instant.now())) {
            sendAuthRequest();
        }
        return this.token;
    }

    public String getTokenUser() {
        if(token==null) getToken();
        return decoded.getClaim("user_id").asString().trim();
    }

    public static void main(String[] args) {
        TokenManager manager = getManager();
        System.out.println(manager.getToken());
    }


}
