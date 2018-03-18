package com.demo.base.di.components;


import com.demo.base.di.modules.FragmentModule;
import com.demo.base.di.scope.PerFragment;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}