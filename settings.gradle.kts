pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Versati"
include(":app")
include(":core:database")
include(":core:designsystem")
include(":core:network")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:imageparsing:api")
include(":feature:imageparsing:impl")
include(":feature:qrcodes")
