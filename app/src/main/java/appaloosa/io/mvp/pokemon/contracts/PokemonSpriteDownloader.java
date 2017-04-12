package appaloosa.io.mvp.pokemon.contracts;


import android.graphics.Bitmap;
import com.squareup.picasso.Target;

public interface PokemonSpriteDownloader extends Target {
    void fetchImage(String url, OnPokemonSpriteDownloadedListener listener);

    interface OnPokemonSpriteDownloadedListener {
        void onSpriteDownloadedSuccess(Bitmap sprite);
        void onSpriteDownloadedError(String error);
    }
}
