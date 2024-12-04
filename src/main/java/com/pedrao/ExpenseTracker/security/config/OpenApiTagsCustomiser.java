package com.pedrao.ExpenseTracker.security.config;

import io.swagger.v3.core.filter.SpecFilter;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.stereotype.Component;

@Component
public class OpenApiTagsCustomiser extends SpecFilter implements OpenApiCustomizer {
    @Override
    public void customise(OpenAPI openApi) {
        // remove the property reference controller
        openApi.getPaths().entrySet().removeIf(path -> path.getValue().readOperations().stream().anyMatch(
                operation -> operation.getTags().stream().anyMatch(tag -> tag.endsWith("property-reference-controller"))));

        openApi.getPaths().entrySet().removeIf(path -> path.getValue().readOperations().stream().anyMatch(
                operation -> operation.getTags().stream().anyMatch(tag -> tag.endsWith("search-controller"))));

        openApi.getPaths().entrySet().removeIf(path -> path.getValue().readOperations().stream().anyMatch(
                operation -> operation.getTags().stream().anyMatch(tag -> tag.endsWith("entity-controller"))));

        openApi.getPaths().entrySet().removeIf(path -> path.getValue().readOperations().stream().anyMatch(
                operation -> operation.getTags().stream().anyMatch(tag -> tag.endsWith("profile-controller"))));

        removeBrokenReferenceDefinitions(openApi);
    }
}