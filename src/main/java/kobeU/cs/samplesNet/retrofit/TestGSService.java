package kobeU.cs.samplesNet.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface TestGSService {
   @GET("exec?type=item")
   Call<TestGSResult<TestGSItem>> getItem(@Query("key") String key);
   
   @GET("exec?type=list")
   Call<TestGSResult<List<String>>> list();
    
   @POST("exec") @Multipart
   Call<Void> putItem(@Part("key") String key, 
                   @Part("text") String text, 
                   @Part("json") TestGSItem json);
    
}
