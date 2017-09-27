package com.inesv.digiccy.persistence.operation;

import com.inesv.digiccy.dto.VoteDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class VotePer {

    private static Logger logger = LoggerFactory.getLogger(VotePer.class);

    @Autowired
    private QueryRunner queryRunner;

    /**
     * 添加投票记录
     * @param voteDto
     */
    public void addVoteInfo(VoteDto voteDto){
        String sql = new ObjectChangeUtil<VoteDto>().objectToSql(voteDto, TableName.T_INESV_VOTE);
        Object params[] = new ObjectChangeUtil().objectToArray(voteDto);
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("添加投票记录失败");
        }
    }

}
