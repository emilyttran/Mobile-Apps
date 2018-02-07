import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.secondapp.R;

/**
 * Created by emily_000 on 2/7/2018.
 */

public class RecipeDetailActivity extends AppCompatActivity {
    // override
    protected void onCreate(Bundle savedInstanceState) {  // everytime you override, you need to call the super
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // This is the receiver of the Intent
        // Needs title and instruction URL
    }
}