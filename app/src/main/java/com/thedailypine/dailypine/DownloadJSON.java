package com.thedailypine.dailypine;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadJSON
    // AsyncTask<Params, Progress, Result>
        extends AsyncTask<String, Void, String> {

        private Context context;
        private TextView name;
        private TextView author;
        private TextView date;
        private TextView desc;

    public DownloadJSON(Context context, TextView name, TextView author, TextView date, TextView desc)  {
            this.context = context;
            this.name = name;
            this.author = author;
            this.date = date;
            this.desc = desc;
        }

        @Override
        protected String doInBackground(String... params) {
            String textUrl = params[0];

            InputStream in = null;
            BufferedReader br= null;
            try {
                URL url = new URL(textUrl);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();
                int resCode = httpConn.getResponseCode();

                if (resCode == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();
                    br= new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb= new StringBuilder();
                    String s= null;
                    while((s= br.readLine())!= null) {
                        sb.append(s);
                        sb.append("\n");
                    }
                    return sb.toString();
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(br);
            }
            return null;
        }

        // When the task is completed, this method will be called
        // Download complete. Lets update UI
        @Override
        protected void onPostExecute(String result) {
            if(result  != null){
                Company company = null;
                try {
                    company = ReadJSONExample.readCompanyJSONFile(result);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.name.setText(company.getName());
                this.author.setText(company.getAuthor());
                this.date.setText(company.getDate());
                this.desc.setText(company.getDesc());
            } else{
                Log.e("MyMessage", "Failed to fetch data!");
            }
        }
    }
