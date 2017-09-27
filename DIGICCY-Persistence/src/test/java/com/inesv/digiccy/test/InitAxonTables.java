package com.inesv.digiccy.test;

import org.axonframework.eventstore.jdbc.SchemaConfiguration;

/**
 * Created by SKINK on 2016/10/10.
 */
public class InitAxonTables {
    public static void main(String[] args) {
        SchemaConfiguration schemaConfiguration = new SchemaConfiguration();
        String sql = "    create table " + schemaConfiguration.snapshotEntryTable() + " (\n" +
                "        aggregateIdentifier varchar(255) not null,\n" +
                "        sequenceNumber bigint not null,\n" +
                "        type varchar(255) not null,\n" +
                "        eventIdentifier varchar(255) not null,\n" +
                "        metaData blob,\n" +
                "        payload blob not null,\n" +
                "        payloadRevision varchar(255),\n" +
                "        payloadType varchar(255) not null,\n" +
                "        timeStamp varchar(255) not null,\n" +
                "        primary key (aggregateIdentifier, sequenceNumber, type)\n" +
                "    );";
        String sql2 = "create table " + schemaConfiguration.domainEventEntryTable() + " (\n" +
                "        aggregateIdentifier varchar(255) not null,\n" +
                "        sequenceNumber bigint not null,\n" +
                "        type varchar(255) not null,\n" +
                "        eventIdentifier varchar(255) not null,\n" +
                "        metaData blob,\n" +
                "        payload blob not null,\n" +
                "        payloadRevision varchar(255),\n" +
                "        payloadType varchar(255) not null,\n" +
                "        timeStamp varchar(255) not null,\n" +
                "        primary key (aggregateIdentifier, sequenceNumber, type)\n" +
                "    );\n";
        System.out.println(sql);
        System.out.println(sql2);
    }
}
