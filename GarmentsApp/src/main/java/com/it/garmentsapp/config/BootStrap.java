package com.it.garmentsapp.config;
import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import org.eclipse.microprofile.auth.LoginConfig;

@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles("Owner")
@SuppressWarnings({"EmptyClass", "SuppressionAnnotation"})
@ApplicationPath("app")
public class BootStrap extends jakarta.ws.rs.core.Application {
}
