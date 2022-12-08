package kobeU.cs.samplesNet.fbRealtimeDB.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthService {
    @POST("v1/accounts:signInWithPassword")
    Call<TokenRecord> get(@Query("key") String key, @Body AuthRequest request);
}