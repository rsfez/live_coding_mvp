package appaloosa.io.mvp.pokemon.contracts;


import android.graphics.Bitmap;

public interface PokemonView {
    void displayPokemonSprite(Bitmap sprite);
    void displayPokemonName(String name);
}
