package com.inesv.digiccy.dto.auth;


import java.util.List;

/**
 * Created by JimJim on 2016/11/4 0004.
 */
public class MenuDto {

    /** 菜单名 */
    private String name;

    private String url;

    /** 子菜单 */
    private List<MenuDto> subMenu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuDto> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<MenuDto> subMenu) {
        this.subMenu = subMenu;
    }

    public MenuDto(String name, String url, List<MenuDto> subMenu) {
        this.name = name;
        this.url = url;
        this.subMenu = subMenu;
    }
}
