pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jcenter.bintray.com")
            maven {
                url = uri("https://jitpack.io")
            }
        }
        jcenter()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jcenter.bintray.com")
            maven {
                url = uri("https://jitpack.io")
            }
        }
        jcenter() // Warning: this repository is going to shut down soon
    }
}

rootProject.name = "OceanGuard"
include(":app")
 