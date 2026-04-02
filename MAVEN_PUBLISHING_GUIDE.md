================================================================================
              HOW TO PUBLISH YOUR JAVA PACKAGE TO MAVEN
                 (Make it downloadable for others)
================================================================================

You want to make your Spring Boot project available as a Maven package
so developers can add it as a dependency:

    <dependency>
        <groupId>com.mukundkumar</groupId>
        <artifactId>books-api</artifactId>
        <version>1.0.0</version>
    </dependency>

This guide shows HOW to do it!

================================================================================
                    WHAT IS MAVEN CENTRAL?
================================================================================

Maven Central Repository:
  - Official repository for Java/Maven packages
  - Used by millions of developers worldwide
  - Free to publish
  - Automatic distribution to all Java developers
  - URL: https://repo1.maven.org/maven2/

When you publish there, developers can use your package with:
  <dependency>
      <groupId>com.mukundkumar</groupId>
      <artifactId>books-api</artifactId>
      <version>1.0.0</version>
  </dependency>

================================================================================
                    STEP 1: PREPARE YOUR PROJECT
================================================================================

Your pom.xml needs these fields BEFORE publishing:

Current pom.xml should have:
─────────────────────────────

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- REQUIRED for publishing -->
    <groupId>com.mukundkumar</groupId>
    <artifactId>books-api</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <!-- REQUIRED metadata -->
    <name>Books REST API</name>
    <description>Complete Spring Boot REST API with 40+ endpoints</description>
    <url>https://github.com/Mukundkumar07/SpringBoot-Rest-Example</url>

    <!-- License (REQUIRED) -->
    <licenses>
        <license>
            <name>Apache License, Version 2.0</name>
            <url>https://www.apache.org/licenses/LICENSE-2.0.txt</url>
        </license>
    </licenses>

    <!-- Developer info (REQUIRED) -->
    <developers>
        <developer>
            <name>Mukund Kumar</name>
            <email>mukundkumar@example.com</email>
            <url>https://github.com/Mukundkumar07</url>
        </developer>
    </developers>

    <!-- SCM info (REQUIRED) -->
    <scm>
        <url>https://github.com/Mukundkumar07/SpringBoot-Rest-Example</url>
        <connection>scm:git:https://github.com/Mukundkumar07/SpringBoot-Rest-Example.git</connection>
        <developerConnection>scm:git:https://github.com/Mukundkumar07/SpringBoot-Rest-Example.git</developerConnection>
    </scm>

    <!-- Rest of your pom.xml -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>4.0.5</version>
    </parent>

    <!-- Your dependencies here -->
    <dependencies>
        ...
    </dependencies>

    <!-- Build plugins -->
    <build>
        <plugins>
            <!-- Maven Compiler Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>

            <!-- Source JAR Plugin (REQUIRED) -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-source-plugin</artifactId>
                <version>3.3.0</version>
                <executions>
                    <execution>
                        <id>attach-sources</id>
                        <goals>
                            <goal>jar</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <!-- Javadoc Plugin (REQUIRED) -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <version>3.6.0</version>
                <executions>
                    <execution>
                        <id>attach-javadocs</id>
                        <goals>
                            <goal>jar</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <!-- GPG Sign Plugin (REQUIRED) -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-gpg-plugin</artifactId>
                <version>3.1.0</version>
                <executions>
                    <execution>
                        <id>sign-artifacts</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>sign</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <!-- Nexus Staging Plugin -->
            <plugin>
                <groupId>org.sonatype.plugins</groupId>
                <artifactId>nexus-staging-maven-plugin</artifactId>
                <version>1.6.13</version>
                <extensions>true</extensions>
                <configuration>
                    <serverId>ossrh</serverId>
                    <nexusUrl>https://s01.oss.sonatype.org/</nexusUrl>
                    <autoReleaseAfterClose>true</autoReleaseAfterClose>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <!-- Distribution -->
    <distributionManagement>
        <snapshotRepository>
            <id>ossrh</id>
            <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
        </snapshotRepository>
        <repository>
            <id>ossrh</id>
            <url>https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/</url>
        </repository>
    </distributionManagement>

</project>

================================================================================
                    STEP 2: CREATE GPG KEYS FOR SIGNING
================================================================================

