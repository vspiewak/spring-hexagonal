package com.vspiewak.stamped.unit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.vspiewak.stamped.domain.annotation.DomainService;

@AnalyzeClasses(
        packages = "com.vspiewak.stamped",
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchitectureTest {

    private static final String BASE_PACKAGE = "com.vspiewak.stamped";
    private static final String DOMAIN_PACKAGE = BASE_PACKAGE + ".domain";
    private static final String DOMAIN_MODEL_PACKAGE = DOMAIN_PACKAGE + ".model";
    private static final String DOMAIN_SERVICE_PACKAGE = DOMAIN_PACKAGE + ".service";
    private static final String DOMAIN_USECASE_PACKAGE = DOMAIN_PACKAGE + ".usecase";
    private static final String DOMAIN_PORT_PACKAGE = DOMAIN_PACKAGE + ".port";
    private static final String APPLICATION_PACKAGE = BASE_PACKAGE + ".application";
    private static final String INFRASTRUCTURE_PACKAGE = BASE_PACKAGE + ".infrastructure";
    private static final String RUNTIME_PACKAGE = BASE_PACKAGE + ".runtime";

    @ArchTest
    static final ArchRule hexagonal_architecture_principles =
            onionArchitecture()
                    .domainModels(DOMAIN_MODEL_PACKAGE + "..")
                    .domainServices(
                            DOMAIN_SERVICE_PACKAGE + "..",
                            DOMAIN_USECASE_PACKAGE + "..",
                            DOMAIN_PORT_PACKAGE + "..")
                    .applicationServices(RUNTIME_PACKAGE + "..")
                    .adapter("application", APPLICATION_PACKAGE + "..")
                    .adapter("infrastructure", INFRASTRUCTURE_PACKAGE + "..");

    @ArchTest
    static final ArchRule domain_should_be_pure =
            classes()
                    .that()
                    .resideInAPackage(DOMAIN_PACKAGE + "..")
                    .should()
                    .onlyDependOnClassesThat()
                    .resideInAPackage(DOMAIN_PACKAGE + "..")
                    .orShould()
                    .dependOnClassesThat()
                    .resideInAPackage("java..");

    @ArchTest
    static final ArchRule service_has_domain_service_annotation =
            classes()
                    .that()
                    .resideInAPackage(DOMAIN_SERVICE_PACKAGE + "..")
                    .should()
                    .beAnnotatedWith(DomainService.class);

    @ArchTest
    static final ArchRule domain_service_annotation_only_in_service =
            classes()
                    .that()
                    .areAnnotatedWith(DomainService.class)
                    .should()
                    .resideInAPackage(DOMAIN_SERVICE_PACKAGE + "..");

    @ArchTest
    static final ArchRule usecase_in_appropriate_package =
            noClasses()
                    .that()
                    .haveNameMatching(".*UseCase")
                    .should()
                    .resideOutsideOfPackage(DOMAIN_USECASE_PACKAGE);

    @ArchTest
    static final ArchRule usecase_has_only_interface =
            classes()
                    .that()
                    .resideInAPackage(DOMAIN_USECASE_PACKAGE + "..")
                    .should()
                    .beInterfaces();

    @ArchTest
    static final ArchRule usecase_has_only_interface_without_default =
            noMethods()
                    .that()
                    .areDeclaredInClassesThat()
                    .resideInAPackage(DOMAIN_USECASE_PACKAGE + "..")
                    .and()
                    .areDeclaredInClassesThat()
                    .areInterfaces()
                    .should()
                    .notHaveModifier(JavaModifier.ABSTRACT);

    @ArchTest
    static final ArchRule port_in_appropriate_package =
            noClasses()
                    .that()
                    .haveNameMatching(".*Port")
                    .should()
                    .resideOutsideOfPackage(DOMAIN_PORT_PACKAGE);

    @ArchTest
    static final ArchRule port_has_only_interface =
            classes().that().resideInAPackage(DOMAIN_PORT_PACKAGE + "..").should().beInterfaces();

    @ArchTest
    static final ArchRule port_has_only_interface_without_default =
            noMethods()
                    .that()
                    .areDeclaredInClassesThat()
                    .resideInAPackage(DOMAIN_PORT_PACKAGE + "..")
                    .and()
                    .areDeclaredInClassesThat()
                    .areInterfaces()
                    .should()
                    .notHaveModifier(JavaModifier.ABSTRACT);
}
