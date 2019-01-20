package kobeU.cs.samplesNet.retrofit;

import java.awt.Point;
import java.io.IOException;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestGSRequestPost {

    public static String baseURL = "https://script.google.com/macros/s/AKfycbxSGKpAW9y_vtyIOIt9UiBQ_xUdoCCF3RSplrDTMaBImrE1m_8/";
    public static UUID uuid0 = new UUID(1234, 901);// 各自、学籍番号などで違う値を利用してください。
    public static void main(String[] args) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(/*gson*/))
                .build();
        TestGSService service = retrofit.create(TestGSService.class);
        
        Point[] test0 = { new Point(1, 2), new Point(3, 4) };
        TestGSItem test= new TestGSItem(test0);
        
        System.out.println("Start Post:"+ uuid0.toString());
        Call<Void> repos = service.putItem(uuid0.toString(), "text00", test);
        
        repos.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("PutItem Success: " + response.message());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("PutItem Failed." );
            }
        });
     }

}
