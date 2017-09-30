/*
 * This file is part of QuickStart Module Loader, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package uk.co.drnaylor.quickstart.exceptions;

public class MissingDependencyException extends Exception {

    public MissingDependencyException(String message) {
        super(message);
    }

}
