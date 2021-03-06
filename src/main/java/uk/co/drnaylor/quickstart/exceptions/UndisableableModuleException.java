/*
 * This file is part of QuickStart Module Loader, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package uk.co.drnaylor.quickstart.exceptions;

import java.text.MessageFormat;

public class UndisableableModuleException extends Exception {

    private final String module;

    public UndisableableModuleException(String module) {
        this(module, MessageFormat.format("Cannot disable the module \"{0}\"", module));
    }

    public UndisableableModuleException(String module, String message) {
        super(message);
        this.module = module;
    }

    public String getModule() {
        return module;
    }
}
