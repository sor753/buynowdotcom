package com.dailycodework.buynowdotcom.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//JavaConfig を作成して、Spring Boot アプリケーションの設定を行う
//JavaConfig : Bean 定義を Java クラスで表現する方法
@Configuration
public class ShopConfig {

//    @Bean メソッドを定義し、戻り値で返したオブジェクトが DI コンテナで管理される
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
