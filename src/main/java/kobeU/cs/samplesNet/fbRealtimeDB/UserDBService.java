package kobeU.cs.samplesNet.fbRealtimeDB;

import retrofit2.Call;
import retrofit2.http.*;

public interface UserDBService {
    @GET("fireblog/users/{uid}.json")
    Call<User> get(@Path("uid") String id);

    @PUT("fireblog/users/{uid}.json")
    Call<User> put(@Path("uid") String id, @Body User user);

    @POST("fireblog/users.json")
    Call<PostResult> post(@Body User user);
}
