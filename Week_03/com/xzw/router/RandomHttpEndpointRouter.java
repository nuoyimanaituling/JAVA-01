package com.xzw.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    private  int roundRibbonIndex =0;


    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }

    @Override
    public String routeRoundRibbon(List<String> endpoints) {
        int index=(this.roundRibbonIndex)%endpoints.size();
        String url =endpoints.get(index);
        this.roundRibbonIndex++;
        return url;
    }
}
