package com.thedailypine.dailypine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.thedailypine.dailypine.Company;
import com.thedailypine.dailypine.ReadJSONExample;

public class MainActivity extends AppCompatActivity {

    private TextView outputText;
    private Button button;

    private TextView postName;
    private TextView postDesc;
    private TextView postDate;
    private TextView postAuthor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.outputText = (TextView) this.findViewById(R.id.editText);
        this.button = (Button) this.findViewById(R.id.button);

        this.postName = (TextView) this.findViewById(R.id.postName);
        this.postDesc = (TextView) this.findViewById(R.id.postDesc);
        this.postDate = (TextView) this.findViewById(R.id.postDate);
        this.postAuthor = (TextView) this.findViewById(R.id.postAuthor);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runExample(view);
            }
        });
    }

    public void runExample(View view)  {
        try {
            if(ReadJSONExample.checkInternetConnection(this)){


                String jsonUrl = "https://thedailypine.ru/api.php?dailypineapi.loadpage={%22page%22:1}";

                // Create a task to download and display json content.
                DownloadJSON task = new DownloadJSON(this, this.postName, this.postAuthor, this.postDate, this.postDesc);

                // Execute task (Pass jsonUrl).
                task.execute(jsonUrl);
            } else {
                outputText.setText("No internet connection");
            }
        } catch(Exception e)  {
            outputText.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}