package appaloosa.io.mvp.pokemon.model;

import android.graphics.Bitmap;
import com.google.gson.annotations.SerializedName;

public class Pokemon {
    @SerializedName("name")
    public String mName;
    @SerializedName("sprites")
    public SpritesUrl mSpritesUrls;
    public Bitmap mSprite;
}
