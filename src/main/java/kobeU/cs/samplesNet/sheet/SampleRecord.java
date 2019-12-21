package kobeU.cs.samplesNet.sheet;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.gson.Gson;

/**
 * Sample の Record. SpreadSheet の読み書き用に List<Object> への変換系を追加している。
 * @author kamada
 *
 */
public class SampleRecord {
	UUID id;
	String tag;
	SampleJsonObj item;

	public SampleRecord (int userId, String tag, SampleJsonObj item) {
		this.id = new UUID(userId, new Random().nextInt());
		this.tag = tag;
		this.item = item;
	}
	private SampleRecord() {}

	public String toString() {
		return "[" + id.toString() + ":" + tag + ":" + item + "]";
	}

	public static SampleRecord restore(List<Object> row) {
		SampleRecord result = new SampleRecord();
		Object str0 = row.get(1);
		Object item0 = row.get(2);
		try {
			result.id = UUID.fromString(row.get(0).toString());
			result.tag = row.get(1).toString();
			Gson gson = new Gson();
			result.item = gson.fromJson(row.get(2).toString(), SampleJsonObj.class);
			return result;
		} catch (Exception e) {
			System.err.println("parse error:" + row);
			return null;
		}
	}

	public List<Object> toRow() {
		Gson gson = new Gson();
		return Arrays.asList(id.toString(), tag, gson.toJson(this.item));
	}

	public static void main(String[] args) {
		Point[] points = { new Point(1,1), new Point(2,4), new Point(3,9) };
		SampleRecord test = new SampleRecord(1234, "tag0", new SampleJsonObj(points));
		List<Object> row = test.toRow();
		System.out.println("row:" + row);
		SampleRecord recovered = SampleRecord.restore(row);
		System.out.println("fin:" + test);
	}


}
