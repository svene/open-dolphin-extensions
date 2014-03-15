package org.opendolphinx.extension.client;

import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientPresentationModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientSideOnlyDolphin {

	private final ClientDolphin clientDolphin;

	public ClientSideOnlyDolphin(ClientDolphin clientDolphin) {
		this.clientDolphin = clientDolphin;
	}

	public ClientPresentationModel presentationModel(String pmId, String...attributeNames) {
		List<ClientAttribute> clientAttributes = Arrays.asList(attributeNames).stream().map(n -> new ClientAttribute(n)).collect(Collectors.toList());
		return presentationModel(pmId, clientAttributes);
	}

	public ClientPresentationModel presentationModel(final String pmId, ClientAttribute...clientAttributes) {
		return presentationModel(pmId, Arrays.asList(clientAttributes));
	}

	private ClientPresentationModel presentationModel(final String pmId, final List<ClientAttribute> clientAttributes) {
		ClientPresentationModel pm = new ClientPresentationModel(pmId, clientAttributes);
		pm.setClientSideOnly(true);
		clientDolphin.getModelStore().add(pm);
		return pm;
	}
}
