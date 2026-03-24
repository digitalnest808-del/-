# تطبيق شات زهراء (ShaatZahraa)

تطبيق مراسلة فورية متكامل للأندرويد مع دعم المكالمات الصوتية والمرئية ولوحة تحكم إدارية.

## 📋 المتطلبات

- **Android Studio:** نسخة Hedgehog أو أحدث
- **Java Development Kit (JDK):** 11 أو أحدث
- **Android SDK:** API 24 أو أحدث
- **Firebase Account:** للخدمات السحابية

## 🏗️ هيكل المشروع

```
ShaatZahraa/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/shaat/zahraa/
│   │   │   │   ├── data/models/          # نماذج البيانات
│   │   │   │   ├── ui/                   # واجهات المستخدم
│   │   │   │   ├── services/             # الخدمات الخلفية
│   │   │   │   └── ShaatZahraaApplication.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/               # ملفات التخطيط
│   │   │   │   ├── drawable/             # الرسومات والأيقونات
│   │   │   │   ├── values/               # الموارد (الألوان، النصوص)
│   │   │   │   ├── menu/                 # قوائم التطبيق
│   │   │   │   └── navigation/           # ملفات التنقل
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

## 🚀 البدء السريع

### 1. إعداد Firebase

1. اذهب إلى [Firebase Console](https://console.firebase.google.com/)
2. أنشئ مشروعاً جديداً
3. أضف تطبيق Android باستخدام اسم الحزمة: `com.shaat.zahraa`
4. حمّل ملف `google-services.json` وضعه في مجلد `app/`

### 2. فتح المشروع

1. افتح Android Studio
2. اختر "Open an existing Android Studio project"
3. اختر مجلد `ShaatZahraa`

### 3. بناء المشروع

```bash
./gradlew build
```

### 4. تشغيل التطبيق

```bash
./gradlew installDebug
```

## 📦 المكتبات المستخدمة

### Firebase
- Firebase Authentication
- Firebase Realtime Database
- Firebase Cloud Storage
- Firebase Cloud Messaging

### UI & Navigation
- Material Design 3
- Jetpack Compose
- Navigation Component
- ViewBinding & DataBinding

### Multimedia
- WebRTC (للمكالمات)
- CameraX (للكاميرا)
- Glide (لتحميل الصور)

### Database
- Room Database (للتخزين المحلي)

### Networking
- Retrofit 2
- OkHttp

## 🎨 الميزات الرئيسية

### 1. المراسلة الفورية
- تبادل الرسائل النصية في الوقت الفعلي
- مؤشرات القراءة والكتابة
- دعم الوسائط (صور، فيديو، صوت)

### 2. الحالات (Status)
- مشاركة الصور والفيديوهات
- تختفي بعد 24 ساعة
- عرض من شاهدها

### 3. المكالمات
- مكالمات صوتية عالية الجودة
- مكالمات فيديو مع WebRTC
- تشفير طرف إلى طرف

### 4. لوحة تحكم المدير
- مراقبة الإحصائيات
- إدارة المستخدمين
- معالجة البلاغات

## 🔐 الأمان

- تشفير البيانات أثناء النقل (HTTPS/TLS)
- تشفير المكالمات (WebRTC)
- مصادقة آمنة عبر Firebase Auth
- صلاحيات محدودة في قاعدة البيانات

## 📱 المتطلبات الأساسية للجهاز

- نظام Android 5.0 (API 24) أو أحدث
- 100 MB من مساحة التخزين
- اتصال إنترنت مستقر

## 🤝 المساهمة

نرحب بمساهماتك! يرجى اتباع الخطوات التالية:

1. Fork المشروع
2. أنشئ فرعاً جديداً (`git checkout -b feature/AmazingFeature`)
3. Commit التغييرات (`git commit -m 'Add some AmazingFeature'`)
4. Push إلى الفرع (`git push origin feature/AmazingFeature`)
5. افتح Pull Request

## 📄 الترخيص

هذا المشروع مرخص تحت رخصة MIT - انظر ملف [LICENSE](LICENSE) للتفاصيل.

## 📞 التواصل

للأسئلة والاستفسارات، يرجى التواصل عبر:
- البريد الإلكتروني: support@shaatzahraa.com
- الموقع: www.shaatzahraa.com

---

**ملاحظة:** هذا المشروع قيد التطوير. قد تتغير الميزات والتفاصيل.
