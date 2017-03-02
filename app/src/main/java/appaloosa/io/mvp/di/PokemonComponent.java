package appaloosa.io.mvp.di;


import appaloosa.io.mvp.MainActivity;
import dagger.Subcomponent;
import javax.inject.Singleton;

@Singleton
@Subcomponent(modules = PokemonModule.class)
public interface PokemonComponent {
    void inject(MainActivity mainActivity);
}
