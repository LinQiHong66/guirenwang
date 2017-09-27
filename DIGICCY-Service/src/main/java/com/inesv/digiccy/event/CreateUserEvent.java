package com.inesv.digiccy.event;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class CreateUserEvent{

    private long id;
    private String username;
    private String password;
    private String nickname;

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

    public CreateUserEvent(long id,String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateUserEvent{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }


}
