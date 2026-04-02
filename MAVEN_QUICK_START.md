================================================================================
                    PUBLISH TO MAVEN - QUICK START
                        (5 Simple Steps)
================================================================================

Want to let developers use your package like this?

    <dependency>
        <groupId>com.mukundkumar</groupId>
        <artifactId>books-api</artifactId>
        <version>1.0.0</version>
    </dependency>

Follow these 5 steps:

================================================================================
                        STEP 1: INSTALL GPG (5 minutes)
================================================================================

On Mac:
  brew install gnupg

Generate key:
  gpg --full-generate-key

Options:
  - Algorithm: RSA and RSA (1)
  - Size: 4096
  - Expiration: 0 (never)
  - Name: Mukund Kumar
  - Email: mukundkumar@example.com
  - Passphrase: [Create strong password]

Copy your key ID:
  gpg --list-keys
  
  Output will show: ABCD1234EFGH5678IJKL9012MNOP3456
  (This is your KEY ID)

Send to server:
  gpg --keyserver keyserver.ubuntu.com --send-keys ABCD1234EFGH5678IJKL9012MNOP3456

✅ DONE!

================================================================================
                        STEP 2: CREATE SONATYPE ACCOUNT (1 hour)
================================================================================

1. Go to: https://issues.sonatype.org/
2. Sign up for account
3. Click "Create" to open ticket
4. Fill in:
   - Summary: "Books REST API - com.mukundkumar"
   - Group ID: com.mukundkumar
   - Project URL: https://github.com/Mukundkumar07/SpringBoot-Rest-Example

5. Wait for approval email (1-2 hours usually)

Note down:
  - Sonatype username
  - Sonatype password

✅ DONE!

================================================================================
                        STEP 3: UPDATE pom.xml (10 minutes)
================================================================================

Add to your pom.xml after </name>:

    <description>Complete Spring Boot REST API with 40+ endpoints</description>
    <url>https://github.com/Mukundkumar07/SpringBoot-Rest-Example</url>

    <licenses>
        <license>
            <name>Apache License, Version 2.0</name>
            <url>https://www.apache.org/licenses/LICENSE-2.0.txt</url>
        </license>
    </licenses>

    <developers>
        <developer>
            <name>Mukund Kumar</name>
            <email>mukundkumar@example.com</email>
            <url>https://github.com/Mukundkumar07</url>
        </developer>
    </developers>

    <scm>
        <url>https://github.com/Mukundkumar07/SpringBoot-Rest-Example</url>
        <connection>scm:git:https://github.com/Mukundkumar07/SpringBoot-Rest-Example.git</connection>
    </scm>

Add build plugins section inside <build></build>:

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

Add after </build>:

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

Commit:
  git add pom.xml
  git commit -m "chore: prepare for maven central release"
  git push origin SpringBoot-Rest-Complete

✅ DONE!

================================================================================
                        STEP 4: CREATE MAVEN SETTINGS FILE (5 minutes)
================================================================================

Create file: ~/.m2/settings.xml

mkdir -p ~/.m2
cat > ~/.m2/settings.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0">

    <servers>
        <server>
            <id>ossrh</id>
            <username>YOUR_SONATYPE_USERNAME</username>
            <password>YOUR_SONATYPE_PASSWORD</password>
        </server>
    </servers>

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
EOF

Replace:
  - YOUR_SONATYPE_USERNAME: Your Sonatype username
  - YOUR_SONATYPE_PASSWORD: Your Sonatype password
  - YOUR_GPG_KEY_ID: Your GPG key ID (from Step 1)
  - YOUR_GPG_PASSPHRASE: Your GPG password

✅ DONE!

================================================================================
                        STEP 5: DEPLOY TO MAVEN CENTRAL (10 minutes)
================================================================================

In terminal:

cd /Users/mukundkumar/books

Build and deploy:
  mvn clean deploy -P gpg-sign

Wait for:
  - Building... (1-2 minutes)
  - Signing... (1 minute)
  - Uploading... (2-3 minutes)
  - Syncing to Maven Central... (5-10 minutes)

After successful deployment, check:

  https://repo1.maven.org/maven2/com/mukundkumar/books-api/1.0.0/

Your package is now LIVE! 🎉

Developers can use:
  <dependency>
      <groupId>com.mukundkumar</groupId>
      <artifactId>books-api</artifactId>
      <version>1.0.0</version>
  </dependency>

View on Maven Central:
  https://mvnrepository.com/artifact/com.mukundkumar/books-api

================================================================================
                    THAT'S IT! 5 EASY STEPS
================================================================================

1. Install GPG and create key ✓
2. Create Sonatype account ✓
3. Update pom.xml ✓
4. Create ~/.m2/settings.xml ✓
5. Run: mvn clean deploy -P gpg-sign ✓

Your package is published! 🚀

Developers worldwide can now use your library!

================================================================================
                    FOR FUTURE RELEASES
================================================================================

Update version in pom.xml:
  <version>1.0.1</version>

Commit and tag:
  git add pom.xml
  git commit -m "chore: bump version to 1.0.1"
  git tag -a v1.0.1 -m "Release 1.0.1"
  git push origin SpringBoot-Rest-Complete
  git push origin v1.0.1

Deploy:
  mvn clean deploy -P gpg-sign

New version appears on Maven Central automatically!

================================================================================
                    EXAMPLE: YOUR PUBLISHED PACKAGE
================================================================================

After publishing, your package appears at:

Maven Central:
  https://mvnrepository.com/artifact/com.mukundkumar/books-api

Repository:
  https://repo1.maven.org/maven2/com/mukundkumar/books-api/

Usage in pom.xml:
  <dependency>
      <groupId>com.mukundkumar</groupId>
      <artifactId>books-api</artifactId>
      <version>1.0.0</version>
  </dependency>

Usage in Gradle:
  implementation 'com.mukundkumar:books-api:1.0.0'

Usage in other build tools (SBT, Leiningen, etc.)

================================================================================

