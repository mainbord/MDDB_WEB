package com.mddb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * Created by mainbord on 30.09.17.
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "DEVICE")
public class Display implements Serializable{
/*
    @Column(name = "DISPLAY_DIAGONAL")
    private String diagonal;

    @Column(name = "DISPLAY_RESOLUTION")
    private String resolution;

    @Column(name = "DISPLAY_TYPE")
    @Enumerated(EnumType.STRING)
    private Display.displayType type;

    @Column(name = "DISPLAY_RATIO")
    private String ratio; //по идее должен вычисляться из других параметров

    @Column(name = "DISPLAY_COLOR_AMOUNT")
    private Integer colorAmount;

    @Column(name = "DISPLAY_SENSOR_TYPE")
    @Enumerated(EnumType.STRING)
    private Display.sensorType sensorType;

    @Column(name = "DISPLAY_MULTITOUCH")
    private Integer multitouch;

    @Column(name = "DISPLAY_GLASS_DESCRIPTION")
    private String glassDescription;

    @Column(name = "DISPLAY_GLARE_FILTER")
    private Boolean glareFilter;

    @Column(name = "DISPLAY_OLEOPHOBIC")
    private Boolean oleophobic;
    *//*Экран:
- диагональ 4.3 дюйма
- разрешение 720 x 1280 точек (HD)
- тип экрана ЖК (LCD)
- соотношение сторон 16:9
- цветной, 16 миллионов миллионов цветов
- тип датчика ёмкостный, одновременное распознавание до 10 касаний (multitouch)
- стекло над экраном марки Corning Gorilla® Glass 3
- имеет антибликовое покрытие
- имеет олеофобное покрытие*//*

    public enum displayType {
        LCD, AMOLED
    }

    public enum sensorType {
        RESISTANCE, CAPACITIVE
    }*/
}


