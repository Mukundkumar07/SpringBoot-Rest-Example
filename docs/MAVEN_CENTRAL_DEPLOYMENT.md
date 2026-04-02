# Maven Central Deployment - Complete Setup Guide

This guide covers all remaining steps to deploy your Books REST API to Maven Central Repository.

## ✅ Completed Steps

- ✅ **pom.xml Updated** with all Maven Central metadata
- ✅ **Build Plugins Added** (maven-source, maven-javadoc, maven-gpg, nexus-staging)
- ✅ **Distribution Management** configured for Sonatype OSSRH
- ✅ **Git Tag Created** (v1.0.0)
- ✅ **Code Pushed to GitHub** with release tag
- ✅ **Maven Settings Template** created at `~/.m2/settings.xml`

## ⏳ Required Next Steps (User Actions)

### Step 1: Generate GPG Keys (Local Machine)

GPG keys are required to digitally sign your Maven artifacts for security and verification.

#### For macOS:

```bash
# Check if GPG is installed
gpg --version

# If not installed, install via Homebrew
brew install gnupg

# Generate a new GPG key pair
gpg --full-generate-key
```

**When prompted, use these settings:**

```
Please select what kind of key you want:
   (1) RSA and RSA (default)
   (4) RSA (sign only)
   (14) Existing key
   (15) EdDSA
   
→ Choose 1 (RSA and RSA)

What keysize do you want? (3072)
→ Press Enter for default (3072)

Key is valid for? (0)
→ Press Enter for unlimited validity (0 = no expiration)

Is this correct? (y/N)
→ Type: y

Real name: Mukund Kumar
Email address: mukundkumar@example.com
Comment: For Maven Central Publishing

Change (N)ame, (C)omment, (E)mail or (O)kay/(Q)uit?
→ Type: O

Enter passphrase:
→ **Save this passphrase securely** - you'll need it in settings.xml
```

#### Verify GPG Key Creation:

```bash
# List your GPG keys
gpg --list-keys

# Copy your key ID (8-digit hex after 'rsa3072/')
# Example: F1234567 is the key ID
```

### Step 2: Configure Maven Settings

Update your `~/.m2/settings.xml` with actual credentials:

```bash
# Open the settings file
open ~/.m2/settings.xml
# or
nano ~/.m2/settings.xml
```

**Replace these placeholders:**

1. **YOUR_SONATYPE_USERNAME** - Your Sonatype JIRA username (see Step 3)
2. **YOUR_SONATYPE_PASSWORD** - Your Sonatype JIRA password
3. **YOUR_GPG_PASSPHRASE** - The passphrase from Step 1

**Example (DO NOT COMMIT THIS):**

```xml
<server>
    <id>ossrh</id>
    <username>mukundkumar</username>
    <password>your_sonatype_password_here</password>
</server>
<server>
    <id>gpg.passphrase</id>
    <passphrase>your_gpg_passphrase_here</passphrase>
</server>
```

### Step 3: Create Sonatype Account & Request groupId

Visit: https://issues.sonatype.org/

**Steps:**

