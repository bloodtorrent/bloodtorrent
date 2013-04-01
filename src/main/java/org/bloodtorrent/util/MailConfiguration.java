package org.bloodtorrent.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Getter @Setter
@SuppressWarnings("PMD.UnusedPrivateField")
public class MailConfiguration extends Configuration {
    @JsonProperty
    private String adminMailId;
    @JsonProperty
    private String adminMailPassword;
    @JsonProperty
    private String donorTitle;
    @JsonProperty
    private String donorContent;
}
