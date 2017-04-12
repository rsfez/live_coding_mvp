package appaloosa.io.mvp.pokemon.model;

import android.graphics.Bitmap;
import com.google.gson.annotations.SerializedName;

public class Pokemon {
    public String name;
    @SerializedName("sprites")
    public Sprite spritesUrls;
    Bitmap mSprite;

    public Bitmap getSprite() {
        return mSprite;
    }

    public void setSprite(Bitmap sprite) {
        mSprite = sprite;
    }
}
