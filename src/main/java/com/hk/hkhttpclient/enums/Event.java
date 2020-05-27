package com.hk.hkhttpclient.enums;

import lombok.Getter;

/**
 * @author : muwei
 * @ClassName:Event
 * @Date: 2020/5/25 15:54
 * @Description: TODO
 * 侵报警事件类型
 *  * 子系统	布防	327937
 *  * 撤防	327938
 *  * 消警	327941
 *  * 防区	旁路	327939
 *  * 旁路恢复	327940
 *  * 报警	327681
 */
@Getter
public enum Event {
    ALARM_CS_BF(327937,"布防"),
    ALARM_CS_CF(327938,"撤防"),
    ALARM_CS_CANCEL(327941,"消警"),
    ALARM_SZ_PL(327939,"旁路"),
    ALARM_SZ_CPL(327940,"旁路恢复"),
    ALARM_SZ_ALARM(327681,"报警"),

    ;
    private int code;
    private String msg;

    Event(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
