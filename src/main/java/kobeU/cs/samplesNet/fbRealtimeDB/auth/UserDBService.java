package kobeU.cs.samplesNet.fbRealtimeDB.auth;

import kobeU.cs.samplesNet.fbRealtimeDB.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserDBService {
    @GET("protected/users/{uid}.json")
    Call<User> get(@Path("uid") String id, @Query("auth") String token);

    @PUT("protected/users/{uid}.json")
    Call<User> put(@Path("uid") String id, @Body User user, @Query("auth") String token);

}
