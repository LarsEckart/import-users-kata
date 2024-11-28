plugins {
    `java-library`
    application
    id("org.jooq.jooq-codegen-gradle") version "3.19.14"
}

repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly(libs.sqlite)
    jooqCodegen(libs.sqlite)
    runtimeOnly("org.xerial:sqlite-jdbc:3.47.0.0")
    implementation("org.jooq:jooq:3.19.14")
    implementation("org.jooq:jooq-meta:3.19.14")
    implementation("org.jooq:jooq-codegen:3.19.14")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")


    implementation(libs.json)
    implementation(libs.slf4j)
    implementation(libs.slf4jbasic)

    testImplementation(libs.junit)
    testImplementation(libs.approvaltests)
    testImplementation("org.assertj:assertj-core:3.26.0")


}

group = "kata"
version = "1.0"

java.sourceCompatibility = JavaVersion.VERSION_21

application {
    mainClass.set("kata.Main")
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}

jooq {
    configuration {
        jdbc {
            driver = "org.sqlite.JDBC"
            url = "jdbc:sqlite:file:users-source.db"
            user = ""
            password = ""
        }
        generator {
            name = "org.jooq.codegen.JavaGenerator"
            database {

                // The database dialect from jooq-meta. Available dialects are
                // named org.jooq.meta.[database].[database]Database.
                //
                name = "org.jooq.meta.sqlite.SQLiteDatabase"

                // All elements that are generated from your schema (A Java regular expression.
                // Use the pipe to separate several expressions) Watch out for
                // case-sensitivity. Depending on your database, this might be
                // important!
                //
                // You can create case-insensitive regular expressions using this syntax: (?i:expr)
                //
                // Whitespace is ignored and comments are possible.
                includes = ".*"

                // All elements that are excluded from your schema (A Java regular expression.
                // Use the pipe to separate several expressions). Excludes match before
                // includes, i.e. excludes have a higher priority
                excludes = """
                        """

                // The schema that is used locally as a source for meta information.
                // This could be your development schema or the production schema, etc
                // This cannot be combined with the schemata element.
                //
                // If left empty, jOOQ will generate all available schemata. See the
                // manual's next section to learn how to generate several schemata
                inputSchema = ""
            }

            // Generation flags: See advanced configuration properties
            generate {}
            target {

                // The destination package of your generated classes (within the
                // destination directory)
                //
                // jOOQ may append the schema name to this package if generating multiple schemas,
                // e.g. org.jooq.your.packagename.schema1
                // org.jooq.your.packagename.schema2
                packageName = "kata.jooq"

                // The destination directory of your generated classes
                directory = "src/main/java"
            }
        }
    }
}
