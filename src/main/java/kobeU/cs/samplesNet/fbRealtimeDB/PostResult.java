package kobeU.cs.samplesNet.fbRealtimeDB;

import com.google.gson.annotations.SerializedName;

public class PostResult {
    @SerializedName("name")
    public String id;

    public String toString() {
        return "[PostResult: id="+id +"]";
    }

}

