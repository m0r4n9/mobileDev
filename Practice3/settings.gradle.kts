pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Practice3"
include(":app")
include(":intentapp")
include(":share")
include(":favoritebook")
include(":systemintentsapp")
include(":simplefragmentapp")
