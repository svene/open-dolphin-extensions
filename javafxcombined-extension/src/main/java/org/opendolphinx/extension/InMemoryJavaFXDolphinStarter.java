package org.opendolphinx.extension;

import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphinx.extension.javafxclient.JavaFXApplicationParameters;

import java.util.function.Consumer;

public class InMemoryJavaFXDolphinStarter {

	public void start(Class javaFXApplicationClass) {
		start(javaFXApplicationClass, serverDolphin -> {});
	}
	public void start(Class javaFXApplicationClass, Consumer<ServerDolphin> serverInitializer) {

		InMemoryClientDolphinProvider dolphinProvider = new InMemoryClientDolphinProvider(new JavaFXUiThreadHandler());

		JavaFXApplicationParameters.clientDolphin = dolphinProvider.getClientDolphin();

		serverInitializer.accept(dolphinProvider.getServerDolphin());

		javafx.application.Application.launch(javaFXApplicationClass);

	}
}