Maven requires code signing for security. You need GPG keys.

Install GPG (Mac):
──────────────────
brew install gnupg

Generate GPG Key:
──────────────────
gpg --full-generate-key

Choose options:
  Algorithm: RSA and RSA (1)
  Key size: 4096
  Expiration: 0 (no expiration)
  Real name: Mukund Kumar
  Email: mukundkumar@example.com
  Comment: Books API Signing Key
  Passphrase: [Create a strong password]

View your keys:
────────────────
gpg --list-keys

You'll see something like:
  pub   rsa4096 2026-04-03 [SC]
        ABCD1234EFGH5678IJKL9012MNOP3456 ← This is your KEY ID
  uid           [ultimate] Mukund Kumar <mukundkumar@example.com>

Distribute public key:
───────────────────────
gpg --keyserver keyserver.ubuntu.com --send-keys ABCD1234EFGH5678IJKL9012MNOP3456

(Replace ABCD... with your actual key ID)

================================================================================
                    STEP 3: SETUP MAVEN CREDENTIALS
================================================================================

Create ~/.m2/settings.xml file:
───────────────────────────────

mkdir -p ~/.m2
nano ~/.m2/settings.xml

Add this content:

<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <!-- Sonatype credentials -->
    <servers>
        <server>
            <id>ossrh</id>
            <username>YOUR_SONATYPE_USERNAME</username>
            <password>YOUR_SONATYPE_PASSWORD</password>
        </server>
    </servers>

    <!-- GPG configuration -->
    <profiles>
        <profile>
            <id>gpg-sign</id>
            <properties>
                <gpg.keyname>YOUR_GPG_KEY_ID</gpg.keyname>
                <gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase>
            </properties>
        </profile>
    </profiles>

    <activeProfiles>
        <activeProfile>gpg-sign</activeProfile>
    </activeProfiles>

</settings>

Replace:
- YOUR_SONATYPE_USERNAME: Your Sonatype username
- YOUR_SONATYPE_PASSWORD: Your Sonatype password
- YOUR_GPG_KEY_ID: Your GPG key ID
- YOUR_GPG_PASSPHRASE: Your GPG password

================================================================================
                    STEP 4: CREATE SONATYPE ACCOUNT
================================================================================

1. Go to: https://issues.sonatype.org/

2. Create account (sign up)

3. Create JIRA ticket:
   - Click "Create" button
   - Project: Community Hosting
   - Issue Type: New Project
   - Summary: "Books REST API - com.mukundkumar"
   - Description: "Spring Boot REST API with 40+ endpoints"
   - Group ID: com.mukundkumar
   - Project URL: https://github.com/Mukundkumar07/SpringBoot-Rest-Example
   - SCM URL: https://github.com/Mukundkumar07/SpringBoot-Rest-Example.git

4. Wait for approval (usually 1-2 hours)

5. Once approved, you'll get Sonatype credentials

================================================================================
                    STEP 5: PREPARE FOR RELEASE
================================================================================

Update version in pom.xml:
──────────────────────────

<version>1.0.0</version>  (Remove -SNAPSHOT if present)

Commit to git:
───────────────
git add pom.xml
git commit -m "chore: prepare for maven release"
git push origin SpringBoot-Rest-Complete

Create git tag:
────────────────
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0

================================================================================
                    STEP 6: BUILD AND DEPLOY
================================================================================

Clean and build:
─────────────────
mvn clean build

Deploy to Maven Central:
────────────────────────
mvn deploy -P gpg-sign

This will:
1. Build your project
2. Create JAR, sources JAR, and javadoc JAR
3. Sign all JARs with GPG
4. Upload to Sonatype staging
5. Close the staging repository
6. Release to Maven Central

Wait for:
──────────
- Building... (3-5 minutes)
- Syncing to Maven Central... (up to 10 minutes)

Check deployment:
──────────────────
https://repo1.maven.org/maven2/com/mukundkumar/books-api/1.0.0/

Your package is published! 🎉

================================================================================
                    STEP 7: VERIFY PUBLICATION
================================================================================

Check on Maven Central:
────────────────────────
https://mvnrepository.com/artifact/com.mukundkumar/books-api/1.0.0

Check on official repo:
────────────────────────
https://repo1.maven.org/maven2/com/mukundkumar/books-api/1.0.0/

