package com.service.demo.service.impl;

import com.service.demo.payload.RequestPayload;
import com.service.demo.payload.ResponsePayload;
import com.service.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DemoServiceImpl implements DemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public ResponsePayload findPerfectCycle(RequestPayload requestPayload) {
        Map<String, Boolean> outPutMap = new HashMap<>();
        requestPayload.getInput().forEach((k, v) -> outPutMap.put(k, isPerfectCycle(k, v)));
        return new ResponsePayload(outPutMap);
    }

    private boolean isPerfectCycle(String listName, List<Integer> list) {
        boolean perfectCycle = false;
        Map<Integer, Boolean> intBolMap = list.stream().collect(Collectors.toMap(integer -> integer, integer -> false));
        int iterate = 0;
        int i = list.get(0);
        intBolMap.put(i, true);
        while (iterate <= list.size()) {
            try {
                i = list.get(i);
                intBolMap.put(i, true);
                if (intBolMap.values().stream().allMatch(integerBooleanEntry -> integerBooleanEntry) && list.get(i) == list.get(0)) {
                    LOGGER.info("Perfect Cycle found for:{} ",listName );
                    perfectCycle = true;
                    break;
                }
                iterate++;
            } catch (IndexOutOfBoundsException ex) {
                LOGGER.error("Exception Array out of bound exception for list: {}",listName);
                break;
            }
        }
        return perfectCycle;
    }

}
