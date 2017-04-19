package appaloosa.io.mvp.pokemon.impl;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import appaloosa.io.mvp.pokemon.contracts.PokemonSpriteDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;


public class PokemonSpriteDownloaderImpl implements PokemonSpriteDownloader, Target {
    private final Picasso mPicasso;
    private OnPokemonSpriteDownloadedListener mListener;

    public PokemonSpriteDownloaderImpl(Picasso picasso) {
        mPicasso = picasso;
    }

    @Override
    public void fetchImage(String url,
                           OnPokemonSpriteDownloadedListener listener) {
        mListener = listener;
        mPicasso.load(url).into(this);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
        mListener.onSpriteDownloadedSuccess(bitmap);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        mListener.onSpriteDownloadedError("error fetching sprite");
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {}
}
