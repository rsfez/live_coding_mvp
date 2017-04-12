package appaloosa.io.mvp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import appaloosa.io.mvp.pokemon.contracts.PokemonPresenter;
import appaloosa.io.mvp.pokemon.contracts.PokemonView;
import appaloosa.io.mvp.pokemon.impl.PokemonInteractorImpl;
import appaloosa.io.mvp.pokemon.impl.PokemonPresenterImpl;
import appaloosa.io.mvp.pokemon.impl.PokemonSpriteDownloaderImpl;
import appaloosa.io.mvp.pokemon.model.Pokemon;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements PokemonView {
    private PokemonPresenter mPokemonPresenter;

    private TextView mPokemonNameTextView;
    private ImageView mPokemonSpriteImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPokemonNameTextView = (TextView) findViewById(R.id.pokemon_name_textview);
        mPokemonSpriteImageView = (ImageView) findViewById(R.id.pokemon_sprite);

        Context context = getApplicationContext();
        mPokemonPresenter = new PokemonPresenterImpl(
                new PokemonInteractorImpl(
                        new Pokemon(),
                        Ion.with(context),
                        new PokemonSpriteDownloaderImpl(Picasso.with(context))
                ), this);

        findViewById(R.id.pokemon_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPokemonPresenter.fetchPokemon();
            }
        });
    }

    @Override
    public void displayPokemonSprite(Bitmap sprite) {
        mPokemonSpriteImageView.setImageDrawable(new BitmapDrawable(getResources(), sprite));
    }

    @Override
    public void displayPokemonName(String name) {
        mPokemonNameTextView.setText(name);
    }
}
