package com.mapper;

import com.alibaba.fastjson.JSON;
import com.dingtalk.enums.DingCloudPushBizTypeEnum;
import com.dingtalk.enums.SyncDataStatusEnum;
import com.dingtalk.mapper.ding.OpenSyncBizDataMapper;
import com.dingtalk.mapper.ding.OpenSyncBizDataMediumMapper;
import com.dingtalk.model.ding.OpenSyncBizDataDO;
import com.dingtalk.service.biz.SystemConfigServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenSyncBizDataBaseMapperTest {

	@Resource
	private OpenSyncBizDataMapper openSyncBizDataMapper;

	@Resource
	private OpenSyncBizDataMediumMapper openSyncBizDataMediumMapper;

	@Resource
	private SystemConfigServiceImpl systemConfigService;
	@Test
	public void testGetOpenSyncBizData() {
		String subscribeId = systemConfigService.getSuiteId()+"_0";
		String corpId = "ding9f50b15bccd16741";
		Integer bizType = DingCloudPushBizTypeEnum.ORG_AUTH.getValue();
		String bizId = String.valueOf(systemConfigService.getSuiteId());

		OpenSyncBizDataDO openSyncBizDataDO = openSyncBizDataMapper.getOpenSyncBizData(subscribeId,corpId,bizType,bizId);
		System.out.println(JSON.toJSONString(openSyncBizDataDO));

		bizType = DingCloudPushBizTypeEnum.ORG_USER.getValue();
		bizId = "sss";
		openSyncBizDataDO = openSyncBizDataMediumMapper.getOpenSyncBizData(subscribeId,corpId,bizType,bizId);
		System.out.println(JSON.toJSONString(openSyncBizDataDO));

	}


	@Test
	public void testOpenSyncBizDataListExcludeCorpIdByStatus() {
		String subscribeId = systemConfigService.getSuiteId()+"_0";
		List<String> excludeCorpIdList = new ArrayList<>();
		excludeCorpIdList.add("aa");
		excludeCorpIdList.add("bb");

		List<OpenSyncBizDataDO> openSyncBizDataDOList = openSyncBizDataMapper.getOpenSyncBizDataListExcludeCorpIdByStatus(subscribeId,excludeCorpIdList, SyncDataStatusEnum.WAITING.getValue(),10);
		System.out.println(JSON.toJSONString(openSyncBizDataDOList));

		openSyncBizDataDOList = openSyncBizDataMediumMapper.getOpenSyncBizDataListExcludeCorpIdByStatus(subscribeId,excludeCorpIdList, SyncDataStatusEnum.WAITING.getValue(),10);
		System.out.println(JSON.toJSONString(openSyncBizDataDOList));
		System.out.println(JSON.toJSONString(openSyncBizDataDOList));
	}


	@Test
	public void testUpdateOpenSyncBizDataStatus() {
		openSyncBizDataMapper.updateOpenSyncBizDataStatus(1L,SyncDataStatusEnum.SUCCEEDED.getValue());
		openSyncBizDataMediumMapper.updateOpenSyncBizDataStatus(1L,SyncDataStatusEnum.WAITING.getValue());

	}






}