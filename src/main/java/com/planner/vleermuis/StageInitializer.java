package com.planner.vleermuis;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<PlannerApplication.StageReadyEvent> {

    @Override
    public void onApplicationEvent(PlannerApplication.StageReadyEvent event) {
        event.getStage();
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
