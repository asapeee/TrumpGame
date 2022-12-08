package kobeU.cs.samplesNet.fbRealtimeDB;

public class User {
	public String name;

	public String nickname;

	public User(String name, String nickname) {
		this.name = name;
		this.nickname = nickname;
	}

	public String toString() {
		return "User[name:"+name + ", nickname:"+ nickname + "]";
	}
}
