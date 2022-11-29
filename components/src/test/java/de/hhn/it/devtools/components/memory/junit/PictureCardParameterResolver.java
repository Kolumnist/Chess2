package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PictureCardParameterResolver implements ParameterResolver {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PictureCardParameterResolver.class);

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return Objects.equals(parameter.getParameterizedType().getTypeName(),
                "java.util.List<de.hhn.it.devtools.apis.memory" +
                        ".PictureCardDescriptor>");
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        PictureCardDescriptor descriptor0 = new PictureCardDescriptor(1,1,null);
        PictureCardDescriptor descriptor1 = new PictureCardDescriptor(-1, 2, "Mario");
        PictureCardDescriptor descriptor2 = new PictureCardDescriptor(-1, 3, "Peach");

        List<PictureCardDescriptor> descriptors = new ArrayList<>();
        descriptors.add(descriptor0);
        descriptors.add(descriptor1);
        descriptors.add(descriptor2);

        return descriptors;
    }
}
