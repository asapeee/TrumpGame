package kobeU.cs.samplesNet.retrofit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SampleCaller {

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        
        System.out.println("Start Sync Call");
        Call<List<Repo>> repos = service.listRepos("plham");
        Response<List<Repo>> response = repos.execute();
        if (response.isSuccessful()) {
            List<Repo> result = response.body();
            System.out.println("Success (Sync): " + result);
        } else {
            System.out.println("Failed (Sync): " + response.message());
        }

        System.out.println("Start Async Call");
        Call<List<Repo>> repos2 = service.listRepos("plham");
        repos2.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    List<Repo> result = response.body();
                    System.out.println("Success (Async): " + result);
                } else {
                    System.out.println("Failed (with Response, Async): " + response.message());
                }
                System.out.println("Termination process....");
                System.exit(0);

            }
            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                System.out.println("Failed (without Response, Async.): " + t.getMessage());
                // throw t; で例外発生させても良い
            }
        });
        System.out.println("Don't wait the completion!");
    }

}
