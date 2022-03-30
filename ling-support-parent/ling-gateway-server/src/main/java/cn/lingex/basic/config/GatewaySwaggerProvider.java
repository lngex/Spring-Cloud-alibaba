//package cn.lingex.basic.config;
//
//import org.springframework.cloud.gateway.config.GatewayProperties;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.support.NameUtils;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Primary
//public class GatewaySwaggerProvider implements SwaggerResourcesProvider {
//    public static final String API_URI = "/v2/api-docs";
//    private final RouteLocator routeLocator;
//    private final GatewayProperties gatewayProperties;
//
//    public GatewaySwaggerProvider(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
//        this.routeLocator = routeLocator;
//        this.gatewayProperties = gatewayProperties;
//    }
//
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        // 取出 Spring Cloud Gateway 中的 route
//        List<String> routes = new ArrayList<>();
//        // 结合 application.yml 中的路由配置, 只获取有效的 route 节点
//        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
//        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
//                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
//                        .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()
//                                .toLowerCase()))
//                        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(), predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI)))));
//        return resources;
//    }
//
//    private SwaggerResource swaggerResource(String name, String location) {
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName(name);
//        swaggerResource.setLocation(location);
//        swaggerResource.setSwaggerVersion("2.0");
//        return swaggerResource;
//    }
//}