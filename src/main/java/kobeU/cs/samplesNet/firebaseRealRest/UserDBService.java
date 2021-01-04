package kobeU.cs.samplesNet.firebaseRealRest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserDBService {
    @GET("users/{uid}.json")
    Call<User> get(@Path("uid") String id);

    @PUT("users/{uid}.json")
    Call<User> put(@Path("uid") String id, @Body User user);
}
