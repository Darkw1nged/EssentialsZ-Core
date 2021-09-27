package me.darkwinged.essentialsz.commands.processor.annotation;

import me.darkwinged.essentialsz.commands.decorator.factory.CommandDecoratorFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DecoratorAnnotation
{
    Class<? extends CommandDecoratorFactory<?, ?>> value();
}
