package com.mddb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mainbord on 30.09.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Display {
    private String diagonal;
    private String resolution;
    private displayType type;
    private String ratio; //по идее должен вычисляться из других параметров
    private Integer colorAmount;
    private sensoreType sensoreType;
    private Integer multitouch;
    private String glassDescription;
    private Boolean glareFilter;
    private Boolean oleophobic;
    /*Экран:
- диагональ 4.3 дюйма
- разрешение 720 x 1280 точек (HD)
- тип экрана ЖК (LCD)
- соотношение сторон 16:9
- цветной, 16 миллионов миллионов цветов
- тип датчика ёмкостный, одновременное распознавание до 10 касаний (multitouch)
- стекло над экраном марки Corning Gorilla® Glass 3
- имеет антибликовое покрытие
- имеет олеофобное покрытие*/

    public enum displayType {
        LCD, AMOLED
    }

    public enum sensoreType{
        RESISTANCE, CAPACITIVE
    }
}


