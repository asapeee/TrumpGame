package kobeU.cs.samplesNet.firebaseRealRest;

public class User {
	public String uid;
	public String name;

	public User(String uid, String name) {
		this.uid = uid;
		this.name = name;
	}

	public String toString() {
		return "User[uid:"+uid+", name:"+ name;
	}
}
