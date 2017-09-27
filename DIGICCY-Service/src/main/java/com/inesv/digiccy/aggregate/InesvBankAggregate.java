package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.InesvBankinfoCommand;
import com.inesv.digiccy.event.InesvBankInfoEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class InesvBankAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Long id;

    public InesvBankAggregate(){}

    @CommandHandler
    public InesvBankAggregate(InesvBankinfoCommand bankinfo){
           System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
           apply(new InesvBankInfoEvent(
                   bankinfo.getUser_no(),
                   bankinfo.getRemark_name(),
                   bankinfo.getBank(),
                   bankinfo.getProvince(),
                   bankinfo.getCity(),
                   bankinfo.getBranch(),
                   bankinfo.getName(),
                   bankinfo.getBank_num(),
                   bankinfo.getDate(),
                   bankinfo.getState(),
                   bankinfo.getId(),
                   bankinfo.getOperation()
           ));
    }

    @EventHandler
    public void on(InesvBankInfoEvent bankInfoEvent){
        id = bankInfoEvent.getId();
    }
}
