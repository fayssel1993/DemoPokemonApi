package com.faysselyabahddou.demopokemonapi.app;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
