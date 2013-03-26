package org.bloodtorrent.bootstrap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import org.bloodtorrent.util.MailConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SimpleConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    @Valid
    @NotNull
    @JsonProperty
    private MailConfiguration mail = new MailConfiguration();

    public DatabaseConfiguration getDatabaseConfiguration() {
        return database;
    }

    public MailConfiguration getMailConfiguration() {
        return mail;
    }

}