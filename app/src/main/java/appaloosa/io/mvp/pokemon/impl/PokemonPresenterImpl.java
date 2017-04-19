package appaloosa.io.mvp.pokemon.impl;

import appaloosa.io.mvp.pokemon.contracts.PokemonInteractor;
import appaloosa.io.mvp.pokemon.contracts.PokemonInteractor.OnPokemonFetchedListener;
import appaloosa.io.mvp.pokemon.contracts.PokemonPresenter;
import appaloosa.io.mvp.pokemon.contracts.PokemonView;
import appaloosa.io.mvp.pokemon.model.Pokemon;


public class PokemonPresenterImpl implements PokemonPresenter, OnPokemonFetchedListener {
    private final PokemonInteractor mPokemonInteractor;
    private final PokemonView mPokemonView;

    public PokemonPresenterImpl(PokemonInteractor pokemonInteractor, PokemonView pokemonView) {
        mPokemonInteractor = pokemonInteractor;
        mPokemonView = pokemonView;
    }

    @Override
    public void fetchPokemon() {
        mPokemonInteractor.getPokemon(this);
    }

    @Override
    public void onPokemonFetchedSuccess(Pokemon pokemon) {
        mPokemonView.displayPokemonName(pokemon.name);
        mPokemonView.displayPokemonSprite(pokemon.mSprite);
    }

    @Override
    public void onPokemonFetchedError(String error) {
        mPokemonView.displayPokemonName(error);
    }
}
