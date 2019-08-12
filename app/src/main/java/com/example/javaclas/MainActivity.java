    package com.example.javaclas;

    import androidx.appcompat.app.AppCompatActivity;

    import android.app.ActionBar;
    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;

    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {
        TextView quantityTextView;
        TextView summaryTextView;
        int numberOfCoffees = 1;
        int number;


        String message;


        public final static String EXTRA_MESSAGE = "newMessage";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            //Inflate the menu, this adds item to the action bar if its present
            getMenuInflater().inflate(R.menu.mymenu, menu);

            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            //This handles the action bar item click

            int id = item.getItemId();
            if (id == R.id.item1) {

                Toast.makeText(getApplicationContext(), "You clicked on Item 1", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, anotherActivity.class);
                startActivity(intent);

                return false;
            }
            return super.onOptionsItemSelected(item);
        }


        /**
         * This method is called when the plus button is clicked
         */

        public void increment(View view) {
            numberOfCoffees = numberOfCoffees + 1;
            if (numberOfCoffees > 100) {

                Toast.makeText(getApplicationContext(), "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();


                return;
            }

            displayQuantity(numberOfCoffees);
        }


        /**
         * This method is called when the minus button is clicked
         */

        public void decrement(View view) {
            numberOfCoffees--;
            if (numberOfCoffees < 1) {

                Toast.makeText(getApplicationContext(), "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();

                return;
            }
            displayQuantity(numberOfCoffees);

        }

        /**
         * This method displays the given quantity value on the screen
         */
        private void displayQuantity(int number) {
            quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setText("" + number);
        }


        //method to display
        private String displayOrderSummary(String nameEdit, boolean hasWhippedCream, boolean hasChocolateCream,
                                           int newprice, String chocolate, String whipped) {

            String showOrder = " My name is " + nameEdit + "\n Quantity of Coffees: " + numberOfCoffees +
                    "\n Whipped Cream: " + whipped + "\n Chocolate Cream: " + chocolate +
                    "\n Price for the coffee: $" + newprice;

            return showOrder;


        }

        //method to differentiate the price
        private String choiceOfCoffees (boolean chocolateCream) {

            String chocolate;


            if (chocolateCream) {
                chocolate = "Required";
            } else {
                chocolate = "Not Required";
            }
            return chocolate;

        }

            private String choiceOfCoffee (boolean whippedCream) {

            String whipped;

            if (whippedCream) {
                whipped = "Required";
            } else {
                whipped = "Not Required";
            }

            return whipped;
        }

        //method to differentiate the price
        private int getNumberOfCoffees(boolean whippedCream, boolean chocolateCream) {

            int newPrice = 2;


            if (chocolateCream) {
                newPrice = (newPrice + 1) * numberOfCoffees;
            }

            if (whippedCream) {
                newPrice = numberOfCoffees * 2;
            }

            if (chocolateCream && whippedCream) {
                newPrice = 5 * numberOfCoffees;

            }

            return newPrice;
        }

        //method for textview
        private void displayMessage(String message) {

            summaryTextView = (TextView) findViewById(R.id.summary_text_view);
            summaryTextView.setText(message);
            summaryTextView.setVisibility(View.GONE);
        }

        public void sendMessage(View view) {

            //edit text
            EditText editText = (EditText) findViewById(R.id.name_edit_text);
            String nameEdit = editText.getText().toString();

            //whipping cream
            CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
            boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

            //chocolate cream
            CheckBox chocolateCreamCheckBox = findViewById(R.id.chocolate_cream_checkbox);
            boolean hasChocolateCream = chocolateCreamCheckBox.isChecked();

            //price
            int newPrice = getNumberOfCoffees(hasWhippedCream, hasChocolateCream);

            //coffee
            String chocolate = choiceOfCoffees (hasChocolateCream);

            //whipped
            String whipped = choiceOfCoffee (hasWhippedCream);



            String showOrder = displayOrderSummary(nameEdit, hasWhippedCream, hasChocolateCream, newPrice, chocolate, whipped);

            displayMessage(showOrder);

            Intent intent = new Intent(MainActivity.this, ToActivity.class);
            message = summaryTextView.getText().toString();

            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);

        }


    }
