package net.pmkjun.totemserver;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 웹 애플리케이션이 시작될 때 실행되는 코드
        System.out.println("===================================");
        System.out.println("Totem Server Started.");
        System.out.println("===================================");

        TotemDB totemData = new TotemDB();
        // 예: DB 커넥션 풀 초기화, 설정 파일 로드 등
    }
}