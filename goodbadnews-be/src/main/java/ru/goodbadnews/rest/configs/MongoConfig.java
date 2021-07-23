package ru.goodbadnews.rest.configs;

import com.mongodb.MongoClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Getter
    @Value("${mongodb.url}")
    private String serverURL;

    @Getter
    @Value("${mongodb.port}")
    private int serverPort;

    public MongoClient mongoClient() {
        return new MongoClient(getServerURL(), getServerPort());
    }

    protected String getDatabaseName() {
        return "mondoTest";
    }
}
