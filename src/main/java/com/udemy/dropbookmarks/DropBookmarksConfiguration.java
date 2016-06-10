package com.udemy.dropbookmarks;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class DropBookmarksConfiguration extends Configuration {

    @NotEmpty
    private String password;

    @NotNull
    @Valid
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("database")
//    "database" keyword is parsed here and is the same as config.yml
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty
    public String getPassword() {
        return password;
    }
}
