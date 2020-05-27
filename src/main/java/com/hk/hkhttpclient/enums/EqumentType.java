package com.hk.hkhttpclient.enums;

import lombok.Getter;

/**
 * @author : muwei
 * @ClassName:EqumentType
 * @Date: 2020/4/10 09:35
 * @Description: TODO
 * region 区域
 * org 组织
 * acsDevice 门禁控制器
 * door 门禁点
 * reader 门禁读卡器
 * encodeDevice 编码设备
 * camera 监控点
 * ioIn 报警输入
 * ioOut 报警输出
 * iasDevice 入侵报警-报警主机
 * subSys 入侵报警-子系统通道
 * defence 入侵报警-防区通道
 * visDeviceInDoor 可视对讲-室内机
 * visDeviceOutDoor 可视对讲-门口机
 * visDeviceWallDoor 可视对讲-围墙机
 * visDeviceManager 可视对讲-管理机
 * ecsDevice 梯控-控制器
 * ladderCardReader 梯控-读卡器
 * floor 梯控-楼层
 * pemsIoOut 动环-开关量
 * transducer 动环-传感器
 * sensor 动环-环境量
 */
@Getter
public enum  EqumentType {
    encodeDevice("encodeDevice","编码设备"),
    camera("camera","监控点"),
    storageDevice("storageDevice","存储设备"),
    decodeDevice("decodeDevice","解码设备"),
    acsDevice("acsDevice","门禁设备"),
    door("door","门禁点"),
    reader("reader","读卡器"),
    iasDevice("iasDevice","报警主机"),
    subSys("subSys","入侵报警-子系统通道 "),
    ladderController("ladderController","梯控主机"),
    ladderCardReader("ladderCardReader","梯控读卡器"),
    visDevice("visDevice","可视对讲设备");
    private String code;
    private String msg;

    EqumentType(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
