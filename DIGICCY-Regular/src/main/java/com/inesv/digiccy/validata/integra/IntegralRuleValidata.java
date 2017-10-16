package com.inesv.digiccy.validata.integra;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.persistence.integral.IntegralRuleOperation;
import com.inesv.digiccy.query.QueryUserInfo;
import com.inesv.digiccy.query.integral.QueryIntegral;
import com.integral.dto.IntegralRuleDto;
import com.pagination.PaginationDto;
import com.respon.R;
import com.respon.ResultEncoding;

/**
 * 积分规则
 * 
 * @author fangzhenxing time 2017年9月19日17:20:29
 */
@Component
public class IntegralRuleValidata {

	@Autowired
	private QueryIntegral integral;

	@Autowired
	private IntegralRuleOperation operation;

	@Autowired
	private QueryUserInfo userInfo;

	/**
	 * 按条件查询积分特权
	 * 
	 * @param integralRuleDto
	 * @return
	 */
	public R query(IntegralRuleDto integralRuleDto, PaginationDto paginationDto) {
		R r = new R();

		try {
			paginationDto.setEntitys(integral.queryIntegralRule(integralRuleDto, paginationDto));
			r.setData(paginationDto);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("查询积分规则异常");
		}

		return r;

	}

	/**
	 * 添加一个积分规则
	 * 
	 * @param integralRuleDto
	 * @return
	 */
	public R insert(IntegralRuleDto integralRuleDto) {
		R r = new R();
		integralRuleDto.setId(UUID.randomUUID().toString());
		// 数据校验
		if (!(R.isNull(integralRuleDto.getId()) && R.isNull(integralRuleDto.getInstruction())
				&& R.isNull(integralRuleDto.getNumber()) && R.isNull(integralRuleDto.getReward())
				&& R.isNull(integralRuleDto.getIdentifier()) && R.isNull(integralRuleDto.getConditions()))) {
			r.setCode(ResultEncoding.R_PARAMETER);
			r.setMsg("参数错误或缺失");
			return r;
		}

		try {
			if (operation.insert(integralRuleDto)) {
				r.setMsg("添加成功");
			} else {
				r.setCode(ResultEncoding.R_ERR);
				r.setMsg("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 修改一个积分规则
	 * 
	 * @param integralRuleDto
	 * @return
	 */
	public R update(IntegralRuleDto integralRuleDto) {
		R r = new R();

		// 数据校验
		if (!(R.isNull(integralRuleDto.getId()) && R.isNull(integralRuleDto.getInstruction())
				&& R.isNull(integralRuleDto.getNumber()) && R.isNull(integralRuleDto.getReward())
				&& R.isNull(integralRuleDto.getIdentifier()) && R.isNull(integralRuleDto.getConditions()))) {
			r.setCode(ResultEncoding.R_PARAMETER);
			r.setMsg("参数错误或缺失");
			return r;
		}

		try {
			if (operation.update(integralRuleDto)) {
				r.setMsg("修改成功");
			} else {
				r.setCode(ResultEncoding.R_ERR);
				r.setMsg("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 删除一个积分规则
	 * 
	 * @param integralRuleDto
	 * @return
	 */
	public R delete(IntegralRuleDto integralRuleDto) {
		R r = new R();

		// 数据校验
		if (!(R.isNull(integralRuleDto.getId()))) {
			r.setCode(ResultEncoding.R_PARAMETER);
			r.setMsg("参数错误或缺失");
			return r;
		}
		try {
			if (operation.delete(integralRuleDto)) {
				r.setMsg("添加成功");
			} else {
				r.setCode(ResultEncoding.R_ERR);
				r.setMsg("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 设置登录积分
	 * 
	 * @param userId
	 */
	@Transactional
	public void addIntegral(Long userId, String identifier) {

		try {
			List<IntegralRuleDto> dtos = new ArrayList<>();
			IntegralRuleDto ruleDto = new IntegralRuleDto();
			ruleDto.setIdentifier(identifier);
			PaginationDto paginationDto = new PaginationDto();

			// 拿到完成任务获取积分状态实体
			dtos = integral.queryIntegralRule(ruleDto, paginationDto);

			if (dtos.size() > 0) {
				// 拿到当天的积分总数
				int number = integral.queryCount(userId.toString(), dtos.get(0).getIdentifier());

				// 判断积分是否超过当天的数量
				if (number >= Integer.parseInt(dtos.get(0).getNumber())) {
					return;
				}

				// 增加积分
				// 增加积分则失败则直接返回
				userInfo.addIntegral(userId.toString(), Integer.parseInt(dtos.get(0).getReward()),
						dtos.get(0).getType(), dtos.get(0).getIdentifier());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 添加积分和积分记录
	 * 
	 * @param userId
	 */
	@Transactional
	public void addIntegral(Long userId, String instruction, double recharge_price) {

		try {
			List<IntegralRuleDto> dtos = new ArrayList<>();
			IntegralRuleDto ruleDto = new IntegralRuleDto();
			ruleDto.setIdentifier(instruction);
			PaginationDto paginationDto = new PaginationDto();

			// 拿到完成任务获取积分状态实体
			dtos = integral.queryIntegralRule(ruleDto, paginationDto);
			double rechargeCoin = Double.parseDouble(dtos.get(0).getConditions());

			// 拿到整数倍数
			int iso = (int) (recharge_price / rechargeCoin);
			if (iso < 1) {
				System.out.println("==========充值金额少于规定金额,无法获取积分===========");
				return;
			}

			// 拿到当天的积分总数
			int number = integral.queryCount(userId.toString(), dtos.get(0).getIdentifier());

			// 拿到差分
			int prices = Integer.parseInt(dtos.get(0).getNumber()) - number;

			// 判断当天剩余的金额
			if (prices <= 0) {
				System.out.println("==========当前积分数已满===========");
				return;
			}

			// 拿到充值总积分
			int count = iso * Integer.parseInt(dtos.get(0).getReward());

			int countNumber = 0;

			// 判断获得的积分和所能充值的积分额度
			if (count > prices) {
				countNumber = prices;
			} else {
				countNumber = count;
			}

			// 增加积分
			if (dtos.size() > 0) {
				// 增加积分则失败则直接返回
				userInfo.addIntegral(userId.toString(), countNumber, dtos.get(0).getType(),
						dtos.get(0).getIdentifier());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 修改状态
	 * @return
	 */
	public R updateState(String id,String state){
		R r=new R();
		
		try {
			
			if(!(R.isNull(id) && R.isNull(state))){
				r.setCode(ResultEncoding.R_PARAMETER);
				r.setMsg("关键字缺失");
			}
			
			if(operation.updateState(state, id)){
				r.setMsg("装填更新成功");
				return r;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		r.setCode(ResultEncoding.R_ERR);
		r.setMsg("更新状态异常");
		return r;
		
	}
}
