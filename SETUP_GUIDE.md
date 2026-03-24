# دليل إعداد مشروع شات زهراء

هذا الدليل يشرح خطوات إعداد وتشغيل مشروع تطبيق "شات زهراء" على جهازك.

## المتطلبات الأساسية

### 1. تثبيت Android Studio
- اذهب إلى [Android Studio Download](https://developer.android.com/studio)
- حمّل النسخة المناسبة لنظام التشغيل الخاص بك
- اتبع تعليمات التثبيت

### 2. تثبيت Java Development Kit (JDK)
```bash
# على Ubuntu/Debian
sudo apt-get install openjdk-11-jdk

# على macOS
brew install openjdk@11

# على Windows
# حمّل من: https://www.oracle.com/java/technologies/downloads/
```

### 3. تثبيت Android SDK
يتم تثبيته تلقائياً مع Android Studio. تأكد من تثبيت:
- Android SDK Platform 34 (أو أحدث)
- Android SDK Build-Tools 34.0.0 (أو أحدث)
- Android Emulator

## إعداد Firebase

### الخطوة 1: إنشاء مشروع Firebase

1. اذهب إلى [Firebase Console](https://console.firebase.google.com/)
2. انقر على "Create a new project"
3. أدخل اسم المشروع: `ShaatZahraa`
4. اختر الدول المناسبة
5. انقر على "Create project"

### الخطوة 2: إضافة تطبيق Android

1. في Firebase Console، انقر على أيقونة Android
2. أدخل اسم الحزمة: `com.shaat.zahraa`
3. أدخل اسم التطبيق: `شات زهراء`
4. انقر على "Register app"
5. حمّل ملف `google-services.json`
6. ضع الملف في المسار: `app/google-services.json`

### الخطوة 3: تفعيل الخدمات

في Firebase Console، اذهب إلى "Build" وفعّل:

#### Authentication
1. اختر "Authentication"
2. انقر على "Get started"
3. فعّل "Phone" و "Email/Password"

#### Realtime Database
1. اختر "Realtime Database"
2. انقر على "Create Database"
3. اختر "Start in test mode"
4. اختر المنطقة الجغرافية

#### Cloud Storage
1. اختر "Storage"
2. انقر على "Get started"
3. اختر "Start in test mode"
4. اختر المنطقة الجغرافية

#### Cloud Messaging
1. اختر "Cloud Messaging"
2. سيتم تفعيله تلقائياً

### الخطوة 4: إعدادات قاعدة البيانات

في Realtime Database، استبدل القواعس بـ:

```json
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "$uid === auth.uid || root.child('users').child($uid).child('isAdmin').val() === true",
        ".write": "$uid === auth.uid",
        ".validate": "newData.hasChildren(['uid', 'displayName', 'phoneNumber'])"
      }
    },
    "chats": {
      "$chatId": {
        ".read": "root.child('chats').child($chatId).child('participants').child(auth.uid).exists()",
        ".write": "root.child('chats').child($chatId).child('participants').child(auth.uid).exists()"
      }
    },
    "messages": {
      "$messageId": {
        ".read": true,
        ".write": "auth.uid !== null"
      }
    },
    "status": {
      "$statusId": {
        ".read": true,
        ".write": "auth.uid !== null"
      }
    },
    "calls": {
      "$callId": {
        ".read": true,
        ".write": "auth.uid !== null"
      }
    },
    "reports": {
      "$reportId": {
        ".read": "root.child('users').child(auth.uid).child('isAdmin').val() === true",
        ".write": "auth.uid !== null"
      }
    }
  }
}
```

## فتح المشروع في Android Studio

### الطريقة 1: من خلال Android Studio

1. افتح Android Studio
2. اختر "File" → "Open"
3. اختر مجلد `ShaatZahraa`
4. انقر على "Open"

### الطريقة 2: من خلال سطر الأوامر

```bash
cd /path/to/ShaatZahraa
studio .
```

## بناء المشروع

### بناء Debug APK

```bash
./gradlew assembleDebug
```

سيتم إنشاء الملف في: `app/build/outputs/apk/debug/app-debug.apk`

### بناء Release APK

```bash
./gradlew assembleRelease
```

## تشغيل التطبيق

### على جهاز افتراضي (Emulator)

1. افتح Android Studio
2. اختر "Tools" → "Device Manager"
3. انقر على "Create Device"
4. اختر جهازاً (مثل Pixel 5)
5. اختر نسخة Android (API 34 أو أحدث)
6. أكمل عملية الإنشاء
7. اضغط على زر التشغيل

### على جهاز حقيقي

1. فعّل "Developer Mode" على جهازك:
   - اذهب إلى "Settings" → "About phone"
   - اضغط على "Build number" 7 مرات
2. فعّل "USB Debugging"
3. وصّل جهازك بجهاز الكمبيوتر عبر USB
4. في Android Studio، اختر جهازك من القائمة
5. اضغط على زر التشغيل (Run)

## استكشاف الأخطاء

### المشكلة: "Could not find com.google.gms:google-services"

**الحل:**
```bash
./gradlew clean
./gradlew build
```

### المشكلة: "Gradle sync failed"

**الحل:**
1. تأكد من وجود `google-services.json` في `app/`
2. اذهب إلى "File" → "Sync Now"
3. تحقق من اتصالك بالإنترنت

### المشكلة: "Build failed with exception"

**الحل:**
```bash
# نظّف المشروع
./gradlew clean

# أعد البناء
./gradlew build

# إذا استمرت المشكلة، احذف مجلد .gradle
rm -rf .gradle
./gradlew build
```

## الخطوات التالية

بعد التثبيت الناجح:

1. **استكشف الكود:** ابدأ بـ `MainActivity.kt`
2. **فهم البنية:** اقرأ `README.md` و `ARCHITECTURE.md`
3. **تطوير الميزات:** ابدأ بإكمال الأنشطة والأجزاء
4. **الاختبار:** استخدم Android Studio Debugger

## موارد إضافية

- [Android Documentation](https://developer.android.com/docs)
- [Firebase Documentation](https://firebase.google.com/docs)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Material Design 3](https://m3.material.io/)

## الدعم

للمساعدة والدعم:
- اطرح أسئلتك على [Stack Overflow](https://stackoverflow.com/questions/tagged/android)
- تصفح [Android Community](https://www.androidcommunity.com/)

---

**آخر تحديث:** مارس 2026
