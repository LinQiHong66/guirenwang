package com.inesv.digiccy.persistence.power;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.PowerInfoDto;
import com.inesv.digiccy.dto.ProwerParamDto;

@Component
public class PowerInfoOperation {
	private static Logger logger = LoggerFactory.getLogger(PowerInfoOperation.class);
	@Autowired
	QueryRunner queryRunner;

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void addInfo(PowerInfoDto dto) throws Exception {
		// 插入权限操作信息
		String sql = "insert into t_inesv_powerinfo (url, info, time, userName) values (?,?,?,?)";
		Object[] params = new Object[] { dto.getUrl(), dto.getInfo(), dto.getTime(), dto.getUserName() };
		Map<Long, Map<String, Object>> keymap = queryRunner.insert(sql, new KeyedHandler<Long>(), params);
		Iterator<Long> keyIter = keymap.keySet().iterator();
		// 插入权限的各种参数信息
		if (keyIter.hasNext()) {
			int key = Integer.parseInt(keyIter.next() + "");
			ArrayList<ProwerParamDto> para = dto.getParams();
			for (ProwerParamDto param : para) {
				// 遍历出来的权限参数
				param.setPowerId(key);
				Object[] infoParams = new Object[] { param.getParamInfo(), param.getParamName(), param.getPowerId(),
						param.getParamValue() == null ? "" : param.getParamValue() };
				String paramSql = "insert into t_inesv_power_params (paramInfo, paramName, powerId, paramValue) values (?,?,?,?)";
				queryRunner.update(paramSql, infoParams);
			}
		} else {
			logger.debug("插入失败");
			throw new Exception("插入失败");
		}
	}
}
