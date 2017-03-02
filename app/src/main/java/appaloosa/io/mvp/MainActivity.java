package appaloosa.io.mvp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.pokemon_button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = getApplicationContext();
                final TextView pokemonNameTextView
                        = (TextView) findViewById(R.id.pokemon_name_textview);
                final ImageView pokemonSprite = (ImageView) findViewById(R.id.pokemon_sprite);

                Ion.with(context).load("http://pokeapi.co/api/v2/pokemon/1")
                        .as(new TypeToken<Pokemon>(){})
                        .setCallback(new FutureCallback<Pokemon>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onCompleted(Exception e, Pokemon pokemon) {
                                if(e == null && pokemon != null) {
                                    pokemonNameTextView.setText(pokemon.name.toUpperCase());
                                    Picasso.with(context).load(pokemon.sprites.frontDefault)
                                            .into(pokemonSprite);
                                } else {
                                    pokemonNameTextView.setText("ERROR FETCHING POKEMON");
                                }
                            }
                        });
            }
        });
    }
}
