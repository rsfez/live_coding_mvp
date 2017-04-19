package appaloosa.io.mvp.pokemon.impl;


import android.graphics.Bitmap;
import appaloosa.io.mvp.pokemon.model.Pokemon;
import appaloosa.io.mvp.pokemon.contracts.PokemonInteractor;
import appaloosa.io.mvp.pokemon.contracts.PokemonSpriteDownloader;
import appaloosa.io.mvp.pokemon.contracts.PokemonSpriteDownloader.OnPokemonSpriteDownloadedListener;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.builder.Builders.Any.B;
import com.koushikdutta.ion.builder.LoadBuilder;

public class PokemonInteractorImpl
        implements PokemonInteractor, FutureCallback<Pokemon>, OnPokemonSpriteDownloadedListener {
    private Pokemon mFetchedPokemon;
    private final LoadBuilder<B> mIonBuilder;
    private final PokemonSpriteDownloader mPokemonSpriteDownloader;

    private OnPokemonFetchedListener mOnPokemonFetchedListener;

    public PokemonInteractorImpl(Pokemon fetchedPokemon,
                                 LoadBuilder<B> ionBuilder,
                                 PokemonSpriteDownloader pokemonSpriteDownloader) {
        mFetchedPokemon = fetchedPokemon;
        mIonBuilder = ionBuilder;
        mPokemonSpriteDownloader = pokemonSpriteDownloader;
    }

    @Override
    public void getPokemon(OnPokemonFetchedListener onPokemonFetchedListener) {
        mOnPokemonFetchedListener = onPokemonFetchedListener;
        mIonBuilder.load("http://pokeapi.co/api/v2/pokemon/1")
                .as(new TypeToken<Pokemon>(){})
                .setCallback(this);
    }

    @Override
    public void onCompleted(Exception e, Pokemon pokemon) {
        if(e == null && pokemon != null) {
            mFetchedPokemon = pokemon;
            mPokemonSpriteDownloader.fetchImage(pokemon.spritesUrls.frontDefault, this);
        } else {
            mOnPokemonFetchedListener.onPokemonFetchedError("Could not fetch pokemon");
        }
    }

    @Override
    public void onSpriteDownloadedSuccess(Bitmap sprite) {
        mFetchedPokemon.mSprite = sprite;
        mOnPokemonFetchedListener.onPokemonFetchedSuccess(mFetchedPokemon);
    }

    @Override
    public void onSpriteDownloadedError(String error) {
        mOnPokemonFetchedListener.onPokemonFetchedError(error);
    }
}
