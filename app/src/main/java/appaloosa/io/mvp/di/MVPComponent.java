package appaloosa.io.mvp.di;


import dagger.Component;

@Component(modules = MVPModule.class)
public interface MVPComponent {
    PokemonComponent plus(PokemonModule pokemonModule);
}
