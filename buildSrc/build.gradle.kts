plugins {
    `kotlin-dsl`
}

repositories{
    gradlePluginPortal()
    google()
    mavenCentral()
}

sourceSets{
    main{
        java{
            srcDir("src/main/kotlin")
        }
    }
}

dependencies{
    implementation("com.android.tools.build:gradle:4.2.1")
    compileOnly("org.ow2.asm:asm-commons:9.1")
    compileOnly("org.ow2.asm:asm-tree:9.1")
    compileOnly("commons-codec:commons-codec:1.15")
}