Your package page:
───────────────────
https://mvnrepository.com/artifact/com.mukundkumar/books-api

Developers can now use:
────────────────────────
<dependency>
    <groupId>com.mukundkumar</groupId>
    <artifactId>books-api</artifactId>
    <version>1.0.0</version>
</dependency>

Or with Gradle:
────────────────
implementation 'com.mukundkumar:books-api:1.0.0'

================================================================================
                    QUICK COMMANDS REFERENCE
================================================================================

Install GPG:
  brew install gnupg

Generate keys:
  gpg --full-generate-key

List keys:
  gpg --list-keys

Send to key server:
  gpg --keyserver keyserver.ubuntu.com --send-keys KEY_ID

Create settings.xml:
  mkdir -p ~/.m2
  nano ~/.m2/settings.xml

Build and deploy:
  mvn clean deploy -P gpg-sign

Check deployment:
  https://repo1.maven.org/maven2/com/mukundkumar/books-api/

Verify on Maven Central:
  https://mvnrepository.com/artifact/com.mukundkumar/books-api

================================================================================
                    TROUBLESHOOTING
================================================================================

ERROR: "GPG not found"
─────────────────────
Solution: Install GPG first
  brew install gnupg

ERROR: "Sonatype credentials not found"
────────────────────────────────────────
Solution: Check ~/.m2/settings.xml has correct credentials

ERROR: "GPG signing failed"
───────────────────────────
Solution: Check GPG passphrase is correct in settings.xml

ERROR: "Javadoc generation failed"
───────────────────────────────────
Solution: Add proper javadoc comments to your code classes

ERROR: "Source JAR not created"
────────────────────────────────
Solution: Ensure maven-source-plugin is in pom.xml

ERROR: "Deployment timeout"
────────────────────────────
Solution: Wait 10-15 minutes for sync, then check Maven Central

ERROR: "Invalid groupId"
──────────────────────
Solution: groupId must be your domain in reverse
  Good: com.mukundkumar
  Bad: mukundkumar (missing domain pattern)

================================================================================
                    ALTERNATIVE: PUBLISH TO JCENTER
================================================================================

If Sonatype is too complex, use JCenter (easier):

1. Create Bintray account: https://bintray.com/

2. Create repository: "maven"

3. Create package: "books-api"

4. Upload JAR files

5. Link to JCenter

Pros:
  ✓ Easier than Sonatype
  ✓ Faster approval
  ✓ Still widely used

Cons:
  ✗ JCenter is shutting down (use Maven Central instead)

Best: Use Maven Central (official, long-term)

================================================================================
                    LATEST VERSION AFTER RELEASE
================================================================================

After first release (1.0.0), for future updates:

Increment version:
  <version>1.0.1</version> (for bug fixes)
  <version>1.1.0</version> (for new features)
  <version>2.0.0</version> (for major changes)

Commit:
  git add pom.xml
  git commit -m "chore: bump version to 1.0.1"
  git push origin SpringBoot-Rest-Complete

Tag:
  git tag -a v1.0.1 -m "Release version 1.0.1"
  git push origin v1.0.1

Deploy:
  mvn deploy -P gpg-sign

Each version will appear on Maven Central!

================================================================================
                    YOUR PACKAGE ON GITHUB
================================================================================

Add to your README.md:

## Installation

### Maven

```xml
<dependency>
    <groupId>com.mukundkumar</groupId>
    <artifactId>books-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```gradle
implementation 'com.mukundkumar:books-api:1.0.0'
```

See it on Maven Central:
https://mvnrepository.com/artifact/com.mukundkumar/books-api

================================================================================
                    SUMMARY
================================================================================

To publish your Spring Boot package to Maven Central:

1. ✅ Prepare pom.xml with all required metadata
2. ✅ Install GPG and create signing keys
3. ✅ Create Sonatype account and get approved
4. ✅ Setup ~/.m2/settings.xml with credentials
5. ✅ Commit changes and create git tag
6. ✅ Run: mvn deploy -P gpg-sign
7. ✅ Wait 10 minutes for sync
8. ✅ Verify on Maven Central
9. ✅ Update README with usage instructions
10. ✅ Share your published package!

Developers worldwide can now use your library! 🎉

================================================================================

