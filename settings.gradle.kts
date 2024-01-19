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
include(":core:firebase")
include(":core:navigation")
include(":core:network")
include(":core:storage")
include(":core:utils")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:charts:api")
include(":feature:charts:impl")
include(":feature:imageparsing:api")
include(":feature:imageparsing:impl")
include(":feature:qrcodes:api")
include(":feature:qrcodes:impl")