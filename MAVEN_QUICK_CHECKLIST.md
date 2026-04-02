# 📋 Quick Reference - Maven Central Publishing Checklist

## Current Status: Ready to Deploy ✅

Your project is **100% configured** for Maven Central publishing. Complete these final steps:

---

## MUST DO - In This Order

### 1️⃣ Generate GPG Keys (5 min)

```bash
gpg --full-generate-key

# When prompted:
# Key type: 1 (RSA and RSA)
# Key size: 3072 (press Enter)
# Validity: 0 (press Enter - no expiration)
# Name: Mukund Kumar
# Email: mukundkumar@example.com
# Passphrase: **SAVE THIS SECURELY** ← IMPORTANT!

# Verify
gpg --list-keys | grep mukundkumar
```

**⚠️ Save your GPG passphrase - you need it in Step 2!**

---

### 2️⃣ Update ~/.m2/settings.xml (5 min)

```bash
nano ~/.m2/settings.xml
```

Replace these 3 placeholders:

```xml
<!-- Line ~25: Your Sonatype username (email) -->
<username>YOUR_SONATYPE_USERNAME</username>
<password>YOUR_SONATYPE_PASSWORD</password>

<!-- Line ~31: GPG passphrase from Step 1 -->
<passphrase>YOUR_GPG_PASSPHRASE</passphrase>

<!-- Line ~40: GPG passphrase again -->
<gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase>
```

Save: `Ctrl+O` then `Ctrl+X` (nano editor)

---

### 3️⃣ Create Sonatype Account & Request Access (15 min)

**Visit:** https://issues.sonatype.org/

**Do This:**
1. Click "Sign up" (top right)
2. Create account with email & password
3. Click "Create" (top left)
   - **Project:** OSSRH
   - **Issue Type:** New Project
   - **Summary:** Request groupId com.mukundkumar
   - **Group Id:** com.mukundkumar
   - **Project URL:** https://github.com/Mukundkumar07/SpringBoot-Rest-Example

4. **WAIT** for approval email (1-2 hours usually)

---

### 4️⃣ Deploy When Approved (5 min)

Once Sonatype approves (you'll get an email):

```bash
cd /Users/mukundkumar/books
mvn clean deploy -P gpg-sign
```

**Expected output:**
```
[INFO] BUILD SUCCESS
[INFO] Uploaded to https://s01.oss.sonatype.org/...
```

---

## ✅ Verify It Worked

After 10 minutes (Maven Central sync):

```bash
# Option 1: Search Online
# https://search.maven.org/search?q=books-api

# Option 2: Check with Maven
mvn dependency:get -Dartifact=com.mukundkumar:books-api:1.0.0
```

---

## Current Configuration

| Item | Value |
|------|-------|
| **Group ID** | com.mukundkumar |
| **Artifact ID** | books-api |
| **Version** | 1.0.0 |
| **License** | Apache 2.0 |
| **GitHub** | https://github.com/Mukundkumar07/SpringBoot-Rest-Example |
| **pom.xml** | ✅ Configured |
| **plugins** | ✅ Added (source, javadoc, gpg, nexus-staging) |
| **settings.xml** | ✅ Created (needs credentials) |
| **Git tag** | ✅ v1.0.0 pushed |

---

## Files That Were Updated

```
✅ pom.xml
   - Added 4 plugins for Maven Central
   - Added distributionManagement section
   
✅ ~/.m2/settings.xml
   - Created with template (needs credentials)
   
✅ Git
   - Commit: "setup maven central publishing - add plugins and distribution config"
   - Tag: v1.0.0 (pushed to GitHub)
```

---

## Deployment Command

When ready (after Sonatype approval):

```bash
cd /Users/mukundkumar/books
mvn clean deploy -P gpg-sign
```

**That's it! Maven handles everything else.**

---

## Next Release (e.g., v1.1.0)

```bash
# 1. Update version in pom.xml
# 2. Commit & tag
git add pom.xml
git commit -m "bump to 1.1.0"
git tag -a v1.1.0 -m "Release v1.1.0"
git push origin SpringBoot-Rest-Complete
git push origin v1.1.0

# 3. Deploy
mvn clean deploy -P gpg-sign
```

---

## Troubleshooting

| Error | Solution |
|-------|----------|
| **Invalid Project Code** | Wait for Sonatype approval - check your JIRA issue |
| **401 Unauthorized** | Check username/password in ~/.m2/settings.xml |
| **GPG key not found** | Run: `gpg-agent --daemon` then retry |
| **Artifact already exists** | Use a new version number (e.g., 1.0.1) |

---

## 🎯 Summary

- ✅ **Code:** Ready to deploy
- ✅ **pom.xml:** Fully configured
- ✅ **Plugins:** Added
- ✅ **Git:** Tagged and pushed
- ⏳ **Your action:** Create GPG key + Sonatype account
- ⏳ **Your action:** Add credentials to settings.xml
- ⏳ **Your action:** Wait for Sonatype approval
- ⏳ **Your action:** Run `mvn clean deploy -P gpg-sign`

**Estimated time:** 2.5-3 hours (mostly waiting for Sonatype)

---

**For detailed instructions, see:** `MAVEN_CENTRAL_DEPLOYMENT.md`
