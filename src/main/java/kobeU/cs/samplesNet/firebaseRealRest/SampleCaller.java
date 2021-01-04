package kobeU.cs.samplesNet.firebaseRealRest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SampleCaller {
	// 以下の URL は各自で変更しましょう。
	static final String baseURL = "https://sample-rtdb.firebaseio.com/";

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserDBService service = retrofit.create(UserDBService.class);

        User sample = new User("student10", "神戸太郎");
        Call<User> repos1 = service.put(sample.uid, sample);
        Response<User> response1 = repos1.execute();
        if (response1.isSuccessful()) {
              System.out.println("PUT Success (Sync): " + response1.body());
        } else {
            System.out.println("PUT Failed (Sync): " + response1.message());
        }

        Call<User> repos2 = service.get("student10");
        Response<User> response2 = repos2.execute();
        if (response2.isSuccessful()) {
            System.out.println("GET Success (Sync): " + response2.body());
        } else {
            System.out.println("GET Failed (Sync): " + response2.message());
        }

    }

}
