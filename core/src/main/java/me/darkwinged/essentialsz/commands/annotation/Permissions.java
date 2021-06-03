package me.darkwinged.essentialsz.commands.annotation;

import me.darkwinged.essentialsz.CoreMessage;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.decorator.factory.PermissionDecoratorFactory;
import me.darkwinged.essentialsz.commands.processor.annotation.DecoratorAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DecoratorAnnotation(PermissionDecoratorFactory.class)
public @interface Permissions
{
    Permission[] value();
    boolean requiresAll() default false;
    CoreMessage noPermissionMessage() default CoreMessage.NO_PERMISSION;
}
