package com.thedailypine.dailypine;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;

public class ReadJSONExample {
    // Read the company.json file and convert it to a java object.
    public static Company readCompanyJSONFile(String result) throws IOException,JSONException {

        // Read content of company.json
        // String jsonText = readText(context, resId);

        JSONObject jsonRoot = new JSONObject(result);

        JSONObject response = jsonRoot.getJSONObject("response");
        JSONArray jsonDataArray = response.getJSONArray("data");

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        for(int i=0;i < jsonDataArray.length();i++) {
            HashMap<String, String> n = new HashMap<String, String>();
            n.put("id",jsonDataArray.getJSONObject(0).getString("id"));
            n.put("author",jsonDataArray.getJSONObject(0).getString("author"));
            n.put("name",jsonDataArray.getJSONObject(0).getString("name"));
            n.put("date",jsonDataArray.getJSONObject(0).getString("date"));
            n.put("description",jsonDataArray.getJSONObject(0).getString("description"));
            data.add(n);
        }

        Company company = new Company();

        company.setData(data.get(0));

        return company;
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while((s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager connManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) return false;
        if (!networkInfo.isConnected()) return false;
        if (!networkInfo.isAvailable()) return false;
        return true;
    }
}
