package com.inesv.digiccy.event;

import java.util.Date;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
public class AddressEvent {

    private Long id;

    private String user_no;

    private String remark_address;

    private String name;

    private String card;

    private String phone;

    private String address;

    private Date date;

    private String attr1;

    private String attr2;

    private String operation;

    public AddressEvent(Long id, String user_no, String remark_address, String name, String card, String phone,
                        String address, Date date, String attr1, String attr2, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.remark_address = remark_address;
        this.name = name;
        this.card = card;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.operation = operation;
    }

    public Long getId() {
        return id;
    }

    public String getUser_no() {
        return user_no;
    }

    public String getRemark_address() {
        return remark_address;
    }

    public String getName() {
        return name;
    }

    public String getCard() {
        return card;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public String getAttr1() {
        return attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public String getOperation() {
        return operation;
    }
}
