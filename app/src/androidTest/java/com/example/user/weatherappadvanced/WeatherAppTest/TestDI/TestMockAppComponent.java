package com.example.user.weatherappadvanced.WeatherAppTest.TestDI;



import com.example.user.weatherappadvanced.Activity.MainActivity;
import com.example.user.weatherappadvanced.DI.AppComponent;

import dagger.Component;


/**
 * Created by User on 05/03/2018.
 */
@TestAppScope
@Component(modules=TestMockAppModule.class)
public interface TestMockAppComponent extends AppComponent {
    void inject(MainActivity activity);
}
