package com.sosck.api;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

    @DefaultValue("dev")
    String env();

    @Key("${env}.host")
    String host();
}
