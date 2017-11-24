package tech.lapsa.insurance.notifier;

import java.io.Serializable;

import com.lapsa.insurance.domain.Request;

import tech.lapsa.java.commons.function.MyObjects;

public final class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    public static NotificationBuilder builder() {
	return new NotificationBuilder();
    }

    public static final class NotificationBuilder {

	private NotificationChannel channel;
	private NotificationRecipientType recipientType;
	private NotificationRequestStage event;
	private Request entity;

	private NotificationBuilder() {
	}

	public NotificationBuilder withChannel(final NotificationChannel channel) {
	    this.channel = MyObjects.requireNonNull(channel, "channel");
	    return this;
	}

	public NotificationBuilder withRecipient(final NotificationRecipientType recipientType) {
	    this.recipientType = MyObjects.requireNonNull(recipientType, "recipientType");
	    return this;
	}

	public NotificationBuilder withEvent(final NotificationRequestStage event) {
	    this.event = MyObjects.requireNonNull(event, "event");
	    return this;
	}

	public NotificationBuilder forEntity(final Request entity) {
	    this.entity = MyObjects.requireNonNull(entity, "entity");
	    return this;
	}

	public Notification build() {
	    return new Notification(channel, recipientType, event, entity);
	}
    }

    private final NotificationChannel channel;
    private final NotificationRecipientType recipientType;
    private final NotificationRequestStage event;
    private final Request entity;

    private Notification(NotificationChannel channel, NotificationRecipientType recipientType,
	    NotificationRequestStage event, Request entity) {
	this.channel = MyObjects.requireNonNull(channel, "channel");
	this.recipientType = MyObjects.requireNonNull(recipientType, "recipientType");
	this.event = MyObjects.requireNonNull(event, "event");
	this.entity = MyObjects.requireNonNull(entity, "entity");
    }

    public NotificationChannel getChannel() {
	return channel;
    }

    public NotificationRecipientType getRecipientType() {
	return recipientType;
    }

    public NotificationRequestStage getEvent() {
	return event;
    }

    public Request getEntity() {
	return entity;
    }
}