/*
 * This file is part of QuickStart Module Loader, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package uk.co.drnaylor.quickstart.tests.modules.extdependenciestest.simple;

import uk.co.drnaylor.quickstart.Module;
import uk.co.drnaylor.quickstart.annotations.ModuleData;
import uk.co.drnaylor.quickstart.config.AbstractConfigAdapter;
import uk.co.drnaylor.quickstart.config.SimpleNodeConfigAdapter;
import uk.co.drnaylor.quickstart.exceptions.MissingDependencyException;

import java.util.Optional;

@ModuleData(id = "modulethree", name = "modulethree", dependencies = "moduletwo")
public class ModuleThree implements Module {

    @Override
    public Optional<AbstractConfigAdapter<?>> getConfigAdapter() {
        return Optional.of(new SimpleNodeConfigAdapter());
    }

    @Override public void checkExternalDependencies() throws MissingDependencyException {
        throw new MissingDependencyException("Test");
    }

    @Override
    public void onEnable() {

    }
}
