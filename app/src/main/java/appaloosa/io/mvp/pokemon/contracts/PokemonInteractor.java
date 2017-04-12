package appaloosa.io.mvp.pokemon.contracts;


import appaloosa.io.mvp.pokemon.model.Pokemon;

public interface PokemonInteractor {
    void getPokemon(OnPokemonFetchedListener onPokemonFetchedListener);

    interface OnPokemonFetchedListener {
        void onPokemonFetchedSuccess(Pokemon pokemon);
        void onPokemonFetchedError(String error);
    }
}
