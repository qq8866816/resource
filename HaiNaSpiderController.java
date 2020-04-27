package com.pajk.hlsync.web.controller;

import com.alibaba.fastjson.JSON;
import com.pajk.hlsync.biz.SyncHaiNaSpiderBiz;
import com.pajk.hlsync.client.model.enums.HaiNaSpiderStatusEnum;
import com.pajk.hlsync.client.model.param.HaiNaSpiderContentDTO;
import com.pajk.hlsync.tair.SpiderCacheManager;
import com.pajk.hlsync.utils.RenderKit;
import com.pajk.hlsync.web.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/haina")
public class HaiNaSpiderController {

    private static final Logger logger = LoggerFactory.getLogger(HaiNaSpiderController.class);

    @Autowired
    private SyncHaiNaSpiderBiz syncHaiNaSpiderBiz;

    @Autowired
    private SpiderCacheManager spiderCacheManager;

    @RequestMapping(value = "/receive")
    public void receive(HttpServletRequest request, HttpServletResponse response) {
        logger.info("receive spider msg...====");
        Result requestResult = new Result();
        try {
            spiderCacheManager.incSpiderReceiveCnt();   //统计
            if (syncHaiNaSpiderBiz.getOrHandleReceiveSwitch(null) != null && !syncHaiNaSpiderBiz.getOrHandleReceiveSwitch(null)) {
                logger.info("receive switch off.");
                requestResult.setData("不接受推送信息");
                requestResult.setCode(98);
                String resJson = requestResult.toJSON();
                RenderKit.renderJSON(resJson, response);
                return;
            }
            String json = request.getParameter("jsonParam");
//            logger.info("jsonParam=\n" + json);
            if (json == null) {
                logger.info(" json is null, request parameter Maps=\n" + JSON.toJSONString(request.getParameterMap()));
                requestResult.setData("入参json parse is failed。 jsonParam＝null");
                requestResult.setCode(10);
                String resJson = requestResult.toJSON();
                RenderKit.renderJSON(resJson, response);
                return;
            }
            HaiNaSpiderContentDTO contentDTO = JSON.parseObject(json, HaiNaSpiderContentDTO.class);
            HaiNaSpiderStatusEnum status = HaiNaSpiderStatusEnum.INIT;
            String reason = "";
            if (StringUtils.isBlank(contentDTO.getPageUrl())) {
                reason = "pageUrl param missing";
                status = HaiNaSpiderStatusEnum.PARAM_MISS;
                requestResult.setData("入参 pageUrl is missing");
                requestResult.setCode(20);
            }
            /*if (StringUtils.isBlank(contentDTO.getPageUrlDomain())) {
                reason = "pageUrlDomain param missing";
                status = HaiNaSpiderStatusEnum.PARAM_MISS;
                requestResult.setData("入参 pageUrlDomain is missing");
                requestResult.setCode(30);
            }*/
            if (StringUtils.isBlank(contentDTO.getArticleTitle())) {
                reason = "articleTitle param missing";
                status = HaiNaSpiderStatusEnum.PARAM_MISS;
                requestResult.setData("入参 articleTitle is missing");
                requestResult.setCode(40);
            }
            if (StringUtils.isBlank(contentDTO.getArticleContent())) {
                reason = "articleContent param missing";
                status = HaiNaSpiderStatusEnum.PARAM_MISS;
                requestResult.setData("入参 articleContent is missing");
                requestResult.setCode(50);
            }
            if (StringUtils.isNotEmpty(reason)) {
                logger.warn(reason);
            }

            logger.info("contentUrl={}, showType={}", contentDTO.getPageUrl(), contentDTO.getShowType());
            boolean result = syncHaiNaSpiderBiz.receiveContent(contentDTO, status, reason);
            logger.info("receive result ===" + result);
            if (requestResult.getCode() == null) {
                requestResult.setData("落库结果" + result);
                requestResult.setCode(0);
            }
            String resJson = requestResult.toJSON();
            RenderKit.renderJSON(resJson, response);
        } catch (Exception e) {
            logger.warn("error:msg process fail.", e);
            requestResult.setData("系统异常");
            requestResult.setCode(99);
            String resJson = requestResult.toJSON();
            RenderKit.renderJSON(resJson, response);
        }
    }

    @RequestMapping(value = "/receiveSwitch", method = RequestMethod.GET)
    public void receiveSwitch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String isHaiNaSpiderOn = request.getParameter("isHaiNaSpiderOn");
            Result result = new Result();
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(password) || !"hlsync".equals(userName)
                    || !"hlsync1012".equals(password)) {
                result.setCode(1);
                result.setData("输入的用户名和密码不正确！");
                String resJson = result.toJSON();
                RenderKit.renderJSON(resJson, response);
                return;
            }
            if (!StringUtils.isBlank(isHaiNaSpiderOn) && !"true".equalsIgnoreCase(isHaiNaSpiderOn)
                    && !"false".equalsIgnoreCase(isHaiNaSpiderOn)) {
                result.setCode(2);
                result.setData("输入的开关状态不对！");
                String resJson = result.toJSON();
                RenderKit.renderJSON(resJson, response);
                return;
            }
            Boolean flag = syncHaiNaSpiderBiz.getOrHandleReceiveSwitch(isHaiNaSpiderOn);
            RenderKit.renderJSON("{\"code\":0,\"data\":\"isHaiNaSpiderOn=" + flag + "\"}"
                    , response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
