package me.darkwinged.essentialsz.commands.processor.annotation;

import me.darkwinged.essentialsz.commands.decorator.factory.PlayersOnlyDecoratorFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DecoratorAnnotation(PlayersOnlyDecoratorFactory.class)
public @interface PlayersOnly {}
