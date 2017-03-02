package appaloosa.io.mvp;

import android.app.Application;
import appaloosa.io.mvp.di.DaggerMVPComponent;
import appaloosa.io.mvp.di.MVPComponent;
import appaloosa.io.mvp.di.MVPModule;


public class MVP extends Application {

    private MVPComponent mPokemonComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mPokemonComponent = DaggerMVPComponent.builder().mVPModule(new MVPModule(this)).build();
    }

    public MVPComponent getPokemonComponent() {
        return mPokemonComponent;
    }
}
