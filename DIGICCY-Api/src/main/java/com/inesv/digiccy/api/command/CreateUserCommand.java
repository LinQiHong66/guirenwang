package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private long id;
    private String username;
    private String password;
    private String nickname;

    public CreateUserCommand(long id,String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
}
