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
public class Ram {
    private Integer size; //mb 1024
    private String type; //LPDDR2
    private Integer clock; //MHZ
    /*Оперативная память (ОЗУ, RAM): всего 2048 Мб, двухканальная LPDDR2, частота 500 МГц, доступно ~1800 Мб, свободно ~1300 Мб*/
}
