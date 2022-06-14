package haneul.haneulspring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

    @Configuration //스프링 빈 설정 클래스. 자동으로 싱글톤으로 만들어준다.
    @ComponentScan( //ComponentScan  :Annotation이 붙은 클래스들을 자동으로 bean으로 등록
        basePackages = "haneul.haneulspring.member",    //어디서부터 찾는지 위치 지정. 여러개 지정 가능
            basePackageClasses = AutoAppConfig.class, //지정한 위치의 클래스를 탐색 위치로 지정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
            //만약 basepackage를 지정 안한다면? AutoAppConfig가 속한 모든 패키지를 다 조회함.
)
public class AutoAppConfig{


}