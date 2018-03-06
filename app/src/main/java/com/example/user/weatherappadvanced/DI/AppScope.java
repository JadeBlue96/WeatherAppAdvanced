package com.example.user.weatherappadvanced.DI;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by User on 05/03/2018.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {

}