1. **Create an account** (if you don't have one)
   - Go to sign-up page
   - Email will be your Sonatype username
   - Create a strong password

2. **Create a new JIRA issue** to request groupId approval
   - Click "Create" button
   - **Project:** OSSRH
   - **Issue Type:** New Project
   - **Summary:** Request groupId com.mukundkumar
   - **Group Id:** com.mukundkumar
   - **Project URL:** https://github.com/Mukundkumar07/SpringBoot-Rest-Example
   - **SCM URL:** https://github.com/Mukundkumar07/SpringBoot-Rest-Example.git

3. **Wait for approval** (usually 1-2 hours, sometimes minutes)
   - Sonatype will comment on your JIRA issue
   - You'll receive an email when approved
   - Check your email and the JIRA issue

### Step 4: Verify Your Setup

Before deploying, verify everything is configured correctly:

```bash
cd /Users/mukundkumar/books

# Check if your GPG key is accessible
gpg --list-keys | grep mukundkumar

# Verify pom.xml syntax
mvn help:validate

# Build the project (without deploying)
mvn clean package -DskipTests
```

**Expected output:**

```
[INFO] BUILD SUCCESS
[INFO] Total time: 25.123 s
[INFO] Finished at: 2024-01-15T10:30:45-05:00
```

### Step 5: Deploy to Maven Central

Once Sonatype approves your groupId (Step 3), you're ready to deploy:

```bash
cd /Users/mukundkumar/books

# Deploy with GPG signing (recommended)
mvn clean deploy -P gpg-sign

# OR without profile activation (if gpg-sign is already active in settings)
mvn clean deploy
```

**What happens during deployment:**

1. Maven compiles your code
2. Generates source JAR (with full source code)
3. Generates Javadoc JAR (with API documentation)
4. Creates signed artifacts (GPG signatures)
5. Uploads to Sonatype OSSRH
6. Auto-releases to Maven Central (since autoReleaseAfterClose=true)
7. Syncs to Maven Central mirrors (takes ~10 minutes)

### Step 6: Verify Your Published Package

After deployment, verify your package is available:

```bash
# Option 1: Search on Maven Central
# https://search.maven.org/search?q=books-api

# Option 2: Check Sonatype
# https://s01.oss.sonatype.org/content/groups/public/com/mukundkumar/books-api/

# Option 3: Use Maven to download
mvn dependency:get -Dartifact=com.mukundkumar:books-api:1.0.0
```

### Step 7: Update Your Project README

Add this to your GitHub README.md so others can use your library:

```markdown
## Installation

### Maven

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>com.mukundkumar</groupId>
    <artifactId>books-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

Add to your `build.gradle`:

```gradle
implementation 'com.mukundkumar:books-api:1.0.0'
```
```

## Troubleshooting

### "Invalid Project Code" Error

**Problem:** `[ERROR] Invalid project code`

**Solution:**
- Make sure you've created the JIRA issue in Step 3
- Sonatype approves the groupId within 1-2 hours
- You cannot deploy until approved

### "GPG key not found" Error

**Problem:** `[ERROR] gpg: can't connect to the GPG agent`

**Solution:**

```bash
# Reset GPG
killall gpg-agent

# Restart GPG
gpg-agent --daemon

# Try deployment again
mvn clean deploy -P gpg-sign
```

### "401 Unauthorized" Error

**Problem:** `[ERROR] 401 Unauthorized`

**Solution:**
- Check your username and password in ~/.m2/settings.xml
- Use your Sonatype JIRA username (email address)
- Use your Sonatype JIRA password
- Make sure there are no extra spaces

### "Failed to sign: key not found" Error

**Problem:** `[ERROR] java.io.IOException: key not found`

**Solution:**

```bash
# Find your GPG key ID
gpg --list-keys | grep mukundkumar

# Export your key ID
export GNUPGHOME=$HOME/.gnupg

# Add key ID to command
mvn clean deploy -Dgpg.keyname=YOUR_KEY_ID -P gpg-sign
```

## Future Releases

For version 1.1.0, 1.2.0, etc.:

### 1. Update Version in pom.xml

```xml
<version>1.1.0</version>
```

### 2. Update Version in Spring Boot

Keep the same Java code, just increment the version.

### 3. Commit and Tag

```bash
git add pom.xml
git commit -m "bump version to 1.1.0"
git tag -a v1.1.0 -m "Release v1.1.0"
git push origin SpringBoot-Rest-Complete
git push origin v1.1.0
```

### 4. Deploy to Maven Central

```bash
mvn clean deploy -P gpg-sign
```

## Security Notes

⚠️ **IMPORTANT:**

- ✅ Never commit `~/.m2/settings.xml` to version control
- ✅ Treat your GPG passphrase like your passwords
- ✅ Treat your Sonatype password like your passwords
- ✅ Use long, complex passwords for Sonatype account
- ✅ Enable 2FA on your Sonatype account if available
- ✅ Keep your GPG backup keys in a safe place

## Reference Links

- **Maven Central:** https://search.maven.org/
- **Sonatype OSSRH:** https://central.sonatype.org/
- **Sonatype Documentation:** https://central.sonatype.org/register/central-aUrl/
- **GPG Documentation:** https://gnupg.org/documentation/
- **Maven Deploy Plugin:** https://maven.apache.org/plugins/maven-deploy-plugin/

## Timeline Summary

| Step | Action | Time | Status |
|------|--------|------|--------|
| 1 | Generate GPG Keys | 5 min | ⏳ Do this |
| 2 | Configure settings.xml | 5 min | ⏳ Do this |
| 3 | Create Sonatype Account | 10 min | ⏳ Do this |
| 3 | Create JIRA Issue | 5 min | ⏳ Do this |
| 3 | Wait for Approval | 1-2 hours | ⏳ Wait |
| 4 | Verify Setup | 10 min | ⏳ Do after approval |
| 5 | Deploy to Maven Central | 5 min | ⏳ Do after approval |
| 6 | Verify Published | 10 min | ⏳ Do after deploy |
| 7 | Update README | 5 min | ⏳ Do after verification |

**Total Time:** ~2.5-3 hours (mostly waiting for Sonatype approval)

---

**Questions?** Check the Sonatype docs or email support@sonatype.com
