package haneul.haneulspring.scope;

import ch.qos.logback.core.net.server.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {


    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(2);


    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init = "+this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy" + this);
        }

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean = ac.getBean(ClientBean.class);
        int count1 = clientBean.logic();
        Assertions.assertThat(count1).isEqualTo(1);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        Assertions.assertThat(count2).isEqualTo(2);

    }

    @Scope("singleton") //싱글톤빈에서 프로토타입 Bean을 생성할때..?
    static class ClientBean{
        private final PrototypeBean prototypeBean; //프로토타입 Bean 생성시점에 주입...X01

        @Autowired  //프로토타입 Bean 만들어져서 할당됨
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
        }
        public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton") //싱글톤빈에서 프로토타입 Bean을 생성할때..?
    static class ClientBean2{
        private final PrototypeBean prototypeBean; //프로토타입 Bean 생성시점에 주입...X02

        @Autowired  //프로토타입 Bean 만들어져서 할당됨
        public ClientBean2(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
        }
        public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }






}
