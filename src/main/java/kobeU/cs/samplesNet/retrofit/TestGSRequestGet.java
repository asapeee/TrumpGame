package kobeU.cs.samplesNet.retrofit;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestGSRequestGet {

    public static String baseURL = "https://script.google.com/macros/s/AKfycbxSGKpAW9y_vtyIOIt9UiBQ_xUdoCCF3RSplrDTMaBImrE1m_8/";
    public static UUID uuid0 = new UUID(1234, 901);// 各自、学籍番号などで違う値を利用してください。
    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(/*gson*/))
                .build();
        TestGSService service = retrofit.create(TestGSService.class);
        
        {
            System.out.println("Start Sync Call");
            Call<TestGSResult<List<String>>> repos = service.list();
            Response<TestGSResult<List<String>>> response = repos.execute();
            if (response.isSuccessful()) {
                System.out.println("List: Success (Sync), " + response.body());
            } else {
                System.out.println("List: Failed (Sync), " + response.message());
            }
        }
        
        {
            System.out.println("Start Sync Call");
            Call<TestGSResult<TestGSItem>> repos = service.getItem(uuid0.toString());
            Response<TestGSResult<TestGSItem>> response = repos.execute();
            if (response.isSuccessful()) {
                System.out.println("GetItem: Success, " + response.body());
            } else {
                System.out.println("GetItem: Failed, " + response.message());
            }
        }
    }
        
}
