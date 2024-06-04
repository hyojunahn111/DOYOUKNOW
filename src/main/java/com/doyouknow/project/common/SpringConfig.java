package com.doyouknow.project.common;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        /*
         INSERT SQL 실행 시 사용자 입력을 기반으로 DTO를 구성 후 Entity를 생성해 영속화함
         Entity 클래스에는 데이터 무결성을 위해 Setter 메소드 사용을 지양한다.

         ModelMapper는 기본적으로 타겟 클래스의 Setter 메소드를 이용해 인스턴스를 생성

         setFieldAccessLevel(Accesslevel.PRIVATE)
         : ModelMapper가 접근가능한 접근제한단계를 PRIVATE으로 설정

         setFieldMatchingEnabled(true)
         : 값을 주입할 때 Setter 메소드를 사용하지 않고 필드 직접 할당 허용
         ( setFieldAccessLevel(AccessLevel.PRIVATE) 설정으로 직접 할당이 가능해짐 )
         */

        return modelMapper;
    }
}
