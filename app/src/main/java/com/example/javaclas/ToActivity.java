    package com.example.javaclas;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.Toolbar;

    import android.app.ActionBar;
    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.TextView;
    //import android.widget.Toolbar;

    import static com.example.javaclas.MainActivity.EXTRA_MESSAGE;

    public class ToActivity extends AppCompatActivity {

        String message;
        String showOrder;
        String ID;
        //String newMessage;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.newactivity);

            Bundle bundle = getIntent().getExtras();
            message = bundle.getString(EXTRA_MESSAGE);

            TextView orderSummaryTextView = findViewById(R.id.summary_text_view);
            orderSummaryTextView.setText(message);

        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu) {
            //Inflate the menu, this adds item to the action bar if its present
            getMenuInflater().inflate(R.menu.mymenu, menu);

            return true;
        }





        public void sendOrder(View view) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");

            //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            //intent.putExtra(Intent.EXTRA_SUBJECT, nameEdit + "'s Order");
            intent.putExtra(Intent.EXTRA_TEXT, message);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        }


    }