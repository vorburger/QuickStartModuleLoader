/*
 * This file is part of QuickStart Module Loader, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package uk.co.drnaylor.quickstart;

import com.google.common.base.Preconditions;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import uk.co.drnaylor.quickstart.config.AbstractAdaptableConfig;
import uk.co.drnaylor.quickstart.config.AbstractConfigAdapter;
import uk.co.drnaylor.quickstart.config.ModulesConfigAdapter;
import uk.co.drnaylor.quickstart.enums.LoadingStatus;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

/**
 * Defines the configuration file that loads the modules, and any {@link AbstractConfigAdapter}s.
 */
public final class SystemConfig<N extends ConfigurationNode, T extends ConfigurationLoader<N>> extends AbstractAdaptableConfig<N, T> {

    private final LoggerProxy proxy;
    private ModulesConfigAdapter configAdapter;

    SystemConfig(T loader, LoggerProxy proxy, Function<ConfigurationOptions, ConfigurationOptions> optionsTransformer) throws IOException {
        super(loader, () -> loader.createEmptyNode(optionsTransformer.apply(loader.getDefaultOptions())), optionsTransformer);
        this.proxy = proxy;
    }

    void attachModulesConfig(List<ModuleSpec> defaults, Function<Class<? extends Module>, String> description, String modulesNode,
            @Nullable String descriptionForModules) throws IOException {
        Preconditions.checkNotNull(defaults);
        Preconditions.checkState(configAdapter == null);

        Map<String, LoadingStatus> msls = defaults.stream().collect(Collectors.toMap(k -> k.getId().toLowerCase(), ModuleSpec::getStatus));
        Map<String, String> msdesc = defaults.stream()
                .collect(Collectors.toMap(k -> k.getId().toLowerCase(), k -> description.apply(k.getModuleClass())));

        configAdapter = new ModulesConfigAdapter(msls, msdesc, proxy, modulesNode, descriptionForModules);
        this.attachConfigAdapter(modulesNode, configAdapter, descriptionForModules);
    }

    public ModulesConfigAdapter getConfigAdapter() {
        return configAdapter;
    }
}
