package kobeU.cs.samplesNet.sheet;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

/**
 * Google Spreadsheet API (v4) を用いて、spreadsheet に読み書きするサンプル
 * Google の tutorial をベースに作成。
 * https://developers.google.com/sheets/api/quickstart/java
 *
 * spreadsheet 毎にインスタンスを作成。
 * データシートを２つ利用。
 * sheet1 は spreadsheet 風にアクセス。
 * https://docs.google.com/spreadsheets/d/1RCRcOImuFFMxIhCYz4gDytOzx2fa6cpGPYkKP3KRoOc/edit#gid=0
 * table1 には オブジェクトの一覧を保存し、オブジェクトの追加や更新ができるようなものを作成
 * https://docs.google.com/spreadsheets/d/1RCRcOImuFFMxIhCYz4gDytOzx2fa6cpGPYkKP3KRoOc/edit#gid=786639789
 * @author kamada
 *
 */
public class SheetReadWriter {
    private static final String APPLICATION_NAME = "KobeU SoftDev Sample using QuickStart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    /*
     * spreadsheetId0: 各自が利用する spreadsheet の id をいれてくれればと。
     */
    private static final String spreadsheetId0 = "1RCRcOImuFFMxIhCYz4gDytOzx2fa6cpGPYkKP3KRoOc";

    /**
     * SCOPES は、spreadsheet などへのアクセス種別を定義.
     * SCOPES を書き換えた場合は、tokens/StoreCredential を削除して再取得しましょう
     * sheet から読むだけの場合は、SheetsScopes.SPREADSHEETS_READONLY を使いましょう。
     * odifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES =
    		Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * getCredential は、tutorial からそのまま借用
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = SheetReadWriter.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


	private String spreadsheetId;
	private Sheets service;

	/*
	 * コンストラクタ
	 */
	public SheetReadWriter(String spreadsheetId) throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		this.spreadsheetId = spreadsheetId;
		this.service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	/*
	 * spreadsheet の指定 range からデータを取得して表示する。
	 */
    public void readTest(String range) throws IOException {
    	ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("readTest: No Data.");
        } else {
        	System.out.println("readTest: begin");
            for (List row : values) {
            	for(Object cell: row) {
            		System.out.print(" " + cell);
            	}
            	System.out.println("");
            }
            System.out.println("readTest: end");
        }
    }
	/*
	 * spreadsheet の指定 range にデータを書き込む。
	 */
	public void writeTest(String range) throws IOException {
		List<List<Object>> vals = Arrays.asList(
				Arrays.asList("fieldG", "fieldH"),
				Arrays.asList("valG1", "valH2"));
		ValueRange body = new ValueRange().setValues(vals);
		UpdateValuesResponse result = service.spreadsheets().values().update(spreadsheetId, range, body)
				.setValueInputOption("RAW").execute();
	}

	/*
	 * spreadsheet の指定 range の行に、複数のデータ (row) を書き込み
	 */
	public void writeRow(String range, List<Object> row) throws IOException {
		List<List<Object>> vals = Collections.singletonList(row);
		ValueRange body = new ValueRange().setValues(vals);
		UpdateValuesResponse result = service.spreadsheets().values().update(spreadsheetId, range, body)
				.setValueInputOption("RAW").execute();
	}

	/**********************
	 * 以下は、 object の読み書き用。 以下の tableRange (table1 シート)を利用
	 */

	public static final String tableRange = "table1!A1:C";
	private static String nthRow(int i) {
		return "table1!A"+i+":C";
	}



    /**
     * key で指定されたオブジェクトを取得。見つからない場合は null.
     * @param key
     * @return
     * @throws IOException
     */
    public SampleRecord get(String key) throws IOException {
       	ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, tableRange)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            return null;
        } else {
            for (List<Object> row : values) {
            	if(row.get(0).equals(key)) return SampleRecord.restore(row);
            }
            return null;
        }
    }


    /**
     * record を保存。すでに同じ record が保存されている場合は上書き保存。なければ追加。
     * @param record
     * @throws IOException
     */
    public void update(SampleRecord record) throws IOException {
       	ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, tableRange)
                .execute();
       	int count = 1;
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            /* do nothing */
        } else {
        	String key = record.id.toString();
            for (List<Object> row : values) {
            	if(row.get(0).equals(key)) break;
            	count++;
            }
        }
        writeRow(nthRow(count), record.toRow());
    }

    /**
     * main: 簡単な利用例
     */
    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.

        SheetReadWriter target = new SheetReadWriter(spreadsheetId0);

        /* spreadsheet 風アクセス */
       target.readTest("sheet1!A2:J");
       target.writeTest("sheet1!G2");

        /* object の読み書き */
		Point[] points = { new Point(1,1), new Point(2,4), new Point(3,9), new Point(4,16) };
		SampleRecord r1 = new SampleRecord(1234, "tag0:", new SampleJsonObj(points));
      	SampleRecord r0 = target.get("00000000-0000-04d2-0000-00000000138c");
      	System.out.println("get():"+r0);
      	r0.tag = r0.tag.substring(0, 6)+ "@" + Calendar.getInstance().getTime();
      	System.out.println("get():"+r0);
      	target.update(r0);
      	target.update(r1);


    }
}
// [END sheets_quickstart]
