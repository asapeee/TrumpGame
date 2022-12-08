package kobeU.cs.samplesNet.fbRealtimeDB.auth;

import kobeU.cs.samplesNet.fbRealtimeDB.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class SampleCaller {
	// 以下の URL は各自で変更しましょう。
    static final String baseURL = "BASE_URL_OF_YOUR_REALTIME_DB"; // "各自の利用する realtimeDB の URL";

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserDBService service = retrofit.create(UserDBService.class);

        TokenManager manager = TokenManager.getManager();
        String token = manager.getToken();
        String id = manager.getTokenUser();

        User sample = new User("神戸太郎", "にっくねーむ");
        Call<User> putCall = service.put(id, sample, token);
        Response<User> putResponse = putCall.execute();
        if (putResponse.isSuccessful()) {
            System.out.println("PUT Success (Sync): " + putResponse.body());
        } else {
            System.out.println("PUT Failed (Sync): " + putResponse.message());
        }

        Call<User> getCall = service.get(id, token);
        Response<User> getResponse = getCall.execute();
        if (getResponse.isSuccessful()) {
            System.out.println("GET Success (Sync): " + getResponse.body());
        } else {
            System.out.println("GET Failed (Sync): " + getResponse.message());
        }
    }

}
