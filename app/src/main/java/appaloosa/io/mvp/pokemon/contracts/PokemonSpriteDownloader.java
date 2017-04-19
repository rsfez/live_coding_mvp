package appaloosa.io.mvp.pokemon.contracts;


import android.graphics.Bitmap;

public interface PokemonSpriteDownloader {
    void fetchImage(String url, OnPokemonSpriteDownloadedListener listener);

    interface OnPokemonSpriteDownloadedListener {
        void onSpriteDownloadedSuccess(Bitmap sprite);
        void onSpriteDownloadedError(String error);
    }
}
