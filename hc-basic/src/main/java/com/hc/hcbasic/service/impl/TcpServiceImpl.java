package com.hc.hcbasic.service.impl;

import com.hc.hcbasic.service.TcpService;
import org.springframework.stereotype.Service;

@Service
public class TcpServiceImpl implements TcpService {

    /**
     * 对tcp传送的数据进行处理
     *
     * @param message 传送的数据
     */
    @Override
    public void messageHandler(String message) {

    }
}
